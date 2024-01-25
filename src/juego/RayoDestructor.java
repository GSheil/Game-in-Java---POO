package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class RayoDestructor {
	int x;
	int y;
	int direccion;
	Image img;
	RayoDestructor (int x, int y, int d){
		this.x = x;
		this.y = y;
		this.direccion = d;
		this.img = Herramientas.cargarImagen("rayo.png");
	}
	
	public void mover() {
		if (direccion ==0)
		{    
			y-=10;	
		}
		if (direccion ==1)
		{
			x+=10;	
		}
		if (direccion ==2)
		{
			y+=10;	
		}
		if (direccion ==3)
		{
			x-=10;	
		}		
	}
	public void dibujarse(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 1);
	}
	
	
}
