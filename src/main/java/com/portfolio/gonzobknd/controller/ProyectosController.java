package com.portfolio.gonzobknd.controller;

import com.portfolio.gonzobknd.dto.DtoProyectos;
import com.portfolio.gonzobknd.entity.Proyectos;
import com.portfolio.gonzobknd.service.ImpProyectosService;
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
@RequestMapping("proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectosController {
    
    @Autowired
    ImpProyectosService impProyectosService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = impProyectosService.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") Long id) {
        if (!impProyectosService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Proyectos proyecto = impProyectosService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoPro) {
        if (StringUtils.isBlank(dtoPro.getNombrePro())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impProyectosService.existsByNombrePro(dtoPro.getNombrePro())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = new Proyectos(dtoPro.getNombrePro(), dtoPro.getDescripcionPro(), dtoPro.getImgPro());
        impProyectosService.save(proyecto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
            @RequestBody DtoProyectos dtoPro) {

        if (!impProyectosService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impProyectosService.existsByNombrePro(dtoPro.getNombrePro()) && impProyectosService.getByNombrePro(dtoPro.getNombrePro()).get().getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoPro.getNombrePro())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = impProyectosService.getOne(id).get();

        proyecto.setNombrePro(dtoPro.getNombrePro());
        proyecto.setDescripcionPro(dtoPro.getDescripcionPro());
        proyecto.setImgPro(dtoPro.getImgPro());

        impProyectosService.save(proyecto);
        return new ResponseEntity(HttpStatus.OK);

    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impProyectosService.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        impProyectosService.delete(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
