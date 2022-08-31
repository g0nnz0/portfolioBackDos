package com.portfolio.gonzobknd.service;

import com.portfolio.gonzobknd.entity.Educacion;
import com.portfolio.gonzobknd.repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {
    @Autowired
    IEducacionRepository iEducacionRepository;
    
    public List<Educacion> list(){
        return iEducacionRepository.findAll();
    }
    
    public Optional<Educacion> getOne(Long id){
        return iEducacionRepository.findById(id);
    }
    
    public Optional<Educacion> getByNombreEdu(String nombreEdu){
        return iEducacionRepository.findByNombreEdu(nombreEdu);
    }
    
    public void save(Educacion edu ){
        iEducacionRepository.save(edu);
    }
    
    public void delete(Long id){
        iEducacionRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return iEducacionRepository.existsById(id);
    }
    
    public boolean existsByNombreEdu(String nombreEdu){
        return iEducacionRepository.existsByNombreEdu(nombreEdu);
        
        
    }
    
}
