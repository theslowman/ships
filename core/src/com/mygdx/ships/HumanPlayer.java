package com.mygdx.ships;

import java.util.Scanner;

public class HumanPlayer implements Player{
	Plansza plansza = new Plansza(SIZE);
	Plansza planszaPrzeciwnika = new Plansza(SIZE);
	Scanner in = new Scanner(System.in);
	boolean prevWasHit = false;



	@Override
	public void addShips() {
		addShip(4);
		displayBattlefield();
		for(int i = 0; i < 2 ; i++){
			addShip(3);
			displayBattlefield();
		}
		for(int i = 0; i < 3 ; i++){
			addShip(2);
			displayBattlefield();
		}
		for(int i = 0; i < 4 ; i++) {
			addShip(1);
			displayBattlefield();
		}
//
	}


	@Override
	public boolean attack(Player enemy) {
		String input;
		int x, y;

		System.out.println("Podaj wspó³rzêdne ataku!");
		input = in.nextLine();

		while(true){
			if(!isCoordinateValid(input)){
				System.out.println("B³êdne dane!");
				input = in.nextLine();

				continue;
			}
			x = getX(input);
			y = getY(input);
			if(wasAttacked(x,y)) {
				System.out.println("Pole by³o atakowane! Podaj kolejny cel:");
				input = in.nextLine();
				x = getX(input);
				y = getY(input);
				continue;
			}

			break;
		}

		if(enemy.beAttacked(x, y)){

			planszaPrzeciwnika.setPole(y, x ,T);
			if(enemy.getPlansza().isDrowned(x, y))
				planszaPrzeciwnika.surroundDrownedShip(x, y);
			return true;

		}
		planszaPrzeciwnika.setPole(y, x ,P);
		System.out.println("Pud³o!");
		return false;
	}

	@Override
	public boolean beAttacked(int x, int y){
		System.out.println("Jesteœ pod ostrza³em!");
		if(plansza.attack(x, y)){
			System.out.println("Pole trafione!");
			if(plansza.isDrowned(x, y)){
				System.out.println("Zatopiony!");
				plansza.surroundDrownedShip(x, y);
			}
			return true;
		}


		return false;
	}

	public boolean wasAttacked(int x, int y){
		if(planszaPrzeciwnika.getPole(y, x).getStatus().equals(T)||planszaPrzeciwnika.getPole(y, x).getStatus().equals(P)) return true;
		return false;
	}
	@Override
	public Plansza getPlansza(){
		return plansza;
	}
	@Override
	public Plansza getEnemyPlansza(){
		return planszaPrzeciwnika;
	}

	public void displayCoordinateRequest(int size){
		System.out.println("Podaj wspó³rzêdne dla statku o wielkoœci: "+size);
	}

	int getX(String s){
		return s.charAt(0)-65;
	}
	int getY(String s){
		if(s.length()==3) return 9;
		return Character.getNumericValue(s.charAt(1))-1;
	}

	void testFunction(int x, int y,int xx, int yy, int size){
		System.out.print(plansza.isAvailable(x, y, xx, yy, size));
	}

	boolean machesArea(String coordinate){
		int x, y;
		x = getX(coordinate);
		y = getY(coordinate);
		if((x>=0&&x<SIZE)&&(((y>=0)&&y<=SIZE))) return true;
		return false;
	}


	boolean isCoordinateValid(String p){
		if(p.length()<2||p.length()>3) return false;
		if(p.length()==3&&(p.charAt(2)!='0')) return false;
		if(machesArea(p)) return true;
		return false;
	}
	boolean isCoordinateValid(String p, String k, int size){
		if(p.length()<2||p.length()>3) return false;
		if(p.length()==3&&(p.charAt(2)!='0')) return false;
		if(machesArea(p)&&machesArea(k)){
			if((getX(p) - getX(k))==0)
				if(Math.abs(getY(p) - getY(k))==size-1)
					return true;
			if((getY(p) - getY(k))==0)
				if(Math.abs(getX(p) - getX(k))==size-1)
					return true;
		}
		return false;
	}
	public void setupShip(int size){
		String p,k;
		int x, y , xx, yy;
		while(true){

			System.out.println("Pocz¹tkowe = "); p = in.nextLine();
			if(size==1) {
				k = p;
				System.out.println("Koñcowe = Pocz¹tkowe");}

			else{
				System.out.println("Koñcowe  = "); k = in.nextLine();
			}

			if(!isCoordinateValid(p,k,size)){
				System.out.println("B³êdne dane!");
				continue;
			}

			x = getX(p);
			y = getY(p);
			xx= getX(k);
			yy= getY(k);
			//testFunction(x, y,xx, yy, size);

			System.out.println();
			if(!plansza.isAvailable(x, y, xx, yy, size)) {
				System.out.println("Pola wokó³ statku zajête!");
				displayCoordinateRequest(size);
				continue;
			}
			plansza.addShip(x, y, xx, yy, size);
			System.out.println("Statek o rozmiarze "+size+" dodany!");
			break;
		}

	}

	public void addShip(int size){
		displayCoordinateRequest(size);
		setupShip(size);
	}
	public void displayPlansza(){
		plansza.displayPlansza();
	}
	public void displayBattlefield(){
		{
			plansza.displayLetterIndexes();
			for(int i = 0; i < SIZE ; i++){
				plansza.drawIndex(i);
				for(int j = 0; j < SIZE ; j++)
					plansza.drawElement(i,j);
				System.out.print("| ");
				plansza.drawIndex(i);
				for(int j = 0; j < SIZE ; j++)
					planszaPrzeciwnika.drawElement(i,j);
				System.out.print("|");
				plansza.drawIndex(i, false);
				System.out.println();
			}

		}
		plansza.displayLetterIndexes();
	}


	public void copyPlansza(Plansza plansza){
		for(int i = 0; i< SIZE ; i++)
			for(int j = 0 ; j < SIZE ; j++)
				this.plansza.setPole(i,j,plansza.getPole(i,j));
		displayBattlefield();
	}


	@Override
	public void setPrevious(boolean x) {
		prevWasHit = x;

	}

	public boolean getPrev(){
		return prevWasHit;
	}

}

