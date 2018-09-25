//Practica 4
//Alejandro Cabezas Garriguez
//Sergio Santa Clotilde Ruiz

package es.ucm.fdi.logica;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.Parser.ParserEventos;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;

public class Controlador {
	private SimuladorTrafico simulador;
	private OutputStream ficheroSalida;
	private InputStream ficheroEntrada;
	private int pasosSimulacion;
	
	public Controlador(SimuladorTrafico sim, int tiempo, InputStream is, OutputStream os){
		this.simulador = sim;
		this.pasosSimulacion = tiempo;
		this.ficheroEntrada = is;
		this.ficheroSalida = os;
	}
	
	public void cargarEventos(InputStream is){
		Ini ini;
		try{
			ini = new Ini(is);
		}
		catch(IOException e){
			throw new ErrorDeSimulacion("Error en la lectura de eventos: " + e);
		}
		for(IniSection sec: ini.getSections()){
			Evento e = ParserEventos.parseaEvento(sec);
			if(e != null) this.simulador.insertaEvento(e);
			else throw new ErrorDeSimulacion("Evento desconocido: " + sec.getTag());
		}
		
		
	}

	public void setSalida(OutputStream os){
		this.simulador.setSalida(os);
	}

	/*
	public String generaInformes(List<Vehiculo> v, List<Carretera> c, List<CruceGenerico<?>> cg) throws IOException{
	     return this.simulador.generaInformesEspecificos(v, c, cg);
    }
    */
	
	public void ejecuta(){
		this.cargarEventos(this.ficheroEntrada);
		this.simulador.ejecuta(this.pasosSimulacion, this.ficheroSalida);
	}

	public void ejecuta(int pasos){
		this.simulador.ejecuta(pasos, this.ficheroSalida);
	}

	public void reinicia(){
		this.simulador.reinicia();
	}

	public void addObserver(ObservadorSimulacionTrafico o){
		this.simulador.addObservador(o);
	}

	public void removeObserver(ObservadorSimulacionTrafico o){
		this.simulador.removeObservador(o);
	}

	public int getSteps(){
		return this.pasosSimulacion;
	}
	
}
