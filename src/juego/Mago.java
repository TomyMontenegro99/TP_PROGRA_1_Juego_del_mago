package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mago {
	private double x;
	private int y;
	private int ancho;
	private int alto;
	private Image imagen;
	boolean direccion;
	private boolean disparar;
	private int cantMuertes;
	private int cantMuertesEnemigos;
	
	

	Salud [] salud;

	public Mago(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 10;
		this.alto = 50;
		this.imagen = Herramientas.cargarImagen("magoImagen.png");
		this.direccion = false;
		this.disparar = false;
		cantMuertes = 0;
		salud = new Salud [3];
		salud[0]=new Salud(50,20);
		salud[1]=new Salud(105,20);
		salud[2]=new Salud(160,20);
		cantMuertesEnemigos=0;

		
	}

	public boolean estaHabilitadoParaDisparar() {
		return disparar;
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0, 0.8);
	}

	public void mover() {
		if (this.direccion) {

			x += 2;
		} else {
			x -= 2;

		}

	}
	
	public void girarHaciaLaDerecha(Entorno entorno) {
		this.direccion = true;
	}

	public void girarHaciaLaIzquierda(Entorno entorno) {
		this.direccion = false;

	}

	public boolean chocaConPlataforma(Plataformas[] pisos) {
		for (int i = 0; i < pisos.length; i++) {

			if (this.x >= pisos[i].getX() - pisos[i].getAncho() / 2
					&& this.x <= pisos[i].getX() + pisos[i].getAncho() / 2 && this.y > pisos[i].getY() - this.alto
					&& this.y < pisos[i].getY() - 47) {
				return true;
			}

		}
		return false;

	}

	public boolean ChocaconunaPlataforma(Plataformas piso)

	{
		if (this.x >= piso.getX() - piso.getAncho() / 2 && this.x <= piso.getX() + piso.getAncho() / 2
				&& this.y > piso.getY() - this.alto && this.y < piso.getY() - 47) {
			return true;
		}
		
		
		

		return false;
	}

	public void caida() {

		this.y += 2;

	}

	public void respawn(Entorno entorno) {
		if (this.y > entorno.alto()) {
			this.y = 0;
		}
	}

	public boolean muerte(Plataformas plataformaMedio, Enemigo enemigo[], Entorno e,Plataformas pisos[]) {
		
		
		for(int i=0;i<enemigo.length;i++)
		{
			if(chocaConEnemigo(enemigo[i])&&enemigo[i].getCongelar()==0)
			{	
				
				enemigo[i].Maxvel();
				enemigo[i].estaRodandoa();
				
			
			
			}
			
			
		}
		
		if (chocaConenemigos(enemigo) ) {
			this.x =400; 
			this.y =0;
			this.desabilitarDisparo();
			for(int i=0;i<enemigo.length;i++)
				enemigo[i].Descongelar();
			return true;

		}
	
		else 
			return false;
	}
	


	public boolean chocaConEnemigo (Enemigo enemigo) {
		
		
			
		{
			
			
			
			
			
		
			if (distanciaIzquierdaEnemigo(enemigo)<10 || distanciaDerechaEnemigo(enemigo) < 10 || distanciaAltoEnemigo(enemigo) < 20 || distanciaPiesEnemigo(enemigo) < 20 || distanciaCentroEnemigo (enemigo)< 20&&enemigo.estaCongelado(enemigo))
			{	
		
				
				if (enemigo.getCongelar()==0)
					{	
					cantMuertesEnemigos+=1;
			

				
					
					}
			
			
			
			
				if(enemigo.getCongelar()==1)
					{
					cantMuertes++;
					}
				return true;
			}
		
			
		}
		return false;
	}
	public int getCantMuertes() {
		return cantMuertes;
	}

	public void setCantMuertes(int cantMuertes) {
		this.cantMuertes = cantMuertes;
	}

	
	public double distanciaIzquierdaEnemigo(Enemigo enemigos)
	{
		double principioEnemigo = enemigos.getX()- enemigos.getAncho();
		 double distancia=0;
			distancia=(Math.pow(this.x-principioEnemigo,2))+Math.pow(this.y-enemigos.getY(),2);
		
		return distancia;
	}
	

	public double distanciaDerechaEnemigo(Enemigo enemigos) {
		double finalEnemigo = enemigos.getX()+ enemigos.getAncho();
		double distancia = 0;
		distancia=(Math.pow(this.x-finalEnemigo,2))+Math.pow(this.y-enemigos.getY(),2);
		return distancia;
	}
	
	public double distanciaAltoEnemigo (Enemigo enemigos) {
		double altoEnemigo = enemigos.getY()-enemigos.getAlto();
		double distancia = 0;
		distancia=(Math.pow(this.x-enemigos.getX(),2))+Math.pow((this.y+this.alto)-altoEnemigo,2);
		return distancia;
		
	}
	
	public double distanciaPiesEnemigo (Enemigo enemigos) {
		double piesEnemigo = enemigos.getY()+enemigos.getAlto();
		double distancia = 0;
		distancia=(Math.pow(this.x-enemigos.getX(),2))+Math.pow((this.y-this.alto)-piesEnemigo,2);
		return distancia;
	}
	
	public double distanciaCentroEnemigo (Enemigo enemigos) {
		
		double distancia=(Math.pow(this.x-enemigos.getX(),2))+Math.pow(this.y-enemigos.getY(),2);
		return distancia;
	}
	


	
	

	
	

	public boolean chocaConenemigos(Enemigo enemigos[]) {
		for (int i = 0; i < enemigos.length; i++) {
			if (chocaConEnemigo(enemigos[i])) {

				return true;
			}
		}
		return false;

	}

	public void chocaConParedes(Entorno entorno, Paredes pared1, Paredes pared2) {
		if (this.x < pared1.getX() + pared1.getAncho()) {
			this.direccion = true;

		}
		if (this.x > pared2.getX() - pared2.getAncho()) {
			this.direccion = false;
		}
	}

	public boolean tocaPlataforma(Plataformas pisos[]) {

		for (int i = 0; i < pisos.length; i++) {
			if (this.y < pisos[i].getY() && this.y > pisos[i].getY() - 55)
				return true;
		}

		return false;

	}
	
	
	public Bala disparar(Plataformas pisos[]) {
		
		if(chocaConPlataforma(pisos)&&direccion==false)
		{
			return new Bala(x, y, true);
		}
		else
		{
			return new Bala(x, y, false);
		} 
		
		 
		
		
	}

	public void desabilitarDisparo() {
		disparar = false;
	}

	public void habilitarDisparo(Plataformas pisos[]) {
		for (int i=0;i<pisos.length;i++){
			if(this.y < pisos[i].getY()- 80 && this.y > pisos[i].getY()-160)	
			{	disparar = true;}
		}
	}



	



	

	public double getX() {
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
	public int getCantMuertesEnemigos() {
		return cantMuertesEnemigos;
	}

	public void mandaraAVolarmago() {
		this.x=1000000;
		
	}

	}

	