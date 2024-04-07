package org.example.entities;

import lombok.Data;

@Data
public class Admin extends SurveyUser{
    private String adminId;
    public Admin(String adminId){
        this.adminId = adminId;
    }
}
