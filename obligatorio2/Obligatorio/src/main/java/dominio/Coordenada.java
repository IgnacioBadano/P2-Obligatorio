/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author joses y ignacio
 */
public class Coordenada {
    private int filaOrigen;
    private int columnaOrigen;

    private String coordenada;
    private char sentido;
    private int cuantas;
    public Coordenada(String coordenada) {
        this.coordenada = coordenada.toUpperCase();
        calcular();
    }

    private void calcular() {
        char letra=coordenada.charAt(0);
        columnaOrigen=((int)letra)-65;//Calculo con ascii. A-65 B-66..
        filaOrigen=Integer.parseInt(coordenada.charAt(1)+"")-1;
        sentido=coordenada.charAt(2);
        cuantas=Integer.parseInt(coordenada.charAt(3)+"")+1;

       
        
        
    }

    public int getFilaOrigen() {
        return filaOrigen;
    }

    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }

    public int getColumnaOrigen() {
        return columnaOrigen;
    }

    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }


    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public char getSentido() {
        return sentido;
    }

    public void setSentido(char sentido) {
        this.sentido = sentido;
    }

    public int getCuantas() {
        return cuantas;
    }

    public void setCuantas(int cuantas) {
        this.cuantas = cuantas;
    }
    
    
}
