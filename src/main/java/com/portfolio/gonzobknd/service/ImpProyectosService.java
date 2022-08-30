
package com.portfolio.gonzobknd.service;

import com.portfolio.gonzobknd.entity.Proyectos;
import com.portfolio.gonzobknd.repository.IProyectosRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectosService {
    @Autowired
    IProyectosRepository iProyectosRepository;
    
    public List<Proyectos> list(){
        return iProyectosRepository.findAll();
    }
    
    public Optional<Proyectos> getOne(Long id){
        return iProyectosRepository.findById(id);
    }
    
    public Optional<Proyectos> getByNombrePro(String nombrePro){
        return iProyectosRepository.findByNombrePro(nombrePro);
    }
    
    public void save(Proyectos pro ){
        iProyectosRepository.save(pro);
    }
    
    public void delete(Long id){
        iProyectosRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return iProyectosRepository.existsById(id);
    }
    
    public boolean existsByNombrePro(String nombrePro){
        return iProyectosRepository.existsByNombrePro(nombrePro);
    }
    
}
