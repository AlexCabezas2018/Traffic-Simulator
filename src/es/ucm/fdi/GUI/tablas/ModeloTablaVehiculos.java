/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.tablas;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ModeloTablaVehiculos extends ModeloTabla<Vehiculo> {
    public ModeloTablaVehiculos(String[] columnsId, Controlador cont) {
        super(columnsId, cont);
        this.lista = new ArrayList<Vehiculo>();
    }

    @Override
    public Object getValueAt(int indiceFil, int indiceCol){
        Object s = null;
        switch(indiceCol){
            case 0: s = this.lista.get(indiceFil).getId(); break;
            case 1: s = this.lista.get(indiceFil).getCarretera(); break;
            case 2: s = this.lista.get(indiceFil).getL(); break;
            case 3: s = this.lista.get(indiceFil).getVelocidadActual(); break;
            case 4: s = this.lista.get(indiceFil).getKilometraje(); break;
            case 5: s = this.lista.get(indiceFil).getTiempoAveria(); break;
            case 6: s = this.lista.get(indiceFil).getItinerario(); break;
            default: assert(false);
        }
        return s;
    }

    @Override
    public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {

    }

    @Override
    public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = new ArrayList<>();
        for(Vehiculo v: map.getListaVehiculos()){
            this.lista.add(v);
        }
        this.fireTableStructureChanged();
    }

    @Override
    public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
        this.lista = new ArrayList<>();
        for(Vehiculo v: mapa.getListaVehiculos()){
            this.lista.add(v);
        }
        this.fireTableStructureChanged();
    }

    @Override
    public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.lista = null;
        this.fireTableStructureChanged();
    }
}
