package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.GenericResponse;
import ar.com.ada.api.questionados.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.services.PreguntaServices;

@RestController
public class PreguntaController {

    @Autowired
    private PreguntaServices service;

    @GetMapping("/preguntas")
    public ResponseEntity <List<Pregunta>> traerPreguntas(){
        return ResponseEntity.ok(service.traerPreguntas());
    }
    @GetMapping("/preguntas/{id}")
    public ResponseEntity<Pregunta> buscarPreguntaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPreguntaPorId(id));
    }
    @PostMapping("preguntas")
    public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva){

        GenericResponse respuesta = new GenericResponse();
        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones);
        //Aca igualo la clase Pregunta con su variable pregunta a todo lo anterior, asi cuando el service
        //llama al metodo crearPregunta, se guarda en la variable pregunta. Y luego en la linea 40...
        respuesta.isOk = true;
        respuesta.id = pregunta.getPreguntaId(); //Aca va el nuevo id que fue creado en linea 36!
        //Esa pregunta se iguala a la respuesta.id
        respuesta.message ="La pregunta fue creada con exito";

        return ResponseEntity.ok(preguntaNueva);
    }
}
