package com.api.thuctaptotnghiepbackend.Paypal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.thuctaptotnghiepbackend.Responses.ResourceNotFoundException;







@CrossOrigin(origins = "*")

@RestController
@RequestMapping("api/paypal")
public class PayPalController {
   private static final Logger log = LoggerFactory.getLogger(PayPalController.class);

    @Autowired
    private paymentInfoRepository paymentInfoRepository;

    @GetMapping
    public List<PaymentInfo> getAllUsers() {
        try {
            List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
            
            log.info("Retrieved paymentInfoList: {}", paymentInfoList);

            return paymentInfoList;
        } catch (Exception e) {
            log.error("Error while fetching paymentInfoList", e);
            throw e;
        }
    }


    @GetMapping("/recent-orders")
public List<PaymentInfo> getRecentOrders() {
    try {
        // Sắp xếp theo thời gian thanh toán giảm dần và giới hạn kết quả trả về là 5
        Sort sortByPaymentTimeDesc = Sort.by(Sort.Direction.DESC, "paymentTime");
        Pageable pageable = PageRequest.of(0, 5, sortByPaymentTimeDesc);

        List<PaymentInfo> recentOrders = paymentInfoRepository.findTop5ByOrderByPaymentTimeDesc(pageable);
        
       

        return recentOrders;
    } catch (Exception e) {
        log.error("Error while fetching recent orders", e);
        throw e;
    }
}


@GetMapping("/summary")
    public ResponseEntity<SummaryData> getSummary() {
        try {
            // Lấy danh sách các thanh toán từ repository
            List<PaymentInfo> paymentInfos = paymentInfoRepository.findAll();

            // Tính toán tổng đơn hàng, tổng doanh thu và tổng số sản phẩm
            int totalOrders = paymentInfos.size();
            double totalRevenue = paymentInfos.stream()
            .filter(pi -> "VERIFIED".equals(pi.getStatus())) // Lọc các PaymentInfo có trạng thái là VERIFIED
            .mapToDouble(PaymentInfo::getAmount) // Lấy số tiền từ mỗi PaymentInfo
            .sum();


            int totalProducts = paymentInfos.stream()
            .filter(pi -> "VERIFIED".equals(pi.getStatus())) // Lọc các PaymentInfo có trạng thái là VERIFIED
            .flatMap(pi -> pi.getPaypalList().stream()) // Chuyển danh sách paypalList thành một Stream<Paypal>
            .mapToInt(paypal -> Integer.parseInt(paypal.getQuantity())) // Chuyển đổi quantity từ String sang int và lấy số lượng sản phẩm từ mỗi Paypal
            .sum(); // Tính tổng số lượng sản phẩm
    
        
         int totalCancelledOrders = (int) paymentInfos.stream().filter(pi -> "REFUNDED".equals(pi.getStatus())).count();
         double totalRevenuerefund = paymentInfos.stream()
         .filter(pi -> "REFUNDED".equals(pi.getStatus())) // Lọc các PaymentInfo có trạng thái là VERIFIED
         .mapToDouble(PaymentInfo::getAmount) // Lấy số tiền từ mỗi PaymentInfo
         .sum();



        System.out.println("Total refunded revenue: " + totalRevenuerefund);
            // Tạo đối tượng SummaryData để trả về
            SummaryData summaryData = new SummaryData(totalOrders, totalRevenue, totalProducts,totalCancelledOrders,totalRevenuerefund);

            return ResponseEntity.ok().body(summaryData);
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi nếu có lỗi xảy ra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



   @DeleteMapping("/{id}")
public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
    try {
         PaymentInfo existingCategory = paymentInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        // Lưu thông tin sản phẩm đã xóa
        Map<String, Object> response = new HashMap<>();
        response.put("id", existingCategory.getId()); // Đưa ID của sản phẩm vào phản hồi
        response.put("message", "Category deleted successfully");

        // Xóa sản phẩm từ cơ sở dữ liệu
          paymentInfoRepository.delete(existingCategory);
        return ResponseEntity.ok(response);
    } catch (ResourceNotFoundException e) {
        // Xử lý nếu sản phẩm không tồn tại
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
    } catch (Exception e) {
        // Xử lý nếu có lỗi xảy ra trong quá trình xóa
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to delete product"));
    }
}




@GetMapping("/getPaypalByUserId/{userId}")
public List<PaymentInfo> getPaypalByUserId(@PathVariable String userId) {
    return paymentInfoRepository.findByidUser(userId);
}


@GetMapping("/{sale}")
    public List<SalesData> getSalesData() {
        return paymentInfoRepository.findSalesData();
    }



}