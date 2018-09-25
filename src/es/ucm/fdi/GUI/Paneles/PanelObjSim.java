/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.Paneles;

import es.ucm.fdi.GUI.RestoComponentes.DialogoInformes;
import es.ucm.fdi.GUI.RestoComponentes.ListModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PanelObjSim<T> extends JPanel {

    private ListModel<T> listModel;
    private JList<T> objList;

    public PanelObjSim(String titulo){
        this.setLayout(new GridLayout(1, 1));
        this.listModel = new ListModel<T>();
        this.objList = new JList<T>(this.listModel);
        addCleanSelectionListener(this.objList);
        this.add(new JScrollPane(objList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        this.setBorde(titulo);
    }

    public void setBorde(String titulo){
        Border b = BorderFactory.createLineBorder(Color.black, 2);
        this.setBorder(BorderFactory.createTitledBorder(b, titulo));
    }

    private void addCleanSelectionListener(JList<T> list){
        list.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == DialogoInformes.TECLA_LIMPIAR){
                    list.clearSelection();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }

    public List<T> getSelectedItems(){
        List<T> l = new ArrayList<>();
        for(int i : this.objList.getSelectedIndices()){
            l.add(listModel.getElementAt(i));
        }
        return l;
    }

    public void setList(List<T> lista){
        this.listModel.setList(lista);
    }
}
