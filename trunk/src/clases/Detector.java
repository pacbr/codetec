package clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Detector {
	int rgbs[];
	float hsb[];
	Integer colorpixel = null;
	int pixelesAnalizados;
	int ganador;
	Sonido s;
	
	public JLabel ejecuta(String ruta){
		 
		// Variables locales
		BufferedImage imagen;
		BufferedImage imgFinal;
		Grafico g;
		JLabel lblDetecta;
		Colores color;
		try {
			imagen = ImageIO.read( new File (ruta));
			
	
			int w = imagen.getWidth();
			int h = imagen.getHeight(null);

			
			
			rgbs = new int[3];
			int value;
			hsb = new float[3];

			color = new Colores();
			
			
			int contador[] = new int[14];

			for(int i=0;i<=13;i++){
				contador[i] = 0;
			}
			
			
			Aclarador aclarador = new Aclarador();
			imgFinal = aclarador.aclara(imagen);
			
			int wAclarada = imgFinal.getWidth();
			int hAclarada = imgFinal.getHeight(null);


			for(int i=1; i<wAclarada; i++){
				if(i%21==0){
					for(int j=1; j<hAclarada;j++){
						if(j%21==0){
							
							value =  imgFinal.getRGB(i,j);
							rgbs = color.obtieneRGB(value);
							Color.RGBtoHSB(rgbs[0], rgbs[1], rgbs[2], hsb);
							colorpixel = color.decideColor(hsb[0], hsb[1], hsb[2]);
							contador[colorpixel]++;


						}
					}
				}
			}

			ganador=0;
			pixelesAnalizados=0;
			
			for(int i=0;i<14;i++){
				pixelesAnalizados+=contador[i];
				if(contador[i]>ganador){
					ganador=contador[i];
					colorpixel=i;
				}
			}
	
			s = new Sonido(colorpixel);

		    	ImageIcon imagenmuestra1 = new ImageIcon(imgFinal);
				JLabel etiqueta1 = new JLabel(imagenmuestra1);
				g = new Grafico(etiqueta1);
				g.show();
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lblDetecta = new JLabel("Color "+new Colores().coloresMap.get(colorpixel)+". Aparicion: "+(100*ganador)/pixelesAnalizados+"%",JLabel.CENTER);
	}
	
	
}
	
