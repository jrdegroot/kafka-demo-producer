package com.example.kafkademoproducer.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Location location;
}
