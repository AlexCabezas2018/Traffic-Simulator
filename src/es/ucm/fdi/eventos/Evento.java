//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.eventos;

import es.ucm.fdi.logica.MapaCarreteras;

public abstract class Evento {
	protected Integer tiempo;
	
	public Evento(int tiempo) {
		this.tiempo = tiempo;
	}
	
	public int getTiempo() {
		return this.tiempo;
	}
	public abstract void ejecuta(MapaCarreteras mapa);

}
