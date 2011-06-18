package cargador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//@Desc Extiende a la clase JPanel que se utiliza para crear un panel que permita visualizar im�genes en su interior

public class PanelDeImagen extends JPanel{
	static final long serialVersionUID=10000;
	Image img;
	Dimension tama�o;
	JScrollPane base;
 
	PanelDeImagen(){
		setBackground(Color.white);   
	}

	//@Desc M�todo a trav�s del cual la clase recibe el objeto de la imagen que ser� visualizada en su interior 
	public void estableceImagen(Image i){   
		img = i;
	}

	// @Desc M�todo a trav�s del cual la clase obtiene la referencia hacia el panel en el cual se encuentra contenido. 
	public void estableceBase(JScrollPane contenedor){
		base = contenedor;
	}  
 
	
	// @Desc M�todo extendido que es llamado cada ves que un objeto de esta clase llama al m�todo repaint(). A este le agregamos
	public void paintComponent(Graphics g){
		super.paintComponent(g);   
		if (img != null){ 
			if(base != null){
				setSize(new Dimension(base.getWidth()-10,base.getHeight()-10));
				setPreferredSize(new Dimension(base.getWidth()-10,base.getHeight()-10));
			}
			tama�o = new Dimension(getWidth(),getHeight());
			int x = tama�o.width - img.getWidth(this);
			while (x < 0){
				tama�o.setSize(tama�o.width+1, tama�o.height);
				x = tama�o.width - img.getWidth(this);
			}
			if(x > 0)
				x = (int) x/2;
			int y = tama�o.height - img.getHeight(this);
			while (y < 0){
				tama�o.setSize(tama�o.width, tama�o.height+1);       
				y = tama�o.height - img.getHeight(this);
			}
			if(y > 0)
				y = (int) y/2;
			   
			if(!getSize().equals(tama�o)){
				setSize(tama�o);
				setPreferredSize(tama�o);
			}        
			g.drawImage(img, x, y, this);
		}
	}
}