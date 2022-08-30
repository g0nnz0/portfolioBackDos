package com.portfolio.gonzobknd.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoSkills {
    
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentajeSkill;

    public DtoSkills() {
    }

    public DtoSkills(String nombreSkill, int porcentajeSkill) {
        this.nombreSkill = nombreSkill;
        this.porcentajeSkill = porcentajeSkill;
    }
    
    
    
    
    
}
