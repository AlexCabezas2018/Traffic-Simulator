/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.GUI.Paneles.PanelBotones;
import es.ucm.fdi.GUI.Paneles.PanelObjSim;
import es.ucm.fdi.GUI.VentanaPrincipal;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogoInformes extends JDialog implements ObservadorSimulacionTrafico {
    public static final char TECLA_LIMPIAR = 'c';

    private PanelBotones panelBotones;
    private PanelObjSim<Vehiculo> panelVehiculo;
    private PanelObjSim<Carretera> panelCarreteras;
    private PanelObjSim<CruceGenerico<?>> panelCruces;
    private VentanaPrincipal ventana;

    public DialogoInformes(VentanaPrincipal mainWindow, Controlador controller){
        controller.addObserver(this);
        this.ventana = mainWindow;
        this.initGUI();
    }

    public void generarReporte(){
        ventana.generaInformes();
    }

    private void initGUI(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.setContentPane(panel);
        this.panelVehiculo = new PanelObjSim<Vehiculo>("Vehiculos");
        this.panelCarreteras = new PanelObjSim<Carretera>("Carreteras");
        this.panelCruces = new PanelObjSim<CruceGenerico<?>>("Cruces");
        this.panelBotones = new PanelBotones(this);

        InformationPanel panelInfo = new InformationPanel();
        panel.add(panelInfo, BorderLayout.PAGE_START);

        JPanel panelAreas = new JPanel();
        panelAreas.setLayout(new GridLayout(1, 3));
        panelAreas.add(this.panelVehiculo);
        panelAreas.add(this.panelCarreteras);
        panelAreas.add(this.panelCruces);

        panel.add(panelAreas);
        this.add(this.panelBotones);
        this.pack();
    }

    public void mostrar(){
        this.setVisible(true);
    }

    private void setMapa(MapaCarreteras mapa){
        this.panelVehiculo.setList(mapa.getListaVehiculos());
        this.panelCarreteras.setList(mapa.getListaCarreteras());
        this.panelCruces.setList(mapa.getListaCruces());
    }

    public List<Vehiculo> getVehiculosSeleccionados(){
        return this.panelVehiculo.getSelectedItems();
    }

    public List<Carretera> getCarreterasSeleccionadas(){
        return this.panelCarreteras.getSelectedItems();
    }

    public List<CruceGenerico<?>> getCrucesSeleccionados(){
        return this.panelCruces.getSelectedItems();
    }

    @Override
    public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {

    }

    @Override
    public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.setMapa(map);
    }

    @Override
    public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
        this.setMapa(mapa);
    }

    @Override
    public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
        this.setMapa(map);
    }
}
