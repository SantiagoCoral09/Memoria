package memoria;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class InterfazGrafica extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Memoria memoria;
    private DefaultTableModel tablaModelo;
    private JTable tabla;
    private JTextArea areaMensajes;
    private Timer temporizador;

    public InterfazGrafica() {
        memoria = new Memoria();

        setTitle("Asignación Contigua Estática - Primer Ajuste");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        // Panel principal que contiene toda la interfaz
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior que contiene la información de la memoria y de la tabla
        JPanel panelInfo = new JPanel(new BorderLayout());

        // Información de la memoria
        JLabel labelInfoMemoria = new JLabel("***    Tamaño Total de Memoria: " + memoria.getTamanoMemoria());
        panelInfo.add(labelInfoMemoria, BorderLayout.NORTH);

        // Información de la tabla
        JPanel panelInfoTabla = new JPanel(new GridLayout(8, 1));
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

        panelInfoTabla.add(labelInfoTabla);
        panelInfoTabla.add(labelInfoTabla1);
        panelInfoTabla.add(labelInfoTabla2);
        panelInfoTabla.add(labelInfoTabla3);
        panelInfoTabla.add(labelInfoTabla4);
        panelInfoTabla.add(labelInfoTabla5);
        panelInfoTabla.add(labelInfoTabla6);
        panelInfoTabla.add(labelInfoTabla7);

        panelInfo.add(panelInfoTabla, BorderLayout.CENTER);

        // Panel central que contiene la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Tabla
        tablaModelo = new DefaultTableModel(new String[] { "Partición", "Tamaño", "Estado", "Proceso:Tamaño", "Tiempo Restante" }, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables, para evitarrr que el usuarioo ingrese datoss en la tabbla
            }
        };
        tabla = new JTable(tablaModelo);
        panelCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Área de mensajes
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);//evitar que el textarea se puede editar texto, es de solo lectuta
        JScrollPane scrollPaneMensajes = new JScrollPane(areaMensajes);//scroll para mostrar los mensajes
        scrollPaneMensajes.setPreferredSize(new Dimension(750, 70));//dimesniones de la textareA
        panelCentral.add(scrollPaneMensajes, BorderLayout.SOUTH);

        // Panel inferior que contiene el botón
        JPanel panelInferior = new JPanel();
        JButton botonAgregarProceso = new JButton("Agregar Proceso");
        botonAgregarProceso.addActionListener(new ActionListener() {
            //Al presionar el boton se ejecuta la funcion agregarProceso()
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProceso();
            }
        });
        panelInferior.add(botonAgregarProceso);

        // Agregar paneles superior, central e inferior al panel principal
        // Es la disposicion de los objetos visuales
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar el panel principal al frame
        add(panelPrincipal);

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
    }

    // Método para agregar un nuevo proceso
    private void agregarProceso() {
        // Campo de entrada para que el usuario ingrese el nombre del proceso
        String nombreProceso = JOptionPane.showInputDialog("Ingrese el nombre del proceso:");
        if (nombreProceso != null && !nombreProceso.isEmpty()) {
            // Si se escribe un nombre del proceso se realiza la asignacion de menmoria
            if (memoria.todasParticionesOcupadas()) {
                // Validar que haya particiones libres si estan ocupadas muetsra el mensaje
                // Aqui se debe implememtar la lista de espera
                areaMensajes.append("Error: No hay particiones libres para el proceso " + nombreProceso + ".\n");

                System.out.println("No hay particiones libres para el proceso " + nombreProceso + ".\n");

            } else {
                // Si hay particiones disponibles creamos el proceso
                Proceso nuevoProceso = new Proceso(nombreProceso);
                boolean asignado = memoria.asignarMemoriaProceso(nuevoProceso);
                // Asignar un nuevo proceso, en caso que haya libres y el tamaño alcance
                if (asignado) {
                    areaMensajes.append("Proceso " + nombreProceso + " asignado correctamente. Tamaño: "
                            + nuevoProceso.getTamano() + "\n");
                    System.out.println("Proceso " + nombreProceso + " asignado correctamente. \n");
                } else {
                    // Si no hay tamaño suficiente, no alcanza
                    areaMensajes.append("Error: Espacio insuficiente para el proceso " + nombreProceso + ". Tamaño: "
                            + nuevoProceso.getTamano() + "\n");
                }

                // Llamar a la funcion que actualiza la tabla con el nuevo proceso
                actualizarTabla();
            }
        }
    }

    // Método para actualizar la tabla de memoria
    private void actualizarTabla() {
        // Se llama a la funcion que verfifica el tiempo restante de los procesos
        memoria.liberarMemoria();
        memoria.reducirTiemposRestantes();

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}
