package com.portfolio.gonzobknd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Proyectos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePro;
    private String descripcionPro;
    private String imgPro;

    public Proyectos() {
    }

    public Proyectos(String nombrePro, String descripcionPro, String imgPro) {
        this.nombrePro = nombrePro;
        this.descripcionPro = descripcionPro;
        this.imgPro = imgPro;
    }
    
    
    
    
    
    
    
}
