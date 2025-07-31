package com.fullstack.controller;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Invoice;
import com.fullstack.service.AdminService;
import com.fullstack.service.EmailService;
import com.fullstack.service.InvoiceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
@SecurityRequirement(name = "Bearer Auth")
@Slf4j
public class InvoiceController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private InvoiceService invoiceService;


    @PostMapping("/createinvoice")
    public ResponseEntity<Invoice> signUp(@Valid @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.save(invoice));
    }

    @GetMapping("/findbyinvoiceid/{invoiceId}")
    public ResponseEntity<Optional<Invoice>> findById(@PathVariable int invoiceId) {
        return ResponseEntity.ok(invoiceService.findById(invoiceId));
    }

    @PostMapping("/sendemail/{custEmail}")
    public ResponseEntity<String> sendEmail(@PathVariable String custEmail) {
        emailService.sendmail(custEmail);
        return ResponseEntity.ok("Invoice send Successfull");
    }

    @GetMapping("/findbyemailid/{custEmail}")
    public ResponseEntity<Invoice> findByEmailId(@PathVariable String custEmail) {
        return ResponseEntity.ok(invoiceService.findByEmail(custEmail));
    }

    @GetMapping("/findallinvoice")
    public ResponseEntity<List<Invoice>> findAll() {
        return ResponseEntity.ok(invoiceService.findAll());
    }

    @PutMapping("/update/{invoiceId}")
    public ResponseEntity<Invoice> updateInvoice(@Valid @PathVariable int invoiceId, @RequestBody Invoice admin) {
        Invoice invoice = invoiceService.findById(invoiceId).orElseThrow(() -> new RecordNotFoundException("#InvoiceId doesnot Exist"));
        log.info("Invoice: " + invoice.getInvoiceId());
        invoice.setCustName(admin.getCustName());
        invoice.setContact(admin.getContact());
        invoice.setInvoiceDate(admin.getInvoiceDate());
        invoice.setEmail(admin.getEmail());

        invoice.setPricePerUnit(admin.getPricePerUnit());
        invoice.setQuantity(admin.getQuantity());
        invoice.setProductDescription(admin.getProductDescription());
        return ResponseEntity.ok(invoiceService.update(invoice));
    }

    @DeleteMapping("/deletebyid/{invoiceId}")
    public ResponseEntity<String> deleteById(@PathVariable int invoiceId) {
        invoiceService.deleteById(invoiceId);
        return ResponseEntity.ok("Customer Invoice deleted successfully");
    }


    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<Invoice> findByName(@PathVariable String custName) {
        return ResponseEntity.ok(invoiceService.findByName(custName));
    }

    @GetMapping("/findbycontact/{contact}")
    public ResponseEntity<Invoice> findByContact(@PathVariable long contact) {
        return ResponseEntity.ok(invoiceService.findByContact(contact));
    }

    @GetMapping("/findbydate")
    public ResponseEntity<List<Invoice>> findByDate(@RequestParam(defaultValue = "10-06-2022") String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return ResponseEntity.ok(invoiceService.findAll().stream().filter(cust -> simpleDateFormat.format(cust.getInvoiceDate()).equals(date)).toList());
    }


    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<Invoice>> findByAnyInput(@PathVariable String input) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return ResponseEntity.ok(invoiceService.findAll().stream().filter(cust -> simpleDateFormat.format(cust.getInvoiceDate()).equals(input)
                || cust.getCustName().equals(input)
                || String.valueOf(cust.getInvoiceId()).equals(input)
                || String.valueOf(cust.getContact()).equals(input)
                || String.valueOf(cust.getQuantity()).equals(input)
                || String.valueOf(cust.getTotalAmount()).equals(input)

                || String.valueOf(cust.getPricePerUnit()).equals(input)
                || cust.getEmail().equals(input)

                || cust.getProductDescription().equals(input)).toList());

    }


    @GetMapping("/sortbydate")
     public  ResponseEntity<List<Invoice>> sortByInvoiceDate( )
    {
        return ResponseEntity.ok(invoiceService.findAll().stream().sorted(Comparator.comparing(Invoice::getInvoiceDate)).toList());
    }

    @GetMapping("/sortbyinvoiceid")
    public  ResponseEntity<List<Invoice>> sortByInvoiceId( )
    {
        return ResponseEntity.ok(invoiceService.findAll().stream().sorted(Comparator.comparing(Invoice::getInvoiceId)).toList());
    }

    @GetMapping("/sortbytotalAmount")
    public  ResponseEntity<List<Invoice>> sortByTotalAmount()
    {
        return ResponseEntity.ok(invoiceService.findAll().stream().sorted(Comparator.comparing(Invoice::getTotalAmount)).toList());
    }
}
