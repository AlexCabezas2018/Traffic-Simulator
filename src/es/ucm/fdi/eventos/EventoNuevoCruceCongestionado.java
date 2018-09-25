package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceCongestionado;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce{
	public EventoNuevoCruceCongestionado(int time, String id){
		super(time, id);
	}

	@Override
	public CruceGenerico<?> creaCruce(){
		return new CruceCongestionado(this.id);
	}

	@Override
	public String toString(){
		return "Nuevo cruce congestionado";
	}
}
