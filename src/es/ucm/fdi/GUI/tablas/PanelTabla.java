/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PanelTabla<T> extends JPanel{
	private ModeloTabla<T> modelo;

	public PanelTabla(String bordeId, ModeloTabla<T> modelo){
		this.setLayout(new GridLayout(1, 1));
		Border b = BorderFactory.createLineBorder(Color.black, 3);
		this.setBorder(BorderFactory.createTitledBorder(b, bordeId));
		this.modelo = modelo;
		JTable tabla = new JTable(this.modelo);
		tabla.setEnabled(false);
		this.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}

}
