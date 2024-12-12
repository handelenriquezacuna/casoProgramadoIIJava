/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoprogramadojavaii;

import javax.swing.JOptionPane;

/**
 *
 * @author hande
 */
public class gestionReservasHotel {

    private int pisos = 5;
    private int habitacionesPorPiso = 5;
    private gestionReservasHotel[] registroReservas;

    private String roomNum;
    private String habitacionEstado;
    private String estado;
    private String habitacionTipo;
    private double precio;

    /*
    Precargar datos de las habitaciones
     */
    public void precargarDatos() {
        for (int i = 1; i <= pisos; i++) {
            double basePrecio = 30 + (i - 1) * 10;
            for (int j = 1; j <= habitacionesPorPiso; j++) {
                String baseRoomNum = Integer.toString(i * 10);
                String strRoom = Integer.toString(j);
                boolean isEven = (j % 2) == 0;

                String tipoHabitacion = isEven ? "Doble" : "Simple";
                double precioHabitacion = isEven ? basePrecio + 20 : basePrecio;

                /*
                Generar número de habitación y registrar en el arreglo
                 */
                String roomNum = baseRoomNum + strRoom;
                registroReservas[(i - 1) * habitacionesPorPiso + (j - 1)]
                        = new gestionReservasHotel(roomNum, "Libre", "Limpia", tipoHabitacion);
                registroReservas[(i - 1) * habitacionesPorPiso + (j - 1)].setPrecio(precioHabitacion);
            }
        }
    }

    public void generarResumenHotel() {
        int totalHabitaciones = pisos * habitacionesPorPiso;
        int libres = 0;
        int ocupadas = 0;
        int sucias = 0;
        double gananciaActual = 0.0;

        /*
        Genera el estado de la habitacion y su respectivo resumen
        */
        for (gestionReservasHotel reserva : registroReservas) {
            if (reserva != null) {
                switch (reserva.getHabitacionEstado().toLowerCase()) {
                    case "libre":
                        libres++;
                        break;
                    case "ocupada":
                        ocupadas++;
                        gananciaActual += reserva.getPrecio(); 
                        break;
                    case "sucia":
                        sucias++;
                        break;
                }
            }
        }

        /*
        Calcular porcentajes
        */ 
        double porcentajeLibres = (libres * 100.0) / totalHabitaciones;
        double porcentajeOcupadas = (ocupadas * 100.0) / totalHabitaciones;
        double porcentajeSucias = (sucias * 100.0) / totalHabitaciones;

        /*
        Imprime y Construye resumen
        */ 
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Estado del Hotel:\n")
                .append("Total de habitaciones: ").append(totalHabitaciones).append("\n")
                .append("Habitaciones libres: ").append(libres).append(" (").append(String.format("%.2f", porcentajeLibres)).append("%)\n")
                .append("Habitaciones ocupadas: ").append(ocupadas).append(" (").append(String.format("%.2f", porcentajeOcupadas)).append("%)\n")
                .append("Habitaciones sucias: ").append(sucias).append(" (").append(String.format("%.2f", porcentajeSucias)).append("%)\n")
                .append("Ganancia actual del hotel: $").append(String.format("%.2f", gananciaActual)).append("\n");

        JOptionPane.showMessageDialog(null, resumen.toString(), "Resumen del Estado del Hotel", JOptionPane.INFORMATION_MESSAGE);
    }

    public void imprimirEstadoHotel() {
        StringBuilder stringBuilt = new StringBuilder();
        stringBuilt.append("HOTEL SC-202_G1\n\n");

        // Encabezado de la tabla
        stringBuilt.append("         Habitación   Habitación   Habitación   Habitación   Habitación\n");
        stringBuilt.append("Piso     1            2            3            4            5\n");
        stringBuilt.append("--------------------------------------------------------------\n");

        // Iterar del piso más alto al más bajo
        for (int i = pisos; i >= 1; i--) {
            stringBuilt.append("Piso ").append(i).append(" | ");

            for (int j = 1; j <= habitacionesPorPiso; j++) {
                String roomNum = (i * 100 + j) + "";
                gestionReservasHotel reserva = registroReservas[(i - 1) * habitacionesPorPiso + (j - 1)];

                if (reserva != null) {
                    stringBuilt.append(reserva.getRoomNum()).append(" ")
                            .append(reserva.getHabitacionEstado()).append(" ")
                            .append(reserva.getHabitacionTipo()).append(" ")
                            .append(reserva.getPrecio()).append("$");
                } else {
                    stringBuilt.append("N/A");
                }

                // Separador entre habitaciones
                if (j < habitacionesPorPiso) {
                    stringBuilt.append("   |   ");
                }
            }
            stringBuilt.append("\n"); // Nueva línea para cada piso
        }

        /*
        Mostrar el estado del hotel en un cuadro de diálogo
         */ 
        JOptionPane.showMessageDialog(null, stringBuilt.toString(), "Estado del Hotel", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    Método para modificar una reserva
    */ 
    public void modificarReserva() {
        String idHabitacion = JOptionPane.showInputDialog("Ingrese el número de la habitación para modificar:");
        boolean reservaEncontrada = false;

        for (int i = 0; i < registroReservas.length; i++) {
            if (registroReservas[i] != null && registroReservas[i].getRoomNum().equals(idHabitacion)) {
                reservaEncontrada = true;
                JOptionPane.showMessageDialog(null, "Reserva encontrada: " + registroReservas[i].toString());

                /*
                Modificar el estado de la habitación
                */ 
                registroReservas[i].setHabitacionEstado(JOptionPane.showInputDialog("¿Está Libre/Ocupada la habitación?"));
                registroReservas[i].setEstado(JOptionPane.showInputDialog("¿Está Limpia/Sucia la habitación?"));
                registroReservas[i].setHabitacionTipo(JOptionPane.showInputDialog("¿Es Simple/Doble la habitación?"));
                String ifAnswer = JOptionPane.showInputDialog("¿Desea modificar el precio? (Y/N):");
                if (ifAnswer.equalsIgnoreCase("Y")) {
                    registroReservas[i].setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio:")));
                }

                JOptionPane.showMessageDialog(null, "Reserva modificada: " + registroReservas[i].toString());
                break;
            }
        }

        if (!reservaEncontrada) {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva para la habitación: " + idHabitacion);
        }
    }

    /*
    Constructores
     */
    public gestionReservasHotel() {
        registroReservas = new gestionReservasHotel[pisos * habitacionesPorPiso];
        precargarDatos();
    }

    public gestionReservasHotel(String roomNum, String habitacionEstado, String estado, String habitacionTipo) {
        this.roomNum = roomNum;
        this.habitacionEstado = habitacionEstado;
        this.estado = estado;
        this.habitacionTipo = habitacionTipo;
    }

    @Override
    public String toString() {
        return "Habitación " + roomNum + " (" + habitacionTipo + ", " + habitacionEstado + ", " + estado + ") - " + precio + "$";
    }

    /*
    Getters & setters
     */
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getHabitacionEstado() {
        return habitacionEstado;
    }

    public void setHabitacionEstado(String habitacionEstado) {
        this.habitacionEstado = habitacionEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHabitacionTipo() {
        return habitacionTipo;
    }

    public void setHabitacionTipo(String habitacionTipo) {
        this.habitacionTipo = habitacionTipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
