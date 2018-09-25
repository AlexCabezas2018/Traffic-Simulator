//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaAutopista;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaAutopista extends ConstructorEventoNuevaCarretera {
	public ConstructorEventoNuevaAutopista(){
		super();
		this.claves = new String[] {"time", "id", "src", "dest", "max_speed", "length", "lanes", "type"};
		this.valoresPorDefecto = new String[] {"", "", "", "", "", "", "", "lanes"};
	}

	@Override
	public Evento parser(IniSection section) {
		String x = section.getValue("type");
		if(!section.getTag().equals(this.etiqueta) ||
				section.getValue("type") == null || !section.getValue("type").equals("lanes")){
			return null;
		}
		else return new EventoNuevaAutopista(identificadorValido(section, claves[1]),
											parseaIntNoNegativo(section, claves[0], 0),
											identificadorValido(section, claves[2]),
											identificadorValido(section, claves[3]),
											parseaInt(section, claves[4]),
											parseaInt(section, claves[5]),
											parseaInt(section, claves[6]));
	}

	@Override
	public String toString(){
		return "Nueva Autopista";
	}

	@Override
	public String completaTemplate(){
		String ret = super.completaTemplate();
		ret += "lanes = \ntype = " + this.valoresPorDefecto[7] + "\n";
		return ret;
	}
}
