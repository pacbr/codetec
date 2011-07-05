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
	public Grafico(JLabel et, String nombre, JLabel jletiqueta){
		super(nombre);
		et1 = et;
		et2 = jletiqueta;
		//Agrego etiqueta que contiene la imagen al frame
//		getContentPane().add(et);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		show();
		new PointSelector(this);
	}
	
	class PointSelector extends MouseAdapter{
		Grafico g;
		JPanel panelBaj;
	    CardLayout esqueGris;
	    int i;

		Colores c;
		
		PointSelector(Grafico grafico) {
			g = grafico;
			panelBaj = new JPanel();
			esqueGris = new CardLayout();
			g.setLayout(esqueGris);
			g.getContentPane().add("jlabel1",g.et1);
			g.getContentPane().add("jlabel2",g.et2);
			g.addMouseListener(this);
			c = new Colores();
			i = 1;
		}

		public void mousePressed(MouseEvent e) {
//			Point p = e.getPoint();
//
//	        // Localiza el origen de la imagen dentrada dentro del label izquierdo
//			int targetWidth = target.getWidth();
//			int targetHeight = target.getHeight();
//			int imageWidth = g.image.getWidth();
//			int imageHeight = g.image.getHeight();
//			int x0 = (targetWidth - imageWidth)/2;
//			int y0 = (targetHeight - imageHeight)/2;
//
//			Rectangle r = new Rectangle(x0, y0, imageWidth, imageHeight);// Comprueba que pincho dentro de la imagen, y no fuera.
//			if(!r.contains(p))
//					return;
//
//	        // Selected location in image coordinate system - not used.
//			int imageX = p.x - x0;
//			int imageY = p.y - y0;
//			new Sonido(c.obtieneColor(g.image.getRGB(imageX, imageY)));
//			JLabel colorLabel = new JLabel(new Colores().getColoresMap().get(c.obtieneColor(g.image.getRGB(imageX, imageY))), JLabel.CENTER);
//			colorLabel.setHorizontalAlignment(JLabel.CENTER);
//			colorLabel.setFont(new Font("Verdana", Font.BOLD, 17));
//			colorLabel.setForeground(Color.ORANGE);
//			colorLabel.setToolTipText("Color del pixel seleccionado");
////	        Font auxFont=colorLabel.getFont();
////	 	   	colorLabel.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
			if (i==1){
				this.esqueGris.show(g.getContentPane(),"jlabel2");
				i=2;
			}else{
				this.esqueGris.show(g.getContentPane(),"jlabel1");
				i=1;
			}
//			System.out.println(":)");
		}
	
	
//	public static void main(String H[]){
//		ImageIcon imagen = new ImageIcon("D:/Descargas/rojo1.jpg");
//		JLabel etiqueta = new JLabel(imagen);
//		Grafico p = new Grafico(etiqueta, "Prueba");
//		p.show();
//		
//		//Permite cerrar la ventana
//		p.addWindowListener(new WindowAdapter()
//		{
//			public void windowClosing(WindowEvent evt)
//			{
//				System.exit(0);
//			}
//		});
//	}
	
}}