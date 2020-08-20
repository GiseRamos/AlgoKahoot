package edu.fiuba.algo3.vista.preguntas;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.vista.*;
import edu.fiuba.algo3.vista.botones.BotonEnviarRespuesta;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VistaMultipleChoiceConPenalidadOParcial extends VBox implements Observador {

    private final List<Opcion> respuesta = new LinkedList<>();
    private final Label infoJugador = new Label();
    private VistaOpcionesMultipleChoice vistaOpciones;
    private VistaBotonesMultiplicadores vistaBotonesMultiplicadores;
    private final Pregunta pregunta;
    private final Stage stage;
    private final Queue<Jugador> jugadores;
    private final int tiempoDisponible = 5;
    private LabelTiempo labelTiempo;
    private Juego juego;

    public VistaMultipleChoiceConPenalidadOParcial(Pregunta pregunta, Stage stage, Juego juego) {
        this.juego = juego;
        vistaBotonesMultiplicadores = new VistaBotonesMultiplicadores(juego);
        labelTiempo = new LabelTiempo(tiempoDisponible, juego);
        this.setSpacing(20);
        this.pregunta = pregunta;
        this.stage = stage;
        this.jugadores = new LinkedList<>(juego.obtenerJugadores());
    }

    public void armarVistaPropia(String clase) {
        this.getChildren().add(labelTiempo);
        juego.guardarObservador(this);

        //Agregado de la info del jugador
        this.getChildren().add(infoJugador);

        //Agregado de la pregunta
        String pregunta = this.pregunta.getPregunta();
        Label labelPregunta = new Label("Multiple Choice "+ clase + " : "  + pregunta);
        this.getChildren().add(labelPregunta);

        //Agregado de las opciones (se resume mucho si hacemos que las preguntas devuelvan todas las opciones)
        List<Opcion> opciones = new LinkedList<>(this.pregunta.getOpcionesCorrectas());
        opciones.addAll(this.pregunta.getOpcionesIncorrectas());
        Collections.shuffle(opciones);
        vistaOpciones = new VistaOpcionesMultipleChoice(opciones, respuesta);
        this.getChildren().add(vistaOpciones);

        //Agregado de la exclusividad
        this.getChildren().add(vistaBotonesMultiplicadores);

        //Agregado del enviar
        BotonEnviarRespuesta botonEnviar = new BotonEnviarRespuesta(respuesta, juego);
        this.getChildren().add(botonEnviar);

        update();
    }

    public void update() {
        labelTiempo.stop();
        if (jugadores.isEmpty()) {
            AvanzadorDeRondas avanzador = new AvanzadorDeRondas();
            avanzador.avanzarRonda(this.stage, juego);
        } else {
            labelTiempo.start();
            respuesta.clear();
            Jugador jugadorActual = jugadores.remove();
            String nombreJugadorActual = jugadorActual.obtenerNombre();
            int puntos = jugadorActual.obtenerPuntajeTotal();
            infoJugador.setText("Turno del jugador: " + nombreJugadorActual + ", puntos: " + puntos);
            vistaOpciones.update();
            vistaBotonesMultiplicadores.actualizar();
        }
    }
}
