//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica;

import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class MapaCarreteras {
	private List<Carretera>carreteras;
	private List<CruceGenerico<?>> cruces;
	private List<Vehiculo> vehiculos;
	private static final String separador = System.getProperty("line.separator");

	private Map<String, Carretera> mapaDeCarreteras;
	private Map<String, CruceGenerico<?>> mapaDeCruces;
	private Map<String, Vehiculo> mapaDeVehiculos;

 public MapaCarreteras(){
 	this.carreteras = new ArrayList<Carretera>();
 	this.cruces = new ArrayList<CruceGenerico<?>>();
 	this.vehiculos = new ArrayList<Vehiculo>();

	this.mapaDeCarreteras = new HashMap<String, Carretera>();
	this.mapaDeCruces = new HashMap<String, CruceGenerico<?>>();
	this.mapaDeVehiculos = new HashMap<String, Vehiculo>();
 }

 public void reinicia() {
	 carreteras.clear();;
	 cruces.clear();
	 vehiculos.clear();
		mapaDeCarreteras.clear();
		mapaDeCruces.clear();
		mapaDeVehiculos.clear();
		
	}

 	public void addCruce(String idCruce, CruceGenerico<?> cruce){
		if(!this.mapaDeCruces.containsKey(idCruce)){
			this.cruces.add(cruce);
			this.mapaDeCruces.put(idCruce, cruce);
		}
		else throw new ErrorDeSimulacion("Error, cruce ya repetido");
 	}

 	public void addVehiculo(String idVehiculo, Vehiculo v) {
 		if(!this.mapaDeVehiculos.containsKey(idVehiculo)){
 			this.vehiculos.add(v);
 			this.mapaDeVehiculos.put(idVehiculo, v);
 			v.moverASiguienteCarretera();
		}
		else throw new ErrorDeSimulacion("Error, vehiculo ya repetido");
	}

	public void addCarretera(String idCarretera, CruceGenerico<?> orig, CruceGenerico<?> dest, Carretera road){
 		if(!this.mapaDeCarreteras.containsKey(idCarretera)) {
			this.carreteras.add(road);
			this.mapaDeCarreteras.put(idCarretera, road);
			orig.addCarreteraSalienteAlCruce(dest, road);
			dest.addCarreteraEntranteAlCruce(idCarretera, road);
		}
		else throw new ErrorDeSimulacion("Error, carretera ya añadida");
	}

	public String generarReporte(int tiempo){
 		String str = "";
		int i = 0;
 		for(CruceGenerico<?> c: this.cruces){
 			str += c.generaInforme(tiempo);
			str += separador;
		}
		for(Carretera c: this.carreteras){
			str += c.generaInforme(tiempo);
			str += separador;
		}
		for(Vehiculo v: this.vehiculos){
			str += v.generaInforme(tiempo);
			i++;
			if(i <= this.vehiculos.size() - 1){
				str += separador;
			}
		}
		return str;
	}

	

	public void actualizar(){
		for(Carretera c: this.carreteras){
			c.avanza();
		}
 		for(CruceGenerico<?> c: this.cruces){
 			c.avanza();
		}
	}

	 public CruceGenerico<?> getCruce(String id){
 		CruceGenerico<?> c = this.mapaDeCruces.get(id);
 		if(c != null) return c;
		else throw new ErrorDeSimulacion("No existe el cruce. Error de parseo");
	 }

	 public Carretera getCarretera(String id){
		 Carretera c = this.mapaDeCarreteras.get(id);
		 if(c != null) return c;
		 else throw new ErrorDeSimulacion("No existe la carretera. Error de parseo");
	 }
	 public Vehiculo getVehiculo(String id){
		 Vehiculo c = this.mapaDeVehiculos.get(id);
		 if(c != null) return c;
		 else throw new ErrorDeSimulacion("No existe el vehiculo. Error de parseo");
	 }

	 public List<Vehiculo> getListaVehiculos(){
        return this.vehiculos;
     }

     public List<Carretera> getListaCarreteras(){
        return this.carreteras;
     }

     public List<CruceGenerico<?>> getListaCruces(){
        return this.cruces;
     }


	
 }