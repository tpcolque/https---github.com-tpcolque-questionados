package ar.com.ada.api.questionados.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.models.response.OpcionPregunta;
import ar.com.ada.api.questionados.models.response.RespuestaVerificada;
import ar.com.ada.api.questionados.request.PreguntaAResolver;
import ar.com.ada.api.questionados.request.RespuestaAVerificar;
import ar.com.ada.api.questionados.services.*;

@RestController

public class QuestionadosController {

    @Autowired
    QuestionadosService service; //declaro el service en el controller para usar un metodo de ahi
   
    @GetMapping("/questionados/next") //obtener la siguiente pregunta//el get es para consultar algo
    public ResponseEntity<PreguntaAResolver> traerPreguntaRandom(){
        
        Pregunta pregunta = service.traerPreguntaRandom(); //aca se guarda en variable pregunta
        
        PreguntaAResolver preguntaAResolver = PreguntaAResolver.convertirDesde(pregunta);

        return ResponseEntity.ok(preguntaAResolver); 
        
        //return ResponseEntity.ok(service.traerPreguntaRandom()); otra forma de codear mas
        //sintetica.
    }

    //verificar si la respuesta de una pregunta es valida
    @PostMapping("/questionados/verificaciones") //se sacan los ids porque no se suben a el postman
    public ResponseEntity <RespuestaVerificada> verficarRespuesta (@RequestBody RespuestaAVerificar respuestaAVerificar){

        RespuestaVerificada respuestaVerificada = new RespuestaVerificada();
        if(service.verificarRespuesta(respuestaAVerificar.preguntaId, respuestaAVerificar.respuestaId)){
            respuestaVerificada.esCorrecta = true;
        }else{
            respuestaVerificada.esCorrecta = false;
        }
        return ResponseEntity.ok(respuestaVerificada);
    }

}
