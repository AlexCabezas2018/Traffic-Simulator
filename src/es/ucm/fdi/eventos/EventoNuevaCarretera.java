//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class EventoNuevaCarretera extends Evento{
	protected String id;
	protected Integer velocidadMaxima;
	protected Integer longitud;
	protected String cruceOrigenId;
	protected String cruceDestinoId;

	public EventoNuevaCarretera(int tiempo, String id, String origen, String destino,
								int velocidadMaxima, int longitud) {
		super(tiempo);
		this.id = id;
		this.velocidadMaxima = velocidadMaxima;
		this.longitud = longitud;
		this.cruceOrigenId = origen;
		this.cruceDestinoId = destino;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		CruceGenerico<?> orig = mapa.getCruce(this.cruceOrigenId);
		CruceGenerico<?> dest = mapa.getCruce(this.cruceDestinoId);
		Carretera nuevaC = crearCarretera(this.id, this.longitud, this.velocidadMaxima, orig, dest);
		mapa.addCarretera(this.id, orig, dest, nuevaC);
	}

	public Carretera crearCarretera(String id, int longitud, int velmax, CruceGenerico<?> orig, CruceGenerico<?> dest){
		return new Carretera(id, longitud, velmax, orig, dest);
	}

	@Override
	public String toString(){
		return "Nueva Carretera";
	}

}
