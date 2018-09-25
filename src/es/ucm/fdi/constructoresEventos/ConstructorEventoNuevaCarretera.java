//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaCarretera;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaCarretera extends ConstructorEventos {
	public ConstructorEventoNuevaCarretera(){
		this.etiqueta = "new_road";
		this.claves = new String[] { "time", "id", "src", "dest",
									"max_speed", "length", "lanes" };
		
	}
	
	@Override
	public Evento parser(IniSection section) {
		if(!section.getTag().equals(this.etiqueta) || section.getValue("type") != null) {
			return null;
		}
		else {
			return new EventoNuevaCarretera(parseaIntNoNegativo(section, claves[0], 0),
											identificadorValido(section, claves[1]),
											identificadorValido(section, claves[2]),
											identificadorValido(section, claves[3]),
											parseaInt(section, claves[4]),
											parseaInt(section, claves[5]));
		}
	}
	
	@Override
	public String toString() {
		return "Nueva carretera";
	}

	@Override
	public String completaTemplate(){
		return "src = \ndest = \nmax_speed = \nlength = \n";
	}
	

}
