package com.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[A-Za-z]*", message = "Name Should  Be Alphabetic Only")
    private String name;

    @Range(min = 1000000000, max = 9999999999L, message = " Admin Contact number must be 10 digit")
    private Long contact;

    @Email(message = "Email Id must be valid")
    private String email;

    @Size(min = 4, message = "Password Atleast 4 character")
    private String pass;
}
