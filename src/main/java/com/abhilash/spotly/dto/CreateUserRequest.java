package com.abhilash.spotly.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;

/**
 * CreateUserRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    
    @NonNull
    private String name;
    
    @Email
    private String email;
}
