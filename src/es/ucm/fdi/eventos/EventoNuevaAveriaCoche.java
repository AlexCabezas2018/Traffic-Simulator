//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.Parser.ParserCarreteras;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.List;

public class EventoNuevaAveriaCoche extends Evento{
	private String[] vehiculos;
	private Integer duration;
	

	public EventoNuevaAveriaCoche(int tiempo, String[] vehiculo, int duracion) {
		super(tiempo);
		this.vehiculos = vehiculo;
		this.duration = duracion;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		//Asignarle la duracion a los coches.
		List<Vehiculo> listaV = ParserCarreteras.parseaListaVehiculos(this.vehiculos, mapa);
		if(listaV != null){
			for(Vehiculo v: listaV){
				v.setTiempoAveria(this.duration);
			}
		}

	}

	@Override
	public String toString(){
		return "Nueva Averia";
	}
	
}
