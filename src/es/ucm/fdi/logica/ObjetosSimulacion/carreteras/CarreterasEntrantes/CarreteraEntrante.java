//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes;

import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class CarreteraEntrante {

	protected Carretera carretera;
	protected List<Vehiculo> colaVehiculos;
	protected boolean semaforo;

	public CarreteraEntrante(Carretera carretera){
		this.carretera = carretera;
		this.colaVehiculos = new ArrayList<Vehiculo>();
		this.semaforo = false;
	}
	public String getId(){ return this.carretera.getId(); }

	public boolean isSemaforo() {
		return semaforo;
	}

	public List<Vehiculo> getColaVehiculos() {
		return colaVehiculos;
	}

	public void addVehicletoRoad(Vehiculo v){
		this.colaVehiculos.add(v);
	}

	public Carretera getCarretera() {
		return carretera;
	}

	public void ponSemaforo(boolean color){
		this.semaforo = color;
	}

	public void anvanzaPrimerVehiculo(){
		if(this.colaVehiculos.size() > 0) {
			Vehiculo v = this.colaVehiculos.get(0);
			v.moverASiguienteCarretera();
			this.colaVehiculos.remove(0);
		}
	}



}
