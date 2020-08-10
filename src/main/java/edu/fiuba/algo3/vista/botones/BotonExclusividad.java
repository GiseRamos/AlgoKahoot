package edu.fiuba.algo3.vista.botones;

import edu.fiuba.algo3.controladores.botonhandler.AplicarExclusividadEventHandler;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.scene.control.Button;

public class BotonExclusividad extends Button {

    public void actualizar(Jugador jugadorActual){
        int exclusividadJugador = jugadorActual.getExclusividadDisponible();
        String text = "Aplicar exclusividad (" + String.valueOf(exclusividadJugador) + ")";
        this.setText(text);
        AplicarExclusividadEventHandler botonExclusividadEventHandler = new AplicarExclusividadEventHandler(this, jugadorActual);
        this.setOnAction(botonExclusividadEventHandler);
        this.setDisable(exclusividadJugador == 0);
    }
}
