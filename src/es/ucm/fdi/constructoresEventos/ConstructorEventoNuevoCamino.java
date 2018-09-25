//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCamino;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCamino extends ConstructorEventoNuevaCarretera {
	public ConstructorEventoNuevoCamino(){
		super();
		this.claves = new String[] {"time", "id", "src", "dest", "max_speed", "length", "type"};
		this.valoresPorDefecto = new String[] {"", "", "", "", "", "", "dirt"};
	}

	@Override
	public Evento parser(IniSection section) {
		if(!section.getTag().equals(this.etiqueta) ||
				section.getValue("type") == null || !section.getValue("type").equals("dirt")){
			return null;
		}
		else return new EventoNuevoCamino(identificadorValido(section, claves[1]),
											parseaIntNoNegativo(section, claves[0], 0),
											identificadorValido(section, claves[2]),
											identificadorValido(section, claves[3]),
											parseaInt(section, claves[4]),
											parseaInt(section, claves[5]));
	}

	public String toString(){
		return "Nuevo camino";
	}

	@Override
	public String completaTemplate(){
		String ret = super.completaTemplate();
		ret += "type = " + this.valoresPorDefecto[6] + "\n";
		return ret;
	}
}
