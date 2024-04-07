package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Option {
    private String optionName;
    private int weightage;
}
