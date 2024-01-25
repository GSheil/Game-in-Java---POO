package juego;

import java.awt.Color;

import entorno.Entorno;

public class Informacion {
	int plantasEliminadas, puntaje;
	
	
	public Informacion(int puntaje, int plantasEliminadas) {
		this.plantasEliminadas = plantasEliminadas;
		this.puntaje = puntaje;
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.cambiarFont("Arial", 18, Color.white);
		entorno.escribirTexto("Puntaje: " + puntaje + "   Plantas Eliminadas: " + plantasEliminadas, 20, 30);
	}
}
