package es.ucm.fdi.logica.ObjetosSimulacion.cruces;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes.CarreteraEntranteConIntervalos;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

public class CruceCongestionado extends CruceGenerico<CarreteraEntranteConIntervalos> {

	public CruceCongestionado(String c){
		super(c);
	}

	@Override
	public CarreteraEntranteConIntervalos creaCarreteraEntrante(Carretera c){
		return new CarreteraEntranteConIntervalos(c, 0);
	}

	@Override
	public void actualizaSemaforo(){
		if(this.indiceSemaforoVerde == -1){
			this.indiceSemaforoVerde = buscarMaximo();
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).
					setIntervaloTiempo(Math.max((this.carreterasEntrantes.get(this.indiceSemaforoVerde).getColaVehiculos().size() / 2), 1));
		}
		else{
			CarreteraEntranteConIntervalos cci = this.carreterasEntrantes.get(this.indiceSemaforoVerde);
			if(cci.tiempoConsumido()){
				cci.ponSemaforo(false);
				cci.setUnidadesDeTiempoUsadas(0);
				cci.setUsoCompleto(false);
				this.indiceSemaforoVerde = buscarMaximo();
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).setUnidadesDeTiempoUsadas(0);
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).
						setIntervaloTiempo(Math.max((this.carreterasEntrantes.get(this.indiceSemaforoVerde).getColaVehiculos().size() / 2), 1));
			}
		}

	}

	private int buscarMaximo(){
		int indiceCarreteraActual = this.indiceSemaforoVerde;
		int i = 0;
		int max = -1;
		int indiceMaximo = this.indiceSemaforoVerde;

		while(i < this.carreterasEntrantes.size()){
			if(this.carreterasEntrantes.get(i).getColaVehiculos().size() > max && indiceCarreteraActual != i){
				max = this.carreterasEntrantes.get(i).getColaVehiculos().size();
				indiceMaximo = i;
			}
			else i++;
		}
		return indiceMaximo;
	}

	@Override
	public void completaDetallesSeccion(IniSection ini){
		String str = "";
		int i = 0;
		int j = 0;
		for(CarreteraEntranteConIntervalos ce: this.carreterasEntrantes){
			str += "(";
			str += ce.getCarretera().getId() + ",";
			if(ce.isSemaforo()){
				str += "green:";
				str += ce.getIntervaloTiempo() - ce.getUnidadesDeTiempoUsadas();
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
		ini.setValue("queues", str);
		ini.setValue("type", "mc");
	}

	@Override
	public String greenRoads(){
		int i = 0;
		String str = "[(";
		for(CarreteraEntranteConIntervalos ce: this.carreterasEntrantes) {
			if (ce.isSemaforo()) {
				str += ce.getId();
				str += ",green:";
				str += ce.getIntervaloTiempo() - ce.getUnidadesDeTiempoUsadas();
				str += ",[";
				for(Vehiculo v: ce.getColaVehiculos()){
					str += v.getId();
					i++;
					if(i < ce.getColaVehiculos().size()){
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
		int j = 0;
		String str = "[";
		for(CarreteraEntranteConIntervalos ce: this.carreterasEntrantes) {
			if (!ce.isSemaforo()) {
				str += "(";
				str += ce.getId();
				str += ",red";
				str += ",[";
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

	@Override
	public String getNombreSeccion(){
		return super.getNombreSeccion();
	}

}
