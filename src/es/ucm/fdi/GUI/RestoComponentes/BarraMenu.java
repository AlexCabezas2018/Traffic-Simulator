/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.RestoComponentes;

import es.ucm.fdi.GUI.VentanaPrincipal;
import es.ucm.fdi.logica.Controlador;

import javax.swing.*;
import java.awt.event.*;

public class BarraMenu extends JMenuBar {
	private JMenu menuFicheros;
	private JMenu menuSimulador;
	private JMenu menuReport;

	public BarraMenu(VentanaPrincipal mainWindow, Controlador controlador){
		super();
		menuFicheros = new JMenu("Ficheros");
		this.add(menuFicheros);
		this.creaMenuFicheros(menuFicheros, mainWindow);

		menuSimulador = new JMenu("Simulador");
		this.add(menuSimulador);
		this.creaMenuSimulador(menuSimulador, controlador, mainWindow);

		menuReport = new JMenu("Informes");
		this.add(menuReport);
		this.creaMenuInformes(menuReport, mainWindow);

	}

	public void desactivarTodo(){
		menuFicheros.setEnabled(false);
		menuSimulador.setEnabled(false);
		menuReport.setEnabled(false);
	}

	public void activarTodo(){
		menuFicheros.setEnabled(true);
		menuSimulador.setEnabled(true);
		menuReport.setEnabled(true);
	}

	private void creaMenuInformes(JMenuItem menu, VentanaPrincipal mainW){
		JMenuItem generaMenuInformes = new JMenuItem("Generar");
		generaMenuInformes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainW.activarDialogoInformes();
			}
		});
		menu.add(generaMenuInformes);
		JMenuItem limpiaAreaInformes = new JMenuItem("Clear");
		limpiaAreaInformes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainW.limpiarReportes(); 
			}
		});
		menu.add(limpiaAreaInformes);
	}

	private void creaMenuFicheros(JMenu menu, VentanaPrincipal mainWindow){
		JMenuItem cargar = new JMenuItem("Cargar Eventos");
		cargar.setMnemonic(KeyEvent.VK_L);
		cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		cargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.cargarFichero();
			}
		});
		JMenuItem salvar = new JMenuItem("Salva Eventos");
		salvar.setMnemonic(KeyEvent.VK_S);
		salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		salvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.salvarFichero();
			}
		});
		JMenuItem salvarI = new JMenuItem("Salvar Informe");
		salvarI.setMnemonic(KeyEvent.VK_R);
		salvarI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		salvarI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.salvarInforme();
			}
		});
		JMenuItem salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_E);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				mainWindow.salir();
			}
		});

		menu.add(cargar);
		menu.add(salvar);
		menu.add(salvarI);
		menu.add(salir);
	}

	private void creaMenuSimulador(JMenu simulador, Controlador controlador, VentanaPrincipal mainWindow){
		JMenuItem ejecuta = new JMenuItem("Ejecuta");
		ejecuta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int pasos = mainWindow.getSteps();
				controlador.ejecuta(pasos);
			}
		});
		JMenuItem reinicia = new JMenuItem("Reinicia");
		reinicia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				controlador.reinicia();
			}
		});
		JCheckBox redirigir = new JCheckBox("Redirigir salida");
		redirigir.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				mainWindow.redirigirSalida();
			}
		});

		simulador.add(ejecuta);
		simulador.add(reinicia);
		simulador.add(redirigir);

	}

}
