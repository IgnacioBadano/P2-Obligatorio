/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author joses y ignacio
 */
public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    private Juego juego;

    public Sistema() {
        listaJugadores=new ArrayList<>();
        listaJugadores.add(new Jugador("Josefina",45));
        listaJugadores.add(new Jugador("Francisco",45));
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }
    public void crearJuego(Jugador blanco, Jugador negro){
        juego=new Juego(blanco,negro);
    }
    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public boolean agregar(Jugador jugador) {
        boolean seInserto=false;
        if(!listaJugadores.contains(jugador))
        {
             listaJugadores.add(jugador);
             seInserto=true;
        }
        return seInserto;
       
        
    }

    public void configuracion(boolean ubicacion, int largo, int cantidadBanda, int cantidadPantalla) {
        Juego.cantidadBanda=cantidadBanda;
        Juego.largoBanda=largo;
        Juego.cantidadPantalla=cantidadPantalla;
        Juego.cualquierLugar=ubicacion;
    }

    public String mover(Coordenada coordenada) {
        return juego.mover(coordenada);
    }

    public boolean hayJugadores() {
        if(listaJugadores.size()>1)
        {
            return true;
        }
        else{
            return false;
        }
    }

  
}
