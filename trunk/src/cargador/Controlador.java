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
 
	public Controlador(PanelSwing panel) {
		this.panel = panel;
		manejador = new ManejadorDeImagenes();

	}

	// @Desc Método que capturará los eventos ocurridos en el menú principal del sistema
	public void actionPerformed(ActionEvent ie) {
		JMenuItem i = (JMenuItem)ie.getSource();   
		if(i.getText() == "Abrir"){
			boolean estado = manejador.cargaArchivoDeImagen(panel, panel.lienzo);
			if(estado) {
				panel.guardar.setEnabled(true);
				panel.detectaPasoAPaso.setEnabled(true);
				panel.detectaErosion.setEnabled(true);
				panel.detectaMedia.setEnabled(true);
				panel.detectaMediana.setEnabled(true);
				panel.detectaApertura.setEnabled(true);
				panel.detectaCierre.setEnabled(true);
				panel.esqueInf.show(panel.panelBajo, "carta1");
			}
		}
		else if(i.getText() == "Guardar")
			manejador.guardaArchivoDeImagen(panel);
		else if(i.getText() == "Salir")
			System.exit(0);
		
		else if(i.getText() == "Paso a paso (Erosión)"){
			//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");		
			JLabel lbl = manejador.detectaColorPasoAPaso();
					panel.panelDetecta.remove(0);
					panel.panelDetecta.add("Center", lbl);
					panel.esqueInf.show(panel.panelBajo, "carta2");
				}
		
		else if(i.getText() == "Erosión"){
	//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");
			JLabel lbl = manejador.detectaColorErosion();
			panel.panelDetecta.remove(0);
			panel.panelDetecta.add("Center", lbl);
			panel.esqueInf.show(panel.panelBajo, "carta2");
		}
		
		else if(i.getText() == "Media"){
			//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");
					JLabel lbl = manejador.detectaColorMedia();
					panel.panelDetecta.remove(0);
					panel.panelDetecta.add("Center", lbl);
					panel.esqueInf.show(panel.panelBajo, "carta2");
		}
		
		else if(i.getText() == "Mediana"){
			//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");
					JLabel lbl = manejador.detectaColorMediana();
					panel.panelDetecta.remove(0);
					panel.panelDetecta.add("Center", lbl);
					panel.esqueInf.show(panel.panelBajo, "carta2");
		}
		
		else if(i.getText() == "Apertura"){
			//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");
					JLabel lbl = manejador.detectaColorApertura();
					panel.panelDetecta.remove(0);
					panel.panelDetecta.add("Center", lbl);
					panel.esqueInf.show(panel.panelBajo, "carta2");
		}
		
		else if(i.getText() == "Cierre"){
			//		panel.panelBajo.remove(0);
			panel.esqueInf.show(panel.panelBajo, "carta1");
					JLabel lbl = manejador.detectaColorCierre();
					panel.panelDetecta.remove(0);
					panel.panelDetecta.add("Center", lbl);
					panel.esqueInf.show(panel.panelBajo, "carta2");
		}
		
		else if(i.getText() == "Ayuda"){
			//		panel.panelBajo.remove(0);
				  
					System.out.println("Ayuda CONTROLADOR");
					manejador.cargaAyuda();
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