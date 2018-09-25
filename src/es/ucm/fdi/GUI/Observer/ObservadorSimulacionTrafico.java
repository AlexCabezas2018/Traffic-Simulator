/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.Observer;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.MapaCarreteras;

import java.util.List;

public interface ObservadorSimulacionTrafico {
	void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err);
	void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos);
	void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos);
	void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos);
}
