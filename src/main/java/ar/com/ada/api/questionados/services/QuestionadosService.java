package ar.com.ada.api.questionados.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.request.RespuestaAVerificar;

@Service
public class QuestionadosService {

    @Autowired
    PreguntaServices preguntaService;

    public Pregunta traerPreguntaRandom() {
        List<Pregunta> todasLasPreguntas = preguntaService.traerPreguntas();
        int min =1;
        int max = preguntaService.traerPreguntas().size();
        int random =(int) (Math.random() * ((max - min) + 1)) + min;
        return todasLasPreguntas.get(random - 1);
    }

    public boolean verificarRespuesta(Integer preguntaId, Integer respuestaId) { //model que bajamos desde el controller, solo paso los parametros individuales.
        
        Pregunta pregunta = preguntaService.buscarPreguntaPorId(preguntaId);

        for (Respuesta respuesta : pregunta.getOpciones()) {
            if(respuesta.getRespuestaId().equals(respuestaId)){
                return respuesta.isEsCorrecta();
            }
        }
        
        return false;
    }


    
}
