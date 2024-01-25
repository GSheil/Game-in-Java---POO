package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Lapida {
	Image img;	
	int x;
	int y;
	public Lapida(int x, int y) {
		this.x = x;
		this.y = y;
		img = Herramientas.cargarImagen("perroMuerto3.png");
	}	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(img, this.x, this.y, 0, 1);
	}
}
