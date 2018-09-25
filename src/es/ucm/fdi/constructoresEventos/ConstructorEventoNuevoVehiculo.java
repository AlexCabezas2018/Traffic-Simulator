//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoVehiculo;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoVehiculo extends ConstructorEventos {

	public ConstructorEventoNuevoVehiculo() {
		this.etiqueta = "new_vehicle";
		this.claves = new String[] {"time", "id", "itinerary", "max_speed", "type"};
		this.valoresPorDefecto = new String[] {"", "", "", "", ""};

	}
	@Override
	public Evento parser(IniSection section) {
		if(!section.getTag().equals(this.etiqueta) || section.getValue(claves[4]) != null) {
			return null;
		}
		else {
			return new EventoNuevoVehiculo(parseaIntNoNegativo(section, claves[0], 0),
											identificadorValido(section, claves[1]),
											parseaInt(section, claves[3]),
											conjuntoIdsValidos(section, claves[2]));
		}
	}
	@Override
	public String toString() {
		return "Nuevo Vehiculo";
	}

	@Override
	public String completaTemplate(){
		return ("itinerary = " + this.valoresPorDefecto[2] + "\nmax_speed = " + this.valoresPorDefecto[3] + "\n");
	}

}
