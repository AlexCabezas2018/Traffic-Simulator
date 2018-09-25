//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.Parser.ParserCarreteras;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.List;

public class EventoNuevoVehiculo extends Evento{
	protected String id;
	protected Integer velocidadMaxima;
	protected String[] itinerario;
	public EventoNuevoVehiculo(int tiempo, String id, int velocidadMaxima, String[] itinerario) {
		super(tiempo);
		this.id = id;
		this.velocidadMaxima = velocidadMaxima;
		this.itinerario = itinerario;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		List<CruceGenerico<?>> iti = ParserCarreteras.parseaListaCruces(itinerario, mapa);
		if(iti == null || iti.size() < 2){throw new ErrorDeSimulacion("Error de simulacion");}
		else{
			Vehiculo nuevoVehiculo = crearVehiculo(this.id, this.velocidadMaxima, iti);
			mapa.addVehiculo(this.id, nuevoVehiculo);
		}
	}

	public Vehiculo crearVehiculo(String id, int velMax, List<CruceGenerico<?>> it){
		return new Vehiculo(id ,velMax, it);
	}

	@Override
	public String toString(){
		return "Nuevo Vehiculo";
	}
	
}
