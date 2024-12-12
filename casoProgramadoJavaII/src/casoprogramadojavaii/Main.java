/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package casoprogramadojavaii;

import javax.swing.JOptionPane;

/**
 *
 * @author hande
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        gestionReservasHotel hotel = new gestionReservasHotel();
        boolean continuar = true;
        while (continuar) {
            /* Lista Opciones de menu facil manejo */
            String[] opciones = {"Imprimir Estado", "Modificar Reserva", "Generar Resume", "Cerrar Aplicacion"};
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Hotel",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0 -> {
                    hotel.modificarReserva();
                }
                case 1 -> {
                    hotel.imprimirEstadoHotel();
                }
                case 2 -> {
                    hotel.generarResumenHotel();
                }
                case 3 -> {
                    continuar = false; // Rompe el bucle y cierra el programa
                    JOptionPane.showMessageDialog(null, "Cerrando la aplicación. ¡Gracias por usar nuestro sistema!");
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            }
        }

    }
}
