//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes.CarreteraEntrante;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevoCruce extends Evento{
	protected String id;
	public EventoNuevoCruce(int tiempo, String id) {
		super(tiempo);
		this.id = id;
	}

	protected CruceGenerico<?> creaCruce(){
		return new Cruce(this.id);
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		mapa.addCruce(this.id, this.creaCruce());
	}

	@Override
	public String toString(){
		return "Nuevo Cruce";
	}

}
