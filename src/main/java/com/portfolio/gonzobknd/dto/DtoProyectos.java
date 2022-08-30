package com.portfolio.gonzobknd.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoProyectos {
    
    @NotBlank
    private String nombrePro;
    @NotBlank
    private String descripcionPro;
    @NotBlank
    private String imgPro;

    public DtoProyectos() {
    }

    public DtoProyectos(String nombrePro, String descripcionPro, String imgPro) {
        this.nombrePro = nombrePro;
        this.descripcionPro = descripcionPro;
        this.imgPro = imgPro;
    }
    
    
    
}
