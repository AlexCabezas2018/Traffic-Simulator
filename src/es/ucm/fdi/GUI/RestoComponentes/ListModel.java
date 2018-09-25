/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import javax.swing.*;
import java.util.List;

public class ListModel<T> extends DefaultListModel<T> {
    private List<T> lista;

    public void setList(List<T> lista){
        this.lista = lista;
        fireContentsChanged(this, 0, this.lista.size());
    }

    @Override
    public T getElementAt(int index){
        return this.lista.get(index);
    }

    @Override
    public int getSize(){
        return (this.lista == null) ? 0: this.lista.size();
    }

}
