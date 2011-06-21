package clases;

import java.awt.image.BufferedImage;
import java.awt.Color;
 
public class Segmentacion {
 
	private int Ancho;
	private int Largo;
	
	private BufferedImage ImagenOriginal;
	 
	private int color[];
 
	public Segmentacion(int Ancho , int Largo) {
       
        this.Ancho = Ancho;
        this.Largo = Largo;
       
        color = new int [6];
       
        // Rojo ...
        color[0] = new Color (255,0,0).getRGB();
   
        // Verde ...
        color[1] = new Color (0,255,0).getRGB();
       
        // Azul ...
        color[2] = new Color (0,0,255).getRGB();
       
        // Amarillo ...
        color[3] = new Color (255,255,0).getRGB();
       
        // Blanco ...
        color[4] = new Color (255,255,255).getRGB();
           
        // Negro ...
        color[5] = new Color (0,0,0).getRGB();
       
    }
 
 
	public  BufferedImage Binarizacion (BufferedImage ImageBfrd , int n ) {
       
		int Contador = 0 ;
        long Umbral = 500000;
		Ancho = ImageBfrd.getWidth();
		Largo = ImageBfrd.getHeight(null);
       
		BufferedImage Nueva_Imagen = new BufferedImage(Ancho,Largo,BufferedImage.TYPE_INT_RGB) ;
		//BufferedImage Nueva_Imagen = ImageBfrd;
		for (int i = 0; i <Ancho ; i ++) {
	        for ( int j = 0; j <Largo ; j ++ ) {
	        	if ( ImageBfrd.getRGB(i,j) <-Umbral * n) {
	               
	                Contador ++;
	                Nueva_Imagen.setRGB(i,j,color[5]);
	           
	            } else {
	           
	                Nueva_Imagen.setRGB(i,j,color[4]);
	           
	            } 
	       
	        } 
       
        }
   
		return Nueva_Imagen;
   
    }
   
 
 
	public BufferedImage Bordes (BufferedImage ImageBfrd ) {
	   
	    BufferedImage Nueva_Imagen = new BufferedImage(Ancho,Largo,BufferedImage.TYPE_INT_RGB);
	//    BufferedImage Nueva_Imagen = ImagenOriginal;
	 
	    for (int i = 1; i <Ancho - 1 ; i ++) {
	        for ( int j = 1; j <Largo - 1 ; j ++ ) {
	        	if (ImageBfrd.getRGB(i,j) == -1 && ImageBfrd.getRGB(i-1,j) != -1 && ImageBfrd.getRGB(i+1,j) == -1) {
			   
			            Nueva_Imagen.setRGB(i,j,color[0]);
			            
			   
			    } else if (ImageBfrd.getRGB(i,j) == -1 && ImageBfrd.getRGB(i+1,j) != -1 && ImageBfrd.getRGB(i-1,j) == -1) {
			       
			            Nueva_Imagen.setRGB(i,j,color[0]);
			   
			   
			    } else if (ImageBfrd.getRGB(i,j) == -1 && ImageBfrd.getRGB(i,j-1) != -1 && ImageBfrd.getRGB(i,j+1) == -1) {
			       
			            Nueva_Imagen.setRGB(i,j,color[0]);
			   
			   
			    } else if (ImageBfrd.getRGB(i,j) == -1 && ImageBfrd.getRGB(i,j+1) != -1 && ImageBfrd.getRGB(i,j-1) == -1) {
			       
			            Nueva_Imagen.setRGB(i,j,color[0]);
			   
			    }  else 
			    	Nueva_Imagen.setRGB(i,j,color[4]);
			 }
	    }
	   
	    return Nueva_Imagen;   
	}
	
	
	public BufferedImage escalaDeGrises(BufferedImage imagen){
		int w = imagen.getWidth();
		int h = imagen.getHeight(null);
		BufferedImage imgg=new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		for(int c=0;c<w;c++){
			for(int r=0;r<h;r++){
				imgg.setRGB(c, r, imagen.getRGB(c,r));
			}
		}
		return imgg;
	}
	
}