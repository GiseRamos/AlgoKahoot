package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.preguntas.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaRonda extends VBox {

    private final Stage stage;

    public VistaRonda(Stage stage) {
        this.stage = stage;
    }

    public void armarVistaDeRonda() {
        Pregunta preguntaRonda = Juego.getInstance().obtenerPreguntaNueva();
        if (preguntaRonda.getClass().equals(PreguntaVerdaderoOFalso.class)) {
            VistaVerdaderoOFalso vista = new VistaVerdaderoOFalso(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }
        if (preguntaRonda.getClass().equals(PreguntaVerdaderoOFalsoConPenalidad.class)) {
            VistaVerdaderoOFalsoConPenalidad vista = new VistaVerdaderoOFalsoConPenalidad(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }
        if (preguntaRonda.getClass().equals(PreguntaMultipleChoiceClasico.class)) {
            VistaMultipleChoiceClasico vista = new VistaMultipleChoiceClasico(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }
        if (preguntaRonda.getClass().equals(PreguntaMultipleChoiceConPenalidad.class)) {
            VistaMultipleChoiceConPenalidad vista = new VistaMultipleChoiceConPenalidad(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }
        /*if (preguntaRonda.getClass().equals(PreguntaMultipleChoiceParcial.class)) {
            VistaMultipleChoiceParcial vista = new VistaMultipleChoiceParcial(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }

        if (preguntaRonda.getClass().equals(PreguntaOrderedChoice.class)) {
            VistaOrderedChoice vista = new VistaOrderedChoice(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }
        if (preguntaRonda.getClass().equals(PreguntaGroupChoice.class)) {
            VistaGroupChoice vista = new VistaGroupChoice(preguntaRonda, stage);
            vista.armarVistaPropia();
            this.getChildren().add(vista);
        }*/
    }
}
