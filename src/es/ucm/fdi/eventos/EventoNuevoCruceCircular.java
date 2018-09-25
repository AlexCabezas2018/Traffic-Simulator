package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceCircular;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevoCruceCircular extends EventoNuevoCruce {
	protected Integer minValorIntervalo;
	protected Integer maxValorIntervalo;

	public EventoNuevoCruceCircular(int time, String id, int minValor, int maxValor){
		super(time, id);
		this.minValorIntervalo = minValor;
		this.maxValorIntervalo = maxValor;
	}

	@Override
	public CruceGenerico<?> creaCruce(){
		return new CruceCircular(this.id, maxValorIntervalo, minValorIntervalo);
	}

	@Override
	public String toString(){
		return "Nuevo cruce circular";
	}
}
