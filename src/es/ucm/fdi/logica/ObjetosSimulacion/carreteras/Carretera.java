//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.carreteras;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.ObjetoSimulacion;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Carretera extends ObjetoSimulacion {
	protected int longitud;
	protected int velocidadMaxima;
	protected CruceGenerico<?> cruceOrigen;
	protected CruceGenerico<?> cruceDestino;
	protected List<Vehiculo> vehiculos;

	protected Comparator<Vehiculo> comparadorVehiculos;

	public Carretera(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest) {
		super(id);
		this.longitud = length;
		this.velocidadMaxima = maxSpeed;
		this.cruceDestino = dest;
		this.cruceOrigen = src;
		this.comparadorVehiculos = new Comparator<Vehiculo>() {
			@Override
			public int compare(Vehiculo vehiculo, Vehiculo t1) {
				if (vehiculo.getLocalizacion() < t1.getLocalizacion()) return 1;
				else if (vehiculo.getLocalizacion() == t1.getLocalizacion()) return 0;
				else return -1;
			}
		};
		this.vehiculos = new ArrayList<>();
	}
	public CruceGenerico<?> getCruceOrigen(){
	    return this.cruceOrigen;
    }

	public String getOrig(){
		return this.cruceOrigen.getId();
	}

	public String getDest(){
		return this.cruceDestino.getId();
	}

	public int getVelocidadMaxima(){
		return this.velocidadMaxima;
	}

	public String getVehiculos(){
		String str = "";
		for(Vehiculo v: this.vehiculos){
			str += v.getId() + " ";
		}
		return str;
	}

	public List<Vehiculo> getLV(){
	    return this.vehiculos;
    }

	public CruceGenerico<?> getCruceDestino() {
		return this.cruceDestino;
	}

	public int getLength() {
		return this.longitud;
	}

	@Override
	public void avanza() {
		int numeroObstaculos = 0;
		for (Vehiculo v : this.vehiculos) {
			if (v.getTiempoAveria() > 0) {
				numeroObstaculos++;
			}
			int velNueva = calcularVelocidadBase() / calcularFactorReduccion(numeroObstaculos);
			if (v.getTiempoAveria() > 0) {
				v.setVelocidadActual(0);
			} else v.setVelocidadActual(velNueva);
			v.avanza();
		}
		this.vehiculos.sort(this.comparadorVehiculos);
	}


	public void entraVehiculo(Vehiculo v) {
		if (!this.vehiculos.contains(v)) {
			this.vehiculos.add(v);
		}
	}

	public void saleVehiculo(Vehiculo v) {
		this.vehiculos.remove(v);
	}

	public void entraVehiculoAlCruce(Vehiculo v) {
		this.cruceDestino.entraVehiculoAlCruce(this.id, v);
	}

	protected int calcularVelocidadBase() {
		return Math.min(this.velocidadMaxima,
				(this.velocidadMaxima / Math.max(this.vehiculos.size(), 1)) + 1);
	}

	protected int calcularFactorReduccion(int obstaculos) {
		return (obstaculos == 0) ? 1 : 2;
	}

	@Override
	public String getNombreSeccion() {
		return "road_report";
	}

	@Override
	public void completaDetallesSeccion(IniSection is) {
		String aux = this.createState();
		is.setValue("state", aux);
	}

	public String createState() {
		String aux = "";
		int i = 0;
		for (Vehiculo v : this.vehiculos) {
			aux += "(" + v.getId() + "," + v.getLocalizacion() + ")";
			if (i < this.vehiculos.size() - 1) {
				i++;
				aux += ",";
			}
		}
		return aux;
	}
}
