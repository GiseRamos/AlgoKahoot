package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.preguntas.GeneradorDePreguntas;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;

import java.util.LinkedList;
import java.util.List;

public class Juego {

    private final List<Jugador> jugadores = new LinkedList<>();
    private final Servicio servicio;
    private final GeneradorDePreguntas generadorDePreguntas;

    public Juego(Servicio servicio, GeneradorDePreguntas generadorDePreguntas) {
        this.servicio = servicio;
        this.generadorDePreguntas = generadorDePreguntas;
    }

    public void comenzarJuego() {
        List<Pregunta> preguntas = crearPreguntas();

        preguntas.forEach(pregunta -> {
            jugarRonda(pregunta, servicio);
            darPuntosAJugadores(jugadores);
        });
    }

    private void jugarRonda(Pregunta pregunta, Servicio servicio) {
        Turno turno = new Turno(pregunta, servicio);
        jugadores.forEach(turno::jugarTurno);
    }

    public void agregarJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
    }

    private List<Pregunta> crearPreguntas() {
        return generadorDePreguntas.obtenerPreguntas();
    }

    private void darPuntosAJugadores(List<Jugador> jugadores) {
        //meter exclusividad, se modifica el puntaje de pregunta.
        jugadores.forEach(Jugador::asignarPuntajeTotal);
    }

    public List<Jugador> obtenerJugadores() {
        return this.jugadores;
    }
}
