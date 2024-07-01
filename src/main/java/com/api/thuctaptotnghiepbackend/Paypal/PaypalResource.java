package com.api.thuctaptotnghiepbackend.Paypal;



import java.math.BigDecimal;
import java.util.ArrayList;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.thuctaptotnghiepbackend.Entity.Productimage;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductimageRepository;
import com.api.thuctaptotnghiepbackend.Request.RefundRequest;
import com.api.thuctaptotnghiepbackend.Responses.ResourceNotFoundException;
import com.api.thuctaptotnghiepbackend.Service.UserService;
import com.api.thuctaptotnghiepbackend.Service.Ipml.EmailService;
import com.api.thuctaptotnghiepbackend.Template.EmailTemplate;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.mail.MessagingException;
@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/api") 
public class PaypalResource {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private PayPalReponsi payPalReponsi;


   @Autowired
    private paymentInfoRepository paymentInfoRepository;
    @Autowired
    private  ProductimageRepository  productimageRepository;


 private final EmailService emailService;
 private final UserService userService;
 private final EmailTemplate emailTemplate;

 @Autowired
    public PaypalResource(EmailService emailService, UserService userService,EmailTemplate emailTemplate) {
        this.emailService = emailService;
        this.userService = userService;
        this.emailTemplate = emailTemplate;
    }


   

   

//hamf add 
@PostMapping("/add")
public ResponseEntity<?> createPayment( @RequestBody List<Payments> Payments) {
    try {
        Payment payment = paypalService.createPayment(Payments, "http://localhost:9011/api/cancel", "http://localhost:9011/api/success/pay");

        System.out.println("Phản hồi thanh toán: " + payment.toJSON());
      
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                Map<String, Object> data = new HashMap<>();
                data.put("id", UUID.randomUUID().toString());
                data.put("approvalUrl", link.getHref());
                return ResponseEntity.ok(data);
            }
        }
    } catch (PayPalRESTException e) {
        e.printStackTrace();
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error creating payment.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    Map<String, Object> notFoundResponse = new HashMap<>();
    notFoundResponse.put("error", "Payment approval URL not found.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(notFoundResponse);
}


    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPay() {
        return ResponseEntity.ok("Payment canceled.");
    }

    


  
    public ModelAndView pay() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay"); // Giả sử "pay" là tên của template HTML của bạn
        // Thêm bất kỳ dữ liệu nào bạn muốn truyền vào view
        modelAndView.addObject("key", "value");
        return modelAndView;
    }

    @GetMapping("/success/pay")
    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                savePaymentToDatabase(payment);
                modelAndView.setViewName("pay");
                modelAndView.addObject("paymentId", paymentId);




                return modelAndView;
            }
        } catch (PayPalRESTException e) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }

    private void savePaymentToDatabase(Payment payment) {
        // Lấy thông tin từ đối tượng Payment và lưu vào cơ sở dữ liệu
        PaymentInfo paymentInfoEntity = new PaymentInfo();
        paymentInfoEntity.setPaymentId(payment.getId());
        paymentInfoEntity.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
        paymentInfoEntity.setAmount(Double.parseDouble(String.valueOf(payment.getTransactions().get(0).getAmount().getTotal())));
        paymentInfoEntity.setStatus(payment.getPayer().getStatus());
          paymentInfoEntity.setIdUser(payment.getTransactions().get(0).getDescription());
        paymentInfoEntity.setMethod(payment.getPayer().getPaymentMethod());
        paymentInfoEntity.setIntent(payment.getIntent());
        paymentInfoEntity.setCurrency(payment.getTransactions().get(0).getAmount().getCurrency());
        paymentInfoEntity.setPaymentTime(new Date()); // Thời gian thanh toán
    
        PaymentInfo savedPaymentInfo = paymentInfoRepository.save(paymentInfoEntity);
        List<String> productList = new ArrayList<>();


        // Lấy thông tin chi tiết thanh toán từ đối tượng Payment và lưu vào cơ sở dữ liệu
        for (Transaction transaction : payment.getTransactions()) {
            for (Item item : transaction.getItemList().getItems()) {
                paypal paymentEntity = new paypal();
                paymentEntity.setPaymentInfo(savedPaymentInfo);
                paymentEntity.setProductId(item.getName());
                String description = item.getDescription();
                if (description != null) {
                    String[] parts = description.split(" \\| ");
                    if (parts.length == 3) {
                        paymentEntity.setColor(parts[0]);
                        paymentEntity.setSize(parts[1]);
                        paymentEntity.setIdUser(parts[2]);
                    }
                    // Nếu không phù hợp định dạng, parts.length != 2, không làm gì cả
                }
                paymentEntity.setQuantity(item.getQuantity());
                paymentEntity.setAmount(item.getPrice());
                paymentEntity.setCurrency(item.getCurrency());
                paymentEntity.setProductname(item.getSku());
                // paymentEntity.setSize(item.getUrl());
                String productInfo = String.format("productId: %s, name: %s, Size: %s, Color: %s, Quantity: %s, Price: %s %s",
                    item.getName(), item.getSku(), paymentEntity.getSize(), paymentEntity.getColor(),
                    item.getQuantity(), item.getPrice(), item.getCurrency());
productList.add(productInfo);

                payPalReponsi.save(paymentEntity);
            }
        }

        String userIdStr  = payment.getTransactions().get(0).getDescription(); // Assuming this is the user ID from payment
        long userId;

    userId = Long.parseLong(userIdStr);

    String userEmail = userService.getEmailByUserId(userId);
    
    String emailContent = emailTemplate.getPaymentSuccessEmail(payment.getId(), productList);

    try {
        emailService.sendEmail(userEmail, "Thanh toán đơn hàng thành công", emailContent);
    } catch (Exception  e) {
        // Handle email sending exception
        e.printStackTrace();
    }
    
}

@GetMapping("/statussale/{paymentId}")
public ResponseEntity<?> getPaymentStatus(@PathVariable String paymentId) {
    try {
        String saleState = paypalService.getSaleState(paymentId);
        String stateDescription = paypalService.getPaymentStateDescription(saleState);
        return ResponseEntity.ok(stateDescription);
    } catch (PayPalRESTException e) {
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}
   


@GetMapping("/getPaypalByPaymentId/{paymentId}")
public List<paypal> getPaypalByPaymentId(@PathVariable String paymentId) {
    return payPalReponsi.findByPaymentInfoPaymentId(paymentId);
}


 @PostMapping("/refund")
    public ResponseEntity<?> refundSale( @RequestBody RefundRequest refundRequest) {
        try {
            Sale sale = paypalService.refundSale(refundRequest.getPaymentId(), refundRequest.getRefundAmount(), refundRequest.getCurrency());
            return ResponseEntity.ok("Refund processed successfully");
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process refund: " + e.getMessage());
        }
    }
   

    @GetMapping("/order")
    public List<paypal> getAllCategorys() {
        return payPalReponsi.findAll();
    }

    @GetMapping("/getPaypalByUserId/{userId}")
    public List<paypal> getPaypalByUserId(@PathVariable String userId) {
        return payPalReponsi.findByidUser(userId);
    }



    
  @GetMapping("/getPaypalByproductIds")
public List<List<Productimage>> getPaypalByproductIds(@RequestParam List<Long> productIds) {
    List<List<Productimage>> result = new ArrayList<>();
    for (Long productId : productIds) {
        List<Productimage> images = productimageRepository.findByProductIdAndIsPrimary(productId, true);
        result.add(images);
    }
    return result;
}



     @DeleteMapping("/{id}")
public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
    try {
         paypal existingCategory = payPalReponsi.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        // Lưu thông tin sản phẩm đã xóa
        Map<String, Object> response = new HashMap<>();
        response.put("id", existingCategory.getId()); // Đưa ID của sản phẩm vào phản hồi
        response.put("message", "Category deleted successfully");

        // Xóa sản phẩm từ cơ sở dữ liệu
          payPalReponsi.delete(existingCategory);
        return ResponseEntity.ok(response);
    } catch (ResourceNotFoundException e) {
        // Xử lý nếu sản phẩm không tồn tại
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
    } catch (Exception e) {
        // Xử lý nếu có lỗi xảy ra trong quá trình xóa
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to delete product"));
    }
}










}