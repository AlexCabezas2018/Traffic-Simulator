//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Bicicleta;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;
import java.util.List;

public class EventoNuevaBicicleta extends EventoNuevoVehiculo{

	public EventoNuevaBicicleta(String id, int time, String[] it, int max){
		super(time, id, max, it);
	}

	@Override
	public Vehiculo crearVehiculo(String id, int velMax, List<CruceGenerico<?>> it){
		return new Bicicleta(id ,velMax, it);
	}

	@Override
	public String toString(){
		return "Nueva bicicleta";
	}

}
