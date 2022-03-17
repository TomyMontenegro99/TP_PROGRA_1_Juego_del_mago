package juego;

import java.awt.Image;




import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image fondo;
	private Mago mago;
	private Paredes pared1;
	private Paredes pared2;
	private Plataformas pisos[];
	private Enemigo enemigos[];
	private Bala[] municion;
	private Salud saludvacia[];
	private Image Gameover;
	private Image ganador;

	public Juego() {
		
		this.entorno = new Entorno(this, "Prueba del entorno", 850, 800);
		this.fondo = Herramientas.cargarImagen("fondo1.jpg");
		
		pisos = new Plataformas[7];
		enemigos = new Enemigo[4];
		municion = new Bala[100];
		saludvacia=new Salud[3];
		this.saludvacia[0]=new Salud(50,20);
		this.saludvacia[1]=new Salud(105,20);
		this.saludvacia[2]=new Salud(160,20);
		this.pared1 = new Paredes(0, entorno.alto() / 2, entorno.alto(), 50);
		this.pared2 = new Paredes(entorno.ancho(), entorno.alto() / 2, entorno.alto(), 50);
		this.pisos[0] = new Plataformas(entorno.ancho() / 2, 120, 500);
		this.pisos[1] = new Plataformas(entorno.ancho() - 100, 250, 300);
		this.pisos[2] = new Plataformas(100, 250, 300);
		this.pisos[3] = new Plataformas(entorno.ancho() / 2, entorno.alto() / 2, 400);
		this.pisos[4] = new Plataformas(entorno.ancho() / 2, entorno.alto() / 2 + 150, 500);
		this.pisos[5] = new Plataformas(entorno.ancho() - 100, entorno.alto() - 100, 250);
		this.pisos[6] = new Plataformas(100, entorno.alto() - 100, 250);
		this.mago = new Mago(pisos[0].getX(), pisos[0].getY() / 3);
		this.enemigos[0] = new Enemigo(pisos[3].getX(), pisos[3].getY() +7);
		this.enemigos[1] = new Enemigo(pisos[5].getX(), pisos[5].getY()+25 );
		this.enemigos[2] = new Enemigo(pisos[2].getX(), pisos[2].getY()/2);
		this.enemigos[3] = new Enemigo(pisos[6].getX(), pisos[6].getY()/2);
		this.Gameover=Herramientas.cargarImagen("gameover.jpg");
		this.ganador=Herramientas.cargarImagen("ganador.jpg");
		this.entorno.iniciar();
		
	}

	public void tick() {
		
		
		if(mago!=null && pisos!=null && pared1!=null && pared2!=null&& enemigos!= null)
	{
		
		
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.57);
		
		
		for(Salud e:mago.salud) {	
			if (e != null) {
				e.dibujar(entorno);
				
				if(mago.getCantMuertes()==2)
				{
					
					mago.salud[0].setX(1000);
				
					
				}
				
				if (mago.getCantMuertes()==4)
				{
					mago.salud[1].setX(1000);
					
				}
				
				if(mago.getCantMuertes()>4)
				{
					
					mago.salud[2].setX(1000);
				}
				
			}
			
			
		}
		
		pisos[0].dibujarPlataforma1(entorno);
		pisos[1].dibujarPlataforma2(entorno);
		pisos[2].dibujarPlataforma2(entorno);
		pisos[3].dibujarPlataforma3(entorno);
		pisos[4].dibujarPlataforma1(entorno);
		pisos[5].dibujarPlataforma4(entorno);
		pisos[6].dibujarPlataforma4(entorno);
		
		for (Enemigo e : enemigos) {
			
			



			if(e!=null)
			{	
				
				
				
				
				
				
				e.dibujar(entorno);
			
			

				e.enemigoChocaConPared1(pared1, entorno);
				e.enemigoChocaConPared2(pared2, entorno);
			

	
			
				if (e.chocaConPlataforma(pisos)) {
				
					e.caminarDerecha();
					e.caminarIzquierda();

				}
			
				else
					{
					
						e.caida();
						
						if(e.isRueda()==false)
							{
								e.respawn(entorno);
							}
						
					}	
			
				if (e.estaRodando())
				{
					e.caida();
					e.MandaravolarEnemigo();
					e=null;
					
				
				}
			
			

			}
		}

		

		if (entorno.sePresiono(entorno.TECLA_DERECHA)) {
			mago.girarHaciaLaDerecha(entorno);
		}
		if (entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
			mago.girarHaciaLaIzquierda(entorno);
		}
		

		

		if (mago.chocaConPlataforma(pisos)) {
			mago.mover();
				
			if (mago.estaHabilitadoParaDisparar()) {
				
				for (int i = 0; i < municion.length; i++) {
					if (municion[i] == null) {
						municion[i] = mago.disparar(pisos);
						mago.desabilitarDisparo();
						break;
					}

				}
			}

		}

		else {
			mago.caida();
			mago.habilitarDisparo(pisos);
		}
		
		
		
		for (Enemigo e:enemigos)
		{
			

		
			
			{	
				for(Bala j: municion)
				{
					
				
				
					if(j!=null)
						
					{	
						
						if (j.AtacoEnemigo(e))
							
						{
							
							
							e.PonerCongelado();
							
									
						}
							
						
						
						if(e.getCongelar()==0 &&e.getTiempocong()>0)
						{
							
						
							e.restarTiempoComgelacion();
						
							
							
							if(e.getTiempocong()==0)
								{	
								
									e.Descongelar();
									e.volveraltiempocongelacion();
								}
								
							  mago.chocaConEnemigo(e);
							  
						}
						
					
					}
				}
			}
		}
		
		
		for(int i=0;i<enemigos.length;i++)
		{
		
		
			for (int j=1;j<enemigos.length;j++)
			{
				enemigos[i].enemigoChocaconotro(enemigos[j]);
				
				
				
			}
			
			
			
			
		}
			
			
		for (Bala e : municion) {
			if (e != null) {
				
				e.dibujar(entorno);
				e.mover();
				
				
				if( e .balachocaconPared(pared1, pared2))
				{	
					e=null;
				}
				
			
		
			}

		}
		
		
		for (int i=0;i<enemigos.length;i++)
		{
			if (enemigos[0].getY()>1000&&enemigos[1].getY()>1000&&enemigos[2].getY()>1000&&enemigos[3].getY()>1000)
			{
				
				mago.mandaraAVolarmago();
				entorno.dibujarImagen(ganador,entorno.ancho() / 2, entorno.alto() / 2, 0, 0.57);
			}
		
			
	
	
		}
		
		mago.muerte(pisos[3], enemigos,entorno,pisos);
		
		
		
		mago.respawn(entorno);
		mago.chocaConParedes(entorno, pared1, pared2);
		mago.dibujar(entorno);
		pared1.dibujar(entorno);
		pared2.dibujar(entorno);
	
		
			
			
		if(mago.getCantMuertes()>4)
			{	
				mago.mandaraAVolarmago();
			
				
				for(Bala e:municion) {
					if (e!=null)
						e.mandarAvolarBala();;
				}
				
				
				for(Enemigo e: enemigos)
				{
				
					e.MandaravolarEnemigo();
					pared1.sacardeEntorno();
					pared2.sacardeEntorno();
					
					entorno.dibujarImagen(Gameover, entorno.ancho() / 2, entorno.alto() / 2, 0, 0.57);
				
				
				}
				
			}
			
	}
	
			
			
	
		
		
		
		
		
	}

	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		
	
	
	}
}
