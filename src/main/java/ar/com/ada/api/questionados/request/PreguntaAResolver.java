package ar.com.ada.api.questionados.request;

import java.util.*;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.models.response.OpcionPregunta;

public class PreguntaAResolver {

    public Integer preguntaId;
    public String enunciado;
    public Categoria categoria;
    public List<OpcionPregunta> opciones = new ArrayList<>();

    public static PreguntaAResolver convertirDesde(Pregunta pregunta){

        PreguntaAResolver preguntaAResolver = new PreguntaAResolver();
        
        preguntaAResolver.preguntaId = pregunta.getPreguntaId();
        preguntaAResolver.enunciado = pregunta.getEnunciado();
        preguntaAResolver.categoria = pregunta.getCategoria();

        preguntaAResolver.opciones = new ArrayList<>();
        // la vuelvo a inicializar la lista por las dudas, se llama opciones, esta vacia!!!
        for (Respuesta respuesta : pregunta.getOpciones()) {
             //cada elemento de la izq va a ser del tipo del elemento que este a la derecha, Opciones, que es lista Respuesta
            //del ciclo for, desde los dos puntos a la derecha va la lista que tengo que recorrer.
            OpcionPregunta opcion = new OpcionPregunta();
            //Lo que hago aca es instanciar un elemento de la lista opciones, que se llama opcion para poder llenarla.
            opcion.respuestaId = respuesta.getRespuestaId();
            opcion.texto = respuesta.getTexto();

            preguntaAResolver.opciones.add(opcion);
        }

        return preguntaAResolver;
    }
    
}
