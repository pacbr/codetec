package clases;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.swing.*;
import javax.swing.border.LineBorder;

import clases.Colores;
import clases.Sonido;

public class DetectorPixeles extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	BufferedImage imagenOriginal;
    JPanel left;
    JPanel right;
    private JButton botones[];
    Colores c = new Colores();
    GridBagConstraints gbc;
    Map<Integer, String> coloresMap;
    JPanel panelBajo = new JPanel();
    CardLayout esqueInf = new CardLayout();
    CardLayout esqueImagenes = new CardLayout();
    Box caja;
    JPanel panelColores;
    JButton cambiaImagen;
    
    public DetectorPixeles(BufferedImage imagenCuantizada, BufferedImage imagenOriginal){
    	
    	panelBajo.setLayout(esqueInf);
    	image = imagenCuantizada;
    	this.imagenOriginal = imagenOriginal;
    	coloresMap = new Colores().getColoresMap();
    }
	
//    public void setImage(BufferedImage image) {
//        right.setIcon(new ImageIcon(image));
//    }

	public JPanel getContent() {
		left = getLeft();
		right = getRight();
		new PointSelector(this);
		//        right.setPreferredSize(left.getPreferredSize().);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		gbc = new GridBagConstraints();
//		gbc.insets = new Insets(2,2,2,2);//Agrega espacios
		gbc.weightx = 1.0;//Para que se estire la fila al estirar la ventana
		panel.add(left, gbc);
		panel.add(right, gbc);
		esqueImagenes.show(left,"imagenCuantizada");
		return panel;
	}

    
	public void setPorcentaje(int color, String porcentaje){
		
		JPanel a = (JPanel)caja.getComponent(color);
//		JButton b =(JButton)a.getComponent(0);
		String p = porcentaje;
		p = porcentaje.replaceAll(",", ".");
		p = p.replaceAll("%", "");
//		System.out.println(p);
		JPanel jpBarra = new JPanel();
//		float numFloat = Float.parseFloat(p);
//		if(numFloat>0.0)
//		b.setPreferredSize(new Dimension((15+(int)Float.parseFloat(p)*130/100),20));
//		b.setPreferredSize(new Dimension(93,20));
		jpBarra.setPreferredSize(new Dimension((1+(int)Float.parseFloat(p)*130/100),20));
		jpBarra.setBackground(new Color(c.colorAHexadecimal(color)));
		jpBarra.setBorder(LineBorder.createBlackLineBorder());
		jpBarra.add(new JLabel());
		
		a.add(jpBarra);
		a.add(new JLabel(porcentaje));
	}
	
    private JPanel getRight(){
    	JPanel right = new JPanel();
    	int altoPanel = left.getPreferredSize().height;
    	if (altoPanel < 480)
    		right.setPreferredSize(new Dimension(280, 480));
    	else
    		right.setPreferredSize(new Dimension(280, altoPanel));
		Container contenido=getContentPane(); 
//		contenido.setLayout(new FlowLayout()); 
		caja = Box.createVerticalBox();
		botones = new JButton[ 12 ];
		ActionListener actionListener = new ActionListener() {
			Segmentacion seg = new Segmentacion();
			Grafico g;
		      public void actionPerformed(ActionEvent actionEvent) {
		    	BufferedImage imagenGrisClaro = seg.escalaDeGrisesEnRGB(seg.escalaDeGrises(imagenOriginal));
				BufferedImage imagenEscalaGrisesColor = seg.escalaDeGrisesConColor(image, imagenGrisClaro,c.obtieneNumeroColor(actionEvent.getActionCommand()));
				ImageIcon im1 = new ImageIcon(imagenEscalaGrisesColor);
				ImageIcon im2 = new ImageIcon(imagenGrisClaro);
				
				g = new Grafico(new JLabel(im1),actionEvent.getActionCommand(),new JLabel(im2));
//				 
				
		      }
		    };
		
		for ( int cuenta = 0; cuenta < botones.length; cuenta++ ) {
			JButton button = new JButton(coloresMap.get(cuenta));
			button.setFont(new Font("Verdana", Font.BOLD, 11));
			button.setPreferredSize(new Dimension(100,20));
			button.addActionListener(actionListener);
			botones[cuenta] = button;
			panelColores = new JPanel();
			panelColores.setLayout(new FlowLayout(FlowLayout.LEFT)); 
			
			if((coloresMap.get(cuenta) != "Negro")){
				botones[cuenta].setForeground(Color.black);
			}else botones[cuenta].setForeground(Color.white);
				
			botones[cuenta].setBackground(new Color(c.colorAHexadecimal(cuenta)));
			
			panelColores.add(botones[cuenta]);
			caja.add(panelColores);
		}
			panelBajo.setBackground(Color.gray);
			JLabel titulo = new JLabel("Pincha sobre la imagen");
			panelBajo.add("carta1",titulo);
			titulo.setHorizontalAlignment(JLabel.CENTER);
			titulo.setFont(new Font("Verdana", Font.BOLD, 17));
			titulo.setForeground(Color.ORANGE);
			//    	   Font auxFont=titulo.getFont();
			//    	   titulo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
			caja.add(panelBajo);
			contenido.add(caja);
			
			
			cambiaImagen = new JButton("Imagen original");
			ActionListener actionListener2 = new ActionListener() {
			
		      public void actionPerformed(ActionEvent actionEvent) {
		    	 
		    	  if(cambiaImagen.getText()=="Imagen original"){
		    		  esqueImagenes.show(left,"imagenOriginal");
		    		  cambiaImagen.setText("Imagen cuantizada");
		    		  
		    	  }
		    	  else{
		    		  esqueImagenes.show(left,"imagenCuantizada");
		    		  cambiaImagen.setText("Imagen original");
		    	  }
		      }
		    };
			cambiaImagen.addActionListener(actionListener2);
		    contenido.add(cambiaImagen,BorderLayout.NORTH);
			right.add(contenido);
			return right;
		}
    
    
	private JPanel getLeft() {
//		try {
//			image = ImageIO.read( new File ("D:/Musica/Paco/imagenesCodetec/img2.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new JLabel(new ImageIcon(image), JLabel.CENTER);
		JPanel jpleft = new JPanel();
		JLabel jlbleft = new JLabel(new ImageIcon(image), JLabel.CENTER);
		JLabel jlbleft2 = new JLabel(new ImageIcon(imagenOriginal), JLabel.CENTER);
		jpleft.setLayout(esqueImagenes);
		jpleft.add("imagenCuantizada",jlbleft);
		jpleft.add("imagenOriginal",jlbleft2);
		return jpleft;
	}

//	public static void main(String[] args) {
//        DetectorPixeles test = new DetectorPixeles(new BufferedImage());
//        JFrame f = new JFrame("Imagen");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(test.getContent());
//        f.pack();
//        f.setLocationRelativeTo(null);
//        f.setVisible(true);
//    }
}

class PointSelector extends MouseAdapter{
	DetectorPixeles view;
	JPanel target;
	JPanel targetRight;
	JPanel panelBajo;
	CardLayout esqueInf;
	Colores c;
	
	PointSelector(DetectorPixeles bt) {
		view = bt;
		target = view.left;
		targetRight = view.right;
		panelBajo = view.panelBajo;
		esqueInf=view.esqueInf;
		target.addMouseListener(this);
		c = new Colores();
	}

	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();

        // Localiza el origen de la imagen dentrada dentro del label izquierdo
		int targetWidth = target.getWidth();
		int targetHeight = target.getHeight();
		int imageWidth = view.image.getWidth();
		int imageHeight = view.image.getHeight();
		int x0 = (targetWidth - imageWidth)/2;
		int y0 = (targetHeight - imageHeight)/2;

		Rectangle r = new Rectangle(x0, y0, imageWidth, imageHeight);// Comprueba que pincho dentro de la imagen, y no fuera.
		if(!r.contains(p))
				return;

        // Selected location in image coordinate system - not used.
		int imageX = p.x - x0;
		int imageY = p.y - y0;
		new Sonido(c.obtieneColor(view.image.getRGB(imageX, imageY)));
		JLabel colorLabel = new JLabel(new Colores().getColoresMap().get(c.obtieneColor(view.image.getRGB(imageX, imageY))), JLabel.CENTER);
		colorLabel.setHorizontalAlignment(JLabel.CENTER);
		colorLabel.setFont(new Font("Verdana", Font.BOLD, 17));
		colorLabel.setForeground(Color.ORANGE);
		colorLabel.setToolTipText("Color del pixel seleccionado");
//        Font auxFont=colorLabel.getFont();
// 	   	colorLabel.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
		panelBajo.add("carta1", colorLabel);
		this.esqueInf.show(this.panelBajo,"carta1");
	}
}