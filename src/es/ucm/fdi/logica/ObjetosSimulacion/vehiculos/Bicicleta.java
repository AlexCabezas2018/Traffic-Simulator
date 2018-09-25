//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz


package es.ucm.fdi.logica.ObjetosSimulacion.vehiculos;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

import java.util.List;

public class Bicicleta extends Vehiculo {
	public Bicicleta(String id, int velocidadMaxima, List<CruceGenerico<?>> itinerario){
		super(id, velocidadMaxima, itinerario);
	}

	@Override
	public void setTiempoAveria(Integer duracionAveria){
		if(this.velocidadActual >= this.velocidadMaxima/2){
			if(this.carretera != null){
				this.tiempoAveria += duracionAveria;
				this.velocidadActual = 0;
			}
		}
	}

	@Override
	public void completaDetallesSeccion(IniSection is){
		super.completaDetallesSeccion(is);
		is.setValue("type", "bike");
	}
}
