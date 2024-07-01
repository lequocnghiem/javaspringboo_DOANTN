package com.api.thuctaptotnghiepbackend.Template;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.api.thuctaptotnghiepbackend.Entity.Productimage;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductimageRepository;

@Component
public class EmailTemplate {

    private final ProductimageRepository productImageRepository;
 private final SpringTemplateEngine templateEngine;
    @Autowired
    public EmailTemplate(ProductimageRepository productImageRepository,SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.productImageRepository = productImageRepository;

    }

    public String getPaymentSuccessEmail(String paymentId, List<String> productList) {
        String productDetails = "";
    
        // Iterate through productList to generate HTML for each product
        for (String productInfo : productList) {
            String[] productInfoParts = productInfo.split(", "); // Assuming the format is "productId: %s, name: %s, Size: %s, Color: %s, Quantity: %d, Price: %.2f %s"
            String productId = getValueByKey(productInfoParts[0], "productId");
            String name = getValueByKey(productInfoParts[1], "name");
            String size = getValueByKey(productInfoParts[2], "Size");
            String color = getValueByKey(productInfoParts[3], "Color");
            int quantity = Integer.parseInt(getValueByKey(productInfoParts[4], "Quantity"));
            double price = parsePriceValue(getValueByKey(productInfoParts[5], "Price"));
    
            String imageUrl = getProductPrimaryImage(Long.parseLong(productId));
           
            // Append product details HTML to productDetails
            productDetails += "<tr>"
            + "<td>" + name + "</td>"
            + "<td>" + quantity + "</td>"
            + "<td>" + size + "</td>"
              + "<td><span style='color: " + color + "; width: 20px; height: 20px; display: inline-block; border: 1px solid #000; background-color: " + color + ";'></span></td>"
            + "<td>" + String.format("%.0f", price)+ "VND"  + "</td>"
            + "</tr>";
        }
    
        // Construct the complete email HTML
        return buildEmailHtml(paymentId, productDetails);
    }
    
    
    

    private double parsePriceValue(String priceStr) {
        // Remove any currency symbol or text and convert to USD if necessary
        priceStr = priceStr.replaceAll("[^\\d.]+", ""); // Keep only digits and dot for decimal
        try {
            double priceUsd = Double.parseDouble(priceStr);
            // Assume the exchange rate for USD to VND is 23000 (you should update this with the current rate)
            double exchangeRate = 23000.0;
            return priceUsd * exchangeRate;
        } catch (NumberFormatException e) {
            return 0.0; // Handle gracefully if priceStr cannot be parsed
        }
    }

    private String getValueByKey(String infoPart, String key) {
        // Split by key and return the value part
        String[] parts = infoPart.split(": ");
        if (parts.length == 2 && parts[0].trim().equals(key)) {
            return parts[1].trim();
        }
        return "";
    }

    private String getProductPrimaryImage(Long productId) {
        List<Productimage> primaryImages = productImageRepository.findByProductIdAndIsPrimary(productId, true);
        if (!primaryImages.isEmpty()) {
            byte[] imageData = primaryImages.get(0).getImageData();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return  base64Image;
        }
        return ""; // Return an empty string if no primary image found
    }

    private String buildEmailHtml(String paymentId, String productDetailsHtml) {
        Context context = new Context();
        context.setVariable("paymentId", paymentId);
        context.setVariable("productDetailsHtml", productDetailsHtml);
        return templateEngine.process("email-template", context);
    }

}
