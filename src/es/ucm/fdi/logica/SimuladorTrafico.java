//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.GUI.Observer.Observador;
import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;

public class SimuladorTrafico implements Observador<ObservadorSimulacionTrafico>{
	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	private List<ObservadorSimulacionTrafico> observadores;
	private OutputStream ficheroSalida;
	
	public SimuladorTrafico(){
		this.mapa = new MapaCarreteras();
		this.contadorTiempo = 0;
		Comparator<Evento> cmp = new Comparator<Evento>(){
			@Override
			public int compare(Evento o1, Evento o2) {
				if(o1.getTiempo() < o2.getTiempo()) return 1;
				else if(o1.getTiempo() == o2.getTiempo()) return 0;
				else return -1;
			}
		};
		this.eventos = new SortedArrayList<Evento>(cmp);
		this.observadores = new ArrayList<>();
		this.ficheroSalida = System.out;
	}
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida){
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		try {
			while (this.contadorTiempo <= limiteTiempo) {
				// ejecutar todos los eventos correspondienes a �this.contadorTiempo�
				//this.notificaNuevoEvento();
				int i = 0;
				int k = 0;
				while (i < this.eventos.size() && this.eventos.get(i) != null) {
					Evento e = this.eventos.get(i);
					if (e.getTiempo() == this.contadorTiempo) {
						e.ejecuta(mapa);
						k++;
					}
					i++;
				}
				if (k > 0) {
					for (int j = k; j > 0; j--) {
						this.eventos.remove(0);
					}
				}
				this.mapa.actualizar();
				this.notificaAvanza();
				String reporte = this.mapa.generarReporte(this.contadorTiempo);

				if (this.observadores.size() == 0) { //Entonces es que estamos en el modo de consola y por tanto utilizamos la salida hecha ya en la practica 4
					PrintStream pr = new PrintStream(ficheroSalida);
					if (pr != null) {
						pr.println(reporte);
					}
				}

				if (this.observadores.size() > 0) { //Eso quiere decir que estamos en el modo grafico y por ello utilizaremos esta salida
					try {
						this.ficheroSalida.write(reporte.getBytes());
					} catch (IOException ioe) {
						ErrorDeSimulacion err = new ErrorDeSimulacion("Problemas a la hora de escribir los eventos");
						this.notificaError(err);
					}
				}

				this.contadorTiempo++;
			}
		}
		catch(ErrorDeSimulacion err){ //Esto significaria que durante la ejecucion de algun evento, este ha lanzado la excepcion (por ejemplo, si la carretera esta repetida etc)
			this.notificaError(err);
			this.reinicia();
		}

	}

	/*public String generaInformesEspecificos(List<Vehiculo> v, List<Carretera> c, List<CruceGenerico<?>> cg){
		 return this.mapa.generarInformeEspecifico(this.contadorTiempo - 1, v, c, cg);
	}
	*/

	public void setSalida(OutputStream salida){
		if(salida == null){
			this.ficheroSalida = System.out;
		}
		else{
			this.ficheroSalida = salida;
		}
	}

	public void insertaEvento(Evento e){
		if(e != null){
			if(e.getTiempo() < this.contadorTiempo){
				ErrorDeSimulacion err = new ErrorDeSimulacion("Error en los eventos");
				this.notificaError(err);
				throw err;
			}
			else{
				this.eventos.add(e);
				this.notificaNuevoEvento();
			}
		}
		else{
			ErrorDeSimulacion err = new ErrorDeSimulacion("Error en los eventos");
			this.notificaError(err);
			throw err;
		}
	}

	private void notificaNuevoEvento(){
		for(ObservadorSimulacionTrafico o: this.observadores){
			o.addEvento(this.contadorTiempo, this.mapa, this.eventos);
		}
	}

	private void notificaError(ErrorDeSimulacion e){
		for(ObservadorSimulacionTrafico o: this.observadores){
			o.errorSimulacion(this.contadorTiempo, this.mapa, this.eventos, e);
		}
	}

	private void notificaReinicio(){
		for(ObservadorSimulacionTrafico o: this.observadores){
			o.reinicia(this.contadorTiempo, this.mapa, this.eventos);
		}
	}

	public void reinicia(){
		this.contadorTiempo = 0;
		this.mapa.reinicia();
		
		/*this.mapa = new MapaCarreteras();
		
		
		Comparator<Evento> cmp = new Comparator<Evento>(){
			@Override
			public int compare(Evento o1, Evento o2) {
				if(o1.getTiempo() < o2.getTiempo()) return 1;
				else if(o1.getTiempo() == o2.getTiempo()) return 0;
				else return -1;
			}
		};
		*/
		this.eventos.clear();

		this.notificaReinicio();
	}

	public void notificaAvanza(){
		for(ObservadorSimulacionTrafico o: this.observadores){
			o.avanza(this.contadorTiempo, this.mapa, this.eventos);
		}
	}

	@Override
	public void addObservador(ObservadorSimulacionTrafico o){
		if(o != null && !this.observadores.contains(o)){
			this.observadores.add(o);
		}
	}

	@Override
	public void removeObservador(ObservadorSimulacionTrafico o){
		if(o != null && this.observadores.contains(o)){
			this.observadores.remove(o);
		}
	}
}
