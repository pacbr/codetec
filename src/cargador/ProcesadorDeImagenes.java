package cargador;

import java.awt.Canvas;
import java.awt.image.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 * @Desc Implementa el procesamiento de im�genes digitales 
 */
public class ProcesadorDeImagenes extends Canvas {
 Image imagenBase;
 Image imagenModificada; 
 String mensajeDeError = "";
 String tipoDeImagen = "";

 /**
  * @Desc M�todo que permite agregar una imagen al procesador que es recibida como par�metro
  * @param imagen
  */
 public void estableceImagen(Image imagen)
 {
  imagenBase = imagen;
  imagenModificada = null;
  tipoDeImagen = (String)imagenBase.getProperty("type", this);
 }
 /**
  * @Desc M�todo que permite agregar una imagen al procesador directamente desde un archivo de imagen
  * @param imagen
  */
 public Image cargaImagen(String ruta, String nombreDeArchivo)
 {
  imagenBase = Toolkit.getDefaultToolkit().getImage(ruta);
  

  
  imagenModificada = null;
  String[] partes = null;
  partes = nombreDeArchivo.split("\\.");  
  int tope = partes.length;
  if(tope > 1)
   tipoDeImagen = partes[tope - 1];
  return imagenBase;
 }
 
 
 
 
 /**
  * @Desc M�todo que modifica el brillo de la imagen base contenida a partir del valor de intesidad recibido
  * @param intensidad
  * @return Verdadero si todo sali� bien, falso en caso de error
  */
 public boolean modificaBrillo(int intensidad)
 {
  boolean estado = true;
  int p, rojo, verde, azul;
  int a = imagenBase.getWidth(this);  //Ancho
     int h = imagenBase.getHeight(this); //Alto  
     int totalDePixeles = a * h;    
  int pixeles[] = new int[totalDePixeles];   //Arreglo de pixeles
  PixelGrabber pg = new PixelGrabber(imagenBase,0,0,a,h,pixeles,0,a);  
  try
  {
   pg.grabPixels();
   for(int i = 0; i < totalDePixeles; i++)
   {
    p = pixeles[i]; //Valor de un pixel
    rojo = (0xff & (p>>16)) + intensidad;  //Desplaza el entero p 16 bits a la derecha y aplica la operacion AND a los primeros 8 bits 
    verde = (0xff & (p>>8)) + intensidad;  //Desplaza el entero p 8 bits a la derecha  y aplica la operacion AND a los siguientes 8 bits
    azul = (0xff & p) + intensidad;        //Aplica la operacion AND a los siguientes 8 bits
    if(rojo>255) rojo=255;
    if(verde>255) verde=255;
    if(azul>255) azul=255;
    if(rojo<0) rojo=0;
    if(verde<0) verde=0;
    if(azul<0) azul=0;
    pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
   }
   imagenModificada  = createImage(new MemoryImageSource(a,h,pixeles,0,a));      
  }catch(InterruptedException e)
  {
   //JOptionPane.showMessageDialog((Component)null,"Error del sistema : "+e.getMessage(),"Error de Imagen",JOptionPane.OK_OPTION);
   estado = false;
   this.mensajeDeError = e.getMessage();
  }
  return estado;
 }
 /**
  * @Desc M�todo que convierte la imagen base contenida en una imagen a escala de grises
  * @return Verdadero si todo sali� bien, falso en caso de error
  */
 public boolean escalaDeGrises()
 {
     boolean estado = true;
  int p, promedio, rojo, verde, azul;
  int a = imagenBase.getWidth(this);  //Ancho
     int h = imagenBase.getHeight(this); //Alto         
     int totalDePixeles = a * h;
  int pixeles[] = new int[totalDePixeles];   //Arreglo de pixeles
  PixelGrabber pg = new PixelGrabber(imagenBase,0,0,a,h,pixeles,0,a);
  try
  {
   pg.grabPixels();
   for(int i = 0; i < totalDePixeles; i++)
   {
    p = pixeles[i]; //Valor de un pixel
    rojo = (0xff & (p>>16));  //Desplaza el entero p 16 bits a la derecha y aplica la operacion AND a los primeros 8 bits 
    verde = (0xff & (p>>8));  //Desplaza el entero p 8 bits a la derecha  y aplica la operacion AND a los siguientes 8 bits
    azul = (0xff & p) ;        //Aplica la operacion AND a los siguientes 8 bits
    promedio = (int) ((rojo+verde+azul)/3);
    pixeles[i]=(0xff000000|promedio<<16|promedio<<8|promedio);
   }
   imagenModificada  = createImage(new MemoryImageSource(a,h,pixeles,0,a));
  }catch(InterruptedException e)
  {
   //JOptionPane.showMessageDialog((Component)null,"Error del sistema : "+e.getMessage(),"Error de Imagen",JOptionPane.OK_OPTION);
   estado = false;
   this.mensajeDeError = e.getMessage();
  }
  return estado;
 }
 /**
  * @Desc M�todo que modifica los colores de la imagen base contenida a partir de los valores de intensidad de los colores rojo, verde y amarillo recibidos
  * @param iRojo
  * @param iVerde
  * @param iAzul
  * @return Verdadero si todo sali� bien, falso en caso de error
  */
 public boolean modificaColor(int iRojo, int iVerde, int iAzul)
 {
  boolean estado = true;
  int p, rojo=0, verde=0, azul=0;
  int a = imagenBase.getWidth(this);  //Ancho
     int h = imagenBase.getHeight(this); //Alto        
     int totalDePixeles = a * h;
  int pixeles[] = new int[totalDePixeles];   //Arreglo de pixeles
  PixelGrabber pg = new PixelGrabber(imagenBase,0,0,a,h,pixeles,0,a);
  try
  {
   pg.grabPixels();
   for(int i = 0;i<(a*h);i++)
   {
    p = pixeles[i]; //Valor de un pixel
    rojo = (0xff & (p>>16)) + iRojo;
    verde = (0xff & (p>>8)) + iVerde;
    azul = (0xff & p) + iAzul;
    if(rojo>255) rojo=255;
    if(verde>255) verde=255;
    if(azul>255) azul=255;
    if(rojo<0) rojo=0;
    if(verde<0) verde=0;
    if(azul<0) azul=0;
    pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
   }
   imagenModificada = createImage(new MemoryImageSource(a,h,pixeles,0,a));   
  }catch(InterruptedException e)
  {   
   estado = false;
   this.mensajeDeError = e.getMessage();
  }   
  return estado;
 }
 /**
  * @Desc M�todo que almacena la imagen contenida en una archivo de imagen, de acuerdo a la informaci�n del archivo que recibe como par�metro
  * @param ruta
  * @param nombreDeArchivo
  * @param tipoDeImagen
  * @return
  */
 public boolean guardaImagen(String ruta, String nombreDeArchivo, String tipoDeImagen) 
 {
  boolean estado = true;
  BufferedImage imagen = creaBufferedImage((imagenModificada != null) ? imagenModificada : imagenBase);   
  try {
   ImageIO.write(imagen, tipoDeImagen, new File(ruta));
  } catch (IOException e) {
   estado = false;
   this.mensajeDeError = e.getMessage();
  }
  return estado;
 }
 /**
  * @Desc Versi�n por defecto del m�todo guardaImagen
  * @param ruta
  * @param nombreDeArchivo
  * @return
  */
 public boolean guardaImagen(String ruta, String nombreDeArchivo)
 {
  String[] partes = null;
  partes = nombreDeArchivo.split("\\.");
  int tope = partes.length;
  if(tope > 1)
   tipoDeImagen = partes[tope - 1];
  return guardaImagen(ruta, nombreDeArchivo, tipoDeImagen);
 }
 /**
  * @Desc M�todo que devuelve la imagen modificada por el procesador en un objeto de la clase Image
  * @return
  */
 public Image devuelveImagenModificada()
 {
  return imagenModificada;
 }
 /**
  * @Desc M�todo que devuelve la imagen base dentro de un objeto de la clase Image
  * @return
  */
 public Image devuelveImagenBase()
 {
  return imagenBase;
 }
 /**
  * @Desc M�todo que retora el �ltimo mensaje de error producido por los m�todos de la clase
  * @return
  */
 public String devuelveMensajeDeError()
 {
  return mensajeDeError;
 }
 /**
  * @Desc Versi�n por defecto del m�todo creaBufferedImage
  * @param imageIn
  * @return El objeto BufferedImage
  */
 public BufferedImage creaBufferedImage(Image imagenDeEntrada) {
  return creaBufferedImage(imagenDeEntrada, BufferedImage.TYPE_INT_RGB);
 }
 /**
  * @Desc M�todo para convertir un objeto Image a un objeto BufferedImage
  * @param imageIn
  * @param imageType
  * @return El objeto BufferedImage
  */
 public BufferedImage creaBufferedImage(Image imagenDeEntrada, int imageType) {  
     BufferedImage bufferedImageDeSalida = new BufferedImage(imagenDeEntrada.getWidth(this), imagenDeEntrada.getHeight(this), imageType);
     Graphics g = bufferedImageDeSalida.getGraphics();
     g.drawImage(imagenDeEntrada, 0, 0, null);
     return bufferedImageDeSalida;
 }
}