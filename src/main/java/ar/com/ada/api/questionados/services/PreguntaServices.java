package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaServices {

    @Autowired
    PreguntaRepository repository;

    public List<Pregunta> traerPreguntas(){

        return repository.findAll();
    }
    public Pregunta buscarPreguntaPorId(Integer preguntaId){

        Optional<Pregunta> resultado = repository.findById(preguntaId);

        if(resultado.isPresent())
            return resultado.get();
            
        return null;
    }

}
