//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.Parser;

import es.ucm.fdi.constructoresEventos.*;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.ini.IniSection;

public class ParserEventos {
	private static ConstructorEventos[] constructoresEventos = {
			new ConstructorEventoNuevoCruce(),
			new ConstructorEventoNuevoCruceCongestionado(),
			new ConstructorEventoNuevoCruceCircular(),
			new ConstructorEventoNuevaAutopista(),
			new ConstructorEventoNuevoCamino(),
			new ConstructorEventoNuevaCarretera(),
			new ConstructorEventoNuevoVehiculo(),
			new ConstructorEventoNuevoCoche(),
			new ConstructorEventoNuevaBicicleta(),
			new ConstructorEventoAveriaCoche()
	};
	
	public static Evento parseaEvento(IniSection sec) {
		int i = 0;
		boolean seguir = true;
		Evento e = null;
		while(i < ParserEventos.constructoresEventos.length && seguir) {
			e = ParserEventos.constructoresEventos[i].parser(sec);
			if(e != null) seguir = false;
			else i++;
		}
		return e;
	}

	public static ConstructorEventos[] getC(){
		return constructoresEventos;
	}
	
}
