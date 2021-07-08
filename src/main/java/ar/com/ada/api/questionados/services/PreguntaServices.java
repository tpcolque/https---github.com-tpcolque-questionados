package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaServices {

    @Autowired
    PreguntaRepository repository;

    @Autowired
    CategoriaServices CategoriaService;

    public List<Pregunta> traerPreguntas(){

        return repository.findAll();
    }
    public Pregunta buscarPreguntaPorId(Integer preguntaId){

        Optional<Pregunta> resultado = repository.findById(preguntaId);

        if(resultado.isPresent())
            return resultado.get();
            
        return null;
    }
    public Pregunta crearPregunta(String enunciado, Integer categoriaId, List<Respuesta> opciones){

        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(enunciado);

        Categoria categoria = CategoriaService.buscarCategoriaPorId(categoriaId);

        pregunta.setCategoria(categoria);
        //pregunta.setOpciones(opciones); SE SACA EL METODO PORQUE NO SE CUMPLE R.BIDIRECCIONAL
        for (Respuesta respuesta : opciones) {
            respuesta.setPregunta(pregunta); //solo este metodo ejecuta la R.bidireccional
        }

        repository.save(pregunta);
        return pregunta;
    }
    

}
