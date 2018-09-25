package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruceCongestionado;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCongestionado extends ConstructorEventoNuevoCruce{
	public ConstructorEventoNuevoCruceCongestionado(){
		super();
		this.claves = new String[] {"id", "time", "type"};
		this.valoresPorDefecto = new String[] {"", "", "mc"};
	}

	@Override
	public Evento parser(IniSection ini){
		if(!ini.getTag().equals(this.etiqueta) || !ini.getValue("type").equals("mc")){
			return null;
		}
		else{
			return new EventoNuevoCruceCongestionado(ConstructorEventos.parseaIntNoNegativo(ini, claves[1], 0),
													ConstructorEventos.identificadorValido(ini, claves[0]));
		}
	}

	@Override
	public String toString(){
		return "Nuevo Cruce congestionado";
	}

	@Override
	public String completaTemplate(){
		return "type = " + this.valoresPorDefecto[2] + "\n";
	}

}
