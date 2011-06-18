package clases;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Aclarador{
	public BufferedImage aclara(BufferedImage imagen){
		
		int w = imagen.getWidth();
		int h = imagen.getHeight(null);
		int min = Math.min(h, w);
		int dif = (Math.abs(h-w))/2;
		if (dif%2!=0)dif++;
		BufferedImage imgf=new BufferedImage(min+1, min+1, BufferedImage.TYPE_INT_RGB);
		int valorRGB;
		int valoresRGB[] = new int[3];
		float valoresHSB[] = new float[3];
		
		
		for(int r=0;r<min;r++){
			
			for(int c=0;c<min;c++){
				valorRGB = imagen.getRGB((r+dif),c);
				valoresRGB = Colores.obtieneRGB(valorRGB);
				Color.RGBtoHSB(valoresRGB[0], valoresRGB[1], valoresRGB[2], valoresHSB);
				valoresHSB[1] = valoresHSB[1] - (float) (0.1);
				valoresHSB[2] = valoresHSB[2] + (float) (0.2);
				
				if (valoresHSB[2]>1){
					valoresHSB[2]=1;
				}
				if (valoresHSB[1]<0){
					valoresHSB[1]=0;
				}
								
				if ((valoresHSB[1]>0.4)){
					valoresHSB[1]=(float) 1.0;
					valoresHSB[2]=(float) 1.0;
					
				}
				
				valorRGB = Color.HSBtoRGB(valoresHSB[0], valoresHSB[1], valoresHSB[2]);
				imgf.setRGB(r, c, valorRGB);
				
			}
						
		}
		
		return imgf;
	}
	
}