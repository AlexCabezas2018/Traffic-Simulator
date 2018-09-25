package es.ucm.fdi.logica.ObjetosSimulacion.cruces;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.CarreterasEntrantes.CarreteraEntranteConIntervalos;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

public class CruceCircular extends CruceGenerico<CarreteraEntranteConIntervalos> {

	protected int minValorIntervalo;
	protected int maxValorIntervalo;


	public CruceCircular(String id, int maxIntervalor, int minIntervalo){
		super(id);
		this.maxValorIntervalo = maxIntervalor;
		this.minValorIntervalo = minIntervalo;
	}

	@Override
	public void actualizaSemaforo(){
		if(this.indiceSemaforoVerde == -1){
			this.carreterasEntrantes.get(0).ponSemaforo(true);
			this.indiceSemaforoVerde++;
		}
		else{
			CarreteraEntranteConIntervalos cci = this.carreterasEntrantes.get(this.indiceSemaforoVerde);
			if(cci.tiempoConsumido()){
				cci.ponSemaforo(false);
				if(cci.usoCompleto()){
					cci.setIntervaloTiempo(Math.min(cci.getIntervaloTiempo() + 1, this.maxValorIntervalo));
				}
				else if(!cci.usada()) {
						cci.setIntervaloTiempo(Math.max(cci.getIntervaloTiempo() - 1, this.minValorIntervalo));
				}

				cci.setUsoCompleto(true);
				cci.setUsadaPorUnVehiculo(false);
				cci.setUnidadesDeTiempoUsadas(0);
				this.indiceSemaforoVerde++;
				this.indiceSemaforoVerde = this.indiceSemaforoVerde % this.carreterasEntrantes.size();
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
			}
		}


	}

	@Override
	public CarreteraEntranteConIntervalos creaCarreteraEntrante(Carretera c){
		return new CarreteraEntranteConIntervalos(c, this.maxValorIntervalo);
	}

	@Override
	public void completaDetallesSeccion(IniSection ini) {
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
		ini.setValue("type", "rr");
	}

	@Override
	public String getNombreSeccion() {
		return super.getNombreSeccion();
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
					if(i <= ce.getColaVehiculos().size() - 1){
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

}
