package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Buyer {
    private String buyerId;
    private String pincode;
}
