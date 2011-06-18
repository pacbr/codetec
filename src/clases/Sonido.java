package clases;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class Sonido extends JFrame {

private static final long serialVersionUID = 1L;
	File sf;
	public Sonido(int sonido)
    {	
		switch (sonido) {
//		case 0:
//			 URL urlDeLaImagen = Test.class.getClassLoader().getResource("sonidos/rojo.wav");
//			 
//			sf = Cargador.getImagen("sonidos/rojo.wav");
//			break;
		case 0:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/rojo.wav");
			break;
		case 1:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/naranja.wav");
			break;
		case 2:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/amarillo.wav");
			break;
		case 3:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/verdeoliva.wav");
			break;
		case 4:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/verde.wav");
			break;
		case 5:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/verdeagua.wav");
			break;
		case 6:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/celeste.wav");
			break;
		case 7:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/azul.wav");
			break;
		case 8:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/violeta.wav");
			break;
		case 9:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/rosa.wav");
			break;
		case 10:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/negro.wav");
			break;
		case 11:
			sf = new File(System.getProperty("user.dir")+"/sonidoscolores/blanco.wav");
			break;



		}
        
        AudioFileFormat aff;
        AudioInputStream ais;
   try
        {
         aff=AudioSystem.getAudioFileFormat(sf);
         ais=AudioSystem.getAudioInputStream(sf);
         AudioFormat af=aff.getFormat();
         DataLine.Info info = new DataLine.Info(
                                          Clip.class,
                                          ais.getFormat(),
                                          ((int) ais.getFrameLength() *
                                              af.getFrameSize()));
       Clip  ol = (Clip) AudioSystem.getLine(info);
       ol.open(ais);
       ol.loop(0);
        
       }
        catch(UnsupportedAudioFileException ee){}
        catch(IOException ea){}
        catch(LineUnavailableException LUE){};
        }
}