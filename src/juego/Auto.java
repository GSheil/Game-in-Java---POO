package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Auto {
	int x;
	int y;
	int direccion;
	Image img;
	int alto;
	int ancho;
	int velocidad;
	
	
	public Auto(int x, int y,int d) {
		this.x = x+25;
		this.y = y+25;
		this.direccion = d;
		this.velocidad = 2;
		if (direccion == 1) {
			this.img = Herramientas.cargarImagen("autito-abajo.png");//getImagen();
		} else if (direccion == 3){
			this.img = Herramientas.cargarImagen("autito-izquierda.png");//getImagen();
		} else if (direccion == 2){
			this.img = Herramientas.cargarImagen("autito-derecha.png");//getImagen();
		}  else if (direccion == 0){
			this.img = Herramientas.cargarImagen("autito-arriba.png");//getImagen();
		}
		this.ancho=img.getWidth(null);
		this.alto=img.getHeight(null);
	}
	public void dibujarse(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 1);
	}
	public void mover() {
		if(this.direccion == 1) {// de arriba para abajo
			this.y += velocidad;
			if (this.y > 700) {
				this.y = -5;
			}
		}
		if (this.direccion == 2) {//de derecha a izquierda
			this.x += velocidad;
			if (this.x > 900) {
				this.x = -5;
			}
		}
		if (this.direccion == 0) {//de abajo para arriba
			this.y -= velocidad;
			if (this.y < -100) {
				this.y = 605;
			}
		}
		if (this.direccion == 3) {
			this.x -= velocidad;
			if (this.x < -100) {
				this.x = 805;
			}
		}
	}
}
