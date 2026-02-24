package com.kunk.orbitra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
