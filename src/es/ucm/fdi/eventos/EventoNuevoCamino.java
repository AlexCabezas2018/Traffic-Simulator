//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Camino;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevoCamino extends EventoNuevaCarretera {
	public EventoNuevoCamino(String id, int time, String origen,
								String destino, int max_speed, int longitud){

		super(time, id, origen, destino, max_speed, longitud);
	}

	@Override
	public Carretera crearCarretera(String id, int longitud, int velmax, CruceGenerico<?> orig, CruceGenerico<?> dest){
		return new Camino(id, longitud, velmax, orig, dest);
	}

	@Override
	public String toString(){
		return "Nuevo camino";
	}
}
