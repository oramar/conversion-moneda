package com.oramar.app;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Objects;

public class MensajeFinal {

    // Carga de iconos con validación básica
    private final ImageIcon iconP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/oramar/icons/icon-question.png")));
    private final ImageIcon iconE = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/oramar/icons/icon-salir.png")));

    // Cambiamos el nombre para que sea un método de acción claro
    public void mostrarDespedida() {
        Object[] opciones = {"Sí", "No"};

        int seleccion = JOptionPane.showOptionDialog(
                null,
                "¿Deseas realizar otra conversión?",
                "Finalizar Programa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                iconP,
                opciones,
                opciones[0]
        );

        // En Swing, YES_OPTION suele ser 0
        if (seleccion == JOptionPane.YES_OPTION) {
            new MenuPrincipal().iniciar();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Programa Finalizado",
                    "Muchas Gracias",
                    JOptionPane.INFORMATION_MESSAGE,
                    iconE
            );
            // Cerramos la aplicación por completo
            System.exit(0);
        }
    }
}