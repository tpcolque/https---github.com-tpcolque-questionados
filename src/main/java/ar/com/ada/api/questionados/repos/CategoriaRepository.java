package ar.com.ada.api.questionados.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.questionados.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
    Categoria findByNombre(String nombre);
    //agrego este metodo en el repo para que pueda usarlo en categoriaService
    
}
