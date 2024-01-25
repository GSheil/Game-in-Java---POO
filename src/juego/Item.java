package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Item {
	int x;
	int y;
	Image img;;
	
	public Item() {
		this.x=400;
		this.y=300;
		img = Herramientas.cargarImagen("corazon.png");
	}
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(img, this.x, this.y, 0, .5);
	}
}
