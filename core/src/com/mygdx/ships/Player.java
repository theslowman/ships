package com.mygdx.ships;





public interface Player{
	public static final String T = "trafiony";
	public static final String ST = "statek";
	public static final String P = "pudlo";
	public static final String N = "neutralny";
	public static final String B = "pole bia³e";
	public static final int SIZE = 10;

	//Plansza plansza = new Plansza(SIZE);
	//Plansza planszaPrzeciwnika = new Plansza(SIZE);

	public boolean getPrev();
	public void  setPrevious(boolean x);
	public boolean beAttacked(int x, int y);
	public void addShips();
	public boolean attack(Player x);
	public void displayPlansza();
	public void displayBattlefield();

	public void copyPlansza(Plansza plansza);
	public Plansza getPlansza();
	public Plansza getEnemyPlansza();
}





