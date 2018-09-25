//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.vehiculos;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.ObjetoSimulacion;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

import java.util.List;

public class Vehiculo extends ObjetoSimulacion {
	protected Carretera carretera;
	protected int velocidadMaxima;
	protected int velocidadActual;
	protected int kilometraje;
	protected int localizacion;
	protected int tiempoAveria;
	protected boolean haLlegado;
	protected boolean estaEnCruce;
	protected List<CruceGenerico<?>> itinerario;
	private int indiceActual; //tenemos que ponerlo para evitar obtener indices equivocados con cruces repetidos

	public Vehiculo(String id, int velocidadMaxima, List<CruceGenerico<?>> itinerario){
		super(id);
		if(velocidadMaxima < 0 || itinerario.size() < 2){
			throw new IllegalArgumentException("Los argumentos no son validos.");
		}
		this.id = id;
		this.setVelocidadActual(0);
		this.velocidadMaxima = velocidadMaxima;
		this.carretera = null;
		this.kilometraje = 0;
		this.itinerario = itinerario;
		this.tiempoAveria = 0;
		this.localizacion = 0;
		this.haLlegado = false;
		this.estaEnCruce = false;
		this.indiceActual = 0;
	}

	public String getCarretera() {
		if(this.carretera != null) {
			return carretera.getId();
		}
		else{
			return "Arrived";
		}

	}

	public int getVelocidadMaxima() {
		return velocidadMaxima;
	}

	public int getVelocidadActual() {
		return velocidadActual;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public String getItinerario() {
		String ret = "";
		for(CruceGenerico<?> cg: this.itinerario){
			ret += cg.getId() + " ";
		}
		return ret;
	}

	public String getL(){
		return (this.haLlegado) ? "0": Integer.toString(this.localizacion);
	}

	public int getLocalizacion(){
		return this.localizacion;
	}
	public int getTiempoAveria(){
		return this.tiempoAveria;
	}

	public void setVelocidadActual(int velocidad){
		if(velocidad < 0){this.velocidadActual = 0;}
		else if(velocidad > this.velocidadMaxima){this.velocidadActual = this.velocidadMaxima;}
		else{this.velocidadActual = velocidad;}
	}
	public void setTiempoAveria(Integer duracionAveria){
		if(this.carretera != null){
			this.tiempoAveria += duracionAveria;
			this.velocidadActual = 0;
		}
	}
	@Override
	public void completaDetallesSeccion(IniSection is){
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" : "(" + this.carretera.getId()
				+ "," + this.getLocalizacion() + ")");
	}
	@Override
	public void avanza(){
		if(this.tiempoAveria > 0){
			this.tiempoAveria--;
		}
		else {
			if (!this.estaEnCruce) {
				this.localizacion += this.velocidadActual;
				this.kilometraje += this.velocidadActual;
				if (localizacion >= this.carretera.getLength()) {
					this.velocidadActual = 0;
					int localizacionNoC = this.localizacion;
					this.localizacion = this.carretera.getLength();
					this.kilometraje -= (localizacionNoC - this.carretera.getLength());
					this.carretera.entraVehiculoAlCruce(this);
					this.estaEnCruce = true;
				}
			}
			else this.velocidadActual = 0; //Esta en cruce y por tanto su velocidad tiene que ser 0
		}
	}

	public void moverASiguienteCarretera(){
		if(this.carretera != null) {
			this.carretera.saleVehiculo(this);
			CruceGenerico<?> c = this.itinerario.get(this.itinerario.size() - 1);
			CruceGenerico<?> c2 = this.carretera.getCruceDestino();
			if (c2.equals(c) && this.indiceActual == this.itinerario.size() - 1) {
				this.haLlegado = true;
				this.carretera = null;
				this.velocidadActual = 0;
				this.localizacion = 0;
			} else {
				CruceGenerico<?> siguiente = this.itinerario.get(this.indiceActual + 1);
				this.indiceActual++;
				Carretera siguienteC = c2.carreteraHaciaElCruce(siguiente);
				if (siguienteC == null) {
					throw new ErrorDeSimulacion("No esta bien");
				}
				this.carretera = siguienteC;
				this.carretera.entraVehiculo(this);
				this.localizacion = 0;
				this.estaEnCruce = false;
				this.velocidadActual = 0;
			}
		}
		else{ //Entonces es nuevo vehiculo, lo colocamos en la primera carretera del itinerario.
			CruceGenerico<?> c = this.itinerario.get(0);
			CruceGenerico<?> s = this.itinerario.get(1);
			Carretera cr = c.carreteraHaciaElCruce(s);
			this.carretera = cr;
			this.carretera.entraVehiculo(this);
			this.indiceActual++;
		}
	}

	@Override
	public String getNombreSeccion(){
		return "vehicle_report";
	}
}
