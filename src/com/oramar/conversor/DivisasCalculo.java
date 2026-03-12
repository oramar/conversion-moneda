package com.oramar.conversor;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DivisasCalculo {
    private final ImageIcon icon = new ImageIcon(getClass().getResource("/com/oramar/icons/icon-divisa.png"));

    private static final Map<String, String[]> CONVERSIONES = Map.of(
            "ARS a USD", new String[]{"ARS", "USD"},
            "BRL a USD", new String[]{"BRL", "USD"},
            "COP a USD", new String[]{"COP", "USD"},
            "USD a ARS", new String[]{"USD", "ARS"},
            "USD a BRL", new String[]{"USD", "BRL"},
            "USD a COP", new String[]{"USD", "COP"},
            "USD a VES", new String[]{"USD", "VES"},
            "VES a USD", new String[]{"VES", "USD"}
    );

    public void resultadoConversion(String tipoDivisa, String cantidadAConvertir) {
        String[] monedas = CONVERSIONES.get(tipoDivisa);
        try {
            double cantidad = Double.parseDouble(cantidadAConvertir.replace(",", "."));
            double cantidadConvertida = new DivisasApi().get(String.valueOf(cantidad), monedas[0], monedas[1]);

            if (cantidadConvertida == 0) {
                JOptionPane.showMessageDialog(null, "Error: No se pudo realizar la conversión (¿Revisó la API Key?)");
                return;
            }

            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String mensaje = String.format("%.2f %s equivalen a %.2f %s", cantidad, monedas[0], cantidadConvertida, monedas[1]);

            JOptionPane.showMessageDialog(null, mensaje, "Resultado - " + fecha, JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido.");
        }
    }
}
