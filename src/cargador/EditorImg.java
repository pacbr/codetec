package cargador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditorImg extends JFrame{
	static final long serialVersionUID=10000;
	PanelSwing panel;
	Controlador controlador;
	public static void main(String[] args) {
//		try {
//			//System.out.println("esperamos 1'5 seg...");
//			Thread.sleep(1500);
//			} catch (InterruptedException x) {
//			System.out.println("ha existido algún error...");
//		}

		EditorImg editor = new EditorImg();
		editor.setBounds(120, 120, 800, 600);
		editor.setVisible(true);
		editor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		editor.addWindowListener(new WindowAdapter(){
			public void WindowCloser(WindowEvent e){        
				System.exit(0);
			}
		});
	}
 
// @Desc Constructor de la clase

	public EditorImg() {
		super("Codetec");
		Container contentPane = getContentPane();
		panel = new PanelSwing(this);
		controlador = new Controlador(panel);
		panel.abrir.addActionListener(controlador);
		panel.guardar.addActionListener(controlador);
		panel.salir.addActionListener(controlador);
		//  panel.detecta1.addActionListener(controlador);
		panel.detecta.addActionListener(controlador);
		
		panel.detectaMedia.addActionListener(controlador);
		panel.detectaMediana.addActionListener(controlador);
		
		
		contentPane.add(panel);
	}
}