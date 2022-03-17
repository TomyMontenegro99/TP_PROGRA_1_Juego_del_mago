package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Paredes {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private Image paredes;
	
	Paredes (int x,int y,int alto,int ancho) {
		this.altura = alto;
		this.ancho=ancho;
		this.x = x;
		this.y = y;
		this.paredes=Herramientas.cargarImagen("paredes.png");
		
		
		
	}
	
	public void dibujar (Entorno entorno) {
		entorno.dibujarImagen(paredes, x, y, 0);

}

	public void sacardeEntorno()
	{
		this.x=1000;
	}
	
	public int getX() {
		return x;
	}

	
	

	public int getY() {
		return y;
	}



	public int getAncho() {
		return ancho;
	}


	public int getAltura() {
		return altura;
	}

	
}