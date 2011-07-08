package clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Detector {
	static 
	{ 
	      System.setProperty("com.sun.media.jai.disableMediaLib", "true"); 
	} 
	int rgbs[];
	float hsb[];
	Integer colorpixel = null;
	int pixelesAnalizados;
	int ganador;
	
	JLabel lblDetecta;
	Colores color;
	Segmentacion seg;
	Aclarador aclarador;
	Filtros fil;
	int value;
	
	
	BufferedImage imagenOriginal;
	
	int ancho;
	int alto;
	
	BufferedImage imgErosion;
	BufferedImage imgAclarada;
	BufferedImage imagenCuantizada2;
	
	Map<Integer,List<Pixel>> mapa;
	Sonido s;
	
	
	int contador[];
	
	public Detector(){
		
		
		
		aclarador = new Aclarador();
		rgbs = new int[3];
		hsb = new float[3];
		color = new Colores();
		contador = new int[12];
		for(int i=0;i<12;i++){
			contador[i] = 0;
		}
	}
	
	
	public BufferedImage getImagenCuantizada(){
		return imagenCuantizada2;
	}
	
	public BufferedImage getImagenOriginal(){
		return imagenOriginal;
	}
	
	public BufferedImage getImagenErosion(){
		return imgErosion;
	}
	
	public BufferedImage getImagenAclarada(){
		return imgAclarada;
	}
	
	public int[] getContadorPixeles(){
		return contador;
	}
	
	public int getPixelesAnalizados(){
		return pixelesAnalizados;
	}
	
	public Map<Integer,List<Pixel>> getMapa(){
		return mapa;
	}
	
	public void obtenerImagen(String ruta){
		try {
			imagenOriginal = ImageIO.read( new File (ruta));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		ancho = imagenOriginal.getWidth();
		alto = imagenOriginal.getHeight(null);
	}
	
	public JLabel ejecutaErosion(String ruta){
		obtenerImagen(ruta);
		imagenCuantizada2 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		seg = new Segmentacion(ancho,alto);
		fil = new Filtros(ancho, alto);
		imgErosion = fil.erode(imagenOriginal);
		
//			imgErosion = fil.dilate(imgErosion); 
		
		imgAclarada = aclarador.aclara(imgErosion);
		mapa = Segmentacion.segmentaFinal(imgAclarada, imagenCuantizada2);
		for(int i=1; i<ancho; i++){
			if(i%21==0){
				for(int j=1; j<alto;j++){
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
				
			
		
		return lblDetecta = new JLabel("Color "+new Colores().getColoresMap().get(colorpixel)+". Aparicion: "+(100*ganador)/pixelesAnalizados+"%",JLabel.CENTER);
	}
	
	
	public JLabel ejecutaMedia(String ruta){
		obtenerImagen(ruta);
		imagenCuantizada2 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		seg = new Segmentacion(ancho,alto);
		fil = new Filtros(ancho, alto);
		BufferedImage imgMedia;
		
		imgMedia = seg.filtroMedia(imagenOriginal);
		imgAclarada = aclarador.aclara(imgMedia);
		mapa = Segmentacion.segmentaFinal(imgAclarada, imagenCuantizada2);

		for(int i=1; i<ancho; i++){
			if(i%21==0){
				for(int j=1; j<alto;j++){
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
		return lblDetecta = new JLabel("Color "+new Colores().getColoresMap().get(colorpixel)+". Aparicion: "+(100*ganador)/pixelesAnalizados+"%",JLabel.CENTER);
	}
	
	
	public JLabel ejecutaMediana(String ruta){
		obtenerImagen(ruta);
		imagenCuantizada2 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		seg = new Segmentacion(ancho,alto);
		fil = new Filtros(ancho, alto);
		BufferedImage imgMediana;
			
		imgMediana = seg.filtroMediana(imagenOriginal);
		imgAclarada = aclarador.aclara(imgMediana);
		mapa = Segmentacion.segmentaFinal(imgAclarada, imagenCuantizada2);
		for(int i=1; i<ancho; i++){
			if(i%21==0){
				for(int j=1; j<alto;j++){
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
		return lblDetecta = new JLabel("Color "+new Colores().getColoresMap().get(colorpixel)+". Aparicion: "+(100*ganador)/pixelesAnalizados+"%",JLabel.CENTER);
	}
	
	
}
	
