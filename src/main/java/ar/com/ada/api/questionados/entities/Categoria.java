package ar.com.ada.api.questionados.entities;

import java.util.*;

import javax.persistence.*;

@Entity //para declarar que esto es una tabla
@Table(name = "categoria")
public class Categoria {

    @Id //Averiguar para que era este id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer categoriaId;
    
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //mapped by el atributo categoria del objeto pregunta
    private List<Pregunta> preguntas = new ArrayList<>();

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void agregarPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
    }
        
    
}
