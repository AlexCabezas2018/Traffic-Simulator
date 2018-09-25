//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruce;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruce extends ConstructorEventos{
	public ConstructorEventoNuevoCruce(){
		this.etiqueta = "new_junction";
		this.claves = new String[] { "time", "id", };
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta) || section.getValue("type") != null){
			return null;
		}
		else{
			return new EventoNuevoCruce(ConstructorEventos.parseaIntNoNegativo(section, claves[0], 0),
										ConstructorEventos.identificadorValido(section, claves[1]));
		}
	}
	
	@Override
	public String toString(){return "Nuevo Cruce";}

	@Override
	public String completaTemplate(){
		return "";
	}
	
	
}
