package com.api.thuctaptotnghiepbackend.Service.Ipml;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Contact;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to,String subject,String body) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            e.getMessage();
            throw new RuntimeException(e);
        }

    }


    public void sendNewContactEmail(Contact contact) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(contact.getEmail());
            helper.setSubject("Thông báo: Liên hệ mới từ LQN&BQL - " + contact.getTitle());
            
            helper.setText("Chào " + contact.getName() + ",\n\nCảm ơn bạn đã liên hệ với chúng tôi. Chúng tôi sẽ xem xét yêu cầu của bạn và phản hồi sớm nhất có thể.\n\nTrân trọng,\nLQN&BQL");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Xử lý lỗi gửi email
        }
    }




    public void sendEmail(String from, String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
        helper.setText(htmlContent, true);
        helper.setTo(to);
        helper.setSubject(subject);
      
        javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
        e.printStackTrace(); // Xử lý lỗi gửi email
    }
    }


}
