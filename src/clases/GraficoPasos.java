package clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cargador.ManejadorDeImagenes;

public class GraficoPasos extends JFrame{
	BufferedImage imagenOriginal;
	BufferedImage imagenErosionada;
	BufferedImage imagenAclarada;
	BufferedImage imagenCuantizada;
	JFrame frame;
	DetectorPixeles detPix;
	CardLayout esqueImagenes;
	JPanel panelImagenes;
	JPanel panelTexto;
	JButton siguiente;
	JLabel lbtexto;
	
	public DetectorPixeles getDetPix(){
		return detPix;
	}
	
	public GraficoPasos(Detector detectorPasoAPaso, ManejadorDeImagenes manejador){
		super("Paso a paso");
		imagenOriginal=detectorPasoAPaso.getImagenOriginal();
		imagenErosionada=detectorPasoAPaso.getImagenErosion();
		imagenAclarada=detectorPasoAPaso.getImagenAclarada();
		imagenCuantizada=detectorPasoAPaso.getImagenCuantizada();
		
		ImageIcon im1 = new ImageIcon(imagenOriginal);
		ImageIcon im2 = new ImageIcon(imagenErosionada);
		ImageIcon im3 = new ImageIcon(imagenAclarada);
		ImageIcon im4 = new ImageIcon(imagenCuantizada);
		
		
		
		 
		 detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
//	     
////		 frame = manejador.cargaFrameDetectorPixeles(detPix, "Erosion");
//	     
		 
		 frame = new JFrame("Erosión");
	     
			//   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 JPanel nuevoJPanel = detPix.getContent();
				 frame.add(nuevoJPanel);
				 frame.pack();
				 frame.setLocationRelativeTo(null);
				 frame.setVisible(false);
		 
		 
		 
		
		
		this.setLayout(new BorderLayout()); 
		
//		this.getContentPane().setLayout (new GridBagLayout());
		JLabel jl1 = new JLabel(im1);
		JLabel jl2 = new JLabel(im2);
		JLabel jl3 = new JLabel(im3);
		JLabel jl4 = new JLabel(im4);
		
		
		esqueImagenes = new CardLayout();
		
		panelImagenes = new JPanel();
		
		panelImagenes.setLayout(esqueImagenes);
		panelImagenes.add("imagenOriginal",jl1);
		panelImagenes.add("imagenErosionada",jl2);
		panelImagenes.add("imagenAclarada",jl3);
		panelImagenes.add("imagenCuantizada",jl4);
		
		
//		
		
		
		JPanel panel = new JPanel();
		JButton finalizar = new JButton("Finalizar");
		siguiente = new JButton("Siguiente");
		siguiente.setName("1");
		ActionListener actionListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(actionEvent.getActionCommand() == "Siguiente"){
					
					if (siguiente.getName() == "1"){
						esqueImagenes.show(panelImagenes,"imagenErosionada");
						siguiente.setName("2");
						lbtexto.setText("Imagen erosionada");
					}
					else if (siguiente.getName() == "2"){
						esqueImagenes.show(panelImagenes,"imagenAclarada");
						siguiente.setName("3");
						lbtexto.setText("Imagen aclarada");
					}
					else if (siguiente.getName() == "3"){
						esqueImagenes.show(panelImagenes,"imagenCuantizada");
						lbtexto.setText("Imagen segmentada y cuantizada");
						siguiente.setVisible(false);
					}
				}
				else if(actionEvent.getActionCommand() == "Finalizar"){
					dispose();
					
					frame.setVisible(true);
					
				}
//				
			}
	    };
		
	    siguiente.addActionListener(actionListener2);
	    finalizar.addActionListener(actionListener2);
	    
	    


		
		panel.add(siguiente);
		panel.add(finalizar);
		panelTexto = new JPanel();
		lbtexto = new JLabel("Imagen original");
		panelTexto.add(lbtexto);
		this.add(panel,BorderLayout.NORTH);
		this.add(panelTexto,BorderLayout.CENTER);
		this.add(panelImagenes,BorderLayout.SOUTH);
	    esqueImagenes.show(panelImagenes,"imagenOriginal");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
		
		
		
	}
	
	
}
