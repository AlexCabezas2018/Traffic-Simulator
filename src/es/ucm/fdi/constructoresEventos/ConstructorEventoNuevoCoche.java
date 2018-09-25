//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCoche;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.MapaCarreteras;

public class ConstructorEventoNuevoCoche extends ConstructorEventoNuevoVehiculo {
	public ConstructorEventoNuevoCoche(){
		super();
		this.claves = new String[]{"id", "time", "itinerary", "max_speed", "type",
									"resistance", "fault_probability", "max_fault_duration", "seed"};
		this.valoresPorDefecto = new String[] {"", "", "", "", "car", "", "", "", ""};
	}

	@Override
	public Evento parser(IniSection ini){
		if(!ini.getTag().equals(this.etiqueta) ||
				ini.getValue("type") == null || !ini.getValue(claves[4]).equals("car")){
			return null;
		}
		else{
			return new EventoNuevoCoche(parseaIntNoNegativo(ini, claves[1], 0),
										identificadorValido(ini, claves[0]),
										conjuntoIdsValidos(ini, claves[2]),
										parseaInt(ini, claves[3]),
										parseaInt(ini, claves[5]),
										parseaDouble(ini, claves[6]),
										parseaInt(ini, claves[7]),
										parseaLong(ini, claves[8]));
		}
	}

	@Override
	public String toString(){
		return "Nuevo coche";
	}

	@Override
	public String completaTemplate(){
		String g = super.completaTemplate();
		g += "resistance = \nfault_probability = \n max_fault_duration = \nseed = \ntype = " + this.valoresPorDefecto[4] + "\n";
		return g;
	}



}
