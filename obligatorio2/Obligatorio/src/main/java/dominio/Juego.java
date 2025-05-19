/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Josefina Sucunza (258389) y Ignacio Badano (320966)
 */
public class Juego {

    public static int cantidadTablero = 1;
    public static int cantidadBanda = 10;
    public static int largoBanda = 4;
    public static int cantidadPantalla = 1;
    public static boolean cualquierLugar = true;

    private Tablero tablero;
    private Jugador blanco;
    private Jugador negro;
    private Jugador ganador;
    private int triangulosGanadosBlanco;
    private int triangulosGanadosNegro;
    private int cantidadBandaBlanco;
    private int cantidadBandaNegra;
    private boolean turnoBlanco;
    private ArrayList<String> historial; // Nuevo

    public Juego(Jugador blanco, Jugador negro) {
        this.blanco = blanco;
        this.negro = negro;
        turnoBlanco = true;
        cantidadBandaBlanco = cantidadBanda;
        cantidadBandaNegra = cantidadBanda;
        tablero = new Tablero(largoBanda);
        triangulosGanadosBlanco = 0;
        triangulosGanadosNegro = 0;
        historial = new ArrayList<>();
        ganador=null;
    }

    public static int getCantidadBanda() {
        return cantidadBanda;
    }

    public static void setCantidadBanda(int cantidadBanda) {
        Juego.cantidadBanda = cantidadBanda;
    }

    public static int getLargoBanda() {
        return largoBanda;
    }

    public static void setLargoBanda(int largoBanda) {
        Juego.largoBanda = largoBanda;
    }

    public static int getCantidadPantalla() {
        return cantidadPantalla;
    }

    public static void setCantidadPantalla(int cantidadPantalla) {
        Juego.cantidadPantalla = cantidadPantalla;
    }

    public static boolean isCualquierLugar() {
        return cualquierLugar;
    }

    public static void setCualquierLugar(boolean cualquierLugar) {
        Juego.cualquierLugar = cualquierLugar;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public boolean isTurnoBlanco() {
        return turnoBlanco;
    }

    public void setTurnoBlanco(boolean turnoBlanco) {
        this.turnoBlanco = turnoBlanco;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Jugador getBlanco() {
        return blanco;
    }

    public void setBlanco(Jugador blanco) {
        this.blanco = blanco;
    }

    public Jugador getNegro() {
        return negro;
    }

    public void setNegro(Jugador negro) {
        this.negro = negro;
    }

    public int getTriangulosGanadosBlanco() {
        return triangulosGanadosBlanco;
    }

    public void setTriangulosGanadosBlanco(int triangulosGanadosBlanco) {
        this.triangulosGanadosBlanco = triangulosGanadosBlanco;
    }

    public int getTriangulosGanadosNegro() {
        return triangulosGanadosNegro;
    }

    public void setTriangulosGanadosNegro(int triangulosGanadosNegro) {
        this.triangulosGanadosNegro = triangulosGanadosNegro;
    }

    public int getCantidadBandaBlanco() {
        return cantidadBandaBlanco;
    }

    public void setCantidadBandaBlanco(int cantidadBandaBlanco) {
        this.cantidadBandaBlanco = cantidadBandaBlanco;
    }

    public int getCantidadBandaNegra() {
        return cantidadBandaNegra;
    }

    public void setCantidadBandaNegra(int cantidadBandaNegra) {
        this.cantidadBandaNegra = cantidadBandaNegra;
    }

    public int getCantidadTablero() {
        return cantidadTablero;
    }

    public void setCantidadTablero(int cantidadTablero) {
        this.cantidadTablero = cantidadTablero;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }
    
    public void cambiarTurno() {
        turnoBlanco = !turnoBlanco;
    }

    public String mover(Coordenada coordenada) {
        if (tablero.coordenadaValida(coordenada.getFilaOrigen(), coordenada.getColumnaOrigen())) {
            // Nuevo
            if (cualquierLugar || (historial.size() == 0 || (tablero.existeElastico(coordenada.getFilaOrigen(), coordenada.getColumnaOrigen())))) {
                if (suficienteBandas(coordenada.getCuantas())) {
                    // Nuevo
                    historial.add(coordenada.getCoordenada());
                    String respuesta = tablero.mover(coordenada);
                    if (respuesta.equals("")) {
                        if (tablero.esTriangulo(coordenada)) {
                            aumentarTriangulo();
                        }
                        restarBandas(coordenada.getCuantas());
                        cambiarTurno();
                    }

                    return respuesta;
                } else {
                    return "No hay banda elastica en el comienzo";
                }
            } else {
                return "El jugador no tiene suficiente bandas";
            }
        } else {
            return "Coordenada origen no valida";
        }
    }

    private void restarBandas(int cuantas) {
        if (turnoBlanco) {
            cantidadBandaBlanco = cantidadBandaBlanco - cuantas;
            if(cantidadBandaBlanco==0)
            {
                ganador();
            }

        } else {
            cantidadBandaNegra = cantidadBandaNegra - cuantas;
            if(cantidadBandaNegra==0)
            {
                ganador();
            }
        }
        
        
    }

    private boolean suficienteBandas(int cuantas) {
        if (turnoBlanco && cantidadBandaBlanco - cuantas >= 0) {
            return true;

        } else {
            if (!turnoBlanco && cantidadBandaNegra - cuantas >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void aumentarTriangulo() {
        if (turnoBlanco) {
            triangulosGanadosBlanco++;

        } else {
            triangulosGanadosNegro++;
        }
    }

    public boolean hayBanda() {
        if(cantidadBandaBlanco!=0 && cantidadBandaNegra!=0)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public void salir() {
        if (turnoBlanco) {
            negro.gano();
            blanco.perdio();
            ganador=negro;

        } else {
            blanco.gano();
            negro.perdio();
            ganador=blanco;
        }
    }

    public Jugador getJugadorTurno() {
        if(turnoBlanco)
        {
            return blanco;
        }
        else{
            return negro;
        }
    }

    private void ganador() {
        if(cantidadBandaBlanco>cantidadBandaNegra){
            blanco.gano();
            negro.perdio();
        }
        else{
            if(cantidadBandaNegra>cantidadBandaBlanco)
            {
                negro.gano();
                blanco.perdio();
            }
        }
    }

    public boolean continuar() {
        return cantidadBandaBlanco!=0 && cantidadBandaNegra!=0;
    }
}