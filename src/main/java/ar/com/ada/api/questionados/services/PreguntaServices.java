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
        //aca estaba un void, con lo cual creaba una pregunta y no devolvia nada, ahora crea una pregunta y 
        //devuelve una pregunta con todos sus atributos.
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(enunciado);

        Categoria categoria = CategoriaService.buscarCategoriaPorId(categoriaId);
        //Aca lo que hago es guardar del metodo buscarCategoriaPorId ese id en categoria
        pregunta.setCategoria(categoria); // y a continuacion cuando lo encuentro se lo paso como parametro a el setCategoria
        //pregunta.setOpciones(opciones); SE SACA EL METODO PORQUE NO SE CUMPLE R.BIDIRECCIONAL
        //En este metodo las preguntas conocen a las opciones pero las opciones no conocen a la pregunta, me doy cuenta
        //porque cuando voy a setOpciones el metodo no tiene las preguntas en ningun lado.

        for (Respuesta respuesta : opciones) { //Tipo de clase y variable: la lista que queremos recorrer
            respuesta.setPregunta(pregunta); //solo este metodo ejecuta la R.bidireccional
        }

        repository.save(pregunta);
        return pregunta; //aca se pone return porque se cambia el void por Pregunta
    }
    

}
