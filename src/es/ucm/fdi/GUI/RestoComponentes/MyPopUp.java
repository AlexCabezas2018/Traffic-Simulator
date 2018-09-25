/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import es.ucm.fdi.GUI.VentanaPrincipal;
import es.ucm.fdi.Parser.ParserEventos;
import es.ucm.fdi.constructoresEventos.ConstructorEventoNuevoVehiculo;
import es.ucm.fdi.constructoresEventos.ConstructorEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPopUp extends JPopupMenu {

    public MyPopUp(VentanaPrincipal mainWindow){
        super();
        JMenuItem cargar = new JMenuItem("Cargar");
        cargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.cargarFichero();
            }
        });

        JMenuItem salvar = new JMenuItem("Salvar");
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.salvarFichero();
            }
        });

        JMenuItem limpiar = new JMenuItem("Limpiar area");
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.limpiarEventos();
            }
        });

        JMenu addTem = new JMenu("Añadir plantilla");
        for(ConstructorEventos ce: ParserEventos.getC()){
            JMenuItem mi = new JMenuItem(ce.toString());
            mi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainWindow.setTextoEnEditor(ce.template() + System.lineSeparator());
                }
            });
            addTem.add(mi);
        }
        
        this.add(addTem);
        this.addSeparator();
        this.add(cargar);
        this.add(salvar);
        this.add(limpiar);
    }

}
