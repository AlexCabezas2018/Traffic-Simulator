//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.carreteras;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class Camino extends Carretera {
	private Integer estadoCamino;
	public Camino(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest){
		super(id, length, maxSpeed, src, dest);
	}

	@Override
	public int calcularVelocidadBase(){
		return this.velocidadMaxima;
	}

	@Override
	public int calcularFactorReduccion(int obstaculos){
		return obstaculos + 1;
	}

	@Override
	public void completaDetallesSeccion(IniSection ini){
		String aux = createState();
		ini.setValue("state", aux);
		ini.setValue("type", "dirt");
	}

}
