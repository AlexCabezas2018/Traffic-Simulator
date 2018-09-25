/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.panelesTexto;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

abstract public class PanelAreaTexto extends JPanel{
	protected JTextArea areaTexto;

	public PanelAreaTexto(String titulo, boolean editable){
		this.setLayout(new GridLayout(1, 1));
		this.areaTexto = new JTextArea(40, 30);
		this.areaTexto.setEditable(editable);
		this.add(new JScrollPane(areaTexto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		this.setBorde(titulo);
	}

	public void setBorde(String titulo){
		Border b = BorderFactory.createLineBorder(Color.black, 3);
		this.setBorder(BorderFactory.createTitledBorder(b, titulo));
	}

	public String getTexto(){
		return this.areaTexto.getText();
	}

	public void setTexto(String texto){
		this.areaTexto.setText(texto);
	}

	public void limpiar(){
		this.areaTexto.setText("");
	}

	public void inserta(String valor){
		this.areaTexto.insert(valor, this.areaTexto.getCaretPosition());
	}

}
