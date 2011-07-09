package cargador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Colores;
import clases.Detector;
import clases.DetectorMedia;
import clases.DetectorMediana;
import clases.DetectorPixeles;
import clases.Grafico;
/*@Desc Clase del nivel de la capa de negocios. Implementa las operaciones que son llamadas desde el Controlador de la aplicación
 * para poder cargar las imagenes, alamacenarlas y modificaralas, apoyandose en un objeto la clase de más bajo nivel, es decir ProcesadorDeImagenes 
 */
public class ManejadorDeImagenes {
	ProcesadorDeImagenes procesador; 
	String ruta;
	boolean editado = false;  
	
	// Constructor de la clase 
	public ManejadorDeImagenes() {
		procesador = new ProcesadorDeImagenes();
	}

//	@Desc Método que lleva a cabo la carga de un archivo de imagen
  
public boolean cargaArchivoDeImagen(JPanel contenedor, PanelDeImagen lienzo){
	String nombreArchivo = "";
	boolean estado = true;
	if(editado){
		//new MsgError("Confirmacion","Aqui debemos pedir confirmación",200,180);
		int resultado = JOptionPane.showConfirmDialog((Component)null, "¿Deseas guardar los cambios en este documento?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(resultado==JOptionPane.YES_OPTION)
			guardaArchivoDeImagen(contenedor);     
	}
	JFileChooser selector = new JFileChooser(System.getProperty("user.dir"));
	selector.addChoosableFileFilter(new FiltroDeArchivo("gif","Archivos Gif"));
	String lista[] = {"jpeg","jpg"};
	selector.addChoosableFileFilter(new FiltroDeArchivo(lista,"Archivos JPEG"));
	selector.setDialogTitle("Abrir archivo de imagen");
	selector.setDialogType(JFileChooser.OPEN_DIALOG);
	int resultado = selector.showOpenDialog(null);
	if(resultado == JFileChooser.APPROVE_OPTION){
		nombreArchivo = selector.getSelectedFile().getName();
		ruta = selector.getSelectedFile().getPath();
		Image imagen = procesador.cargaImagen(ruta, nombreArchivo);
	
		lienzo.estableceImagen(imagen);   
		lienzo.repaint();        
		editado = false;
	}else
		estado = false; 
	return estado;
}

//@Desc Método que lleva a cabo la operación de salvar el archivo de imagen cargado
public boolean guardaArchivoDeImagen(JPanel contenedor){
	boolean estado = true;
	JFileChooser selector = new JFileChooser(System.getProperty("user.dir"));
	selector.addChoosableFileFilter(new FiltroDeArchivo("gif","Archivos Gif"));
	String lista[] = {"jpeg","jpg"};
	selector.addChoosableFileFilter(new FiltroDeArchivo(lista,"Archivos JPEG"));
	selector.setDialogTitle("Guardar archivo de imagen");
	selector.setDialogType(JFileChooser.SAVE_DIALOG);  
	int resultado = selector.showSaveDialog(contenedor);
	if(resultado == JFileChooser.APPROVE_OPTION){
		//guardar archivo en la ruta especificada
		String nombreArchivo = selector.getSelectedFile().getName();
		String ruta = selector.getSelectedFile().getPath();   
		estado = procesador.guardaImagen(ruta, nombreArchivo);
		if(!estado)
			JOptionPane.showMessageDialog((Component)null,"Error del sistema : "+procesador.devuelveMensajeDeError(),"Error de Imagen",JOptionPane.OK_OPTION);
		editado = false;
	}else
		estado = false; 
	return estado;
}
 
 
 ////////////////////
 
 public JLabel detectaColorErosion(){
	 BufferedImage imagenCuantizada;
	 BufferedImage imagenOriginal;
	 Detector detector = new Detector();
		
	 JLabel jl = detector.ejecutaErosion(ruta);
	 imagenCuantizada=detector.getImagenCuantizada();
	 imagenOriginal=detector.getImagenOriginal();
	 int contador[]=detector.getContadorPixeles();
	 int pixelesAnalizados = detector.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);

	 cargaFrameDetectorPixeles(detPix, "Erosion");

	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 

 }
 
 public JLabel detectaColorMedia(){
	 Detector detectorMedia = new Detector();
		
	 BufferedImage imagenCuantizada;
	 BufferedImage imagenOriginal;
	 JLabel jl = detectorMedia.ejecutaMedia(ruta);
	 imagenCuantizada=detectorMedia.getImagenCuantizada();
	 imagenOriginal=detectorMedia.getImagenOriginal();
	 int contador[]=detectorMedia.getContadorPixeles();
	 int pixelesAnalizados = detectorMedia.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
     
	 cargaFrameDetectorPixeles(detPix, "Media");

	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 
//	 c.imprimeConfirmacion();
 }
 
 public JLabel detectaColorMediana(){
	 Detector detectorMediana = new Detector();
	 BufferedImage imagenCuantizada;
	 BufferedImage imagenOriginal;
	 JLabel jl = detectorMediana.ejecutaMediana(ruta);
	 imagenCuantizada=detectorMediana.getImagenCuantizada();
	 imagenOriginal=detectorMediana.getImagenOriginal();
	 int contador[]=detectorMediana.getContadorPixeles();
	 int pixelesAnalizados = detectorMediana.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
     
	 cargaFrameDetectorPixeles(detPix, "Mediana");
     
	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 

 }
 
 public JLabel detectaColorApertura(){
	 Detector detectorApertura = new Detector();
	 BufferedImage imagenCuantizada;
	 BufferedImage imagenOriginal;
	 JLabel jl = detectorApertura.ejecutaApertura(ruta);
	 imagenCuantizada=detectorApertura.getImagenCuantizada();
	 imagenOriginal=detectorApertura.getImagenOriginal();
	 int contador[]=detectorApertura.getContadorPixeles();
	 int pixelesAnalizados = detectorApertura.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
     
	 cargaFrameDetectorPixeles(detPix, "Apertura");
     
	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 

 }
 
 public JLabel detectaColorCierre(){
	 Detector detectorCierre = new Detector();
	 BufferedImage imagenCuantizada;
	 BufferedImage imagenOriginal;
	 JLabel jl = detectorCierre.ejecutaCierre(ruta);
	 imagenCuantizada=detectorCierre.getImagenCuantizada();
	 imagenOriginal=detectorCierre.getImagenOriginal();
	 int contador[]=detectorCierre.getContadorPixeles();
	 int pixelesAnalizados = detectorCierre.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
     
	 cargaFrameDetectorPixeles(detPix, "Cierre");
     
	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 

 }
 
 public JLabel detectaColorPasoAPaso(){
	 Detector detectorPasoAPaso = new Detector();
	 BufferedImage imagenOriginal;
	 BufferedImage imagenErosionada;
	 BufferedImage imagenAclarada;
	 BufferedImage imagenCuantizada;
	 
	 
	 detectorPasoAPaso.ejecutaPasoAPaso1Erosion(ruta);
	 detectorPasoAPaso.ejecutaPasoAPaso2Aclarado(ruta);
	 JLabel jl = detectorPasoAPaso.ejecutaPasoAPaso3SegmentadoCuantizado(ruta);
	 
	 imagenOriginal=detectorPasoAPaso.getImagenOriginal();
	 imagenErosionada=detectorPasoAPaso.getImagenErosion();
	 imagenAclarada=detectorPasoAPaso.getImagenAclarada();
	 imagenCuantizada=detectorPasoAPaso.getImagenCuantizada();
	 
	 int contador[]=detectorPasoAPaso.getContadorPixeles();
	 int pixelesAnalizados = detectorPasoAPaso.getPixelesAnalizados();
	 
	 DetectorPixeles detPix = new DetectorPixeles(imagenCuantizada, imagenOriginal);
     
	 cargaFrameDetectorPixeles(detPix, "Paso a paso");
     
	 DecimalFormat formateador = new DecimalFormat("####.#%");
	 String porcentaje;
	 for(int i=0;i<contador.length;i++){
		 porcentaje = formateador.format((float)(contador[i])/(pixelesAnalizados));
		 detPix.setPorcentaje(i,porcentaje);

	 }
     
     
     
	 return jl;
	 

 }
 
 public void cargaFrameDetectorPixeles(DetectorPixeles dp, String nombreDelFrame){
	 JFrame f = new JFrame(nombreDelFrame);
     
//   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JPanel nuevoJPanel = dp.getContent();
	 f.add(nuevoJPanel);
	 f.pack();
	 f.setLocationRelativeTo(null);
	 f.setVisible(true);
 }
 
 
 public void cargaAyuda(){
	 System.out.println("AYUDA");
 }
 //////////////
 
 
//@Desc Método que lleva a cabo la transformación de la imagen cargada a una imagen de escala de grises y la despliega en pantalla
  
// public void muestraEscalaDeGrises(PanelDeImagen lienzo)
// {
//  procesador.escalaDeGrises();
//  lienzo.estableceImagen(procesador.devuelveImagenModificada());
//  lienzo.repaint();
// }
 
//@Desc Método que lleva a cabo la modificación del brillo de la imagen cargada y despliega la imagen resultante en pantalla
// public void muestraBrillo(PanelDeImagen lienzo, int valor)
// {
//  procesador.modificaBrillo(valor);
//  lienzo.estableceImagen(procesador.devuelveImagenModificada());
//  lienzo.repaint();
//  editado = true;
// }

//@Desc @Desc Método que lleva a cabo la modificación de los colores de la imagen cargada y despliega la imagen resultante en pantalla
  
// public void muestraColores(PanelDeImagen lienzo, int rojo, int verde, int azul)
// {
//  procesador.modificaColor(rojo,verde,azul);
//  lienzo.estableceImagen(procesador.devuelveImagenModificada());
//  lienzo.repaint();
//  editado = true;
// }

//@Desc Método que coloca en la pantalla la imagen original que se cargó con el método cargarArchivoDeImagen
	public void restableceImagen(PanelDeImagen lienzo){
		lienzo.estableceImagen(procesador.devuelveImagenBase());
		lienzo.repaint();
		editado = false;
	}
}