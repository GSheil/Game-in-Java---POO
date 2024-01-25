package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Layka {

	// Variables de instancia
	int x;
	int y;
	int ancho;
	int alto;
	int direccion;
	int vidas;
	Image[] img;;
	Image imgSalud;

	//constructor

	public Layka(int x, int y, int direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.img = new Image[4];
		this.imgSalud = Herramientas.cargarImagen("corazon.png");
		this.vidas = 3;
		
		for (int i=0; i < img.length ; i++) {

			img[i] = Herramientas.cargarImagen("layka" + i +".png");

		}
		this.ancho=img[0].getWidth(null);
		this.alto=img[0].getHeight(null);

	}

	public void dibujarse(int d, Entorno entorno)
	{
		entorno.dibujarImagen(img[d], this.x, this.y, 0, 1);
		for (int x = 0;x<vidas;x++) {
			entorno.dibujarImagen(imgSalud, 600+(50*x), 30, 0, 1);
		}
	}
	public void mover(int d, Entorno e)
	{
		this.direccion=d;

		if (direccion ==0)
		{    
			y-=3;	
		}
		if (direccion ==1)
		{
			x+=3;	
		}
		if (direccion ==2)
		{
			y+=3;	
		}
		if (direccion ==3)
		{
			x-=3;	
		}
	}
	public boolean lastimar(Entorno e) {
		if(vidas>1){
			vidas+=-1;
			return true;
		} else {
			return false;
		}
		
	}
	public void curar() {
		if(vidas<3){
			vidas+=+1;
		}
		
	}
	public RayoDestructor disparar() {
		return new RayoDestructor(x,y,direccion);
	}
}
