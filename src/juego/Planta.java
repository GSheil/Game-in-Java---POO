package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Planta {
	int x;
	int y;
	int direccion;
	Image img;
	BolaDeFuego bolaFuego;
	int ancho;
	int alto;
	int velocidad = 2;
	
	public Planta(int x,int y,int d) {
		this.x = x+50;
		this.y = y+50;
		this.direccion = d;
		if (direccion == 1) {
			this.img = Herramientas.cargarImagen("PlantaAbajo.png");//getImagen();
		} else if (direccion == 3){
			this.img = Herramientas.cargarImagen("PlantaIzquierda.png");//getImagen();
		} else if (direccion == 2){
			this.img = Herramientas.cargarImagen("PlantaDerecha.png");//getImagen();
		}  else if (direccion == 0){
			this.img = Herramientas.cargarImagen("PlantaArriba.png");//getImagen();
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
	public BolaDeFuego disparar(Entorno e) {
		return bolaFuego = new BolaDeFuego(x,y,direccion,velocidad);	
	}
}
