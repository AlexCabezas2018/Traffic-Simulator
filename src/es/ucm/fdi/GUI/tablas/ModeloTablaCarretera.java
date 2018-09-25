/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;

import java.util.ArrayList;
import java.util.List;

public class ModeloTablaCarretera extends ModeloTabla<Carretera> {
    public ModeloTablaCarretera(String[] columnsId, Controlador cont) {
        super(columnsId, cont);
        this.lista = new ArrayList<Carretera>();
    }

    @Override
    public Object getValueAt(int indiceFila, int indiceColumna){
        Object s = null;
        switch(indiceColumna){
            case 0: s = this.lista.get(indiceFila).getId(); break;
            case 1: s = this.lista.get(indiceFila).getOrig(); break;
            case 2: s = this.lista.get(indiceFila).getDest(); break;
            case 3: s = this.lista.get(indiceFila).getLength(); break;
            case 4: s = this.lista.get(indiceFila).getVelocidadMaxima(); break;
            case 5: s = this.lista.get(indiceFila).getVehiculos(); break;
            default: assert(false);
        }
        return s;
    }


    @Override
    public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {

    }

    @Override
    public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = new ArrayList<Carretera>();
        for(Carretera c: map.getListaCarreteras()){
            this.lista.add(c);
        }
        this.fireTableDataChanged();

    }

    @Override
    public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
        this.lista = new ArrayList<Carretera>();
        for(Carretera c: mapa.getListaCarreteras()){
            this.lista.add(c);
        }
        this.fireTableDataChanged();
    }

    @Override
    public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = null;
        this.fireTableDataChanged();
    }
}
