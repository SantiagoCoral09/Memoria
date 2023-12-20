package memoria;

// Clase para manejar las partiiciones que tenga la memoria

public class Particion {
    // Atributos de la clase Particion
    private String nombre;          // Nombre de la partición
    private int tamano;             // Tamaño de la partición
    private Proceso procesoAsignado; // Proceso asignado a la partición

    // Constructor de la clase Particion
    // Se recibe un nombre y un tamaño para inicializar una nueva partición
    public Particion(String nombre, int tamano) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.procesoAsignado = null; // Inicialmente, no hay proceso asignado
    }

    // Método que devuelve true si la partición está ocupada por un proceso
    public boolean estaOcupada() {
        return procesoAsignado != null;
    }

    // Método para asignar un proceso a la partición
    public boolean asignarProceso(Proceso proceso) {
        // Verifica si la partición está libre y si el tiempo restante del proceso cabe en la partición
        if (!estaOcupada() && proceso.getTiempoRestante() <= tamano) {
            procesoAsignado = proceso;
            return true; // Devuelve true si el proceso se asigna correctamente
        }
        return false; // Devuelve false si no se puede asignar el proceso a la partición
    }

    // Método para liberar la partición, eliminando el proceso asignado
    public void liberar() {
        this.procesoAsignado = null;
    }

    // Método para obtener el nombre de la partición
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el tamaño de la partición
    public int getTamano() {
        return tamano;
    }

    // Método para obtener el proceso asignado a la partición
    public Proceso getProcesoAsignado() {
        return procesoAsignado;
    }

    // Método para obtener el tiempo restante del proceso asignado (si hay uno)
    public int getTiempoRestante() {
        // Se verifica si hay proceso asignado (!=null) ppara devolver el tiempo restante
        // Sino se devuelve cero : 0 
        return procesoAsignado != null ? procesoAsignado.getTiempoRestante() : 0;
    }

    // Método para reducir en 1 el tiempo restante del proceso asignado (si hay uno)
    public void reducirTiempoRestante() {
        if (procesoAsignado != null) {
            procesoAsignado.reducirTiempoRestante();
            if (procesoAsignado.getTiempoRestante() == 0) {
                procesoAsignado = null; // Liberar la partición si el tiempo restante llega a 0
            }
        }
    }
}
