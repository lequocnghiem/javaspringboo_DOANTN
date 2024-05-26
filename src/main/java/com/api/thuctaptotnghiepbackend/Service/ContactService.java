package com.api.thuctaptotnghiepbackend.Service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Contact;


public interface ContactService {
    
    public Contact createContact(Contact contact);
    public Contact getContactById(Long contactId);
    public Page<Contact> getAllContacts(Pageable pageable);
    public Contact updateContact(Contact contact);
    public void deleteContact(Long contactId);
    public Contact replyToContact(Long id, String reply);
}