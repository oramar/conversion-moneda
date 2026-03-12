package com.oramar.app;

import com.oramar.conversor.Divisas;

import javax.swing.*;
import java.util.Objects;

public class MenuPrincipal {
    // Constantes para evitar errores de dedo al escribir el texto
    private static final String OPCION_CONVERSOR = "Conversor de Divisas";
    private static final String OPCION_SALIR = "Salir del Programa";

    public void iniciar() {
        // Validación de recursos: Objects.requireNonNull ayuda a encontrar errores rápido
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/oramar/icons/icon-next.png")));

        Object[] opciones = { OPCION_CONVERSOR, OPCION_SALIR };

        // El JOptionPane ya es suficientemente visual, el JPanel 'Color.GREEN'
        // no se está aplicando al diálogo a menos que lo metas dentro.
        Object elegirOpcion = JOptionPane.showInputDialog(
                null,
                "Seleccione una opción de conversión:",
                "Menú Principal - Orliluq Conversor",
                JOptionPane.QUESTION_MESSAGE,
                icon,
                opciones,
                opciones[0]
        );

        // Control de flujo: Si es null, el usuario cerró la ventana
        if (elegirOpcion == null || elegirOpcion.equals(OPCION_SALIR)) {
            salirDelSistema();
        } else if (elegirOpcion.equals(OPCION_CONVERSOR)) {
            Divisas conversorDivisas = new Divisas();
            conversorDivisas.convertirDivisa();
        }
    }

    private void salirDelSistema() {
        JOptionPane.showMessageDialog(null, "Cerrando la aplicación...");
        System.exit(0); // Importante para cerrar procesos en segundo plano
    }
}