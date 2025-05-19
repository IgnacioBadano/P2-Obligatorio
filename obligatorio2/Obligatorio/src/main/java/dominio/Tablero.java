/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Josefina Sucunza (258389) y Ignacio Badano (320966)
 */
public class Tablero {

    private int largoElatico;
    private char[][] matriz;

    public Tablero(int largoElatico) {
        this.largoElatico = largoElatico;
        inicializar();
    }

    public void inicializar() {
        matriz = new char[7][13];
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3 - fila; columna++) {
                matriz[fila][columna] = 'A';
            }
        }

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 10 + fila; columna < 13; columna++) {
                matriz[fila][columna] = 'A';
            }
        }
        int cantidad = 1;
        for (int fila = 4; fila < 7; fila++) {
            for (int columna = 0; columna < cantidad; columna++) {
                matriz[fila][columna] = 'A';
            }
            cantidad++;
        }
        cantidad = 0;
        for (int fila = 4; fila < 7; fila++) {
            for (int columna = 12 - cantidad; columna < 13; columna++) {
                matriz[fila][columna] = 'A';
            }
            cantidad++;
        }
        boolean mostrar = true;
        for (int fila = 0; fila < 7; fila++) {
            for (int columna = 0; columna < 13; columna++) {
                if (matriz[fila][columna] != 'A') {
                    if (mostrar) {
                        matriz[fila][columna] = 'V';
                    } else {
                        matriz[fila][columna] = 'A';
                    }
                    mostrar = !mostrar;
                }
            }
            mostrar = true;
        }
    }

    public int getLargoElatico() {
        return largoElatico;
    }

    public void setLargoElatico(int largoElatico) {
        this.largoElatico = largoElatico;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        return "Tablero{" + "largoElatico=" + largoElatico + ", matriz=" + matriz + '}';
    }

    public String mover(Coordenada coordenada) {

        int filaAux = coordenada.getFilaOrigen();
        int columnaAux = coordenada.getColumnaOrigen();
        switch (coordenada.getSentido()) {
            case 'Q':
                //TAREA : Poner las condiciones de los demas
                if (filaAux - (coordenada.getCuantas() + 1) >= 0 && columnaAux - (coordenada.getCuantas() + 1) >= 0) {
                    for (int i = 1; i <= coordenada.getCuantas() + 1; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }
                        filaAux--;
                        columnaAux--;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
            case 'E':
                if ((filaAux - coordenada.getCuantas()) >= 0 && (columnaAux + coordenada.getCuantas() < 13)) {
                    for (int i = 1; i <= coordenada.getCuantas() + 1; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }
                        filaAux--;
                        columnaAux++;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
            case 'A':
                if (columnaAux - (coordenada.getCuantas() * 2) >= 0) {
                    for (int i = 1; i <= coordenada.getCuantas() * 2; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }

                        columnaAux--;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
            case 'D':
                if (columnaAux + (coordenada.getCuantas() * 2) < 13) {
                    for (int i = 1; i <= coordenada.getCuantas() * 2; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }

                        columnaAux++;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
            case 'Z':
                if ((filaAux + coordenada.getCuantas()) < 13 && ((columnaAux - coordenada.getCuantas()) >= 0)) {
                    for (int i = 1; i <= coordenada.getCuantas() + 1; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }
                        filaAux++;
                        columnaAux--;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
            case 'C':
                if (((filaAux + coordenada.getCuantas()) < 13) && ((columnaAux + coordenada.getCuantas()) < 13)) {
                    for (int i = 1; i <= coordenada.getCuantas() + 1; i++) {
                        if (matriz[filaAux][columnaAux] != 'A') {
                            matriz[filaAux][columnaAux] = 'E';
                        }
                        filaAux++;
                        columnaAux++;
                    }
                } else {
                    return "Se sale del tablero";
                }
                break;
        }
        return "";

    }

    public boolean coordenadaValida(int filaOrigen, int columnaOrigen) {
        if (matriz[filaOrigen][columnaOrigen] == 'A') {
            return false;
        } else {
            return true;
        }
    }

    public boolean esTriangulo(Coordenada coordenada) {
        return false;
    }

    public boolean existeElastico(int filaOrigen, int columnaOrigen) {
        if (matriz[filaOrigen][columnaOrigen] == 'E') {
            return true;
        } else {
            return false;
        }
    }

}