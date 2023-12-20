package memoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase Memoria simula la asignación de memoria mediante particiones y
 * gestiona la lista de procesos en espera.
 */
public class Memoria {
    private int tamanoMemoria; // Tamaño total de la memoria
    private List<Particion> particiones; // Lista de particiones de memoria
    // private List<Proceso> listaEspera; // Lista de procesos en espera, 
    // debe validar que el proceso alcance en una particion para dejarlo en la lista de espera sino no se guarda en espera

    /**
     * Constructor de la clase Memoria. Inicializa el tamaño de la memoria y las
     * particiones de manera aleatoria.
     */
    public Memoria() {
        Random random = new Random();
        // Numero aleatorio entre 1000 y 2000 para la memoria total
        tamanoMemoria = random.nextInt(1001) + 1000;

        particiones = new ArrayList<>();
        // listaEspera = new ArrayList<>(); // Inicializar lista de espera

        // Particion para el sistema operativo
        particiones.add(new Particion("Sistema Operativo", 200));

        // Numero de partiviones entero aleatorio entre 2 y 10
        int numParticiones = random.nextInt(9) + 2;

        // Se resta el espacio total - 200 del sistema operativo
        int espacioRestante = tamanoMemoria - 200;

        // Crear particiones aleatorias
        for (int i = 0; i < numParticiones - 1; i++) {
            int tamanoParticion = random.nextInt(espacioRestante / 2) + 1;
            particiones.add(new Particion("Partición " + (i + 1), tamanoParticion));
            espacioRestante -= tamanoParticion;
        }

        // Crear la última partición con el espacio restante
        particiones.add(new Particion("Partición " + numParticiones, espacioRestante));
    }

    /**
     * Obtiene el tamaño total de la memoria.
     * 
     * @return Tamaño total de la memoria.
     */
    public int getTamanoMemoria() {
        return tamanoMemoria;
    }

    /**
     * Obtiene la lista de particiones de memoria.
     * 
     * @return Lista de particiones de memoria.
     */
    public List<Particion> getParticiones() {
        return particiones;
    }

    /**
     * Intenta asignar memoria a un proceso en alguna partición disponible.
     * 
     * @param proceso Proceso que se intentará asignar a la memoria.
     * @return true si se asigna correctamente, false si no hay suficiente espacio y
     *         se agrega a la lista de espera.
     */

    public boolean asignarMemoriaProceso(Proceso proceso) {
        for (Particion particion : particiones) {
            // Se valida que la partivion no sea del sist. operativo y que no esté ocupada
            if (!particion.estaOcupada() && !particion.getNombre().equals("Sistema Operativo")) {
                if (particion.getTamano() >= proceso.getTamano()) {
                    particion.asignarProceso(proceso);

                    System.out.println("Alcanza el tamaño particion:" + particion.getTamano());
                    System.out.println("Tamaño proceso:" + proceso.getTamano());
                    return true;
                }
            }
        }

        // Si no hay suficiente espacio o todas las particiones están ocupadas
        // if (todasParticionesOcupadas()) {
        //     System.out.println("No hay particiones libres.");
        //     System.out.println("Se debe implementar lista de espera.");
        // }

        return false;
    }

    /**
     * Libera la memoria de las particiones cuyos procesos han terminado su tiempo
     * de ejecución.
     */
    public void liberarMemoria() {
        for (Particion particion : particiones) {
            if (particion.getProcesoAsignado() != null && particion.getProcesoAsignado().getTiempoRestante() == 0) {
                particion.liberar();
            }
        }
    }

    /**
     * Reduce los tiempos restantes de ejecución de los procesos en las particiones
     * y asigna memoria a los procesos en espera.
     */
    public void reducirTiemposRestantes() {
        // Reducir los tiempos restantes de ejecución de los procesos en las particiones
        for (Particion particion : particiones) {
            particion.reducirTiempoRestante();
        }

        // Verificar si hay procesos en espera y tratar de asignarles memoria
        // for (Proceso procesoEspera : new ArrayList<>(listaEspera)) {
        // if (asignarMemoriaProceso(procesoEspera)) {
        // // Si se asignan en memoria se sacan de la cola
        // listaEspera.remove(procesoEspera);
        // }
        // }
    }

    public boolean todasParticionesOcupadas() {
        // Es para la lista de espera
        for (Particion particion : particiones) {
            if (!particion.estaOcupada() && !particion.getNombre().equals("Sistema Operativo")) {
                return false; // Si al menos una partición no está ocupada, retorna false
            }
        }
        return true; // Todas las particiones están ocupadas
    }

    /**
     * Agrega un proceso directamente a la lista de espera.
     * 
     * @param proceso Proceso que se agregará a la lista de espera.
     */
    // public void agregarProcesoEspera(Proceso proceso) {
    //     listaEspera.add(proceso);
    // }

    // Retorna los procesos que están en espera
    // public List<Proceso> getProcesosEnEspera() {
    //     return listaEspera;
    // }

    // public void removerProcesoEspera(Proceso proceso) {
    //     listaEspera.remove(proceso);
    // }

}
