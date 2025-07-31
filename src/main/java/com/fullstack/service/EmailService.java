package com.fullstack.service;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Admin;
import com.fullstack.model.Invoice;
import com.fullstack.repository.AdminRepository;
import com.fullstack.repository.InvoiceRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username")
    String from;

    public void sendmail(String custEmail) {

        MimeMessage mimeMessag = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessag, true);
            mimeMessageHelper.setFrom(from);
            // mimeMessageHelper.setCc(email.getCcEmail());
            /// mimeMessageHelper.setBcc(email.getBccEmail());
            // mimeMessageHelper.setSubject(email.getEmailSubject());
            Invoice getemail = invoiceRepository.findByEmail(custEmail);


            if (custEmail.equals(getemail.getEmail())) {

                mimeMessageHelper.setTo(getemail.getEmail());
            } else {
                new RecordNotFoundException("record not found");
            }

            mimeMessageHelper.setText(String.valueOf(getemail));


            // FileSystemResource fileSystemResource=new FileSystemResource(new File(email.getEmailAttachment()));
            // mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            javaMailSender.send(mimeMessag);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
