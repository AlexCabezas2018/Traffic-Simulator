//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.constructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaBicicleta;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaBicicleta extends ConstructorEventoNuevoVehiculo{
	public ConstructorEventoNuevaBicicleta(){
		super();
		this.valoresPorDefecto = new String[] {"", "", "", "", "bike"};
	}

	@Override
	public Evento parser(IniSection ini){
		if(!ini.getTag().equals(this.etiqueta) ||
				ini.getValue("type") == null || !ini.getValue("type").equals("bike")){
			return null;
		}
		else {
			return new EventoNuevaBicicleta(identificadorValido(ini, claves[1]),
											parseaIntNoNegativo(ini, claves[0], 0),
											conjuntoIdsValidos(ini, claves[2]),
											parseaInt(ini, claves[3]));
		}
	}

	@Override
	public String toString(){
		return "Nueva bicicleta";
	}

	@Override
	public String completaTemplate(){
		String ret = "";
		ret += super.completaTemplate();
		ret += "type =" + this.valoresPorDefecto[4] + "\n";
		return ret;
	}
}
