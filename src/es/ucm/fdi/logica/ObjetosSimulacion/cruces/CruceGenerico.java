package es.ucm.fdi.logica.ObjetosSimulacion.cruces;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.ObjetoSimulacion;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes.CarreteraEntrante;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class CruceGenerico<T extends CarreteraEntrante> extends ObjetoSimulacion {
	protected int indiceSemaforoVerde;
	protected List<T> carreterasEntrantes;

	protected Map<String, T> mapaCarreterasEntrantes;
	protected Map<CruceGenerico<?>, Carretera> mapaCarreterasSalientes;

	public CruceGenerico(String id){
		super(id);
		this.indiceSemaforoVerde = -1;
		this.carreterasEntrantes = new ArrayList<T>();
		this.mapaCarreterasEntrantes = new HashMap<String, T>();
		this.mapaCarreterasSalientes = new HashMap<CruceGenerico<?>, Carretera>();
	}

	public Carretera carreteraHaciaElCruce(CruceGenerico<?> cruce){
		return this.mapaCarreterasSalientes.get(cruce);
	}

	public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera){
		T ri = creaCarreteraEntrante(carretera);
		this.carreterasEntrantes.add(ri);
		this.mapaCarreterasEntrantes.put(idCarretera, ri);
	}

	public List<T> getCarreteras(){
		return this.carreterasEntrantes;
	}

	abstract protected T creaCarreteraEntrante(Carretera c);

	public void addCarreteraSalienteAlCruce(CruceGenerico<?> destino, Carretera car){
		this.mapaCarreterasSalientes.put(destino, car);
	}

	public void entraVehiculoAlCruce(String idCarretera, Vehiculo v){
		T carreteraAEntrar = this.mapaCarreterasEntrantes.get(idCarretera);
		carreteraAEntrar.addVehicletoRoad(v);
	}

	@Override
	public void avanza(){
		if(this.carreterasEntrantes.size() > 0){
			T ce;
			if(this.indiceSemaforoVerde != -1) {
				ce = this.carreterasEntrantes.get(this.indiceSemaforoVerde);
				ce.anvanzaPrimerVehiculo();
			}
			this.actualizaSemaforo();
		}
	}

	abstract protected void actualizaSemaforo();

	@Override
	public String getNombreSeccion(){return "junction_report";}

	@Override
	public void completaDetallesSeccion(IniSection is){
		String str = "";
		int i = 0;
		int j = 0;
		for(CarreteraEntrante ce: this.carreterasEntrantes){
			str += "(";
			str += ce.getCarretera().getId() + ",";
			if(ce.isSemaforo()){
				str += "green";
			}
			else{
				str += "red";
			}
			str += ",";
			str += "[";
			for(Vehiculo v: ce.getColaVehiculos()){
				str += v.getId();
				j++;
				if(j < ce.getColaVehiculos().size()){
					str += ",";
				}
			}
			j = 0;
			str += "]";
			str += ")";
			i++;
			if(i <= this.carreterasEntrantes.size() - 1){
				str += ",";
			}
		}
		is.setValue("queues", str);
	}

	public abstract String greenRoads();
	public abstract String redRoads();

}
