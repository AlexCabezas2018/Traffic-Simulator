package es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes;

import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

public class CarreteraEntranteConIntervalos extends CarreteraEntrante{
	private int intervaloTiempo;
	private int unidadesDeTiempoUsadas;
	private boolean usoCompleto;
	private boolean usadaPorUnVehiculo;

	public CarreteraEntranteConIntervalos(Carretera c, int intervalTiempo){
		super(c);
		this.intervaloTiempo = intervalTiempo;
		this.unidadesDeTiempoUsadas = 0;
		this.usoCompleto = true;
		this.usadaPorUnVehiculo = false;
	}

	@Override
	public void anvanzaPrimerVehiculo(){
		this.unidadesDeTiempoUsadas++;
		if(this.colaVehiculos.size() == 0){
			this.usoCompleto = false;
		}
		else{
			Vehiculo v = this.colaVehiculos.get(0);
			this.colaVehiculos.remove(0);
			v.moverASiguienteCarretera();
			this.usadaPorUnVehiculo = true;
		}

	}

	public boolean tiempoConsumido(){
		return (this.unidadesDeTiempoUsadas >= this.intervaloTiempo);
	}

	public boolean usoCompleto(){
		return this.usoCompleto;
	}

	public boolean usada(){
		return this.usadaPorUnVehiculo;
	}

	public void setIntervaloTiempo(int tiempo){
		this.intervaloTiempo = tiempo;
	}

	public void setUnidadesDeTiempoUsadas(int tiempo){
		this.unidadesDeTiempoUsadas = tiempo;
	}

	public void setUsoCompleto(boolean valor){
		this.usoCompleto = valor;
	}

	public int getIntervaloTiempo(){
		return this.intervaloTiempo;
	}

	public int getUnidadesDeTiempoUsadas(){
		return this.unidadesDeTiempoUsadas;
	}

	public void setUsadaPorUnVehiculo(boolean valor){
		this.usadaPorUnVehiculo = valor;
	}

}
