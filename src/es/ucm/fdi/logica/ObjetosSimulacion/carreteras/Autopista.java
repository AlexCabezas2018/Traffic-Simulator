//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.carreteras;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

public class Autopista extends Carretera {
	private Integer carriles;
	public Autopista(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest, int lanes){
		super(id, length, maxSpeed, src, dest);
		this.carriles = lanes;
	}

	@Override
	public int calcularVelocidadBase(){
		return (Math.min(this.velocidadMaxima,
				((this.velocidadMaxima * this.carriles)
						/Math.max(this.vehiculos.size(), 1)) + 1));
	}

	@Override
	public int calcularFactorReduccion(int obstaculos){
		return obstaculos < this.carriles ? 1:2;
	}

	@Override
	public void completaDetallesSeccion(IniSection ini){
		String aux = createState();
		ini.setValue("state", aux);
		ini.setValue("type", "lanes");
	}

}
