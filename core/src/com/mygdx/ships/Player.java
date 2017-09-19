package com.mygdx.ships;





public abstract class Player{
	public static final String T = "trafiony";
	public static final String P = "pudlo";
	public static final String B = "pole bia³e";
	public static final int SIZE = 10;


	public abstract boolean beAttacked(int x, int y);
	public abstract void addShips();
	public abstract boolean attack(Player x);
	public abstract void displayBattlefield();

	public abstract void copyPlansza(Plansza plansza);
	public abstract Plansza getPlansza();
	public abstract Plansza getEnemyPlansza();
}





