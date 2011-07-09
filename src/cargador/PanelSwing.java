package cargador;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//@Desc Crea componentes de la interfaz gráfica 
 public class PanelSwing extends JPanel {
 static final long serialVersionUID = 10000;
 String nombreArchivo, ruta;
 JMenuBar barraMenu;
 JMenu menuArchivo, menuFiltros, menuDetecta;
 JMenuItem abrir, abrir2, guardar, salir, detectaErosion, detectaMedia, detectaMediana, detectaApertura, detectaCierre, detectaPasoAPaso, ayuda;
 JScrollPane panelDespl;
 JPanel panelBajo, panelDetecta, panelColor, panelVacio;
 int altura = 80;
 Image imagen;
 Image imgAux;
 EditorImg editor;
 PanelDeImagen lienzo;
 //JSlider jslBrillo, jslRojo, jslVerde, jslAzul;
 //JLabel lblRojo, lblVerde, lblAzul;
 CardLayout esqueInf;

//@Desc Constructor de la clase
  
 PanelSwing(EditorImg editor)
 {
  this.editor = editor; 
  this.setLayout(new BorderLayout());
  barraMenu = new JMenuBar();
  FlowLayout f = new FlowLayout();
  f.setAlignment(FlowLayout.LEFT);
  barraMenu.setLayout(f);
  menuArchivo = new JMenu("Archivo");
  menuFiltros = new JMenu("Filtros");
//  menuDetecta = new JMenu("Detecta1");
  abrir = menuArchivo.add("Abrir");
  guardar = menuArchivo.add("Guardar");
  guardar.setEnabled(false);
  menuArchivo.addSeparator();
  salir = menuArchivo.add("Salir");   
  barraMenu.add(menuArchivo);
  
  detectaErosion = menuFiltros.add("Erosión");
  detectaMedia = menuFiltros.add("Media");
  detectaMediana = menuFiltros.add("Mediana");
  menuFiltros.addSeparator();
  detectaApertura = menuFiltros.add("Apertura");
  detectaCierre = menuFiltros.add("Cierre");
  detectaErosion.setEnabled(false);
  detectaMedia.setEnabled(false);
  detectaMediana.setEnabled(false);
  detectaApertura.setEnabled(false);
  detectaCierre.setEnabled(false);
  barraMenu.add(menuFiltros);
  
  detectaPasoAPaso = menuFiltros.add("Paso a paso (Erosión)");
  detectaPasoAPaso.setEnabled(false);
  barraMenu.add(detectaPasoAPaso); //PARA AGREGAR JMENUITEM HAY QUE TOCAR TB EN LAS CLASES EDITORIMG y CONTROLADOR
  
//  detectaMedia = menuArchivo.add("Media");
//detectaMedia.setEnabled(false);
//barraMenu.add(detectaMedia);
//
//
//detectaMediana = menuArchivo.add("Mediana");
//detectaMediana.setEnabled(false);
//barraMenu.add(detectaMediana);
  

ayuda = menuArchivo.add("Ayuda");
barraMenu.add(ayuda);
  

  this.add("North",barraMenu);  //Agregamos la barra de menu
  creapanelCentral();     //Creamos el panel en el que se mostrara la imagen seleccionada
  creapanelBajo();     //Creamos el panel en el que se mostraran los controles para manipular la imagen  
 }

 //@Desc Método que crea el contenido del panel central de la ventana
  
 private void creapanelCentral()
 {     
  lienzo = new PanelDeImagen();
  panelDespl = new JScrollPane(lienzo);
  lienzo.estableceBase(panelDespl);  
  add("Center",panelDespl);
 }
//  @Desc Método que crea el contenido del panel inferior de la ventana
 
 private void creapanelBajo()
 {
  panelBajo = new JPanel();
  esqueInf = new CardLayout();   
  panelBajo.setLayout(esqueInf);
  panelBajo.setPreferredSize(new Dimension(this.getWidth(),altura));

  panelColor = new JPanel();
  panelVacio = new JPanel();
  panelDetecta = new JPanel(new BorderLayout());

//  Coloreado c = new Coloreado();
//  panelDetecta.add("Center", c.);
//  panelDetecta.add("Center", c.creaJLabel());
JLabel lbl = new JLabel();
panelDetecta.add(lbl);

  panelBajo.add("carta1", panelVacio);
  panelBajo.add("carta2", panelDetecta);


  //creaPaletas();   
  esqueInf.show(panelBajo, "carta1");

  this.add("South",panelBajo);
 }
 
// @Desc Método que crea el contenido del panel inferior de la ventana

// private void creaPaletas()
// {
//  GridBagLayout gridbag = new GridBagLayout();
//  GridBagConstraints constrain = new GridBagConstraints();
//  panelColor.setLayout(gridbag);
//  lblRojo = new JLabel("Rojo");
//  lblVerde = new JLabel("Verde");
//  lblAzul = new JLabel("Azul");
//  constrain.gridx = 0; constrain.gridy = 0;
//  constrain.gridheight = 1; constrain.gridwidth = 2;
//  gridbag.setConstraints(lblRojo, constrain);
//  panelColor.add(lblRojo);
//  constrain.gridx = 2; constrain.gridy = 0;
//  gridbag.setConstraints(lblVerde, constrain);
//  panelColor.add(lblVerde);
//  constrain.gridx = 4; constrain.gridy = 0;
//  gridbag.setConstraints(lblAzul, constrain);
//  panelColor.add(lblAzul);
//  jslRojo = new JSlider(SwingConstants.HORIZONTAL,0,50,0);
//  jslVerde = new JSlider(SwingConstants.HORIZONTAL,0,50,0);
//  jslAzul = new JSlider(SwingConstants.HORIZONTAL,0,50,0);
//  constrain.gridx = 0; constrain.gridy = 1;
//  constrain.gridheight = 1; constrain.gridwidth = 2;
//  gridbag.setConstraints(jslRojo, constrain);   
//  panelColor.add(jslRojo);
//  constrain.gridx = 2; constrain.gridy = 1;
//  gridbag.setConstraints(jslVerde, constrain);   
//  panelColor.add(jslVerde);
//  constrain.gridx = 4; constrain.gridy = 1;
//  gridbag.setConstraints(jslAzul, constrain);   
//  panelColor.add(jslAzul);   
//  panelBajo.add("carta3", panelColor);
// }
}