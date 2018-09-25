/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.Paneles;


import es.ucm.fdi.GUI.RestoComponentes.DialogoInformes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel{
    private JButton botonCancelar;
    private JButton botonGenerar;

    public PanelBotones(DialogoInformes dialogoI){
        this.setLayout(new FlowLayout());
        this.botonCancelar = new JButton("Cancelar");
        this.botonCancelar.setToolTipText("Cancela la accion");
        this.botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogoI.setVisible(false);
            }
        });
        this.botonGenerar = new JButton("Generar");
        this.botonGenerar.setToolTipText("Genera el informe de los objetos seleccionados");
        this.botonGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dialogoI.generarReporte();
               dialogoI.setVisible(false);
            }
        });

        this.add(this.botonGenerar);
        this.add(this.botonCancelar);
    }

}
