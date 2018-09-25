/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.Observer;

public interface Observador<T> {
	void addObservador(T o);
	void removeObservador(T o);
}
