package com.fullstack.repository;

import com.fullstack.model.Admin;
import com.fullstack.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Integer> {
    public Invoice findByInvoiceId(int invoiceId);
    public Invoice findByEmail(String email);
    public Invoice findByCustName(String custName);
    public Invoice findByContact(long contact);

   public Invoice findByInvoiceDate(Date invoiceDate);
}
