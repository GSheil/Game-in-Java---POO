package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
	
public class Calles {
	
	double x;
	double y;
	Image img;
	int direccion;
	
	public Calles(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		if (direccion == 0) {
			img = Herramientas.cargarImagen("calleTex.png");
		} else if (direccion == 1) {
			img = Herramientas.cargarImagen("calleH2Tex.png");
		}
		
	}	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(img, x+50, y+50, 0, 1);
	}
	
}
