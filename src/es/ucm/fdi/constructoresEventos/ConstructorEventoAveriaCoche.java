//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaAveriaCoche;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoAveriaCoche extends ConstructorEventos {

	public ConstructorEventoAveriaCoche() {
		this.etiqueta = "make_vehicle_faulty";
		this.claves = new String[] {"time", "vehicles", "duration",};
		this.valoresPorDefecto = new String[] {"", "", ""};
	}
	@Override
	public Evento parser(IniSection section) {
		if(!section.getTag().equals(this.etiqueta)) {
			return null;
		}
		else {
			return new EventoNuevaAveriaCoche(parseaIntNoNegativo(section, claves[0], 0),
												conjuntoIdsValidos(section, claves[1]),
												parseaInt(section, claves[2]));
		}
	}

	@Override
	public String toString() {
		return "Nueva averia coche";
	}

	@Override
	public String completaTemplate(){
		return ("vehicles = " + this.valoresPorDefecto[1] + "\nmax_speed = " + this.valoresPorDefecto[2] + "\n");
	}

}
