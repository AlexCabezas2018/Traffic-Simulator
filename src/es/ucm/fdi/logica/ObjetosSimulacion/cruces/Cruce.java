//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.cruces;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes.CarreteraEntrante;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

public class Cruce extends CruceGenerico<CarreteraEntrante> {

	public Cruce(String id){
		super(id);
	}

	@Override
	protected CarreteraEntrante creaCarreteraEntrante(Carretera c){
		return new CarreteraEntrante(c);
	}

	@Override
	protected void actualizaSemaforo(){
		if(this.indiceSemaforoVerde == -1){
			this.carreterasEntrantes.get(0).ponSemaforo(true);
			this.indiceSemaforoVerde++;

		}
		else {
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
			this.indiceSemaforoVerde++;

			this.indiceSemaforoVerde  = this.indiceSemaforoVerde % this.carreterasEntrantes.size();

			this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		}
	}

	@Override
	public String getNombreSeccion(){return super.getNombreSeccion();}

	@Override
	public void completaDetallesSeccion(IniSection sec){
		super.completaDetallesSeccion(sec);
	}

	@Override
	public String greenRoads(){
		int i = 0;
		String str = "[(";
		for(CarreteraEntrante ce: this.carreterasEntrantes) {
			if (ce.isSemaforo()) {
				str += ce.getId();
				str += ",green, ";
				str += "[";
				for(Vehiculo v: ce.getColaVehiculos()){
					str += v.getId();
					i++;
					if(i < ce.getColaVehiculos().size() - 1){
						str += ",";
					}
				}
				str += "]";
			}
		}
		str += ")]";
		return str.equals("[()]") ? "[]": str;
	}

	@Override
	public String redRoads(){
		int i = 0;
		String str = "[";
		for(CarreteraEntrante ce: this.carreterasEntrantes) {
			if (!ce.isSemaforo()) {
				str += "(";
				str += ce.getId();
				str += ",red";
				str += "[";
				for(Vehiculo v: ce.getColaVehiculos()){
					str += v.getId();
					i++;
					if(i < ce.getColaVehiculos().size()){
						str += ",";
					}
				}
				str += "]";
				str += ")";
			}
		}
		str += "]";
		return str;
	}
}
