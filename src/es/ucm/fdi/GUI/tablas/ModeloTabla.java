/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;
import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.logica.Controlador;

import javax.swing.table.DefaultTableModel;

import java.util.List;

public abstract class ModeloTabla<T> extends DefaultTableModel implements ObservadorSimulacionTrafico{

	protected String[] columnsId;
	protected List<T> lista;

	public ModeloTabla(String[] columnsId, Controlador cont){
		this.lista = null;
		this.columnsId = columnsId;
		cont.addObserver(this);
	}

	@Override
	public String getColumnName(int col){
		return this.columnsId[col];
	}

	@Override
	public int getColumnCount(){
		return this.columnsId.length;
	}

	@Override
	public int getRowCount(){
		return (this.lista == null) ? 0: this.lista.size();
	}
}
