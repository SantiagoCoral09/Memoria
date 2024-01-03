package memoria;

import javax.swing.table.DefaultTableModel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InterfazGrafica extends JFrame {
    private Memoria memoria;
    private DefaultTableModel tablaModelo;
    private JTable tabla;
    private JTextArea areaMensajes;
    private Timer temporizador;

    private GraficoParticiones graficoParticiones; // Nueva instancia para el gráfico


    // Para la tabla de procesos en espera
    private DefaultTableModel tablaModeloEspera;
    private JTable tablaEspera;

    public InterfazGrafica() {
        memoria = new Memoria();

        setTitle("Asignación Contigua Estática - Primer Ajuste");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 720); // Ajusta el tamaño del frame

        // Panel principal que contiene toda la interfaz
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior que contiene la información de la memoria
        JPanel panelMemoria = new JPanel(new GridLayout(2, 1));
        JLabel labelInfoM = new JLabel("");
        JLabel labelInfoMemoria = new JLabel("ASIGNACIÓN CONTIGUA ESTÁTICA - PRIMER AJUSTE",
                SwingConstants.CENTER);
        panelMemoria.add(labelInfoM);
        panelMemoria.add(labelInfoMemoria, BorderLayout.NORTH);

        // Panel central que contiene la tabla principal y la tabla de procesos en
        // espera
        // JSplitPane panelCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane panelCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Panel izquierdo para la tabla de particiones y botón
        JPanel panelIzquierdo = new JPanel(new BorderLayout());

        // Texto informativo de la tabla de particiones
        JPanel panelInfoTabla = new JPanel(new GridLayout(9, 1));
        JLabel labelInfoTabla = new JLabel("");
        JLabel labelInfoTabla1 = new JLabel("*** Información de la tabla ***");
        JLabel labelInfoTabla2 = new JLabel("- Partición: Nombre de la partición.");
        JLabel labelInfoTabla3 = new JLabel("- Tamaño: Tamaño total de la partición.");
        JLabel labelInfoTabla4 = new JLabel(
                "- Estado: Estado de la partición, que puede ser 'Libre', 'Ocupada' o 'Reservado' si es la del sistema operativo.");
        JLabel labelInfoTabla5 = new JLabel(
                "- Proceso Asignado: Tamaño. Nombre del proceso asignado a la partición y el tamaño que ocupa en memoria.");
        JLabel labelInfoTabla6 = new JLabel(
                "- Tiempo Restante: Tiempo restante de ejecución del proceso asignado. Si la partición está libre, esta columna estará en cero.");
        JLabel labelInfoTabla7 = new JLabel("");
        JLabel labelInfoTabla8 = new JLabel("Tabla de particiones. Tamaño Total de Memoria: " + memoria.getTamanoMemoria()+".",
                SwingConstants.CENTER);

        panelInfoTabla.add(labelInfoTabla);
        panelInfoTabla.add(labelInfoTabla1);
        panelInfoTabla.add(labelInfoTabla2);
        panelInfoTabla.add(labelInfoTabla3);
        panelInfoTabla.add(labelInfoTabla4);
        panelInfoTabla.add(labelInfoTabla5);
        panelInfoTabla.add(labelInfoTabla6);
        panelInfoTabla.add(labelInfoTabla7);
        panelInfoTabla.add(labelInfoTabla8);

        // Tabla principal
        tablaModelo = new DefaultTableModel(
                new String[] { "Partición", "Tamaño", "Estado", "Proceso:Tamaño", "Tiempo Restante" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(tablaModelo);
        JScrollPane scrollPaneTabla = new JScrollPane(tabla);
        panelIzquierdo.add(panelInfoTabla, BorderLayout.NORTH);
        panelIzquierdo.add(scrollPaneTabla, BorderLayout.CENTER);

        // Botón
        JPanel panelBoton = new JPanel();
        JButton botonAgregarProceso = new JButton("Agregar Proceso");
        botonAgregarProceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProceso();
            }
        });
        panelBoton.add(botonAgregarProceso);
        panelIzquierdo.add(panelBoton, BorderLayout.SOUTH);

        // Espacio entre las dos tablas
        panelIzquierdo.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);

        // Agregar paneles superior, central e inferior al panel principal
        panelPrincipal.add(panelMemoria, BorderLayout.NORTH);
        panelCentral.add(panelIzquierdo);

        // Panel derecho para la tabla de procesos en espera
        JPanel panelDerecho = new JPanel(new BorderLayout());

        // Tabla de procesos en espera
        tablaModeloEspera = new DefaultTableModel(new String[] { "Proceso", "Tamaño" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEspera = new JTable(tablaModeloEspera);
        JScrollPane scrollPaneTablaEspera = new JScrollPane(tablaEspera);
        panelDerecho.add(new JLabel("<html><div style='text-align:center;'>Procesos en Espera</div></html>",
                SwingConstants.CENTER), BorderLayout.NORTH);
        panelDerecho.add(scrollPaneTablaEspera, BorderLayout.CENTER);

        // Agrega el panel derecho al panel central
        panelCentral.add(panelDerecho);

        // Agrega el panel central al panel principal
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Área de mensajes
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        // Envuelve el JTextArea en un JScrollPane
        JScrollPane scrollPaneMensajes = new JScrollPane(areaMensajes);
        scrollPaneMensajes.setPreferredSize(new Dimension(750, 70)); // Dimensiones personalizadas del área de mensajes

        // Nuevo componente para el gráfico
        graficoParticiones = new GraficoParticiones(memoria);
        JScrollPane scrollPaneGrafico = new JScrollPane(graficoParticiones);
        scrollPaneGrafico.setPreferredSize(new Dimension(750, 200)); // Ajusta el tamaño del área del gráfico

        // Panel mensajes que contiene toda la interfaz
        JPanel panelMensajesGrafico = new JPanel(new BorderLayout());

        // Agregar el área de mensajes y el gráfico al panel principal
        panelMensajesGrafico.add(scrollPaneMensajes, BorderLayout.NORTH);
        panelMensajesGrafico.add(scrollPaneGrafico, BorderLayout.SOUTH);
        panelPrincipal.add(panelMensajesGrafico, BorderLayout.SOUTH);

        // Funcion de inicialización y actualización de la tabla
        actualizarTabla();

        // Configuración del temporizador para actualizar la tabla periódicamente
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        actualizarTabla();
                    }
                });
            }
        }, 0, 1000);

        // Agrega el panel principal al frame
        add(panelPrincipal);
        // Establece la ubicación y tamaño predeterminados del divisor del JSplitPane
        panelCentral.setDividerLocation(0.7); // Ajusta según sea necesario

        // Establece el tamaño preferido del JSplitPane
        panelCentral.setPreferredSize(new Dimension(800, 500)); // Ajusta según sea necesario

    }

    // Método para agregar un nuevo proceso
    private void agregarProceso() {
        // Campo de entrada para que el usuario ingrese el nombre del proceso
        String nombreProceso = JOptionPane.showInputDialog("Ingrese el nombre del proceso:");
        if (nombreProceso != null && !nombreProceso.isEmpty()) {
            // Si se escribe un nombre del proceso se realiza la asignacion de menmoria
            Proceso nuevoProceso = new Proceso(nombreProceso);

            if (memoria.procesoAlcanzaEnParticion(nuevoProceso)) {
                // Primero se valida si el tamaño del proceso alcanza en una de las particiones

                // Verificar si hay procesos en la lista de espera
                // if (!memoria.getProcesosEnEspera().isEmpty()) {

                //     // Agregar el proceso al final de la lista de espera
                //     memoria.agregarProcesoEspera(nuevoProceso);
                //     areaMensajes.append("Proceso " + nombreProceso + " agregado a la lista de espera.\n");
                //     System.out.println("Proceso " + nombreProceso + " agregado a la lista de espera.\n");
                //     actualizarTabla();
                //     return; // Salir del método ya que el proceso se ha agregado a la lista de espera

                // } else
                 if (memoria.todasParticionesOcupadas()) {
                    // Validar que haya particiones libres si estan ocupadas muetsra el mensaje
                    // Aqui se debe implememtar la lista de espera, se supone que es el primer
                    // proceso que se agrega a espera
                    memoria.agregarProcesoEspera(nuevoProceso);
                    areaMensajes.append("Error: No hay particiones libres para el proceso " + nombreProceso + ". Se agregó a la lista de espera\n");
                    System.out.println("No hay particiones libres para el proceso " + nombreProceso + ". Se agregó a la lista de espera\n");

                } else {
                    // Si hay particiones disponibles creamos el proceso

                    // El proceso alcanzó una partición
                    boolean asignado = memoria.asignarMemoriaProceso(nuevoProceso);
                    // Asignar un nuevo proceso, en caso que haya libres y el tamaño alcance
                    if (asignado) {
                        JOptionPane.showMessageDialog(this, "Proceso agregado correctamente.", "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);

                        areaMensajes.append("Proceso " + nombreProceso + " asignado correctamente. Tamaño: "
                                + nuevoProceso.getTamano() + "\n");
                        System.out.println("Proceso " + nombreProceso + " asignado correctamente. \n");
                    } else {
                        // Si no hay particion libre pero si alcanza en una se agrega a la lista de
                        // espera
                        areaMensajes.append("Error: El proceso '" + nombreProceso + "', de Tamaño: "
                                + nuevoProceso.getTamano() + " alcanza pero las particiones están ocupadas. Se agregó a la lista de espera.\n");
                        System.out.println("Error: El proceso '" + nombreProceso + "', de Tamaño: "
                                + nuevoProceso.getTamano() + " alcanza pero las particiones están ocupadas. Se agregó a la lista de espera.\n");
                        memoria.agregarProcesoEspera(nuevoProceso);

                    }

                }

            } else {
                // Si no hay tamaño suficiente, no alcanza
                areaMensajes.append("Error: El proceso " + nombreProceso + " no alcanza en ninguna partición. Tamaño: "
                        + nuevoProceso.getTamano() + "\n");
                System.out.println("Error: El proceso " + nombreProceso + " no alcanza en ninguna partición. Tamaño: "
                        + nuevoProceso.getTamano() + "\n");
            }
            // Llamar a la funcion que actualiza la tabla con el nuevo proceso
            graficoParticiones.setMemoria(memoria);

            actualizarTabla();
        }
    }

    // Método para actualizar la tabla de memoria
    private void actualizarTabla() {
        graficoParticiones.repaint();
        // Se llama al metodo para actualizar la tabla de espera
        actualizarTablaEspera();

        // Se llama a la funcion que verfifica el tiempo restante de los procesos
        memoria.liberarMemoria();
        memoria.reducirTiemposRestantes();

        // Intentar asignar procesos en espera a particiones disponibles
        asignarProcesosEnEspera();

        // Limpiar el modelo de la tabla
        tablaModelo.setRowCount(0);

        // Llenar la tabla con información actualizada de las particiones de memoria
        for (Particion particion : memoria.getParticiones()) {
            String estado;

            // Validar si es la primera particion se deja 'Resrvada ' para el sist.
            // operativo
            if (particion.getNombre().equals("Sistema Operativo")) {
                estado = "Reservado";
            } else {
                estado = particion.estaOcupada() ? "Ocupada" : "Libre";
            }
            // Obtener el nombre del proceso asignado a la partición, si hay uno
            String procesoAsignado = particion.getProcesoAsignado() != null
                    ? particion.getProcesoAsignado().getNombre() + ": " + particion.getProcesoAsignado().getTamano()
                    : "";

            // Obtener el tiempo restante de ejecución del proceso asignado
            int tiempoRestante = particion.getTiempoRestante();

            // Agregar una nueva fila a la tabla con la información de la partición
            tablaModelo.addRow(new Object[] { particion.getNombre(), particion.getTamano(), estado, procesoAsignado,
                    tiempoRestante });

        }
    }

    // Para actualizar la tabla de procesos e espera
    private void actualizarTablaEspera() {
        // Limpiar el modelo de la tabla de procesos en espera
        tablaModeloEspera.setRowCount(0);

        // Llenar la tabla con información actualizada de los procesos en espera
        for (Proceso proceso : memoria.getProcesosEnEspera()) {
            tablaModeloEspera.addRow(new Object[] { proceso.getNombre(), proceso.getTamano() });
        }
    }

    // Método para intentar asignar procesos en espera a particiones disponibles
    private void asignarProcesosEnEspera() {
        // Se crea una copia de la lista de espera para iterarla
        List<Proceso> procesosEnEspera = new ArrayList<>(memoria.getProcesosEnEspera());

        for (Proceso procesoEspera : procesosEnEspera) {
            if (memoria.asignarMemoriaProceso(procesoEspera)) {
                // Se llama al metodo para asignar el proceso en una particion disponible

                // Si se asigna en memoria, se saca de la lista de espera
                memoria.removerProcesoEspera(procesoEspera);
                areaMensajes.append("Proceso " + procesoEspera.getNombre() + " asignado desde lista de espera. Tamaño: "
                        + procesoEspera.getTamano() + "\n");
                System.out.println("Proceso " + procesoEspera.getNombre() + " asignado desde lista de espera. \n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}
