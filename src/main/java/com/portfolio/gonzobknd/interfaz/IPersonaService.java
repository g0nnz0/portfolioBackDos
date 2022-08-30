package com.portfolio.gonzobknd.interfaz;

import com.portfolio.gonzobknd.entity.Persona;
import java.util.List;



public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo Persona
    public void savePersona (Persona persona);
    
    //Eliminar un objeto de tipo persona, buscandolo por ID
    public void deletePersona (Long id);
    
    //Buscar un objeto de tipo persona, por ID
    public Persona findPersona (Long id);
    
}
