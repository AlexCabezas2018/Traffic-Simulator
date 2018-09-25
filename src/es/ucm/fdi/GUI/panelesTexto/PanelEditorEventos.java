/*Practica 5
 * Alejandro Cabezas Garriguez*/

package es.ucm.fdi.GUI.panelesTexto;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import es.ucm.fdi.GUI.RestoComponentes.MyPopUp;
import es.ucm.fdi.GUI.VentanaPrincipal;

public class PanelEditorEventos extends PanelAreaTexto {
	public PanelEditorEventos(String titulo, String texto, boolean editable, VentanaPrincipal mainWindow){
		super(titulo, editable);
		this.setTexto(texto);

		//Opcional
		MyPopUp m = new MyPopUp(mainWindow);
		this.areaTexto.add(m);


		this.areaTexto.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) { }

			@Override
			public void mousePressed(MouseEvent mouseEvent) { //Aqui no funciona
				if(mouseEvent.isPopupTrigger() && areaTexto.isEnabled()){
					m.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) { //Aqui si
				if(mouseEvent.isPopupTrigger() && areaTexto.isEnabled()){
					m.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
				}
			}
			@Override
			public void mouseEntered(MouseEvent mouseEvent) { }
			@Override
			public void mouseExited(MouseEvent mouseEvent) { }
		});

	}

}
