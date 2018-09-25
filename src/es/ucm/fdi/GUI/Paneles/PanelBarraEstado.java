/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.Paneles;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelBarraEstado extends JPanel implements ObservadorSimulacionTrafico {
	private JLabel infoEjecucion;

	public PanelBarraEstado(String mensaje, Controlador ctrl){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.infoEjecucion = new JLabel(mensaje);
		this.add(this.infoEjecucion);
		this.setBorder(BorderFactory.createBevelBorder(1));

		ctrl.addObserver(this);
	}

	public void setMensaje(String m){
		this.infoEjecucion.setText(m);
	}

	@Override
	public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {
		this.infoEjecucion.setText("Error");
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
		this.infoEjecucion.setText("Paso: " + tiempo + " del Simulador");
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.infoEjecucion.setText("Evento añadido al simulador");
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
		this.infoEjecucion.setText("");
	}
}
