package juego;


import java.awt.Image;



import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double angulo;
	private boolean direccion;
	private int congelar=1;
	private double velocidad;
	private double tiempocong;
	private Image imagen;
	private boolean rueda;
	
	public boolean isRueda() {
		return rueda;
	}





	

	public Enemigo(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 10;
		this.alto = 50;
		this.direccion =true;
		this.imagen = Herramientas.cargarImagen("enemi.png");
		this.tiempocong=900;
		this.angulo=0;
		this.velocidad=1;
		this.rueda = false;
		
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen,x, y, angulo,0.3);
	}
	


	public void caminarDerecha() {
		if (this.direccion == true&&this.congelar==1) {
		this.x+=velocidad;
		angulo=0;
		}
		
	
		
		
	}
	

	
	public void caminarIzquierda() {
		if (this.direccion == false&&this.congelar==1) {
		 
		this.x-=velocidad;
		angulo=0;
		}
	}	
	
	
	public double getTiempocong() {
		return tiempocong;
	}

	public void setTiempocong(double d) {
		this.tiempocong = d;
	}

	public void Maxvel()
	{


		
		if (this.direccion)
				{
			
					caminarDerecha();
					velocidad=7;
					
				}
			if (this.direccion == false)
				{
					caminarIzquierda();
					velocidad=7;
					
				}
	}
	

	public void girarDerecha()
	{
		this.direccion=true;
	}
	
	public void girarIzquierda()
	{
		this.direccion=false;
	}
	
	public void Maxvelcaida()
	{
		
		this.y-=5;
		
		
	}
	
	
	public boolean estaCongelado(Enemigo enemigo)
	
	{
		
		if(this.congelar==0)
				return true;
			
		return false;

		
		
	}
	
	
	public void enemigoChocaConPared1(Paredes pared1, Entorno entorno) {

		if (this.x < pared1.getX() + pared1.getAncho()) {
			this.direccion = true;
		}	
	}
	
	public void enemigoChocaConPared2 (Paredes pared2,Entorno entorno) {
		if (this.x > entorno.ancho() - pared2.getAncho()) {
			this.direccion = false;
		}
		
	}
	public boolean chocaConPlataforma(Plataformas [] pisos) {
		if(estaRodando())
			return false;
		
		
		for (int i = 0; i < pisos.length; i++) {

			if (this.x >=pisos[i].getX()-pisos[i].getAncho()/2 && this.x<=pisos[i].getX()+pisos[i].getAncho()/2 &&  this.y >  pisos[i].getY()-this.alto && this.y<pisos[i].getY()-24) {
				
				return true;
				
			}
			
		
		}
		return false;
	}
	
	
	
	
	public void  rodar()
	{
		
		
		angulo+=0.3;
				
			
	}
	
	
	public boolean estaRodando()

	{	
	if (this.angulo==1)
	{
		
		return true;
	}
	
	else 
		return false;
				
	}
	public void caida() {
		
		if (estaRodando())

			this.y += 500;
		
		else
			this.y +=2;

	}
	public void respawn(Entorno entorno) {
		if (this.y > entorno.alto()) {
			this.y = 0;
		}
	}
	

	
	public void enemigoChocaconotro(Enemigo enemigo2)
	
	{
		if (distanciaIzquierdaEnemigo(enemigo2)<10 || distanciaDerechaEnemigo(enemigo2) < 10||distanciaAltoEnemigo(enemigo2) < 20  )
		{
			
			
			
			if(this.rueda==true)
			{
				enemigo2.Maxvel();
				enemigo2.rueda=true;
			}
			
			else
				{
				
					if(enemigo2.direccion=true)
					{
						this.girarIzquierda();
					}	
					else
						{
							this.girarDerecha();
						}
						
			
				}
		}
		
		
		
	}
	
	
	
	public double distanciaAltoEnemigo (Enemigo enemigo2) {
		double altoEnemigo = this.getY()-this.getAlto();
		double distancia = 0;
		distancia=(Math.pow(enemigo2.x-this.x,2))+Math.pow((enemigo2.ancho+enemigo2.alto)-altoEnemigo,2);
		return distancia;
		
	}
	
	
	public double distanciaIzquierdaEnemigo(Enemigo enemigo2)
	{
		double principioEnemigo = this.x- this.ancho;
		 double distancia=0;
			distancia=(Math.pow(enemigo2.x-principioEnemigo,2))+Math.pow(enemigo2.y-this.y,2);
		
		return distancia;
	}
	

	public double distanciaDerechaEnemigo(Enemigo enemigo2) {
		double finalEnemigo = this.x+this.ancho;
		double distancia = 0;
		distancia=(Math.pow(enemigo2.x-finalEnemigo,2))+Math.pow(enemigo2.y-this.y,2);
		return distancia;
	}
	
	
	public void MandaravolarEnemigo()
	{
		this.x=10000;
		
		
	}
	
	
	public int PonerCongelado() {
			
		return this.congelar=0;
			
}
	
	public int Descongelar()
	{
		
		return this.congelar=1;
		
	}
	
	
	public double restarTiempoComgelacion() {
		
		return tiempocong-=1;
		
		
	}
	
	
	
	public double volveraltiempocongelacion()
	{
		
		return tiempocong=1500;
		
	}
	
	
	public boolean estaRodandoa()
	{
		
		return this.rueda=true;
	}
	
	public int getCongelar() {
		return congelar;
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

	

	public int getAlto() {
		return alto;
	}

	
	public boolean isDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}
	
	

	
		
}


