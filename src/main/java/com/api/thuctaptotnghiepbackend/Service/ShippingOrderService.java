package com.api.thuctaptotnghiepbackend.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.thuctaptotnghiepbackend.Entity.ShippingOrder;
import com.api.thuctaptotnghiepbackend.Paypal.PayPalReponsi;
import com.api.thuctaptotnghiepbackend.Paypal.PaymentInfo;
import com.api.thuctaptotnghiepbackend.Paypal.paymentInfoRepository;
import com.api.thuctaptotnghiepbackend.Paypal.paypal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class ShippingOrderService {

    @Value("${ghn.api.token}")
    private String ghnApiToken;

    @Value("${ghn.api.endpoint}")
    private String ghnApiEndpoint;



    
    @Autowired
private paymentInfoRepository paypalRepository;


    private final RestTemplate restTemplate;

    public ShippingOrderService(paymentInfoRepository paypalRepository,RestTemplate restTemplate) {
        this.paypalRepository = paypalRepository;
        this.restTemplate = restTemplate;
    }


 





    // public ResponseEntity<String> createShippingOrder(ShippingOrder shippingOrder) {
    //     String url = ghnApiEndpoint + "/shipping-order/create";
        
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.set("Content-Type", "application/json");
    //     headers.set("ShopId", "192607"); // Đặt ShopId theo yêu cầu của GHN
    //     headers.set("Token", ghnApiToken);

    //     HttpEntity<ShippingOrder> request = new HttpEntity<>(shippingOrder, headers);

    //     return restTemplate.exchange(url, HttpMethod.POST, request, String.class);



    // }




        public ResponseEntity<String> createShippingOrder(ShippingOrder shippingOrder) {
        String url = ghnApiEndpoint + "/shipping-order/create";
        System.err.println(shippingOrder.getIdOrder());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("ShopId", "192607"); // Đặt ShopId theo yêu cầu của GHN
        headers.set("Token", ghnApiToken);

        HttpEntity<ShippingOrder> request = new HttpEntity<>(shippingOrder, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            if (responseBody != null) {
                if (responseBody.contains("Lỗi gọi API")) {
                    // Xử lý trường hợp phản hồi từ API GHN chứa thông báo lỗi
                    System.err.println("Không thể tạo đơn hàng với API GHN. Lỗi: " + responseBody);
                } else {
                    String orderCode = extractOrderCode(responseBody);
                    if (orderCode != null) {
                        updatePaypalOrderCode(shippingOrder.getIdOrder(), orderCode); // Cập nhật vào cơ sở dữ liệu paypal
                    }
                }
            } else {
                // Xử lý trường hợp phản hồi rỗng từ API GHN
                System.err.println("Phản hồi từ API GHN là rỗng.");
            }
        } else {
            // Xử lý trường hợp StatusCode không phải là 200 OK
            System.err.println("Không thể tạo đơn hàng với API GHN. StatusCode: " + response.getStatusCode());
        }



        return response;
    }

    private void updatePaypalOrderCode(Long paypalId, String orderCode) {
        try {
            Optional<PaymentInfo> optionalPaypal = paypalRepository.findById(paypalId);
            if (optionalPaypal.isPresent()) {
                PaymentInfo paypal = optionalPaypal.get();
                paypal.setOrder_code(orderCode); // Cập nhật trường orderCode của đối tượng Paypal
                paypalRepository.save(paypal); // Lưu lại thay đổi vào cơ sở dữ liệu
            } else {
                // Xử lý trường hợp không tìm thấy đối tượng Paypal với id tương ứng
                System.err.println("Không tìm thấy thông tin Paypal với id: " + paypalId);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Đã xảy ra IllegalArgumentException: " + e.getMessage());
            e.printStackTrace();
            // Xử lý ngoại lệ phù hợp
        } catch (Exception e) {
            System.err.println("Đã xảy ra Exception: " + e.getMessage());
            e.printStackTrace();
            // Xử lý các ngoại lệ khác
        }
    }

    private String extractOrderCode(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode orderCodeNode = rootNode.path("data").path("order_code");
            return orderCodeNode.asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


 public ResponseEntity<String> getShippingOrderDetails(String orderCode) {
        String url = ghnApiEndpoint + "/shipping-order/detail";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Token", ghnApiToken);

        // Build the URL with order code as a query parameter
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("order_code", orderCode);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        return response;
    }



}
