package clases;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;

public class Grafico extends JFrame{
	public Grafico(JLabel et, String nombre){
		super(nombre);
		
		//Agrego etiqueta que contiene la imagen al frame
		getContentPane().add(et);
		
		this.setSize(500, 500);
		
	}
	
	JLabel et1;
	JLabel et2;
	Grafico g;
	CardLayout esqueGris;
    int i;
    int cont;
    Timer timer;
	public Grafico(JLabel et, String nombre, JLabel jletiqueta){
		super(nombre);
		et1 = et;
		et2 = jletiqueta;
		//Agrego etiqueta que contiene la imagen al frame
//		getContentPane().add(et);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		esqueGris = new CardLayout();
		setLayout(esqueGris);
		getContentPane().add("jlabel1",et1);
		getContentPane().add("jlabel2",et2);
		show();
		
		new PointSelector(this);
	}
	

    
    public void cambiaImagen(){
    	timer = new Timer (300, new ActionListener ()
		
		{
    		
    		public void actionPerformed(ActionEvent e){
		    	if (i==1){
					esqueGris.show(g.getContentPane(),"jlabel2");
					i=2;
				}else{
					esqueGris.show(g.getContentPane(),"jlabel1");
					i=1;
				}
		    	cont++;
		    	System.out.println(cont);
		    	if(cont==6){
		    		timer.stop();
				}
			}
		});
		
		timer.start();
	}

	class PointSelector extends MouseAdapter{
		PointSelector(Grafico grafico) {
			g = grafico;
			g.addMouseListener(this);
			i = 1;
		}

		public void mousePressed(MouseEvent e) {
			cont = 0;
			cambiaImagen();
		}
	}
}