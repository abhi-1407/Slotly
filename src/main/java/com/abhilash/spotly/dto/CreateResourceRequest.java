package com.abhilash.spotly.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResourceRequest{
    
    @NotNull
    private String name;
    
    @NotNull
    @Length(max = 100)
    private String description;
}