package com.api.thuctaptotnghiepbackend.Service.Ipml;



import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Contact;
import com.api.thuctaptotnghiepbackend.Repository.Contact.ContactRepository;
import com.api.thuctaptotnghiepbackend.Service.ContactService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository; // Đảm bảo sửa đổi repository nếu cần
    private final EmailService emailService;

    @Override
    public Contact createContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        emailService.sendNewContactEmail(savedContact);
        return savedContact;
    }

    @Override
    public Contact getContactById(Long contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        return optionalContact.orElse(null);
    }

    @Override
    public Page<Contact> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = contactRepository.findById(contact.getId()).orElse(null);

        if (existingContact != null) {
            existingContact.getUser().setId(contact.getUser().getId());
            existingContact.setName(contact.getName());
            existingContact.setEmail(contact.getEmail());
            existingContact.setPhone(contact.getPhone());
            existingContact.setTitle(contact.getTitle());
            existingContact.setContent(contact.getContent());
       
            existingContact.setCreatedAt(contact.getCreatedAt());
            existingContact.setUpdatedAt(contact.getUpdatedAt());
          
            existingContact.setStatus(contact.getStatus());

            Contact updatedContact = contactRepository.save(existingContact);
            return updatedContact;
        } else {
            // Handle the case where the contact with the given ID is not found.
            return null;
        }
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }


    @Override
    public Contact replyToContact(Long id, String reply) {
        // Tìm liên hệ theo ID
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        
        // Thêm phản hồi vào nội dung của liên hệ
        String updatedContent = contact.getContent() + "\n[Admin] " + reply;
        contact.setContent(updatedContent);
        
        // Lưu liên hệ đã cập nhật vào cơ sở dữ liệu
        return contactRepository.save(contact);
    }
}
