/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;

/**
 *
 * @author Josefina Sucunza (258389) y Ignacio Badano (320966)
 */
public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private int edad;
    private int ganadas;
    private int rachas;
    private int mejorRacha;
    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        ganadas=0;
        rachas=0;
        mejorRacha=0;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        edad=18;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public int getRachas() {
        return rachas;
    }

    public void setRachas(int rachas) {
        this.rachas = rachas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", edad=" + edad + '}'; 
    }


    @Override
    public boolean equals(Object obj) {
        return ((Jugador)obj).getNombre().equalsIgnoreCase(getNombre());
    }

   public void gano(){
       ganadas++;
       rachas++;
   }
   
   public void perdio()
   {
       if(rachas>mejorRacha)
           mejorRacha=rachas;
       rachas=0;
   }

    @Override
    public int compareTo(Jugador o) {
       return o.getGanadas()-getGanadas();
    }
   
    
    
}