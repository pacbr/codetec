package clases;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Colores {
	Map<Integer, String> coloresMap = new HashMap<Integer,String>();
	public Colores(){
		coloresMap.put(0, "Rojo");
		coloresMap.put(1, "Naranja");
		coloresMap.put(2, "Amarillo");
		coloresMap.put(3, "Gris");
		coloresMap.put(4, "Verde");
		coloresMap.put(5, "Verde agua");
		coloresMap.put(6, "Celeste");
		coloresMap.put(7, "Azul");
		coloresMap.put(8, "Violeta");
		coloresMap.put(9, "Rosa");
		coloresMap.put(10, "Negro");
		coloresMap.put(11, "Blanco");
	}
	
	public static int colorAHexadecimal(Integer color){
		Integer valor = null;
		if(color == 0){
			valor = 0xFF0000;
		}else if(color == 1){
			valor = 0xFF8000;
		}else if(color == 2){
			valor = 0xFFFF00;
		}else if(color == 3){
			valor = 0x848484;
//			valor = 0x6B8E23; //VERDE Oliva
		}else if(color == 4){
			valor = 0x00FF00;
		}else if(color == 5){
			valor = 0x7FFFD4;
		}else if(color == 6){
			valor = 0x00FFFF;
		}else if(color == 7){
			valor = 0x0000FF;
		}else if(color == 8){
			valor = 0xEE82EE;
		}else if(color == 9){
			valor = 0xFF1493;
		}else if(color == 10){
			valor = 0x000000;
		}else if(color == 11){
			valor = 0xFFFFFF;
		}
		return valor;
	}
	
	public static Integer decideColor(float h, float s, float v){
		Integer color = null;
		float matiz = h*360;
		float saturacion = s*100;
		float brillo = v*100;
		boolean enc = false;
		
		if (((saturacion<9 && brillo>97)||(saturacion<6 && brillo>89))&&!enc){
			//			System.out.println("Color BLANCO");
			color = 11;
			enc=true;
		}else if (((brillo<13)||(saturacion<5)&&(brillo<30))&&!enc){
//			System.out.println("Color NEGRO");
			color = 10;
			enc=true;
		}
		else if (((saturacion<=10 && brillo>30&& brillo<80)||(saturacion<=6 && brillo>30&& brillo<=90)||(saturacion<=20 && brillo<=48)||(saturacion<15 && brillo<60)||(saturacion<10 && brillo<76)||(saturacion<5 && brillo<90)) && !enc){
//			System.out.println("Color GRIS");
			color = 3;
			enc=true;
		}else if ((matiz<=14.0 || matiz>347) &&!enc){
	//			System.out.println("Color ROJO");
				if(brillo<30) color = 10;
				else color = 0;
				enc=true;
		}else if ((matiz>=14.0 && matiz<41)&&!enc){
	//			System.out.println("Color NARANJA");
				color = 1;
				enc=true;
		}else if ((matiz>=41 && matiz<71)&&!enc){
//			System.out.println("Color AMARILLO");
			color = 2;
			enc=true;
		}
		
//		if (matiz>=90 && matiz<120){
//			System.out.println("Color Verde Oliva");
//			
//			
//		}	
		
		else if ((matiz>=71 && matiz<150)&&!enc){
//			System.out.println("Color Verde");
			color = 4;
			enc=true;
		}else if ((matiz>=150 && matiz<161)&&!enc){
//			System.out.println("Color Verde agua");
			color = 5;
			enc=true;
		}else if (((matiz>=161 && matiz<190) || (saturacion>15 && saturacion<36&& brillo>80))&&!enc){
//			System.out.println("Color Celeste");
			color = 6;
			enc=true;
		}else if ((matiz>=190 && matiz<262)&&!enc){
//			System.out.println("Color Azul");
			color = 7;
			enc=true;
		}else if (((matiz>=262 && matiz<320)|| (matiz>248 && matiz<333 && brillo>15 && brillo<75))&&!enc){
//			System.out.println("Color Violeta");
			color = 8;
			enc=true;
		}else if (((matiz>=320 && matiz<347) || (matiz>353 && saturacion<70) || (matiz<7 && saturacion<70))&&!enc)  {
//			System.out.println("Color Rosa");
			color = 9;
			enc=true;
		}else{
			color = 10;
		}
		return color;
	}
	
	public static int[] obtieneRGB (int valor){
		int[] values = new int[3];
		values[0]   = (valor & 0x00ff0000) >> 16;
		values[1] = (valor & 0x0000ff00) >> 8;
		values[2]  =  valor & 0x000000ff;
		return values;
	}
	
	public static int getRGB (int[] rgbs){
		int valor;
		valor = rgbs[0] & 0x11ff1111;
		valor = valor &(rgbs[1] & 0x1111ff11);
		valor = valor &(rgbs[2] & 0x111111ff);
		return valor;
	}
	
	public  Integer obtieneColor(Integer valor){
		int rgbs[] = new int[3];;
		float hsb[] = new float[3];
		Integer colorpixel = null;
		rgbs = obtieneRGB(valor);
		Color.RGBtoHSB(rgbs[0], rgbs[1], rgbs[2], hsb);
		colorpixel = Colores.decideColor(hsb[0], hsb[1], hsb[2]);
		return colorpixel;
	}
}