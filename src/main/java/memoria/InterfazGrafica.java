/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package memoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Santiago Coral
 */
public class InterfazGrafica extends javax.swing.JFrame {

    /**
     * Creates new form InterfazGrafica
     */
    private Timer temporizador;

    public Memoria memoria;
    private DefaultTableModel tablaModelo;  // Cambié el tipo a DefaultTableModel
    private JTable tabla;
    private javax.swing.JTable tablaEspera;
    private javax.swing.table.DefaultTableModel tablaModeloEspera;

    GraficoParticiones graficoParticiones; // Nueva instancia para el gráfico
    
    public InterfazGrafica() {
        memoria = new Memoria(); // Asegúrate de que memoria tenga particiones asignadas
        graficoParticiones = new GraficoParticiones(memoria);
        initComponents();
        //JTableHeader headerE = tablaEspera.getTableHeader();
        //headerE.setBackground(new java.awt.Color(0,102,102));
        //tabla.setBackground(new Color(191,191,191));
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBackground(new java.awt.Color(138,163,163));
        JScrollPane scrollPane = new JScrollPane(graficoParticiones);
        frame.add(scrollPane);
        frame.setVisible(true);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelTitulo = new javax.swing.JPanel();
        LabelTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PanelEtiquetas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        JLabelTama = new javax.swing.JLabel("Tabla de particiones. Tamaño Total de Memoria: " + memoria.getTamanoMemoria()+".",SwingConstants.CENTER);
        jScrollPane1 = new javax.swing.JScrollPane();
        btnAgregarProceso = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ScrollPane1 = new javax.swing.JScrollPane();
        scrollMensajes = new javax.swing.JScrollPane();
        areaMensajes = new javax.swing.JTextArea();
        labelMensajes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asignación Contigua Estática - Primer Ajuste");
        setBackground(new java.awt.Color(138, 163, 163));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setLocation(new java.awt.Point(200, 0));
        setSize(new java.awt.Dimension(900, 700));
        setType(java.awt.Window.Type.UTILITY);

        PanelPrincipal.setBackground(new java.awt.Color(138, 168, 163));
        PanelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PanelPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelPrincipal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(976, 594));

        PanelTitulo.setBackground(new java.awt.Color(18, 115, 105));
        PanelTitulo.setForeground(new java.awt.Color(255, 255, 255));

        LabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("ASIGNACIÓN CONTIGUA ESTÁTICA - PRIMER AJUSTE");
        LabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelTituloLayout = new javax.swing.GroupLayout(PanelTitulo);
        PanelTitulo.setLayout(PanelTituloLayout);
        PanelTituloLayout.setHorizontalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTituloLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(LabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        PanelTituloLayout.setVerticalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTituloLayout.createSequentialGroup()
                .addComponent(LabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(138, 163, 163));

        PanelEtiquetas.setBackground(new java.awt.Color(136, 166, 160));
        PanelEtiquetas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PanelEtiquetas.setLayout(new java.awt.GridLayout(7, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("INFORMACIÓN DE LA TABLA");
        jLabel1.setAlignmentX(1.0F);
        PanelEtiquetas.add(jLabel1);

        jLabel2.setText("Partición: Nombre de la partición");
        PanelEtiquetas.add(jLabel2);

        jLabel3.setText("Tamaño: Tamaño total de la partición");
        PanelEtiquetas.add(jLabel3);

        jLabel4.setText("Estado: Estado de la partición, que puede ser 'Libre', 'Ocupada' o 'Reservado' si es la del sistema operativo.");
        PanelEtiquetas.add(jLabel4);

        jLabel5.setText("Proceso Asignado: Tamaño. Nombre del proceso asignado a la partición y el tamaño que ocupa en memoria.");
        PanelEtiquetas.add(jLabel5);

        jLabel6.setText("Tiempo Restante: Tiempo restante de ejecución del proceso asignado. Si la partición está libre, esta columna estará en cero.");
        PanelEtiquetas.add(jLabel6);

        jPanel2.setBackground(new java.awt.Color(138, 166, 163));

        JLabelTama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        JLabelTama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setBackground(new java.awt.Color(138, 166, 163));
        jScrollPane1.setBorder(null);
        jScrollPane1.setRowHeaderView(null);

        btnAgregarProceso.setBackground(new java.awt.Color(0, 102, 102));
        btnAgregarProceso.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        btnAgregarProceso.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProceso.setText("Agregar Proceso");
        btnAgregarProceso.setToolTipText("Puede agregar un proceso ingresando su nombre. (El tamaño se asignará aleatoriamente))");
        btnAgregarProceso.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 255, 204), new java.awt.Color(255, 255, 153), new java.awt.Color(153, 0, 153)));
        btnAgregarProceso.setBorderPainted(false);
        btnAgregarProceso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProceso.setFocusCycleRoot(true);
        btnAgregarProceso.setHideActionText(true);
        btnAgregarProceso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarProceso.setMargin(new java.awt.Insets(5, 14, 3, 14));
        btnAgregarProceso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarProcesoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarProcesoMouseExited(evt);
            }
        });
        btnAgregarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProcesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(JLabelTama, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(261, 261, 261))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(JLabelTama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregarProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Procesos en espera");

        ScrollPane1.setBackground(new java.awt.Color(0, 102, 102));
        ScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PanelEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        areaMensajes.setEditable(false);
        areaMensajes.setColumns(20);
        areaMensajes.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        areaMensajes.setRows(5);
        scrollMensajes.setViewportView(areaMensajes);

        labelMensajes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMensajes.setText("Mensajes de Información.");

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMensajes)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMensajes))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMensajes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        // Crear y configurar la nueva tabla con el modelo personalizado
        tablaModelo = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Partición", "Tamaño", "Estado", "Proceso:Tamaño", "Tiempo Restante"}
        ){
            boolean[] canEdit = new boolean[]{false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }};

            tabla = new JTable(tablaModelo);
            jScrollPane1.setViewportView(tabla);

            // Centrar el texto en todas las celdas
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tabla.setDefaultRenderer(Object.class, centerRenderer);

            // Establecer el fondo y el texto del encabezado
            JTableHeader header = tabla.getTableHeader();
            ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setBackground(new java.awt.Color(0,0,128));
            header.setForeground(new Color(0,0,0));
            header.setBackground(new Color(255,0,0));
            header.setFont(new java.awt.Font("Segoe UI", 1, 12));

            // Centrar el texto en el encabezado
            ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

            // Crear el modelo de tabla para la tabla de espera
            tablaModeloEspera = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Proceso", "Tamaño"}
            ) {
                boolean[] canEdit = new boolean[]{false, false};

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };

            // Crear la tabla de espera y asignarle el modelo
            tablaEspera = new javax.swing.JTable(tablaModeloEspera);
            ScrollPane1.setViewportView(tablaEspera);

            // Establecer el fondo y el texto del encabezado
            JTableHeader headerE = tablaEspera.getTableHeader();
            headerE.setFont(new java.awt.Font("Segoe UI", 1, 12));

            // Centrar el texto en el encabezado
            ((DefaultTableCellRenderer) tablaEspera.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProcesoActionPerformed
        // TODO add your handling code here:
        agregarProceso();
    }//GEN-LAST:event_btnAgregarProcesoActionPerformed

    private void btnAgregarProcesoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProcesoMouseEntered
        // TODO add your handling code here:
        btnAgregarProceso.setBackground(new java.awt.Color(60, 110, 140));
        btnAgregarProceso.setFont(new java.awt.Font("Segoe UI Emoji", 0, 15)); // NOI18N
        btnAgregarProceso.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnAgregarProcesoMouseEntered

    private void btnAgregarProcesoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProcesoMouseExited
        // TODO add your handling code here:
        btnAgregarProceso.setBackground(new java.awt.Color(0, 102, 102));
        btnAgregarProceso.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        btnAgregarProceso.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnAgregarProcesoMouseExited

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
            tablaModelo.addRow(new Object[]{particion.getNombre(), particion.getTamano(), estado, procesoAsignado,
                tiempoRestante});

        }
    }

    // Para actualizar la tabla de procesos e espera
    private void actualizarTablaEspera() {
        // Limpiar el modelo de la tabla de procesos en espera
        tablaModeloEspera.setRowCount(0);

        // Llenar la tabla con información actualizada de los procesos en espera
        for (Proceso proceso : memoria.getProcesosEnEspera()) {
            tablaModeloEspera.addRow(new Object[]{proceso.getNombre(), proceso.getTamano()});
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelTama;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JPanel PanelEtiquetas;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JScrollPane ScrollPane1;
    private javax.swing.JTextArea areaMensajes;
    private javax.swing.JButton btnAgregarProceso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMensajes;
    private javax.swing.JScrollPane scrollMensajes;
    // End of variables declaration//GEN-END:variables
}
