//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Coche;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.List;


public class EventoNuevoCoche extends EventoNuevoVehiculo {
	private Integer resistance;
	private double probabilidad;
	private Integer maxDuracionAveria;
	private long seed;

	public EventoNuevoCoche(int tiempo, String id, String[] it, int maxS,
							int resis, double probF, int maxProbF, long semilla){
		super(tiempo, id, maxS, it);
		this.resistance = resis;
		this.probabilidad = probF;
		this.maxDuracionAveria = maxProbF;
		this.seed = semilla;
	}

	public Vehiculo crearVehiculo(String id, int velMax, List<CruceGenerico<?>> it){
		return new Coche(id, velMax, this.resistance, this.probabilidad,
						this.seed, this.maxDuracionAveria, it);
	}

	@Override
	public String toString(){
		return "Nuevo coche";
	}
}
