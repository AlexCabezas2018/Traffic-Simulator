/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.GUI.Paneles.PanelBarraEstado;
import es.ucm.fdi.GUI.RestoComponentes.BarraMenu;
import es.ucm.fdi.GUI.RestoComponentes.ComponenteMapa;
import es.ucm.fdi.GUI.RestoComponentes.DialogoInformes;
import es.ucm.fdi.GUI.RestoComponentes.MyToolBar;
import es.ucm.fdi.GUI.panelesTexto.PanelAreaTexto;
import es.ucm.fdi.GUI.panelesTexto.PanelEditorEventos;
import es.ucm.fdi.GUI.panelesTexto.PanelInformes;
import es.ucm.fdi.GUI.tablas.ModeloTablaCarretera;
import es.ucm.fdi.GUI.tablas.ModeloTablaCruce;
import es.ucm.fdi.GUI.tablas.ModeloTablaEventos;
import es.ucm.fdi.GUI.tablas.ModeloTablaVehiculos;
import es.ucm.fdi.GUI.tablas.PanelTabla;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;
import es.ucm.fdi.logica.ObjetosSimulacion.carreteras.Carretera;
import es.ucm.fdi.logica.ObjetosSimulacion.cruces.CruceGenerico;
import es.ucm.fdi.logica.ObjetosSimulacion.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.List;

public class VentanaPrincipal extends JFrame implements ObservadorSimulacionTrafico {

	//Parte del modelo Vista-Controlador
	private Controlador controlador;
	private File ficheroActual;
	private boolean modoSalida;

	//Hilos
	private Thread hilo;

	public static Border bordePorDefecto = BorderFactory.createLineBorder(Color.black, 2);

	//panel superior
	private static final String[] columnaIdEventos = {"#", "Tiempo", "Tipo"};

	private PanelAreaTexto panelEditorEventos;
	private PanelAreaTexto panelInformes;
	private PanelTabla<Evento> panelColaEventos;

	//Menu y toolbar
	private JFileChooser fc;
	private MyToolBar toolbar;
	private BarraMenu menuBar;


	//panel grafico
	private ComponenteMapa componenteMapa;

	//status bar (info at the bottom of the window)
	private PanelBarraEstado panelBarraEstado;

	//Panel inferior
	static private final String[] columnIdVehiculo = {"ID", "Carretera", "Localizacion", "Vel.", "Km", "Tiempo. Ave.", "Itinerario"};
	static private final String[] columnIdCarretera = {"ID", "Origen", "Destino", "Longitud", "Vel. Max", "Vehiculos"};
	static private final String[] columnIdCruce = {"ID", "Verde", "Rojo"};

	private PanelTabla<Vehiculo> panelVehiculos;
	private PanelTabla<Carretera> panelCarretera;
	private PanelTabla<CruceGenerico<?>> panelCruces;

	//Report dialog
	private DialogoInformes dialogoInformes;

	//Constructora
	public VentanaPrincipal(String ficheroEntrada, Controlador ctrl){
		super("Simulador de trafico");
		super.setExtendedState(JFrame.MAXIMIZED_BOTH); //Se ejecuta en pantalla completa

		this.modoSalida = false;
		this.ficheroActual = ficheroEntrada != null ? new File(ficheroEntrada) : null;
		this.fc = new JFileChooser();
		this.controlador = ctrl;
		this.toolbar = new MyToolBar(this, this.controlador);
		this.panelEditorEventos = new PanelEditorEventos("Eventos: ", " ", true, this);
		this.panelInformes = new PanelInformes("Informes: ", false, this.controlador);
		this.panelColaEventos = new PanelTabla<Evento>("Cola de Eventos: ", new ModeloTablaEventos(VentanaPrincipal.columnaIdEventos, this.controlador));
		this.panelVehiculos = new PanelTabla<Vehiculo>("Vehiculos", new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo, this.controlador));
		this.panelCarretera = new PanelTabla<Carretera>("Carreteras", new ModeloTablaCarretera(VentanaPrincipal.columnIdCarretera, this.controlador));
		this.panelCruces = new PanelTabla<CruceGenerico<?>>("Cruces", new ModeloTablaCruce(VentanaPrincipal.columnIdCruce, this.controlador));
		this.componenteMapa = new ComponenteMapa(this.controlador);
		this.dialogoInformes = new DialogoInformes(this, this.controlador);
		this.menuBar = new BarraMenu(this, this.controlador);

		this.initGUI();

		if(this.ficheroActual != null){
			try{
				this.cargarFicheroEnPanel(this.ficheroActual);
			}
			catch(IOException ioe){
				this.muestraDialogoError("Ha habido un error cargando el fichero inicial " + ioe.getMessage());
			}
		}

		ctrl.addObserver(this);

	}


	private void initGUI(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent windowEvent) { }

			@Override
			public void windowClosing(WindowEvent windowEvent) {
				salir();
			}

			@Override
			public void windowClosed(WindowEvent windowEvent) { }
			@Override
			public void windowIconified(WindowEvent windowEvent) { }
			@Override
			public void windowDeiconified(WindowEvent windowEvent) { }
			@Override
			public void windowActivated(WindowEvent windowEvent) { }
			@Override
			public void windowDeactivated(WindowEvent windowEvent) { }
		});
		JPanel panelPrincipal = createPanelPrincipal();
		this.setContentPane(panelPrincipal);

		//Barra Estado
		this.addBarraEstado(panelPrincipal);

		//Panel Central (Lo que contiene el resto de componentes)
		JPanel panelCentrar = createPanelCentral();
		panelPrincipal.add(panelCentrar, BorderLayout.CENTER);

		//Barra de herramientas
		panelPrincipal.add(this.toolbar, BorderLayout.PAGE_START);

		//Panel Superior
		this.creaPanelSuperior(panelCentrar);

		//Panel inferior
		this.creaPanelInferior(panelCentrar);

		//Menu
		this.setJMenuBar(this.menuBar);

		this.pack();
		this.setVisible(true);
	}

	public String getTextoEditorEventos() {
		return panelEditorEventos.getTexto();
	}

	public void activarDialogoInformes(){
		this.dialogoInformes.mostrar();
	}

	public void salir(){
		int n = JOptionPane.showOptionDialog(this, "�Quiere salir?", "Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null );
		if(n == 0){
			System.exit(0);
		}
	}

	private void addBarraEstado(JPanel panel){
		this.panelBarraEstado = new PanelBarraEstado("Bienvenido al Simulador", this.controlador);
		panel.add(this.panelBarraEstado, BorderLayout.SOUTH);
	}

	public void setMensaje(String mensaje){
		this.panelBarraEstado.setMensaje(mensaje);
	}

	private JPanel createPanelPrincipal(){
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		return panelPrincipal;
	}

	private JPanel createPanelCentral(){
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2, 1));
		return panelCentral;
	}

	private void creaPanelSuperior(JPanel panelCentral){
		JPanel panelS = new JPanel();
		panelS.setLayout(new BoxLayout(panelS, BoxLayout.LINE_AXIS));
		panelS.add(this.panelEditorEventos);
		panelS.add(this.panelColaEventos);
		panelS.add(this.panelInformes);
		panelCentral.add(panelS);
	}

	private void creaPanelInferior(JPanel panelCentral){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		JPanel panelTablas = new JPanel();
		panelTablas.setLayout(new GridLayout(3, 1));
		panelTablas.add(this.panelVehiculos);
		panelTablas.add(this.panelCarretera);
		panelTablas.add(this.panelCruces);



		panel.add(panelTablas);
		panel.add(this.componenteMapa);
		panel.add(new JScrollPane(this.componenteMapa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		panelCentral.add(panel);

	}
	
	
	public String generarInformeEspecifico(int tiempo, List<Vehiculo> v, List<Carretera> c, List<CruceGenerico<?>> cg){
 		String str = "";
 		
		for(CruceGenerico<?> cgs: cg){
			str += cgs.generaInforme(tiempo - 1);
			str += System.getProperty("line.separator");
		}
		for(Carretera ca: c){
			str += ca.generaInforme(tiempo - 1);
			str +=System.getProperty("line.separator");
		}
		for(Vehiculo ve: v){
			str += ve.generaInforme(tiempo - 1);
			str += System.getProperty("line.separator");
			
		}

		return str;
	}
	
	

	public void generaInformes(){
		//try {
			String g = this.generarInformeEspecifico(this.toolbar.getTime(),this.dialogoInformes.getVehiculosSeleccionados(),
			dialogoInformes.getCarreterasSeleccionadas(),
			dialogoInformes.getCrucesSeleccionados());

			if(!g.equals("")) {
				this.panelInformes.limpiar();
				this.panelInformes.setTexto(g);
			}
		//}
		//catch(IOException ioe){
		//	this.muestraDialogoError("Error en la entrada y salida " + ioe.getMessage());
		//}
    }

    public void limpiarReportes(){
	    this.panelInformes.limpiar();
    }

	public void cargarFichero(){
		int returnVal = this.fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File fichero = this.fc.getSelectedFile();
			try{
				this.cargarFicheroEnPanel(fichero);
				this.ficheroActual = fichero;
			}
			catch(IOException e){
				this.muestraDialogoError("Error durante la lectura del fichero " + e.getMessage());
			}
		}
	}

	public void cargarFicheroEnPanel(File file) throws IOException{
		String s = leeFichero(file);
		this.controlador.reinicia();
		this.panelEditorEventos.setTexto(s);
		this.ficheroActual = file;
		this.panelEditorEventos.setBorde(this.ficheroActual.getName());
		this.panelBarraEstado.setMensaje("Fichero " + file.getName() + " de eventos cargados en el editor");
	}

	public void salvarFichero(){
		try{
            String f = this.panelEditorEventos.getTexto();
            if(!f.equals("")) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.ficheroActual));
                bw.write(f);
                bw.close();
            }
            else{
                this.muestraDialogoError("�No hay nada para guardar!");
            }
		}
		catch(IOException ioe){
			this.muestraDialogoError("Error durante la lectura del fichero " + ioe.getMessage());
		}
		catch (NullPointerException ne){
			this.muestraDialogoError("�No has cargado ningun archivo!");
		}
	}

	public void limpiarEventos(){
		this.panelEditorEventos.limpiar();
    }

	public void salvarInforme(){
		try{
		    String g = this.panelInformes.getTexto();
			if(!g.equals("")) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.ficheroActual + ".out"));
                bw.write(this.panelInformes.getTexto());
                bw.close();
            }
            else{
			    this.muestraDialogoError("�No hay nada que guardar!");
            }
		}
		catch(IOException ioe){
			this.muestraDialogoError("Error durante la lectura del fichero " + ioe.getMessage());
		}
		catch (NullPointerException ne){
			this.muestraDialogoError("�No has cargado ningun archivo!");
		}
	}
	private void muestraDialogoError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.WARNING_MESSAGE);
	}


	public int getSteps(){
		return this.toolbar.getSteps();
	}

	public void redirigirSalida(){
		this.modoSalida = !this.modoSalida;
		if(this.modoSalida){ //Lo ponemos a escribir en su salida
			OutputStream os = new OutputStream() {
				@Override
				public void write(int b) {} //No lo utilizamos pero es necesario ponerlo
				@Override
				public void write(byte[] b) throws IOException {
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					bo.write(b);
					panelInformes.inserta(bo.toString());
				}
			};
			this.controlador.setSalida(os);
		}
		else{ //por consola.
			this.controlador.setSalida(null);
		}
	}

	public void setTextoEnEditor(String text){
		this.panelEditorEventos.inserta(text);
	}


	private String leeFichero(File file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ret = "";
		String aux = br.readLine();
		while(aux != null) {
			ret += aux + System.getProperty("line.separator");
			aux = br.readLine();
		}
		br.close();
		return ret;
	}

	public void disableAll(){
		this.toolbar.desactivarTodo();
		this.menuBar.desactivarTodo();
	}

	public void enableAll(){
		this.toolbar.activarTodo();
		this.menuBar.activarTodo();
	}

	public void ejecutaConHilos(){
			this.hilo = new Thread(new Runnable() {
				@Override
				public void run() {
					int pasos = toolbar.getSteps();
					int delay = toolbar.getTimeDelay();
					int i = 0;
					while (!hilo.isInterrupted() && i < pasos) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								try {
									controlador.ejecuta(1);
									disableAll();
								}
								catch(Exception e){
									e.printStackTrace();
								}
							}
						});
						try {
							Thread.sleep(delay * 1000); //Multiplica para pasar a segundos
						}
						catch(InterruptedException iex){
							Thread.currentThread().interrupt();
						}
						i++;
					}
					enableAll();
				}
			});
			this.hilo.start();
	}

	public void interrumpirHilo(){
		if(this.hilo != null){
			this.hilo.interrupt();
		}
		enableAll();
	}

	@Override
	public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {
		this.muestraDialogoError(err.getMessage());
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {

	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {

	}
	
	
	
}
