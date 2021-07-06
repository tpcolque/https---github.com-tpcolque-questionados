package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.services.CategoriaServices;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaServices service;

        @GetMapping("/categorias")
        public ResponseEntity<List<Categoria>> traerCategorias(){
            
            return ResponseEntity.ok(service.traerCategorias());
        }

        @PostMapping("/categorias")
        public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){
            service.crearCategoria(categoria);

            GenericResponse respuesta = new GenericResponse();
            respuesta isOk = true;
            respuesta id = categoria.getCategoriaId;
            
        }
    
}
