package com.example.bookstoreapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Valid
public class UserDto {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Name(First Letter Should be in Upper Case and min 3 Characters.)")
    String first_name;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{1,}$", message = "Invalid Last Name(First Letter Should be in Upper Case")
    String last_name;
    @NotEmpty(message = "Address Cannot be Empty")
    String address;
    @NotNull(message = "Email Address cannot be Null")
    String email_address;
    @JsonFormat(pattern = "yyyy MM dd")
    @NotNull(message = "Start Date cannot be Empty")
    @PastOrPresent(message = "Start Date should be past or present date")
    LocalDate DOB;
    /*
     * (?=.*[A-Z]) represents an upper case character that must occur at least once.
     * (?=.*[0-9]) represents a digit must occur at least once.
     * (?+.*[@#$%^&*()] represent the special symbol at least once.
     * (?=.*[a-zA-z0-9]) represents a lower case character or number must occur at least once.
     * {8,} represents at least 8 or more characters.
     */
    @NotEmpty(message = "Address Cannot be Empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*()-+=])([a-zA-Z0-9@._-]).{8,}$", message = "Invalid Password\n(1. Upper case character that must occur at least once.\n" +
            "2. A digit must occur at least once.\n3. Special symbol at least once.\n4. lower case character or number must occur at least once.\n5. Represents at least 8 or more characters.)")
    String password;

}

