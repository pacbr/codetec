package clases;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class Grafico extends JFrame{
	public Grafico(JLabel et, String nombre){
		super(nombre);
		
		getContentPane().add(et);
		
		this.setSize(500, 500);
		
	}
	
	JLabel et1;
	JLabel et2;
	JPanel panelImagenes = new JPanel();
	Grafico g;
	CardLayout esqueGris;
    int i;
    int cont;
    Timer timer;
    
	public Grafico(JLabel et, String nombre, JLabel jletiqueta){
		super(nombre);
		et1 = et;
		et2 = jletiqueta;
		timer = new Timer (300, new ActionListener ());
		
		
		esqueGris = new CardLayout();
		JLabel jl = new JLabel("Pincha sobre la imagen");
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setFont(new Font("Verdana", Font.BOLD, 17));
		jl.setForeground(Color.ORANGE);
		getContentPane().add(jl,BorderLayout.NORTH);
		
		panelImagenes.setLayout(esqueGris);
		
		panelImagenes.add("jlabel1",et1);
		panelImagenes.add("jlabel2",et2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().add(panelImagenes,BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
	 	this.setVisible(true);
		
		new PointSelector(this);
	}
	

	class ActionListener implements java.awt.event.ActionListener{		
		public void actionPerformed(ActionEvent e){
	    	if (i==1){
				esqueGris.show(g.panelImagenes,"jlabel2");
				i=2;
			}else{
				esqueGris.show(g.panelImagenes,"jlabel1");
				i=1;
			}
	    	cont++;
//	    	System.out.println(cont);
	    	if(cont==6){
	    		timer.stop();
			}
		}
	}
    
    
	class PointSelector extends MouseAdapter{
		PointSelector(Grafico grafico) {
			g = grafico;
			g.addMouseListener(this);
		}

		public void mousePressed(MouseEvent e) {
			cont = 0;
			i=1;
			timer.start();
		}
	}
}