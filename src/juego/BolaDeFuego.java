package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {
	int x;
	int y;
	int direccion;
	Image img;
	int ancho;
	int alto;
	int velocidad;
	
	
	BolaDeFuego(int x,int y,int direccion, int velocidad){
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.velocidad = velocidad;
		this.img = Herramientas.cargarImagen("fueguito.png");
		this.ancho=img.getWidth(null);
		this.alto=img.getHeight(null);
	}
	public void mover() {
		if (direccion ==0)
		{    
			
			y-=velocidad*2;
		}
		if (direccion ==1)
		{
			
			y+=velocidad*2;	
		}
		if (direccion ==2)
		{
			x+=velocidad*2;
			
		}
		if (direccion ==3)
		{
			x-=velocidad*2;	
			
		}
	}
	public void dibujarse(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 1);
	}
}
