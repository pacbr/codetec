package clases;

import javax.swing.*;
import java.awt.event.*;

public class Grafico extends JFrame{
	public Grafico(JLabel et){
		super("Muestra de Imagen en JAVA...");
		
		//Agrego etiqueta que contiene la imagen al frame
		getContentPane().add(et);
		
		this.setSize(500, 500);
		
	}
	
	
	public static void main(String H[]){
		ImageIcon imagen = new ImageIcon("D:/Descargas/rojo1.jpg");
		JLabel etiqueta = new JLabel(imagen);
		Grafico p = new Grafico(etiqueta);
		p.show();
		
		//Permite cerrar la ventana
		p.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});
	}
	
}