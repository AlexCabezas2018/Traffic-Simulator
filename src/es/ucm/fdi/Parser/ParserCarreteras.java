//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.Parser;

import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ParserCarreteras {
	public static List<CruceGenerico<?>> parseaListaCruces(String[] itinerario, MapaCarreteras mapa){
		List<CruceGenerico<?>>listaCruces = new ArrayList<>();
		for(String str: itinerario){
			CruceGenerico<?> c = mapa.getCruce(str);
			listaCruces.add(c);
		}
		return listaCruces;
	}

	public static List<Vehiculo>parseaListaVehiculos(String[] vs, MapaCarreteras mapa){
		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		for(String str: vs){
			Vehiculo v = mapa.getVehiculo(str);
			lv.add(v);
		}
		return lv;
	}
}
