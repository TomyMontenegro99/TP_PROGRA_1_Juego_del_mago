package juego;


import java.awt.Image;
import java.awt.Rectangle;



import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private Image disparo;
	private Rectangle ataque;
	
	private boolean direccion;
	
	
	
	Bala (double x, int y,boolean direccion) {
		this.ancho = 10;
		this.alto = 5;
		this.disparo = Herramientas.cargarImagen("ataquesinfondo.png");
		this.x = x;
		this.y =y;
		this.ataque=new Rectangle();
		this.direccion=direccion;
	}
	


	public Rectangle getAtaque() {
		return ataque;
	}



	public void setAtaque(Rectangle ataque) {
		this.ataque = ataque;
	}






	public boolean isDireccion() {
		return direccion;
	}



	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}






	public void dibujar (Entorno entorno) {
		entorno.dibujarImagen(disparo, x, y,0);
		
	}
	
	public void mover()
	{
		if (this.direccion)
		{
			this.x+=2;
		}
		else
		{
			this.x-=2;
		}
	}
	
	
	public boolean balachocaconPared(Paredes pared1,Paredes pared2)
	{
		if (this.x < pared1.getX() + pared1.getAncho())
			return true;
		
		if  (this.x > pared2.getX() - pared2.getAncho())
			return true;
		
		
		
		return false;	
		
		
		
	}
	
	public double distanciaEnemigoDerecha (Enemigo  enemigo) {
		double distancia = 0;
		if(enemigo!=null)
		{	
		double finalEnemigo = enemigo.getX()+ enemigo.getAncho();
		distancia=(Math.pow(this.x-finalEnemigo,2))+Math.pow(this.y-enemigo.getY(),2);
		
		}
		
		
		return distancia;
	}
		
	public boolean AtacoEnemigo (Enemigo e) {
		
		if (e!=null)
		{
			if  (distanciaEnemigoDerecha(e) < 250||distanciaIzquierdaEnemigo(e)<250)
			{	
			
				this.y-=200000;
				return true;
			
			}
		}
		return false;
	}
	

		
		
	
	
	public double distanciaIzquierdaEnemigo(Enemigo enemigo)
	{
		double principioEnemigo = enemigo.getX()- enemigo.getAncho();
		 double distancia=0;
			distancia=(Math.pow(this.x-principioEnemigo,2))+Math.pow(this.y-enemigo.getY(),2);
		
		return distancia;
	}
	

	
	

	
	
	
	public double getX() {
		return x;
	}

	
	public double getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}



	public int getAlto() {
		return alto;
	}

	
	public Image getDisparo() {
		return disparo;
	}

	
	public void mandarAvolarBala()
	{
		this.x=100000;
		
		
	}

}
