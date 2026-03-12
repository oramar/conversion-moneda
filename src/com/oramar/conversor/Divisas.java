package com.oramar.conversor;

import com.oramar.app.MensajeFinal;
import javax.swing.*;
import java.util.Objects;

public class Divisas {
    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/oramar/icons/icon-divisa.png")));

    public void convertirDivisa() {
        Object[] opciones = { "ARS a USD","BRL a USD","COP a USD","USD a ARS",  "USD a BRL","USD a COP", "USD a VES","VES a USD" };
        Object seleccion = JOptionPane.showInputDialog(null, "Seleccione moneda:", "Conversor",
                JOptionPane.QUESTION_MESSAGE, icon, opciones, opciones[0]);

        if (seleccion == null) {
            new MensajeFinal().mostrarDespedida();
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese cantidad:");
        if (input != null && !input.isEmpty()) {
            // Pasamos el input original para que DivisasCalculo haga el parseo
            new DivisasCalculo().resultadoConversion((String) seleccion, input);
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada o cantidad vacía.");
        }

        new MensajeFinal().mostrarDespedida();
    }
}