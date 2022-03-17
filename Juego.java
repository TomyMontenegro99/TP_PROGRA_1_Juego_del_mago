package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Image fondo;
	private Mago mago;
	private Salud barraHP;
	private Paredes pared1;
	private Paredes pared2;
	private Plataformas pisos[]=new Plataformas[7];
	private Enemigo enemigos [] = new Enemigo [4];
	private Bala[] municion=new Bala[7];	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Prueba del entorno",850, 800);
		this.fondo = Herramientas.cargarImagen("fondo1.jpg");
		this.barraHP = new Salud (700,40);
		this.pared1= new Paredes(0,entorno.alto()/2,entorno.alto(),50);
		this.pared2 = new Paredes (entorno.ancho(),entorno.alto()/2,entorno.alto(),50);
		this.pisos[0]=new Plataformas(entorno.ancho()/2,120,500);
		this.pisos[1]=new Plataformas(entorno.ancho()-100,250,300);
		this.pisos[2]=new Plataformas(100,250,300);
		this.pisos[3]=new Plataformas(entorno.ancho()/2,entorno.alto()/2,400);
		this.pisos[4]=new Plataformas (entorno.ancho()/2,entorno.alto()/2+150,500);
		this.pisos[5]=new Plataformas(entorno.ancho()-100,entorno.alto()-100,250);
		this.pisos[6]=new Plataformas(100,entorno.alto()-100,250);
		this.mago = new Mago (pisos[0].getX(),pisos[0].getY()/3);
		this.enemigos[0] = new Enemigo (pisos[3].getX(),pisos[3].getY()/3);
		this.enemigos[1] = new Enemigo (pisos[5].getX(),pisos[5].getY()/3);
		this.enemigos[2] = new Enemigo (pisos[5].getX(),pisos[5].getY()/7);
		this.enemigos[3]=new Enemigo(pisos[6].getX(),pisos[6].getY()/3);
		
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0, 0.57);
		
		for(Plataformas e: pisos)
		{
			e.dibujar(entorno);
		}
		
		
		for (Enemigo e:enemigos)
		{
			e.dibujar(entorno);
			
			e.enemigoChocaConPared1(pared1,entorno);
			e.enemigoChocaConPared2(pared2,entorno);
			
			
			if (e.chocaConPlataforma(pisos))
			{
				e.caminarDerecha();
				e.caminarIzquierda();
				
			}	
			
			else
				e.caida();
			e.respawn(entorno);
		
		}
		
		mago.muerte(pisos[3],enemigos);
		
		
	
			
		for(int i=0;i<pisos.length;i++)
		{
					
				
				if (mago.ChocaconunaPlataforma(pisos[i])&&mago.isDisparar())
				{	
					
				
					municion[i] = new Bala(mago.getX(),mago.getY(),mago.direccion);
					mago.setDisparar(false);
				}		
		}
		
	
		
		
		for(Bala e:municion)
		{
			if(e!=null)
			{
				
				e.dibujar(entorno);
				e.mover();
			}
			
			
			
		}
		

		
			
		
			
		
		//bala.dibujar(entorno, mago);
		
		if (mago.chocaConPlataforma(pisos))
		{
			
			mago.moverDerecha(entorno);
			mago.moverIzquierda(entorno);
			mago.cambiarDireccion();
			mago.setDisparar(true);
		}	
		
		else
		{
			
			mago.caida();
		
		}
		
		
		
			
			
			
			
		mago.respawn(entorno);
		
		
		mago.chocaConParedes(entorno,pared1,pared2);
		
		
		//mago.chocaConentorno(entorno);
			
		mago.dibujar(entorno);
		//barraHP.dibujar(entorno);
		pared1.dibujar(entorno);
		pared2.dibujar(entorno);
		}
		
		
		// Procesamiento de un instante de tiempo
		// ...
		

	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
