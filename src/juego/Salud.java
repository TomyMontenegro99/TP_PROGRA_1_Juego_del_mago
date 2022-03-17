package juego;

import java.awt.Image;
import entorno.Entorno;

import entorno.Herramientas;

public class Salud {
	private Image barra;
	private int x;
	private int y;
	boolean saludvacia;
	
	
	Salud (int x,int y) {
		this.barra = Herramientas.cargarImagen("Corazonlleno.png");
		this.x = x;
		this.y = y;
		this.saludvacia = false;
	}
	
	public void dibujar (Entorno entorno) {
		entorno.dibujarImagen(barra, x, y, 0, 0.3);
		
	}
	
	
	public void quitarVida(Mago mago,Enemigo[] enemigos)
	
	{
		
		for(int i=0;i<enemigos.length;i++)
			
			
			if(mago.chocaConenemigos(enemigos)&&enemigos[i].estaCongelado(enemigos[i])==false)
			{
				this.x=100000;
			
			
			}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
