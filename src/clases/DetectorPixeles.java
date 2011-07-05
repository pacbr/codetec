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
    JLabel left;
    JPanel right;
    private JButton botones[];
    Colores c = new Colores();
    GridBagConstraints gbc;
    Map<Integer, String> coloresMap;
    JPanel panelBajo = new JPanel();
    CardLayout esqueInf = new CardLayout();
    Box caja;
    JPanel panelColores;
    public DetectorPixeles(BufferedImage imagenCuantizada){
    	panelBajo.setLayout(esqueInf);
    	image = imagenCuantizada;
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
		panel.setBackground(Color.black);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);//Agrega espacios
		gbc.weightx = 1.0;//Para que se estire la fila al estirar la ventana
		panel.add(left, gbc);
		panel.add(right, gbc);
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
		float numFloat = Float.parseFloat(p);
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
		right.setSize(100, image.getHeight());
		Container contenido=getContentPane(); 
		contenido.setLayout(new FlowLayout()); 
		caja = Box.createVerticalBox();
		botones = new JButton[ 12 ];
		ActionListener actionListener = new ActionListener() {
			Segmentacion seg = new Segmentacion();
			Grafico g;
		      public void actionPerformed(ActionEvent actionEvent) {
		        BufferedImage imagenEscalaGrises = seg.escalaDeGrisesConColor(image, c.obtieneNumeroColor(actionEvent.getActionCommand()));
				ImageIcon imagenmuestra1 = new ImageIcon(imagenEscalaGrises);
				JLabel etiqueta1 = new JLabel(imagenmuestra1);
				g = new Grafico(etiqueta1,actionEvent.getActionCommand(),new JLabel(new ImageIcon(seg.escalaDeGrises(image))));
				 ParameterBlock pb = new ParameterBlock();
			        pb.addSource(image);
//			        pb.add(kernel);
			        
			        BufferedImage imageGray = new BufferedImage(image.getWidth(), image.getHeight(),  
			        	    BufferedImage.TYPE_BYTE_GRAY);  
			        	Graphics g2 = image.getGraphics();  
			        	g2.drawImage(image, 0, 0, null);  
			        	g2.dispose();
			        	
			        
//			        g = new Grafico(etiqueta1,actionEvent.getActionCommand(),new JLabel(new ImageIcon(JAI.create("convertToGrayscale", pb).getAsBufferedImage())));
//				g.show();
				
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
//			panelColores.add(new JLabel("Inicio"));
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
			right.add(contenido);
			return right;
		}
    
    
	private JLabel getLeft() {
//		try {
//			image = ImageIO.read( new File ("D:/Musica/Paco/imagenesCodetec/img2.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new JLabel(new ImageIcon(image), JLabel.CENTER);
		return new JLabel(new ImageIcon(image), JLabel.CENTER);
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
	JLabel target;
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