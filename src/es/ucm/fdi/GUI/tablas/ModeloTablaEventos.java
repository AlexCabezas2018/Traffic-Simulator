/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;

import java.util.List;

public class ModeloTablaEventos extends ModeloTabla<Evento> {
	public ModeloTablaEventos(String[] columnIdEventos, Controlador c){
		super(columnIdEventos, c);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol){
			case 0: s = indiceFil; break;
			case 1: s = this.lista.get(indiceFil).getTiempo(); break;
			case 2: s = this.lista.get(indiceFil).toString(); break;
			default: assert(false);
		}
		return s;
	}

	@Override
	public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) { }

	@Override
	public void avanza(int tiempo, MapaCarreteras map, List<Evento> events){
		this.lista = events;
		this.fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras map, List<Evento> events){
		this.lista = events;
		this.fireTableStructureChanged();
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras map, List<Evento> events){
		this.lista = events;
		this.fireTableStructureChanged();
	}
}
