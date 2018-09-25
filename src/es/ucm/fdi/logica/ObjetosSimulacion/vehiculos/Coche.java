//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica.ObjetosSimulacion.vehiculos;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.Cruce;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

import java.util.List;
import java.util.Random;

public class Coche extends Vehiculo {
	private Integer resistencia;
	private double probabilidad;
	private Random semilla;
	private Integer duracion;
	private Integer KilometroUltimaAveria;

	public Coche(String id, int velocidadMaxima, int resistencia, double prob, long semilla,
				 int duracionM, List<CruceGenerico<?>>it){
		super(id, velocidadMaxima, it);
		this.resistencia = resistencia;
		this.duracion = duracionM;
		this.probabilidad = prob;
		this.KilometroUltimaAveria = 0;
		this.semilla = new Random(semilla);
	}

	@Override
	public void avanza(){
		if(this.tiempoAveria > 0){
			this.KilometroUltimaAveria = this.kilometraje;
		}
		else{
			if(this.kilometraje - this.KilometroUltimaAveria > this.resistencia && this.semilla.nextDouble() <= this.probabilidad){ //ojo
				this.setTiempoAveria(this.semilla.nextInt(this.duracion) + 1);
			}
		}
		super.avanza();
	}

	@Override
	public void completaDetallesSeccion(IniSection is){
		super.completaDetallesSeccion(is);
		is.setValue("type", "car");
	}
}
