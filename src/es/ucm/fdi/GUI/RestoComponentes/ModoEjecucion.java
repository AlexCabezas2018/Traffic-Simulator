/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

public enum ModoEjecucion {
	BATCH("batch"), GUI("gui");

	private	String modo;

	private ModoEjecucion(String mod){
		this.modo = mod;
	}

	private String getModo(){
		return this.modo;
	}
}
