package clases;

import java.util.HashMap;
import java.util.Map;

public class Colores {
	Map<Integer, String> coloresMap = new HashMap<Integer,String>();
	public Colores(){
		coloresMap.put(0, "Rojo");
		coloresMap.put(1, "Naranja");
		coloresMap.put(2, "Amarillo");
		coloresMap.put(3, "Verde oliva");
		coloresMap.put(4, "Verde");
		coloresMap.put(5, "Verde agua");
		coloresMap.put(6, "Celeste");
		coloresMap.put(7, "Azul");
		coloresMap.put(8, "Violeta");
		coloresMap.put(9, "Rosa");
		coloresMap.put(10, "Negro");
		coloresMap.put(11, "Blanco");
	}
	
	public Integer decideColor(float h, float s, float v){
		Integer color = null;
		float matiz = h*360;
		float saturacion = s*100;
		float brillo = v*100;
		boolean enc = false;
		
		if (((brillo<4)||(saturacion<5)&&(brillo<60))&&!enc){
//			System.out.println("Color Negro");
			color = 10;
			enc=true;
		}	
		
		if ((saturacion<9 && brillo>97)&&!enc){
//			System.out.println("Color Blanco");
			color = 11;
			enc=true;
		}
		
		
		if ((matiz<=14.0 || matiz>347) &&!enc){
//			System.out.println("Color ROJO");
			if(brillo<30) color = 10;
			else color = 0;
			enc=true;
		}		
		
		if ((matiz>=14.0 && matiz<41)&&!enc){
//			System.out.println("Color NARANJA");
			color = 1;
			enc=true;
		}	
		
		if ((matiz>=41 && matiz<71)&&!enc){
//			System.out.println("Color AMARILLO");
			color = 2;
			enc=true;
		}
		
//		if (matiz>=90 && matiz<120){
//			System.out.println("Color Verde Oliva");
//			color = 3;
//			
//		}	
		
		if ((matiz>=71 && matiz<150)&&!enc){
//			System.out.println("Color Verde");
			color = 4;
			enc=true;
		}
		
		if ((matiz>=150 && matiz<161)&&!enc){
//			System.out.println("Color Verde agua");
			color = 5;
			enc=true;
		}	
	
		
		if ((matiz>=161 && matiz<190)&&!enc){
//			System.out.println("Color Celeste");
			color = 6;
			enc=true;
		}
		
		if ((matiz>=190 && matiz<262)&&!enc){
//			System.out.println("Color Azul");
			color = 7;
			enc=true;
		}	
		
		if (((matiz>=262 && matiz<318)|| (matiz>248 && matiz<333 && brillo>15 && brillo<75))&&!enc){
//			System.out.println("Color Violeta");
			color = 8;
			enc=true;
		}	

		
		if (((matiz>=318 && matiz<347) || (matiz>353 && saturacion<70) || (matiz<7 && saturacion<70))&&!enc)  {
//			System.out.println("Color Rosa");
			color = 9;
			enc=true;
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
}