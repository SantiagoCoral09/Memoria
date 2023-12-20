package memoria;

import java.util.Random;

// Clase para manejar los procesos en memmoria. 
// Se necesita un nombre y el tiempo que permanece en memoria
public class Proceso {
    private String nombre;
    private int tiempoRestante;
    private int tamano;

    // Constructor de la clase Proceso
    // Se recibe el nombre del proceso como parámetro al crear una nueva instancia
    public Proceso(String nombre) {
        // Se asigna el nombre del proceso
        this.nombre = nombre;
        // Se asigna un tiempo restante aleatorio entre 5 y 10 segundos
        // Se puede cambiar el tiempos si es neccesarioo
        // ¨Por ejemplo para que este entre 10 y 15 segundos un proceso sería:::
        // ... =new Random().nextInt(6)+10;
        // >La parte de .nextInt(bound 6) genera un aleatorio entre 0 y 5
        // Luego al sumarle +5 se genera un aleatorio entre 5 y 10
        this.tiempoRestante = new Random().nextInt(6) + 20;
        // Tamaño aleatorio entre 5 y 300 (puedes ajustar estos valores según tus necesidades)
        this.tamano = new Random().nextInt(296) + 5;
    }

    // Método para obtener el nombre del proceso
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el tiempo restante del proceso
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTamano() {
        return tamano;
    }


    // Método para reducir en 1 el tiempo restante del proceso
    public void reducirTiempoRestante() {
        tiempoRestante--;
    }
}
