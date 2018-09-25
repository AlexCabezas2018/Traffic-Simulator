package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruceCircular;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCircular extends ConstructorEventoNuevoCruce {
	public ConstructorEventoNuevoCruceCircular(){
		this.claves = new String[] {"id", "time", "max_time_slice", "min_time_slice", "type"};
		this.valoresPorDefecto = new String[]{"", "", "", "", "rr"};
	}

	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta) || !section.getValue("type").equals("rr")){
			return null;
		}
		else{
			return new EventoNuevoCruceCircular(parseaIntNoNegativo(section, claves[1], 0),
												identificadorValido(section, claves[0]),
												parseaInt(section, claves[3]),
												parseaInt(section, claves[2]));
		}
	}

	@Override
	public String toString(){
		return "Nuevo Cruce circular";
	}

	@Override
	public String completaTemplate(){
		return "max_time_slice = \nmin_time_slice = \ntype = " + this.valoresPorDefecto[4] + "\n";
	}

}
