package com.api.thuctaptotnghiepbackend.Paypal;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Refund;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;



@Service
public class PaypalService {
	

    @Autowired
    private paymentInfoRepository paymentInfoRepository;
	@Autowired
	private APIContext apiContext;
	
	public Payment createPayment(List<Payments> payments, String cancelUrl, String successUrl) throws PayPalRESTException {
        double totalAmount = 0.0;



        List<Item> items = new ArrayList<>();

        for (Payments payment : payments) {
           
            BigDecimal itemTotal = new BigDecimal(payment.getAmount()).setScale(2, RoundingMode.HALF_UP);

            // Nhân số lượng với giá tiền để có tổng giá trị của sản phẩm
            int quantity = Integer.parseInt(payment.getQuantity());

            // Nhân số lượng với giá tiền để có tổng giá trị của sản phẩm
            itemTotal = itemTotal.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);

   BigDecimal totalBigDecimal = new BigDecimal(payment.getAmount()).setScale(2, RoundingMode.HALF_UP);
           
            Item item = new Item();
            item.setName(payment.getIdProduct());
            item.setCurrency(payment.getCurrency());
            item.setPrice(totalBigDecimal.toString());
            item.setQuantity(payment.getQuantity());
           item.setSku(payment.getProductname());
            // item.setCurrency(payment.getSize());
            // String color = payment.getColor();
            // if (color.startsWith("#")) {
            //     color = color.substring(1); // Loại bỏ ký tự đầu tiên (#)
            // }
            // item.setUrl(color);
           
            String description = payment.getColor() + " | " + payment.getSize() + " | " + payment.getIdUser();
            item.setDescription(description);
            
            items.add(item);




            totalAmount += itemTotal.doubleValue();

            System.out.println("Product: " + payment.getIdProduct() + ", Quantity: " + quantity + ", Item Total: " + itemTotal);
           
        }



ItemList itemList = new ItemList();
    itemList.setItems(items);


  



        // Tổng thanh toán cho tất cả sản phẩm
        Amount totalAmountObj = new Amount();
        totalAmountObj.setCurrency(payments.get(0).getCurrency());
        BigDecimal totalBigDecimal = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP);
        totalAmountObj.setTotal(totalBigDecimal.toString());
        
 System.out.println("Total Amount: " + totalAmountObj);

        Transaction totalTransaction = new Transaction();
        totalTransaction.setItemList(itemList);
        totalTransaction.setDescription( payments.get(0).getIdUser());
        totalTransaction.setAmount(totalAmountObj);

        System.out.println("totalTransaction " + totalTransaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(payments.get(0).getMethod().toString());
        payer.setStatus(payments.get(0).getStatus());


        Payment payment = new Payment();
        payment.setIntent(payments.get(0).getIntent().toString());
        payment.setPayer(payer);
        payment.setTransactions(Collections.singletonList(totalTransaction));



        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);
        apiContext.setMaskRequestId(true);

      
       
        return payment.create(apiContext);
       
    } 






public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
    Payment payment = new Payment();
    payment.setId(paymentId);

  
    

    PaymentExecution paymentExecute = new PaymentExecution();
    paymentExecute.setPayerId(payerId);

    return payment.execute(apiContext, paymentExecute);
}



public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
    return Payment.get(apiContext, paymentId);
}

public Sale refundSale(String paymentId, BigDecimal refundAmount, String currency) throws PayPalRESTException {
   
    Payment payment = getPaymentDetails(paymentId);

   
    String saleId = extractSaleId(payment);

   
    Amount refundAmountObj = createRefundAmount(refundAmount, currency);

    updatePaymentInfoStatus(paymentId, "REFUNDED");
    return executeRefund(saleId, refundAmountObj);
}

private String extractSaleId(Payment payment) throws PayPalRESTException {
    if (payment.getTransactions() != null && !payment.getTransactions().isEmpty()) {
        Transaction transaction = payment.getTransactions().get(0);
        if (transaction.getRelatedResources() != null && !transaction.getRelatedResources().isEmpty()) {
            RelatedResources relatedResources = transaction.getRelatedResources().get(0);
            if (relatedResources.getSale() != null) {
                return relatedResources.getSale().getId();
            } else {
                throw new PayPalRESTException("Sale object not found in RelatedResources.");
            }
        } else {
            throw new PayPalRESTException("RelatedResources list is empty or null.");
        }
    } else {
        throw new PayPalRESTException("No transactions found in the Payment.");
    }
}

private Amount createRefundAmount(BigDecimal refundAmount, String currency) {
    Amount refundAmountObj = new Amount();
    refundAmountObj.setCurrency(currency);
    refundAmountObj.setTotal(refundAmount.setScale(2, RoundingMode.HALF_UP).toString());
    return refundAmountObj;
}

private Sale executeRefund(String saleId, Amount refundAmount) throws PayPalRESTException {
    Sale sale = new Sale();
    sale.setId(saleId);
    Refund refund = new Refund();
    refund.setAmount(refundAmount);
   sale.refund(apiContext, refund);

return sale;
}


public void updatePaymentInfoStatus(String paymentId, String status) {
    List<PaymentInfo> paymentInfos = paymentInfoRepository.findByPaymentId(paymentId);
    for (PaymentInfo paymentInfo : paymentInfos) {
        paymentInfo.setStatus(status);
        paymentInfoRepository.save(paymentInfo);
    }
}





private String extractSaleState(Payment payment) throws PayPalRESTException {
    if (payment.getTransactions() != null && !payment.getTransactions().isEmpty()) {
        Transaction transaction = payment.getTransactions().get(0);
        if (transaction.getRelatedResources() != null && !transaction.getRelatedResources().isEmpty()) {
            RelatedResources relatedResources = transaction.getRelatedResources().get(0);
            if (relatedResources.getSale() != null) {
                return relatedResources.getSale().getState();
            } else {
                throw new PayPalRESTException("Sale object not found in RelatedResources.");
            }
        } else {
            throw new PayPalRESTException("RelatedResources list is empty or null.");
        }
    } else {
        throw new PayPalRESTException("No transactions found in the Payment.");
    }
}


public String getSaleState(String paymentId) throws PayPalRESTException {
    Payment payment = getPaymentDetails(paymentId);
    return extractSaleState(payment);
}

public String getPaymentStateDescription(String state) {
    switch (state) {
        case "approved":
            return "Đã thanh toán";
        case "created":
            return " Thanh toán đã được tạo nhưng chưa được chấp nhận";
        case "failed":
            return "Thanh toán đã thất bại";
        case "canceled":
            return "Thanh toán đã bị hủy.";
        case "expired":
            return "Thanh toán đã hết hạn";
        case "pending":
            return "Thanh toán đang chờ xử lý";
        case "completed":
            return " Thanh toán đã hoàn tất";
        case "denied":
            return "Thanh toán đã bị từ chối";
        case "refunded":
            return "Đã hoàn tiền";
        case "partially_refunded":
            return "The payment has been partially refunded.";
        case "voided":
            return "Thanh toán đã bị vô hiệu hóa";
        default:
            return null;
    }
}




   


}