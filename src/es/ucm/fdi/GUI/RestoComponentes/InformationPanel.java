/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    public InformationPanel() {
        this.setLayout(new GridLayout(3, 1));
        JLabel p1 = new JLabel("Selecciona objetos para generar informes");
        JLabel p2 = new JLabel("Usa 'c' para deseleccionar todos");
        JLabel p3 = new JLabel("Usa Ctrl+A para seleccionar todos");

        p1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p3.setAlignmentX(Component.LEFT_ALIGNMENT);


        this.add(p1);
        this.add(p2);
        this.add(p3);
    }

}
