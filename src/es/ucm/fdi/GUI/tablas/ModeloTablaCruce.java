/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;

import java.util.ArrayList;
import java.util.List;

public class ModeloTablaCruce extends ModeloTabla<CruceGenerico<?>> {
    public ModeloTablaCruce(String[] columnsId, Controlador cont) {
        super(columnsId, cont);
        this.lista = new ArrayList<>();
    }

    @Override
    public Object getValueAt(int indiceFila, int indiceCol){
        Object s = null;
        switch(indiceCol){
            case 0: s = this.lista.get(indiceFila).getId(); break;
            case 1: s = this.lista.get(indiceFila).greenRoads(); break;
            case 2: s = this.lista.get(indiceFila).redRoads(); break;
            default: assert(false);
        }
        return s;
    }

    @Override
    public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {

    }

    @Override
    public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = new ArrayList<CruceGenerico<?>>();
        for(CruceGenerico<?>cg: map.getListaCruces()){
            this.lista.add(cg);
        }
        this.fireTableStructureChanged();
    }

    @Override
    public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
        this.lista = new ArrayList<CruceGenerico<?>>();
        for(CruceGenerico<?>cg: mapa.getListaCruces()){
            this.lista.add(cg);
        }
        this.fireTableStructureChanged();
    }

    @Override
    public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = null;
        this.fireTableStructureChanged();
    }
}
