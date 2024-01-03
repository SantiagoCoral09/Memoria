package memoria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoParticiones extends JPanel {

    private Memoria memoria;

    public GraficoParticiones(Memoria memoria) {
        this.memoria = memoria;
        //setPreferredSize(new Dimension(800, 300));
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (memoria != null) {
        List<Particion> particiones = memoria.getParticiones();

        // Título
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        String titulo = "GRAFICO DE DESCRIPCION DE PARTICIONES";
        int tituloWidth = g.getFontMetrics().stringWidth(titulo);
        int tituloX = (getWidth() - tituloWidth) / 2;
        int tituloY = 20;
        g.drawString(titulo, tituloX, tituloY);

        int particionHeight = 30;
        int startY = tituloY + 5;

        for (Particion particion : particiones) {
            int particionY = startY + particiones.indexOf(particion) * particionHeight;

            // Dibuja el rectángulo de la partición
            int particionWidth = (int) (getWidth() * particion.getTamano() / memoria.getTamanoMemoria() +50);
            g.setColor(getColorParticion(particion));
            g.fillRect(20, particionY + 25, particionWidth, particionHeight - 10);

            // Obtener el nombre de la partición
            String nombreParticion = particion.getNombre();

            // Obtener el texto que se mostrará dentro del rectángulo
            String textoInterior = obtenerTextoInterior(particion);

            // Dibuja el nombre de la partición al lado del rectángulo
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            int textX = 20 + particionWidth;
            int textY = particionY + 40;
            g.drawString(" :  "+nombreParticion + " (" + particion.getTamano() + ")", textX, textY);

            // Dibuja el texto interior dentro del rectángulo
            g.drawString(textoInterior, 20, textY);
        }
    }
}

// Método para obtener el texto interior del rectángulo
private String obtenerTextoInterior(Particion particion) {
    if (particion.getNombre().equals("Sistema Operativo")) {
        return "    Reservado";
    } else if (particion.estaOcupada()) {
        return "    Asignado (" + particion.getProcesoAsignado().getTamano() + ")";
    } else {
        return "    Libre";
    }
}


    private Color getColorParticion(Particion particion) {
        if (particion.getNombre().equals("Sistema Operativo")) {
            return Color.YELLOW;  // Amarillo para el sistema operativo
        } else {
            return particion.estaOcupada() ? Color.RED : Color.GREEN;
        }
    }
}
