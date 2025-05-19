/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import dominio.Coordenada;
import dominio.Juego;
import dominio.Jugador;
import dominio.Sistema;
import dominio.Tablero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josefina Sucunza (258389) y Ignacio Badano (320966)
 */
public class Interfaz {

    private static Scanner teclado = new Scanner(System.in);
    private static final String[] COLORES={"\u001B[0m","\u001B[31m","\u001B[32m","\u001B[33m","\u001B[34m","\u001B[35m","\u001B[36m"};
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1 - Registrar jugadores");
        System.out.println("2 - Configurar la partida");
        System.out.println("3 - Comenzar la partida");
        System.out.println("0 - Salir");
        System.out.print("Opcion : ");
    }

    public static void agregarJugador(Sistema modelo) {
        System.out.println("Registrar jugador");
        System.out.print("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese su edad : ");
        int edad = teclado.nextInt();
        teclado.nextLine();
        if (nombre.isBlank()) {
            System.out.println("El nombre es obligatorio");
        } else {
            if (edad < 0 || edad > 100) {
                System.out.println("Error, la edad debe estar entre 0 a 100");
            } else {
                Jugador jugador = new Jugador(nombre, edad);
                boolean sePudo = modelo.agregar(jugador);
                if (sePudo) {
                    System.out.println("Se registro correctamente");
                } else {
                    System.out.println("No se registro, nombre repetido");
                }
            }
        }

    }

    public static void configuracion(Sistema modelo) {
        System.out.println("Configuracion del tablero");
        boolean ubicacion = opcionUbicacion();
        int largo = opcionLargo();
        int cantidadBanda = opcionCantidadBanda();
        int cantidadPantalla = opcionCantidadPantalla();

        modelo.configuracion(ubicacion, largo, cantidadBanda, cantidadPantalla);
        System.out.println("Se modifico correctamente");

    }

    public static void comenzar(Sistema modelo) {
        if (modelo.hayJugadores()) {
            ArrayList<Jugador> jugadores = modelo.getListaJugadores();
            mostrarJugadores(jugadores);

            Jugador blanco = seleccionarBlanco(jugadores);
            Jugador negro = seleccioneNegro(jugadores, blanco);
            modelo.crearJuego(blanco, negro);
            Juego juego = modelo.getJuego();
            Interfaz.mostrar(juego.getTablero());
            System.out.println("El turno es de " + juego.getJugadorTurno().getNombre());
            String cadenaCoordenada = "";
            while (!cadenaCoordenada.equalsIgnoreCase("X") && juego.continuar()) {
                cadenaCoordenada = Interfaz.solicitarCoordenada();
                switch (cadenaCoordenada) {
                    case "X":
                        juego.salir();
                        break;
                    case "H":
                        mostrarHistorial(juego);
                        break;
                    default:
                        Coordenada coordenada = new Coordenada(cadenaCoordenada);
                        String respuesta = modelo.mover(coordenada);

                        Interfaz.mostrar(juego.getTablero());
                        System.out.println("Cantidad blanco:" + juego.getTriangulosGanadosBlanco());
                        System.out.println("Cantidad negro:" + juego.getTriangulosGanadosNegro());
                        System.out.println("El turno es de " + juego.getJugadorTurno().getNombre());
                        if (!respuesta.equals("")) {
                            System.out.println(respuesta);
                        }
                }

            }
            if (juego.getGanador() != null) {
                fuegosArtificiales();
                System.out.println("El ganador es " + juego.getGanador().getNombre());
            } else {
                System.out.println("Es empate");
            }
        } else {
            System.out.println("No hay suficiente jugadores para jugar");
        }
    }

    private static boolean opcionUbicacion() {
        System.out.println("Ubicacion: ");
        System.out.println("\t1 - Contacto banda anterior ");
        System.out.println("\t2 - Cualquier lugar ");
        System.out.print("Opcion : ");
        int opcion = teclado.nextInt();
        while (opcion < 1 || opcion > 2) {
            System.out.println("Error, es 1 o 2");
            System.out.println("Ubicacion: ");
            System.out.println("\t1 - Contacto banda alguna banda ");
            System.out.println("\t2 - Cualquier lugar ");
            System.out.print("Opcion : ");
            opcion = teclado.nextInt();

        }
        boolean cualquierLugar = true;
        if (opcion == 1) {
            cualquierLugar = false;
        }
        return cualquierLugar;
    }

    private static int opcionLargo() {
        System.out.println("Ingrese el largo de las bandas");
        int largo = teclado.nextInt();
        while (largo < 1 || largo > 4) {
            System.out.println("Error, el largo es entre 1 a 4");
            System.out.println("Ingrese el largo de las bandas");
            largo = teclado.nextInt();
        }
        return largo;
    }

    private static int opcionCantidadBanda() {
        System.out.println("Ingrese la cantidad de banda");
        int cantidad = teclado.nextInt();
        while (cantidad < 1) {
            System.out.println("Error, la cantidad de banda tiene que ser mayor o igual 1");
            System.out.println("Ingrese la cantidad de banda");
            cantidad = teclado.nextInt();
        }
        return cantidad;
    }

    private static int opcionCantidadPantalla() {
        System.out.println("Ingrese el cantidad de pantalla");
        int cantidad = teclado.nextInt();
        while (cantidad < 1 || cantidad > 4) {
            System.out.println("Error, el largo es entre 1 a 4");
            System.out.println("Ingrese el cantidad de pantalla");
            cantidad = teclado.nextInt();
        }
        return cantidad;
    }

    public static void mostrar(Tablero tablero) {
        System.out.println("A B C D E F G H I J K L M");
        System.out.println("");

        for (int fila = 0; fila < 7; fila++) {
            //Linea de las horizontales
            for (int columna = 0; columna < 13; columna++) {
                lineaHorizontal(tablero, fila, columna);

            }
            System.out.println("");
            //Lineas diagonales
            for (int columna = 0; columna < 13; columna++) {
                if (tablero.getMatriz()[fila][columna] == 'E') {

                    if (columna + 1 < 13 && fila + 1 < 7 && tablero.getMatriz()[fila + 1][columna + 1] == 'E') {

                        System.out.print(" \\ ");
                    } else {
                        System.out.print("  ");
                    }
                } else {
                    if ((columna + 1) < 13 && (fila + 1) < 7 && tablero.getMatriz()[fila + 1][columna] == 'E' && tablero.getMatriz()[fila][columna + 1] == 'E') {

                        System.out.print(" /");
                    } else {
                        System.out.print("  ");
                    }

                }
            }
            System.out.println("");

        }
    }

    public static String solicitarCoordenada() {
        System.out.print("Ingrese coordenada:");
        String alfabeto = "ABCDEFGHIJKLM";
        char letra = ' ';
        int fila = 0;
        char sentido = ' ';
        int largo = 0;
        String cadena = teclado.next().toUpperCase().trim();
        if (cadena.equalsIgnoreCase("H") || cadena.equals("X")) {
            return cadena;
        } else {
            if (cadena.length() == 4) {
                letra = cadena.charAt(0);
                fila = Integer.parseInt(cadena.charAt(1) + "");
                //Hacer control sentido y largo de cantidad elastico
                sentido = cadena.charAt(2);
                largo = Integer.parseInt(cadena.charAt(3) + "");
            }
            while (cadena.length() != 4 || !alfabeto.contains(letra + "") || fila < 1 || fila > 8 || largo < 1 || largo > 4 || !"ADZCEQ".contains(sentido + "")) {
                if (cadena.length() != 4) {
                    System.out.println("El formato invalido");
                } else if (!alfabeto.contains(letra + "")) {
                    System.out.println("La letra de columna no existe");

                } else {
                    if (fila < 1 || fila > 8) {
                        System.out.println("Error, las filas es entre 1 a 7");
                    } else if (largo < 1 || largo > 4) {
                        System.out.println("El largo deberia ser entre 1 a 4");
                    } else {
                        System.out.println("Error en la letra del sentido son solo valida ADEQZC");
                    }
                }
                System.out.print("Ingrese coordenada:");
                cadena = teclado.next().toUpperCase().trim();
                letra = cadena.charAt(0);
                fila = Integer.parseInt(cadena.charAt(1) + "");
                sentido = cadena.charAt(2);
            }
            return cadena;
        }
    }

    private static void lineaHorizontal(Tablero tablero, int fila, int columna) {
        if (tablero.getMatriz()[fila][columna] == 'A') {
            if ((columna > 0 && columna + 1 < 13 && (tablero.getMatriz()[fila][columna - 1] == 'E' && tablero.getMatriz()[fila][columna + 1] == 'E'))) {
                System.out.print("--");
            } else {
                System.out.print("  ");
            }
        } else {
            System.out.print("*");
            if (columna + 2 < 13 && tablero.getMatriz()[fila][columna] == 'E' && tablero.getMatriz()[fila][columna + 2] == 'E') {
                System.out.print("-");
            } else {
                System.out.print(" ");
            }
        }
    }

    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        System.out.println("Listado de jugadores");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println((1 + i) + "-" + jugador.getNombre());

        }
    }

    private static Jugador seleccionarBlanco(ArrayList<Jugador> jugadores) {
        System.out.print("Selecione el jugador blanco: ");
        int opcion = teclado.nextInt();
        while (opcion < 1 || opcion > jugadores.size()) {
            System.out.println("Error, el numero es entre 1 a " + jugadores.size());
            System.out.print("Selecione el jugador blanco: ");
            opcion = teclado.nextInt();
        }
        return jugadores.get(opcion - 1);
    }

    private static Jugador seleccioneNegro(ArrayList<Jugador> jugadores, Jugador blanco) {
        System.out.print("Selecione el jugador negro: ");
        int opcion = teclado.nextInt();
        while (opcion < 1 || opcion > jugadores.size() || jugadores.get(opcion - 1).equals(blanco)) {
            if (opcion < 1 || opcion > jugadores.size()) {
                System.out.println("Error, el numero es entre 1 a " + jugadores.size());
            } else {
                System.out.println("Error, ya fue seleccionado el jugador");
            }
            System.out.print("Selecione el jugador negro: ");
            opcion = teclado.nextInt();
        }
        return jugadores.get(opcion - 1);
    }

    // MODIFICADO: Historial único de movimientos y tableros en paralelo
    private static void mostrarHistorial(Juego juego) {
        System.out.println("Historial de movimientos: " + juego.getHistorial());

        if (juego.getHistorial().isEmpty()) return;

        // Lista de tableros tras cada jugada
        ArrayList<Tablero> tableros = new ArrayList<>();

        for (int i = 0; i < juego.getHistorial().size(); i++) {
            Tablero tableroTemp = new Tablero(Juego.getLargoBanda());
            for (int j = 0; j <= i; j++) {
                Coordenada c = new Coordenada(juego.getHistorial().get(j));
                tableroTemp.mover(c);
            }
            tableros.add(tableroTemp);
        }

        imprimirTablerosLadoALado(tableros);
    }

    // Imprime todos los tableros uno al lado del otro, con el mismo formato de mostrar()
    private static void imprimirTablerosLadoALado(ArrayList<Tablero> tableros) {
        // Encabezados
        for (int t = 0; t < tableros.size(); t++) {
            System.out.print("A B C D E F G H I J K L M   ");
        }
        System.out.println();

        // Para cada fila
        for (int fila = 0; fila < 7; fila++) {
            // Linea de las horizontales en paralelo
            for (int t = 0; t < tableros.size(); t++) {
                Tablero tablero = tableros.get(t);
                for (int columna = 0; columna < 13; columna++) {
                    lineaHorizontal(tablero, fila, columna);
                }
                System.out.print("   ");
            }
            System.out.println();
            // Lineas diagonales en paralelo
            for (int t = 0; t < tableros.size(); t++) {
                Tablero tablero = tableros.get(t);
                for (int columna = 0; columna < 13; columna++) {
                    if (tablero.getMatriz()[fila][columna] == 'E') {
                        if (columna + 1 < 13 && fila + 1 < 7 && tablero.getMatriz()[fila + 1][columna + 1] == 'E') {
                            System.out.print(" \\ ");
                        } else {
                            System.out.print("  ");
                        }
                    } else {
                        if ((columna + 1) < 13 && (fila + 1) < 7 && tablero.getMatriz()[fila + 1][columna] == 'E' && tablero.getMatriz()[fila][columna + 1] == 'E') {
                            System.out.print(" /");
                        } else {
                            System.out.print("  ");
                        }
                    }
                }
                System.out.print("   ");
            }
            System.out.println();
        }
    }

    public static void fuegosArtificiales() {
        String[] patrones={"               *       *       ",
                           "            *      *     *     ",
                           "            *          *          *",
                           "                *         *      * ",
                           "       *       *       ",
                           "","",
                           "       \u25CB       \u25CB       ",
                           "    \u25CB     \u25CB     \u25CB     ",
                           "\u25CB          \u25CB          \u25CB",
                           "    \u25CB         \u25CB     \u25CB ",
                           "       \u25CB       \u25CB       "};
        
        
        String colorReset=COLORES[0];
        for(int i=0;i<10;i++)
        {
            try {
                
                for(String linea:patrones){
                    int color=new Random().nextInt(1,6);
                    String colorAzar=COLORES[color];
                    System.out.print(colorAzar);
                    System.out.println(linea);
                }
                Thread.sleep(300);
                System.out.println(colorReset);
            } catch (InterruptedException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void listadoRanking(Sistema modelo) {
        System.out.println("Ranking de jugadores");
        Collections.sort(modelo.getListaJugadores());
        int i=1;
        for(Jugador jugador:modelo.getListaJugadores())
        {
            System.out.println(i+" - "+jugador.getNombre());
            i++;
        }
        
    }
}