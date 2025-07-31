package com.fullstack.service;

import com.fullstack.model.Admin;
import com.fullstack.model.Invoice;
import com.fullstack.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(int invoiceId) {


        return invoiceRepository.findById(invoiceId);

    }

    public Invoice findByEmail(String custEmail) {
        return invoiceRepository.findByEmail(custEmail);
    }


    public Invoice update(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public String deleteById(int invoiceId) {
        invoiceRepository.deleteById(invoiceId);
        return "InvoiceDeletedSuccessfully";
    }


    public Invoice save(Invoice invoice) {
        double  totalAmount=(invoice.getQuantity()*invoice.getPricePerUnit())*1.18;
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }

    public Invoice findByName(String name) {
        return invoiceRepository.findByCustName(name);
    }

    public Invoice findByContact(long contact) {
        return invoiceRepository.findByContact(contact);
    }

    public Invoice findByDate(Date date) {
        return invoiceRepository.findByInvoiceDate(date);
    }
}
