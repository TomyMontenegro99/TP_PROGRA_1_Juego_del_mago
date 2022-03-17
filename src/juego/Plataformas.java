package juego;




import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;

public class Plataformas {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Image plataforma1;
	private Image plataforma2;
	private Image Plataforma3;
	private Image Plataforma4;
	
	
	public Plataformas (int d,int y,int ancho) {
		this.x = d;
		this.y = y;
		this.alto = 20;
		this.ancho = ancho;
		this.plataforma1=Herramientas.cargarImagen("plataforma1.png");
		this.plataforma2=Herramientas.cargarImagen("Plataforma2.png");
		this.Plataforma3=Herramientas.cargarImagen("Plataforma3.png");
		this.Plataforma4=Herramientas.cargarImagen("Plataforma.png");
	}
	
	public void dibujarPlataforma1(Entorno entorno)
	{
		entorno.dibujarImagen(plataforma1, x, y, 0);
		
		
	}

	
	public void dibujarPlataforma2(Entorno entorno)
	{
		
		entorno.dibujarImagen(plataforma2, x, y, 0);
		
		
		
	}
	
	
	public void dibujarPlataforma3(Entorno entorno)
	{
		
		entorno.dibujarImagen(Plataforma3, x, y, 0);
		
		
	}
	
	
	public void dibujarPlataforma4(Entorno entorno)
	{
		
		entorno.dibujarImagen(Plataforma4, x, y, 0);
		
		
		
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public  int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	

}
