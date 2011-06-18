package cargador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import clases.Detector;

//@Desc Implementa la gestión de evento en la interfaz de usuario
public class Controlador implements ActionListener, ChangeListener{
	ManejadorDeImagenes manejador;
	PanelSwing panel;
	Detector detector;
 
	public Controlador(PanelSwing panel) {
		this.panel = panel;
		manejador = new ManejadorDeImagenes();
		detector = new Detector();
	}

	// @Desc Método que capturará los eventos ocurridos en el menú principal del sistema
	public void actionPerformed(ActionEvent ie) {
		JMenuItem i = (JMenuItem)ie.getSource();   
		if(i.getText() == "Abrir"){
			boolean estado = manejador.cargaArchivoDeImagen(panel, panel.lienzo);
			if(estado) {
				panel.guardar.setEnabled(true);
	//    		panel.detecta1.setEnabled(true);
				panel.detecta.setEnabled(true);
	  
				panel.esqueInf.show(panel.panelBajo, "carta1");
			}
		}
		else if(i.getText() == "Guardar")
			manejador.guardaArchivoDeImagen(panel);
		else if(i.getText() == "Salir")
			System.exit(0);
	  
	//  else if(i.getText() == "Detecta1")
	//	   manejador.detectaColor(t);
	  
		else if(i.getText() == "Detecta"){
	//		panel.panelBajo.remove(0);
		  
			JLabel lbl = manejador.detectaColor(detector);
			panel.panelDetecta.remove(0);
			panel.panelDetecta.add("Center", lbl);
			panel.esqueInf.show(panel.panelBajo, "carta2");
		}
	}
	  
  
	//@Desc Método que captarará los eventos ocurridos en los componentes JSlider de la interfaz de usuario
	public void stateChanged(ChangeEvent e){
	//  JSlider slider = (JSlider) e.getSource();
	//  if(slider == panel.jslBrillo)
	//   manejador.muestraBrillo(panel.lienzo, slider.getValue());
	//  else if(slider == panel.jslRojo) {
	//   manejador.muestraColores(panel.lienzo, slider.getValue(), panel.jslVerde.getValue(), panel.jslAzul.getValue());
	//  }
	//  else if(slider == panel.jslVerde)
	//   manejador.muestraColores(panel.lienzo, panel.jslRojo.getValue(), slider.getValue(), panel.jslAzul.getValue());
	//  else if(slider == panel.jslAzul)
	//   manejador.muestraColores(panel.lienzo, panel.jslRojo.getValue(), panel.jslVerde.getValue(), slider.getValue());  
	}
}