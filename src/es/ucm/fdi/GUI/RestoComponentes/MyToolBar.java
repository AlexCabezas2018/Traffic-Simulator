/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import es.ucm.fdi.GUI.Observer.ObservadorSimulacionTrafico;
import es.ucm.fdi.GUI.VentanaPrincipal;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.logica.Controlador;
import es.ucm.fdi.logica.MapaCarreteras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.util.List;

public class MyToolBar extends JToolBar implements ObservadorSimulacionTrafico {
	private JSpinner steps;
	private JSpinner delay;
	private JTextField time;

	//Botones
	private JButton botonCargar;
	private JButton salvar;
	private JButton botonLimpiar;
	private JButton botonCheckIn;
	private JButton play;
	private JButton reiniciar;
	private JButton botonGenerarReporte;
	private JButton botonLimpiarReporte;
	private JButton botonSalvarReporte;
	private JButton salir;
	private JButton botonDetener;

	public MyToolBar(VentanaPrincipal mainWindow, Controlador controlador){ //Faltan Botones
		super();
		controlador.addObserver(this);

		botonCargar = new JButton();
		botonCargar.setToolTipText("Carga un fichero de eventos");
		botonCargar.setIcon(new ImageIcon("resources/iconos/open.png"));
		botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.cargarFichero();
			}
		});
		this.add(botonCargar);

		salvar = new JButton();
		salvar.setToolTipText("Salva el archivo de eventos");
		salvar.setIcon(new ImageIcon("resources/iconos/save.png"));
		salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.salvarFichero();
            }
        });
		this.add(salvar);

        botonLimpiar = new JButton();
        botonLimpiar.setToolTipText("Limpia la zona de eventos");
        botonLimpiar.setIcon(new ImageIcon("resources/iconos/clear.png"));
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.limpiarEventos();
            }
        });
        this.add(botonLimpiar);

        this.addSeparator();

		botonCheckIn = new JButton();
		botonCheckIn.setToolTipText("Carga los eventos del simulador");
		botonCheckIn.setIcon(new ImageIcon("resources/iconos/events.png"));
		botonCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try{
					controlador.reinicia();
					byte[] contenido = mainWindow.getTextoEditorEventos().getBytes();
					controlador.cargarEventos(new ByteArrayInputStream(contenido));
				} catch(ErrorDeSimulacion err){

				}
				mainWindow.setMensaje("Eventos cargados al simulador!");
			}
		});
		this.add(botonCheckIn);

		play = new JButton();
		play.setToolTipText("Inicia el simulador");
		play.setIcon(new ImageIcon("resources/iconos/play.png"));
		play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.ejecutaConHilos();
            }
        });
		this.add(play);

		botonDetener = new JButton();
		botonDetener.setToolTipText("Detiene la simulacion");
		botonDetener.setIcon(new ImageIcon("resources/iconos/stop.png"));
		botonDetener.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.interrumpirHilo();
			}
		});
		this.add(botonDetener);

		reiniciar = new JButton();
		reiniciar.setToolTipText("Reinicia el simulador");
		reiniciar.setIcon(new ImageIcon("resources/iconos/reset.png"));
		reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.reinicia();
            }
        });
		this.add(reiniciar);

		this.addSeparator();

		this.add(new JLabel(" Pasos: "));
		this.steps = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
		this.steps.setToolTipText("Pasos a ejecutar: 1 - 1000");
		this.steps.setMaximumSize(new Dimension(70, 70));
		this.steps.setMinimumSize(new Dimension(70, 70));
		this.steps.setValue(1);
		this.add(steps);

		this.addSeparator();

		this.add(new JLabel(" Delay: "));
		this.delay = new JSpinner(new SpinnerNumberModel(5, 1, 60, 1));
		this.delay.setToolTipText("Delay entre pasos para ejecutar (segundos)");
		this.delay.setMaximumSize(new Dimension(70, 70));
		this.delay.setMinimumSize(new Dimension(70, 70));
		this.delay.setValue(1);
		this.add(delay);

		this.add(new JLabel(" Tiempo: "));
		this.time = new JTextField("0", 5);
		this.time.setToolTipText("Tiempo Actual");
		this.time.setMaximumSize(new Dimension(70, 70));
		this.time.setMinimumSize(new Dimension(70, 70));
		this.time.setEnabled(false);
		this.add(this.time);

		this.addSeparator();

		botonGenerarReporte = new JButton();
		botonGenerarReporte.setToolTipText("Genera informes");
		botonGenerarReporte.setIcon(new ImageIcon("resources/iconos/report.png"));
		botonGenerarReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.activarDialogoInformes();
			}
		});
		this.add(botonGenerarReporte);

		botonLimpiarReporte = new JButton();
		botonLimpiarReporte.setToolTipText("Limpia el area de reportes");
		botonLimpiarReporte.setIcon(new ImageIcon("resources/iconos/delete_report.png"));
		botonLimpiarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.limpiarReportes();
            }
        });
		this.add(botonLimpiarReporte);

		botonSalvarReporte = new JButton();
		botonSalvarReporte.setToolTipText("Salva el reporte");
		botonSalvarReporte.setIcon(new ImageIcon("resources/iconos/save_report.png"));
		botonSalvarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.salvarInforme();
            }
        });
		this.add(botonSalvarReporte);

		salir = new JButton();
		salir.setToolTipText("Salir del simulador");
		salir.setIcon(new ImageIcon("resources/iconos/exit.png"));
		salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.salir();
            }
        });
		this.add(salir);
	}

	@Override
	public void errorSimulacion(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion err) {

	}

	@Override
	public void avanza(int tiempo, MapaCarreteras map, List<Evento> eventos) {
		this.time.setText("" + (tiempo));
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras map, List<Evento> eventos) {
		this.steps.setValue(1);
		this.time.setText("0");
	}

	public void desactivarTodo(){
		botonCargar.setEnabled(false);
		salvar.setEnabled(false);
		botonLimpiar.setEnabled(false);
		botonCheckIn.setEnabled(false);
		play.setEnabled(false);
		reiniciar.setEnabled(false);
		botonGenerarReporte.setEnabled(false);
		botonLimpiarReporte.setEnabled(false);
		botonSalvarReporte.setEnabled(false);
		salir.setEnabled(false);
	}

	public void activarTodo(){
		botonCargar.setEnabled(true);
		salvar.setEnabled(true);
		botonLimpiar.setEnabled(true);
		botonCheckIn.setEnabled(true);
		play.setEnabled(true);
		reiniciar.setEnabled(true);
		botonGenerarReporte.setEnabled(true);
		botonLimpiarReporte.setEnabled(true);
		botonSalvarReporte.setEnabled(true);
		salir.setEnabled(true);
	}

	public Integer getSteps(){
		return (Integer)this.steps.getValue();
	}
	
	public Integer getTime(){
		return Integer.parseInt(this.time.getText());
	}

	public Integer getTimeDelay(){
		return (Integer)this.delay.getValue();
	}
}
