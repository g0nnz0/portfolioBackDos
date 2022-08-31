package com.portfolio.gonzobknd.controller;

import com.portfolio.gonzobknd.dto.DtoSkills;
import com.portfolio.gonzobknd.entity.Skills;
import com.portfolio.gonzobknd.service.ImpSkillsService;
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
@RequestMapping("skills")
@CrossOrigin(origins = "https://gonzoportfoliofront.web.app")
public class SkillsController {
    
    @Autowired
    ImpSkillsService impSkillService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = impSkillService.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") Long id) {
        if (!impSkillService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Skills skill = impSkillService.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtoSkill) {
        if (StringUtils.isBlank(dtoSkill.getNombreSkill())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impSkillService.existsByNombreSkill(dtoSkill.getNombreSkill())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Skills skill = new Skills(dtoSkill.getNombreSkill(),dtoSkill.getPorcentajeSkill());
        impSkillService.save(skill);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
            @RequestBody DtoSkills dtoSkill) {

        if (!impSkillService.existsById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (impSkillService.existsByNombreSkill(dtoSkill.getNombreSkill()) && impSkillService.getByNombreSkill(dtoSkill.getNombreSkill()).get().getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoSkill.getNombreSkill())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Skills skill = impSkillService.getOne(id).get();

        skill.setNombreSkill(dtoSkill.getNombreSkill());
        skill.setPorcentajeSkill(dtoSkill.getPorcentajeSkill());

        impSkillService.save(skill);
        return new ResponseEntity(HttpStatus.OK);

    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impSkillService.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        impSkillService.delete(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }

    
}
