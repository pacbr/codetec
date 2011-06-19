package clases;

import javax.swing.*;
import java.awt.event.*;

public class Grafico extends JFrame{
	public Grafico(JLabel et){
		super("Muestra de Imagen en JAVA...");
		
		//AGREGAMOS LA ETIQUETA QUE CONTIENE LA IMAGEN AL FRAME
		getContentPane().add(et);
		
		//ESTABLECEMOS EL TAMAÑO DEL FRAME
		this.setSize(500, 500);
		
	}
	
	
	public static void main(String H[]){
		ImageIcon imagen = new ImageIcon("D:/Descargas/rojo1.jpg");
		JLabel etiqueta = new JLabel(imagen);
		Grafico p = new Grafico(etiqueta);
		p.show();
		
		//COLOCAMOS EL CODIGO QUE PERMITE CERRAR LA VENTANA
		p.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});
	}
	
}