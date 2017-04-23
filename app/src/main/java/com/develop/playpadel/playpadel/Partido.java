package com.develop.playpadel.playpadel;

import java.util.Date;

public class Partido {
	
	private Usuario jugador1;
	private Usuario jugador2;
	private Usuario jugador3;
	private Usuario jugador4;
	private boolean[] resultado;
	
	
	//TODO No sabemos como manejar la localizacion
	//private String localizacion;
	
	private boolean finalizado;
	private int [] fecha;
	
	/**
	 * 
	 * @param uno Usuario que inicia el partido
	 * @param fecha Fecha del partido
	 */
	public Partido(Usuario uno, int fecha []){
		this.jugador1 = uno;
		this.fecha = fecha;
		resultado = new boolean[4];
	}
	
	
	
	/**
	 * 
	 * @return la cantidad de juagdores que faltan para que se complete un partido
	 */
	public int jugadoresRestantes(){
		if(jugador2 == null)
			return 3;
		if(jugador3 == null)
			return 2;
		if(jugador4 == null)
			return 1;
		return 0;
	}

	/**
	 * Crea los jugadores en funcion de los jugadores que falten en el partido
	 * 
	 * @param jugador Jugador a meter en el partido
	 */
	public void setJugadoresRestantes(Usuario jugador){
		int jugadoresRestantes = jugadoresRestantes();
		if(jugadoresRestantes == 3){
			setJugador2(jugador);
		}
		else if(jugadoresRestantes == 2){
			setJugador3(jugador);
		}
		else if(jugadoresRestantes == 1){
			setJugador4(jugador);
		}
		else{
			//Mandar toast de que el partido esta completo
		}
	}
	
	public void setFinalizado(boolean finalizado){
		this.finalizado = finalizado;
	}
	
	public boolean getFinalizado(){
		return finalizado;
	}
	
	public Usuario getJugador1() {
		return jugador1;
	}

	public Usuario getJugador2() {
		return jugador2;
	}

	public Usuario getJugador3() {
		return jugador3;
	}

	public Usuario getJugador4() {
		return jugador4;
	}

	public int[] getDate() {
		return fecha;
	}

	public void setJugador1(Usuario jugador1) {
		this.jugador1 = jugador1;
	}

	public void setJugador2(Usuario jugador2) {
		this.jugador2 = jugador2;
	}

	public void setJugador3(Usuario jugador3) {
		this.jugador3 = jugador3;
	}

	public void setJugador4(Usuario jugador4) {
		this.jugador4 = jugador4;
	}

	public void setDate(int [] fecha) {
		
		this.fecha = fecha;
	}

	/**
	 * @return the resultado
	 */
	public boolean[] getResultado() {
		return resultado;
	}

	/**
	 * 
	 * @param jugador Jugador del que quieres poner el resultado
	 * @param aux Si es false, pierde, si es true gana
	 */
	public void setResultado(Usuario jugador, boolean aux) {
		this.resultado[getIndice(jugador)] = aux;
	}
	
	public int getIndice(Usuario jugador){
		if(this.jugador1 == jugador)
		return 0;
		if(this.jugador2 == jugador)
			return 1;
		if(this.jugador3 == jugador)
			return 2;
		if(this.jugador4 == jugador)
			return 3;
		return -1;
	}
	
	public void calcularNivelJugadores(){
		 Usuario[] jugadores = {jugador1, jugador2, jugador3, jugador4};
		 for(int i =0 ; i<4; i++){
			 nivel(jugadores[i]);
		 }
	}
	
	private void nivel(Usuario jugador){
		
		int totalGanados = jugador.sizeGanados();
		int totalPerdidos = jugador.sizePerdidos();
		int totalPartidos = totalGanados + totalPerdidos;
		
		double porcentajeGanados = totalGanados/totalPartidos;
		double porcentajePerdidos = totalPerdidos/totalPartidos;
		
		double mediaGanado = jugador.getMediaGanados();
		double mediaPerdido = jugador.getMediaPerdidos();
		
		double media = 0;
		if(mediaGanado != 0 && mediaPerdido != 0){
			media = (mediaGanado*porcentajeGanados + mediaPerdido*porcentajePerdidos);
		}
	    else if(mediaGanado == 0) {
			media = mediaPerdido;
		}
	    else{
	    	media = mediaGanado;
	    }
		double nivelJugador = jugador.getNivel();
		
		double mediaFinal = (nivelJugador + media)/2;
		jugador.setNivel(mediaFinal);
	}
	
	
		

}
