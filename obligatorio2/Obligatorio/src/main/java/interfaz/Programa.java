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
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Josefina Sucunza (258389) y Ignacio Badano (320966)
 */
public class Programa {

    public static void main(String args[]) {
       
        try {
            System.setOut(new PrintStream(System.out,true,StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        
        
         Sistema modelo=new Sistema();
         Scanner teclado=new Scanner(System.in);
       /* modelo.crearJuego();
        Juego juego=modelo.getJuego();
        Tablero tablero=juego.getTablero();
        
        Interfaz.mostrar(tablero);
        while(true){
            String cadenaCoordenada=Interfaz.solicitarCoordenada();
            Coordenada coordenada=new Coordenada(cadenaCoordenada);
            String respuesta=modelo.mover(coordenada);

            Interfaz.mostrar(tablero);

           
            if(!respuesta.equals(""))
            {
                System.out.println(respuesta);
            }
        }
        */
       
       
        
      int opcion;
        Interfaz.menu();
        opcion = teclado.nextInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    Interfaz.agregarJugador(modelo);
                    break;
                case 2:
                    Interfaz.configuracion(modelo);
                    break;

                case 3:
                    Interfaz.comenzar(modelo);
                    break;
                case 4:
                    Interfaz.listadoRanking(modelo);
                case 0:

                    break;
                default:
                    System.out.println("Opcion es desconocida");

            }
            Interfaz.menu();
            opcion = teclado.nextInt();

        }
        System.out.println("Hasta luego");
    }

}