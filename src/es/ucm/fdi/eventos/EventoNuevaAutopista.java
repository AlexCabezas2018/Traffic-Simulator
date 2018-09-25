//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Autopista;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevaAutopista extends EventoNuevaCarretera {
	protected Integer lanes;
	public EventoNuevaAutopista(String id, int time, String origen,
								String destino, int max_speed, int longitud, int lanes){

		super(time, id, origen, destino, max_speed, longitud);
		this.lanes = lanes;
	}

	@Override
	public Carretera crearCarretera(String id, int longitud, int velmax, CruceGenerico<?> orig, CruceGenerico<?> dest){
		return new Autopista(id, longitud, velmax, orig, dest, this.lanes);
	}

	@Override
	public String toString(){
		return "Nueva autopista";
	}
}
