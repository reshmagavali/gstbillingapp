package com.fullstack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;

    @Pattern(regexp = "[A-Za-z]*", message = "Name Should  Be Alphabetic Only")
    private String custName;

    @Range(min = 1000000000, max = 9999999999L, message = "Contact number must be 10 digit")
    private Long contact;

    @Email(message = "Email Id must be valid")
    private String email;


    @Size(min = 4, message = "Description Atleast 4 character")
    private String productDescription;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;

    @NotNull
    private long quantity;

    @NotNull
    private double pricePerUnit;

    @JsonIgnore
    private double totalAmount;

}
