package com.portfolio.gonzobknd.controller;

import com.portfolio.gonzobknd.dto.DtoExperiencia;
import com.portfolio.gonzobknd.entity.Experiencia;
import com.portfolio.gonzobknd.service.ImpExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {

    @Autowired
    ImpExperienciaService impExperienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = impExperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") Long id){
        if(!impExperienciaService.existsById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Experiencia experiencia = impExperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExp) {
        if (StringUtils.isBlank(dtoExp.getNombreExp())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impExperienciaService.existsByNombreExp(dtoExp.getNombreExp())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Experiencia exp = new Experiencia(dtoExp.getNombreExp(), dtoExp.getDescripcionExp());
        impExperienciaService.save(exp);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoExperiencia dtoExp) {
        if (!impExperienciaService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (impExperienciaService.existsByNombreExp(dtoExp.getNombreExp()) && impExperienciaService.getByNombreExp(dtoExp.getNombreExp()).get().getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoExp.getNombreExp())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Experiencia exp = impExperienciaService.getOne(id).get();
        exp.setNombreExp(dtoExp.getNombreExp());
        exp.setDescripcionExp(dtoExp.getDescripcionExp());

        impExperienciaService.save(exp);
        return new ResponseEntity(HttpStatus.OK);

    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!impExperienciaService.existsById(id)) 
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        impExperienciaService.delete(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
