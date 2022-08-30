package com.portfolio.gonzobknd.service;

import com.portfolio.gonzobknd.entity.Experiencia;
import com.portfolio.gonzobknd.repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpExperienciaService {
    @Autowired
    IExperienciaRepository iExperienciaRepository;
    
    public List<Experiencia> list(){
        return iExperienciaRepository.findAll();
    }
    
    public Optional<Experiencia> getOne(Long id){
        return iExperienciaRepository.findById(id);
    }
    
    public Optional<Experiencia> getByNombreExp(String NombreExp){
        return iExperienciaRepository.findByNombreExp(NombreExp);
    }
    
    public void save(Experiencia exp){
            iExperienciaRepository.save(exp);
    }
    
    public void delete(Long id){
        iExperienciaRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return iExperienciaRepository.existsById(id);
    }
    
    public boolean existsByNombreExp(String nombreExp){
        return iExperienciaRepository.existsByNombreExp(nombreExp);
    }
    
    
}
