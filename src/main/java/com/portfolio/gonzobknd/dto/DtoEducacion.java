package com.portfolio.gonzobknd.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoEducacion {
    
    @NotBlank
    private String nombreEdu;
    @NotBlank
    private String descripcionEdu;
    @NotBlank
    private String periodoEdu;

    public DtoEducacion() {
    }

    public DtoEducacion(String nombreEdu, String descripcionEdu, String periodoEdu) {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.periodoEdu = periodoEdu;
    }
    
    
}
