package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
//import entorno.Herramientas;
//import java.util.Arrays;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego {

	private Entorno entorno; // EL OBJETO ENTORNO CONTROLA EL TIEMPO Y OTRAS COSAS

	Layka layka;
	Planta[] plantas, plantasMuertas;
	Item item;
	RayoDestructor rayo;
	BolaDeFuego bolaFuego;
	Manzana[] manzanas;
	Cartel cartel;
	Lapida lapida;
	Auto[] autos, autosRotos;
	Informacion informacion;
	Calles [] callesVerticales, callesHorizontales;
	int contPosElemento, altoPantalla, anchoPantalla, cantidadManzanasH, cantidadManzanasV, laykaDireccion;
	boolean salirJuego, seDisparoRayo, rayosMaximosEnPantalla, BolasMaximasEnPantalla, seDisparoBola, seDisparoB, infoStage, revivirEnProceso, repararEnProceso, sigueViva;
	int[] direccionDeLasPlantas, direccionDeLosAutos;
	int randomBola;
	int zonaParedIzquierda;
	int zonaParedArriba;
	int zonaParedAbajo;
	int zonaParedDerecha;
	int timer,timerAuto,tiempoParaRevivir,quienRevive,quienRepara,tiempoParaRepararse;
	
	
	
	
	public Juego() {	
		
		// Inicializa el objeto entorno
		
	
		this.anchoPantalla = 800;
		this.altoPantalla = 600;
		this.entorno = new Entorno(this, "Juego", anchoPantalla, altoPantalla);


		// Inicializar lo que haga falta para el juego
		
		informacion = new Informacion(0,0);
		laykaDireccion = 2; //Para donde inicia mirando Layka
		callesVerticales=new Calles[24]; //CREAR CONSTANTE DE CANTIDAD DE CALLES
		callesHorizontales=new Calles[9];
		cantidadManzanasH = 3;
		cantidadManzanasV = 2;
		rayosMaximosEnPantalla = false;
		layka=new Layka(400,550,2);
		autos=new Auto[3];
		plantas=new Planta[4];
		plantasMuertas=new Planta[4];
		autosRotos =new Auto[3];
		manzanas = new Manzana[6];
		item = null;
		contPosElemento = 0;
		infoStage = false;
		sigueViva=true;
		seDisparoRayo = false;
		seDisparoB = false;
		revivirEnProceso = false;
		repararEnProceso = false;
		quienRevive = 0;
		quienRepara = 0;
		Random rV = new Random();
		Random rH = new Random();
		direccionDeLasPlantas = new int[4];
		direccionDeLosAutos = new int[4];
		rayo = null;
		salirJuego = false;
		randomBola = 0;
		timer=0;
		timerAuto=0;
		tiempoParaRevivir=350;
		tiempoParaRepararse=400;
		
		
		
		
		
		
		for (int i = 0; i < direccionDeLasPlantas.length; i+=2) {
			direccionDeLasPlantas[i] = rV.nextInt(2);
		}
		for (int i = 1; i < direccionDeLasPlantas.length; i+=2) {
			direccionDeLasPlantas[i] = rH.nextInt(2, 4);
		}
		for (int j = 0; j < direccionDeLosAutos.length; j+=2) {
			direccionDeLosAutos[j] = rV.nextInt(2);
		}
		for (int j = 1; j < direccionDeLosAutos.length; j+=2) {
			direccionDeLosAutos[j] = rH.nextInt(2, 4);
		}
		
		
		//PREPARA LOS DIBUJOS DE LAS MANZANAS, CALLES V y CALLES H
		for (int y=0; y < cantidadManzanasV;y++) {
			for (int x=0; x < cantidadManzanasH;x++) {
				manzanas[contPosElemento]=new Manzana(x*(133+100/*Tamaño de Manzana + Tamaño Primera calle (Esto es cuanto se desplaza la siguiente manzana)*/)+66+100 /*Tamaño de Manzana media manzana + Tamaño Primera calle (Esto es la pocicion inicial)*/ ,y*(150+100)+ 175);
				contPosElemento += 1; 
			}
		}
		contPosElemento = 0;
		for (int x=0; x <= 3; x++) {
			for (int y=0; y <= 5; y++) { 
				callesVerticales[contPosElemento]=new Calles(x*((anchoPantalla/2)/cantidadManzanasH+100),100*y,0);
				contPosElemento += 1;
			}
		}
		contPosElemento = 0;
		for (int x=0; x <= 2; x++) {
			for (int y=0; y <= 2; y++) { 	
				callesHorizontales[contPosElemento]=new Calles((x*233+116),250*y,1); 
				contPosElemento += 1;
			}
		}
		for(int i = 0; i < plantas.length; i++) {
			if (i%2 == 0) {
				plantas[i] = new Planta(i*(233),250*i,direccionDeLasPlantas[i]);//verticales con movimiento vertical
			}else {
				plantas[i] = new Planta(277*i,(i-1)*(250),direccionDeLasPlantas[i]);//horizontales con movimiento horizontal 
			}
		}	
		for(int j = 0; j < autos.length; j++) {
			if(j%2 == 1) {
				autos[j] = new Auto(0,(100+150),direccionDeLosAutos[j]);
				
			}else {
				autos[j] = new Auto((100+133)*(j+1), 0, direccionDeLosAutos[j]);
			}
		}
		//PREPARA LOS DIBUJOS DE LAS PLANTAS, MANZANAS, CALLES V y CALLES H
		
		
			// Inicia el juego!
			this.entorno.iniciar();		
		}
		
	
	
	
	
	
		public void tick() {
			if (informacion.puntaje>=100){
					cartel = new Cartel(0);
					cartel.dibujarse(entorno);
			}else if (salirJuego==true){
				cartel = new Cartel(1);
				cartel.dibujarse(entorno);
			} else {
				
				
				//MAPA
				dibujarMapa(entorno, manzanas, callesVerticales, callesHorizontales);
				informacion.dibujarse(entorno);
				//LAPIDA
				if (layka == null){
					lapida.dibujarse(entorno);
				}
				
				//VIDAEXTRA
				if (item!=null) {
					item.dibujarse(entorno);
					int zonaParedIzquierda=item.x - 10;
					int zonaParedArriba=item.y - 10;
					int zonaParedAbajo=item.y + 10;
					int zonaParedDerecha=item.x + 10;
					
					if (layka != null) {
						if(    (layka.y >= zonaParedArriba && layka.y <= zonaParedAbajo && layka.x >= zonaParedIzquierda && layka.x <= zonaParedDerecha)          ) {
							layka.curar();
							item = null;
						}		
					}
					
				}
						
				//LAYKA
				if (layka != null) {
					if (entorno.estaPresionada(entorno.TECLA_DERECHA) && (restriccionManzanas(manzanas,layka) != 1) && (restriccionPared(layka) != 1) && (restriccionPared(layka) != 121) && (restriccionPared(layka) != 101)){
						layka.mover(1, this.entorno);
						laykaDireccion = 1;	
					}
					if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && (restriccionManzanas(manzanas,layka) != 0) && (restriccionPared(layka) != 0) && (restriccionPared(layka) != 103) && (restriccionPared(layka) != 101)) {
						layka.mover(0, this.entorno);
						laykaDireccion = 0;
					}	
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)&& (restriccionManzanas(manzanas,layka) != 2) && (restriccionPared(layka) != 2) && (restriccionPared(layka) != 121) && (restriccionPared(layka) != 123)) {
						layka.mover(2, this.entorno);
						laykaDireccion = 2;
					}
					if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)&& (restriccionManzanas(manzanas,layka) != 3) && (restriccionPared(layka) != 3) && (restriccionPared(layka) != 103) && (restriccionPared(layka) != 123)) {
						layka.mover(3,this.entorno);
						laykaDireccion = 3;
					}	
					layka.dibujarse(laykaDireccion,this.entorno);
					
					if (entorno.sePresiono(entorno.TECLA_FIN)){
						if (!infoStage) {
							this.infoStage = true;
						} else {
							this.infoStage = false;
						}
					}		
					
					// INFORMACION PARA PROGRAMAR
					if (this.infoStage){
						dibujarInformacion(entorno, layka, manzanas);
					}
				}
				
				
				// RAYO
				if (layka != null) {
					if ((entorno.sePresiono(entorno.TECLA_ESPACIO))&& (rayo==null)) {
						rayo = layka.disparar();	
						rayo.dibujarse(entorno);
						seDisparoRayo = true;
					}
					if (rayo != null) {
						rayo.dibujarse(entorno);
						rayo.mover();
						if (rayo.y > 600 || rayo.y < 0 || rayo.x > 800 || rayo.x < 0) {
							rayo = null; 
						}
					}	
				}
	
				
				// BOLA DE FUEGO
				if (bolaFuego != null) {
					bolaFuego.dibujarse(entorno);
					bolaFuego.mover();
					if (bolaFuego.y > 700 || bolaFuego.y < -100 || bolaFuego.x > 900 || bolaFuego.x < -100) {
						bolaFuego = null;	
					}
				} else if (plantas.length>0) {
					
					Random rB = new Random();
					randomBola = rB.nextInt(plantas.length);
					if (plantas[randomBola] != null){
						bolaFuego = plantas[randomBola].disparar(entorno);
					}
					
				}
				
				
	
					
				//CAMINION RESTRICCION	
				for(int j = 0; j < autos.length; j++) {
					if (autos[j]!=null) {
						autos[j].dibujarse(entorno);
						autos[j].mover();	
						int zonaParedIzquierda=autos[j].x - autos[j].ancho/2;
						int zonaParedArriba=autos[j].y - autos[j].alto/2;
						int zonaParedAbajo=autos[j].y + autos[j].alto/2;
						int zonaParedDerecha=autos[j].x + autos[j].ancho/2;				
						if (layka != null) {
							if(    (layka.y >= zonaParedArriba && layka.y <= zonaParedAbajo && layka.x >= zonaParedIzquierda && layka.x <= zonaParedDerecha)          ) {
								lapida = new Lapida(layka.x, layka.y);
								layka = null;	
							}	
							if (bolaFuego != null) {
								if(    (bolaFuego.y >= zonaParedArriba && bolaFuego.y <= zonaParedAbajo && bolaFuego.x >= zonaParedIzquierda && bolaFuego.x <= zonaParedDerecha)          ) {	
								
									
									autosRotos[j] = autos[j];
									if (item == null) {
										item = new Item();
						
													
									}
									
									autos[j] = null;
									bolaFuego = null;
								}
							}
							if (rayo != null) {
								if(    (rayo.y >= zonaParedArriba && rayo.y <= zonaParedAbajo && rayo.x >= zonaParedIzquierda && rayo.x <= zonaParedDerecha)          ) {	
								
									rayo= null;
								}
							}
						}
					} else {
						
						//TIMER REVIVIR
						if (repararEnProceso == false) {
							repararEnProceso = true;
							quienRepara = j;
						} else {
							timerAuto+=1;
						}
						if (timerAuto == tiempoParaRepararse){
							autos[quienRepara] = autosRotos[quienRepara];
							if (autos[quienRepara].velocidad < 12); {
								autos[quienRepara].velocidad += 1; 
							}
							autosRotos[quienRepara] = null;
							repararEnProceso = false;
							timerAuto = 0;
						}								
					}
				}
				
				
				
				
				// PLANTAS RESTRICCION
				for(int i = 0; i < plantas.length; i++) {
					if (plantas[i]!=null) {
						plantas[i].dibujarse(entorno);
						plantas[i].mover();		
									
						int zonaParedIzquierda=plantas[i].x - (plantas[i].ancho/2)  - 10;
						int zonaParedArriba=plantas[i].y - (plantas[i].alto/2) - 10;
						int zonaParedAbajo=plantas[i].y + (plantas[i].alto/2) + 10;
						int zonaParedDerecha=plantas[i].x + (plantas[i].ancho/2) + 10;				
						if (layka != null) {
							if(    (layka.y >= zonaParedArriba && layka.y <= zonaParedAbajo && layka.x >= zonaParedIzquierda && layka.x <= zonaParedDerecha)          ) {
								lapida = new Lapida(layka.x, layka.y);
								layka = null;
							}		
						}
								
						if (rayo != null) {
							if(    (rayo.y >= zonaParedArriba && rayo.y <= zonaParedAbajo && rayo.x >= zonaParedIzquierda && rayo.x <= zonaParedDerecha)          ) {	
								plantasMuertas[i] = plantas[i];
								plantas[i] = null;
								informacion.plantasEliminadas += 1; 
								informacion.puntaje+=5;
								rayo = null;
							}
						}
					} else {
						
						//TIMER REVIVIR
						if (revivirEnProceso == false) {
							revivirEnProceso = true;
							quienRevive = i;
						} else {
							timer+=1;
						}
						if (timer == tiempoParaRevivir){
							plantas[quienRevive] = plantasMuertas[quienRevive];
							if (plantas[quienRevive].velocidad < 5); {
								plantas[quienRevive].velocidad += 1; 
							}
							plantasMuertas[quienRevive] = null;
							revivirEnProceso = false;
							timer = 0;
						}				
					}
				}	
				
						
				//MANZANAS
				for(int i = 0; i < manzanas.length; i++) {
					zonaParedIzquierda=manzanas[i].x - manzanas[i].ancho/2;
					zonaParedArriba=manzanas[i].y - manzanas[i].alto/2;
					zonaParedAbajo=manzanas[i].y + manzanas[i].alto/2;
					zonaParedDerecha=manzanas[i].x + manzanas[i].ancho/2;			
					if (rayo!=null) {
						if(    (rayo.y >= zonaParedArriba && rayo.y <= zonaParedAbajo && rayo.x >= zonaParedIzquierda && rayo.x <= zonaParedDerecha)          ) {
							rayo=null;
						}	
					}	
				}
				
					
				//BOLA RESTRICCIONES
				if (bolaFuego != null) {
					int zonaParedIzquierda=bolaFuego.x - (bolaFuego.ancho/2)-10;
					int zonaParedArriba=bolaFuego.y - (bolaFuego.alto/2)-10;
					int zonaParedAbajo=bolaFuego.y + (bolaFuego.alto/2)+10;
					int zonaParedDerecha=bolaFuego.x + (bolaFuego.ancho/2)+10;
	
					if (layka != null) {
						if(    (layka.y >= zonaParedArriba && layka.y <= zonaParedAbajo && layka.x >= zonaParedIzquierda && layka.x <= zonaParedDerecha)          ) { 		
							sigueViva = layka.lastimar(entorno);
							if (!sigueViva) {
								lapida = new Lapida(layka.x, layka.y);
								layka = null;
							}
							
							bolaFuego = null;
												
						}
					}
					if (rayo != null){
						if(    (rayo.y >= zonaParedArriba && rayo.y <= zonaParedAbajo && rayo.x >= zonaParedIzquierda && rayo.x <= zonaParedDerecha)          ) { 		
							bolaFuego = null;
							rayo = null;	
						}
					}
				}
				
				//CARTEL
				if (layka == null){
					timer+=1;
					if (timer == 250){
						salirJuego = true;
					}
				} 
				
			}	
		}
	
		//FIN METODO TICK
		
		private int restriccionManzanas(Manzana[] m, Layka a) {
			for(int i=0; i < m.length;i++) {
				if(restriccionManzana(m[i],a,altoPantalla, anchoPantalla) < 4){
					 return restriccionManzana(m[i],a,altoPantalla, anchoPantalla);
				}
			}
			return 5;
		}			
		private int restriccionPared(Layka a) {			
			if ((a.y > altoPantalla - 30) && (a.x < 30)){
			    return 123; // COLISIÓN CON PARED INFERIOR Y PARED DERECHA
			}
			if ((a.y > altoPantalla - 30) && (a.x > anchoPantalla -30)) {
			    return 121; // COLISIÓN CON PARED INFERIOR Y PARED IZQUIERDA
			}
			if ((a.y < 30) && (a.x < 30)) {
			    return 103; // COLISIÓN CON PARED SUPERIOR Y PARED DERECHA
			}
			if ((a.y < 30) && (a.x > anchoPantalla -30)) {
			    return 101; // COLISIÓN CON PARED SUPERIOR Y PARED IZQUIERDA 
			}
			if (a.y > altoPantalla - 30) {
			    return 2; // COLISIÓN CON PARED INFERIOR
			}
			if (a.y < 30) {
			    return 0; // COLISIÓN CON PARED SUPERIOR
			}
			if (a.x < 30) {
			    return 3; // COLISIÓN CON PARED DERECHA
			}
			if (a.x > anchoPantalla -30) {
			    return 1; // COLISIÓN CON PARED IZQUIERDA
			}	
			return 125;	  //NO COLISION
		}
		public int restriccionManzana(Manzana m, Layka a, int altoPantalla, int anchoPantalla) {
			double zona1=m.x-m.ancho/2;
			double zona2=m.y-m.alto/2;
			double zona0=m.y+m.alto/2;
			double zona3=m.x+m.ancho/2;
			if((a.y > zona2 && a.y < zona0 && a.x > zona1-20 && a.x < zona3)) { //COLISION IZQUIERDA
				return 1;
			}
			if(a.y > zona2 && a.y < zona0 && a.x > zona1 && a.x < zona3+20) { //COLISION DERECHA
				return 3;
			}
			if(a.y > zona2-20 && a.y < zona0 && a.x > zona1 && a.x < zona3) { //COLISION INFERIOR
				return 2;
			}
			if(a.y > zona2 && a.y < zona0+20 && a.x > zona1 && a.x < zona3) { //COLISION SUPERIOR
				return 0;
			}	
			return 5; //NO COLISION
		}
		public void dibujarInformacion(Entorno entorno, Layka layka, Manzana [] manzanitas) {
		    int rPantalla = restriccionManzanas(manzanitas, layka);
		    int rpPantalla = restriccionPared(layka);
		    
		    // DIBUJA INFORMACION LAYKA
		    entorno.cambiarFont("Arial", 18, Color.white);
		    entorno.escribirTexto("Layka en x:  " + layka.x, 600, 50);
		    entorno.escribirTexto("Layka en y:  " + layka.y, 600, 100);
		    entorno.escribirTexto("Restriccion Manzana:  " + rPantalla, 600, 150);
		    entorno.escribirTexto("Restriccion Pared: " + rpPantalla, 600, 200);
		    entorno.escribirTexto("Planta pos1: " + direccionDeLasPlantas[0], 600, 250);
		    entorno.escribirTexto("Planta pos1: " + direccionDeLasPlantas[1], 600, 300);
		    entorno.escribirTexto("Planta pos1: " + direccionDeLasPlantas[2], 600, 350);
		    entorno.escribirTexto("Planta pos1: " + direccionDeLasPlantas[3], 600, 400);
		}
		public void dibujarMapa(Entorno entorno, Manzana[] manzanitas, Calles[] callesVerticales, Calles[] callesHorizontales) {
		    for (int i = 0; i < manzanitas.length; i++) {
		        manzanitas[i].dibujarse(entorno, i);
		    }
		    
		    for (int i = 0; i < callesVerticales.length; i++) {
		        callesVerticales[i].dibujarse(entorno);
		    }
		    
		    for (int i = 0; i < callesHorizontales.length; i++) {
		        callesHorizontales[i].dibujarse(entorno);
		    }
		}
		
		@SuppressWarnings("unused")
		public static void main(String[] args) {
			Juego juego = new Juego();
		}
	}














