/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.panelesTexto;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;

import java.util.List;

public class PanelInformes extends PanelAreaTexto implements ObservadorSimulacionTrafico {
	public PanelInformes(String titulo, boolean editable, Controlador controller){
		super(titulo, editable);
		controller.addObserver(this);
	}

	@Override
	public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) { }

	@Override
	public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) { }

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) { }

	@Override
	public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
		this.limpiar();
	}
}
