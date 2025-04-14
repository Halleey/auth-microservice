package com.api.auth.dto;

public class DoctorResponseDTO {
    private String name;
    private String crm;
    private String expertise;
    private String role;

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getCrm() {
        return crm;
    }

    public String getExpertise() {
        return expertise;
    }

}
