package clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DetectorMediana {
	static 
	{ 
	      System.setProperty("com.sun.media.jai.disableMediaLib", "true"); 
	} 
	int rgbs[];
	float hsb[];
	Integer colorpixel = null;
	int pixelesAnalizados;
	int ganador;
	Sonido s;
	BufferedImage imagenCuantizada2;
	int contador[];
	
	public BufferedImage getImagenCuantizada(){
		return imagenCuantizada2;
	}
	
	public int[] getContadorPixeles(){
		return contador;
	}
	
	public int getPixelesAnalizados(){
		return pixelesAnalizados;
	}
	
	
	public JLabel ejecuta(String ruta){
		 
		// Variables locales
		BufferedImage img;
		BufferedImage imgAclarada;
		BufferedImage imgMediana;
		JLabel lblDetecta;
		Colores color;
		Segmentacion seg;
		Aclarador aclarador;
		Filtros fil;
		try {
			img = ImageIO.read( new File (ruta));
			
			
			int ancho = img.getWidth();
			int alto = img.getHeight(null);

			seg = new Segmentacion(ancho,alto);
			aclarador = new Aclarador();
			fil = new Filtros(ancho, alto);
			
			rgbs = new int[3];
			int value;
			hsb = new float[3];

			color = new Colores();
			
			
			contador = new int[12];

			for(int i=0;i<12;i++){
				contador[i] = 0;
			}
			
			
			
			
			

			imgMediana = seg.filtroMediana(img);
			
			imgAclarada = aclarador.aclara(imgMediana);
		
			imagenCuantizada2 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
			Map<Integer,List<Pixel>> mapa = Segmentacion.segmentaFinal(imgAclarada, imagenCuantizada2);
			
//			ImageIcon imagenmuestra1 = new ImageIcon(imgMediana);
//			JLabel etiqueta1 = new JLabel(imagenmuestra1);
//			g1 = new Grafico(etiqueta1, "Mediana");
//			g1.show();
////		
////			
////			ImageIcon imagenmuestra2 = new ImageIcon(imgAclarada);
////			JLabel etiqueta2 = new JLabel(imagenmuestra1);
////			g = new Grafico(etiqueta1, "Aclarado");
////			g.show();
//			
//			ImageIcon imagenmuestra3 = new ImageIcon(imgSegmentacion);
//			JLabel etiqueta3 = new JLabel(imagenmuestra3);
//			g3 = new Grafico(etiqueta3, "Segmentacion Mediana");
//			g3.show();
			
			int wAclarada = imgAclarada.getWidth();
			int hAclarada = imgAclarada.getHeight(null);
			for(int i=1; i<wAclarada; i++){
				if(i%21==0){
					for(int j=1; j<hAclarada;j++){
						if(j%21==0){
							
							value =  imgAclarada.getRGB(i,j);
							rgbs = Colores.obtieneRGB(value);
							Color.RGBtoHSB(rgbs[0], rgbs[1], rgbs[2], hsb);
							colorpixel = Colores.decideColor(hsb[0], hsb[1], hsb[2]);
							contador[colorpixel]++;


						}
					}
				}
			}

			ganador=0;
			pixelesAnalizados=0;
			
			for(int i=0;i<12;i++){
				pixelesAnalizados+=contador[i];
				if(contador[i]>ganador){
					ganador=contador[i];
					colorpixel=i;
				}
			}
	
			s = new Sonido(colorpixel);

		    	

				
				
				
//				BufferedImage imgb;
//				BufferedImage imgb2;
//				BufferedImage imgb3;
//				imgb = seg.Binarizacion(img,20);//34 es el máximo
//				imgb2 = seg.Bordes(imgb);
//				imgb3 = seg.escalaDeGrises(img);
//				int []vector = seg.ObtenerVector(imgb);
//				int []vector2 = seg.QuickSort(vector,0,226599);
//				imgb = seg.recuadraImagen(imgb);
//				
//				
//		    	ImageIcon imagenmuestra2 = new ImageIcon(imgb);
//				JLabel etiqueta2 = new JLabel(imagenmuestra2);
//				g = new Grafico(etiqueta2);
//				g.show();
//				
//				
//		    	ImageIcon imagenmuestra3 = new ImageIcon(imgb2);
//				JLabel etiqueta3 = new JLabel(imagenmuestra3);
//				g = new Grafico(etiqueta3);
//				g.show();
//				
//				
//		    	ImageIcon imagenmuestra4 = new ImageIcon(imgb3);
//				JLabel etiqueta4 = new JLabel(imagenmuestra4);
//				g = new Grafico(etiqueta4);
//				g.show();
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lblDetecta = new JLabel("Color ",JLabel.CENTER);
	}
	
	
}