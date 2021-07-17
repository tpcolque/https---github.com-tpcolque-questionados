package ar.com.ada.api.questionados.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "respuesta")
public class Respuesta {
    
    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremental
    @Column(name = "respuesta_id")
    private Integer respuestaId;

    private String texto;

    @Column(name = "es_correcta")
    private boolean esCorrecta; //por defecto el resultado de boolean es falso

    @ManyToOne
    @JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id") //corregido
    @JsonIgnore
    private Pregunta pregunta; //para que agregamos esta clase? Respondido
    
    public Pregunta getPregunta() {  
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.pregunta.agregarRespuesta(this); //relacion bidireccional
    }

    public Integer getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Integer respuestaId) {
        this.respuestaId = respuestaId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }
    
}
