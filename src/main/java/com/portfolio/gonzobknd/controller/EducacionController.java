package com.portfolio.gonzobknd.controller;

import com.portfolio.gonzobknd.dto.DtoEducacion;
import com.portfolio.gonzobknd.entity.Educacion;
import com.portfolio.gonzobknd.service.ImpEducacionService;
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
@RequestMapping("educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    @Autowired
    ImpEducacionService impEducacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = impEducacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") Long id) {
        if (!impEducacionService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = impEducacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEdu) {
        if (StringUtils.isBlank(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impEducacionService.existsByNombreEdu(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEdu.getNombreEdu(), dtoEdu.getDescripcionEdu(), dtoEdu.getPeriodoEdu());
        impEducacionService.save(educacion);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
            @RequestBody DtoEducacion dtoEdu) {

        if (!impEducacionService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impEducacionService.existsByNombreEdu(dtoEdu.getNombreEdu()) && impEducacionService.getByNombreEdu(dtoEdu.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = impEducacionService.getOne(id).get();

        educacion.setNombreEdu(dtoEdu.getNombreEdu());
        educacion.setDescripcionEdu(dtoEdu.getDescripcionEdu());
        educacion.setPeriodoEdu(dtoEdu.getPeriodoEdu());

        impEducacionService.save(educacion);
        return new ResponseEntity(HttpStatus.OK);

    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impEducacionService.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        impEducacionService.delete(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }

}
