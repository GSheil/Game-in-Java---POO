package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {
		int x;
		int y;
		int ancho;
		int alto;
		Image [] img;
		double escala;
		int cont = 1;
		
		public Manzana(int x, int y) {
			
			this.x = x;
			this.y = y;
			
			this.img = new Image[6];
			for (int i=1; i <= img.length ; i++) {
				img[i-1] = Herramientas.cargarImagen("manzanaPKM" + i + ".png");
			}
			
			
			this.ancho=img[0].getWidth(null);
			this.alto=img[0].getHeight(null);
		}
	
		public void dibujarse(Entorno entorno, int apariencia)
		{
			
			entorno.dibujarImagen(img[apariencia], this.x , this.y , 0, 1);
			
		}
			
		
}
