package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Cartel {
	int x;
	int y;
	int resultadoPartida;
	Image img;;
	String texto= "";
	
	public Cartel(int resultadoPartida) {
		this.x=400;
		this.y=300;
		this.resultadoPartida = resultadoPartida;
		if (resultadoPartida == 0) {
			img = Herramientas.cargarImagen("victoria.gif");
			texto=("¡GANASTE!");
		} else {
			img = Herramientas.cargarImagen("derrota.gif");
			texto=("¡PERDISTE!");
		}
		
	}
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(img, this.x, this.y, 0, 1);
		entorno.cambiarFont("Arial", 70, Color.white);
		entorno.escribirTexto(texto, 200, 500);
	}
}