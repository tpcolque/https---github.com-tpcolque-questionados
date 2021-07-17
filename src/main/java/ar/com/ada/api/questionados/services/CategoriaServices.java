package ar.com.ada.api.questionados.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service

public class CategoriaServices {

    @Autowired
    CategoriaRepository repository;

    public List<Categoria> traerCategorias(){
        
        return repository.findAll();
    }
    
    public Categoria buscarCategoriaPorId(Integer categoriaId){ //brenda buscarCategoria

        Optional<Categoria> resultado = repository.findById(categoriaId);
        Categoria categoria = null;

        if(resultado.isPresent())
            return resultado.get();
            
        return categoria;
    }
    public boolean crearCategoria(Categoria categoria){
        if(existe(categoria.getNombre()))
            return false;

        repository.save(categoria);

        return true;
    }
    public boolean existe(String nombre){
        Categoria categoria = repository.findByNombre(nombre);
        //Aca reemplazo por el metodo anterior
        return categoria != null;
    }
    public boolean existeV2(String nombre){
        return repository.existsByNombre(nombre);
    }
    public void eliminarCategoriaPorId(Integer id){
        Categoria categoria = this.buscarCategoriaPorId(id);
        repository.deleteById(id);
    }


    
}
