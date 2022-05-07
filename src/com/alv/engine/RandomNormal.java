package com.alv.engine;

import java.util.Date;

public class RandomNormal {

	
	private static long  SEED = 19;
	private static long LAST_GENERATED = 0;
	private static long ADDING = 0;
	private static long MULTIPLY = 0;
	
	public RandomNormal() {
		SEED = 104729;
		ADDING = (new Date()).getTime() % SEED;
		ADDING = ADDING < 5 ? 13 : ADDING; 
		LAST_GENERATED = ADDING;
		MULTIPLY=17112;
		
	}
	public RandomNormal(long seed, long adding, long multiply) {
		this.SEED= seed;
		this.ADDING = adding;
		this.MULTIPLY = multiply;
		LAST_GENERATED = ADDING;
	}
	
	public double Next() {
		return getNext();
	}
	
	public int NextInt(int init, int end) {
		double aux = getNext();
		int rango = end-init;
		aux = aux*(rango*1.0);
		
		return ((int)Math.round(aux))+init;
		
	}
	
	private double getNext() {
		LAST_GENERATED = ((LAST_GENERATED*MULTIPLY) + ADDING) % SEED;
		
		return LAST_GENERATED*1.0/SEED*1.0;
	}
	
}
