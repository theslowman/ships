package com.mygdx.ships;

import java.util.Random;

public class AIPlayer implements Player{

	Plansza plansza = new Plansza(SIZE);
	Plansza planszaPrzeciwnika = new Plansza(SIZE);
	Random generator = new Random();
	int prevX, prevY;
	boolean prevWasHit = false;
	@Override
	public void addShips() {
		addShip(4);

		for(int i = 0; i < 2 ; i++){
			addShip(3);

		}
		for(int i = 0; i < 3 ; i++){
			addShip(2);

		}
		for(int i = 0; i < 4 ; i++) {
			addShip(1);

		}

	}

	@Override
	public boolean attack(Player enemy) {

		int x, y;
		System.out.println("prevX:"+prevX);
		System.out.println("prevY:"+prevY);
		System.out.println("prevWas:"+prevWasHit);

		if(prevWasHit)
			while(true){
				x = prevX;
				y = prevY;
				int xx,yy;
				x = setLeftBorderX(x,y);
				System.out.println("x = "+x);
				xx = setRightBorderX(x,y);
				System.out.println("xx = "+xx);
				y = setUpperBorderY(x,y);
				System.out.println("y = "+y);
				yy = setLowerBorderY(x,y);
				System.out.println("yy = "+yy);
				if(x==xx&&y==yy){
					System.out.print("X==XX,Y==YY");
					if(randomNumber()%2==1){
						x += randomOne(x);
						if(planszaPrzeciwnika.getPole(y,x).getStatus().equals(P))
							continue;
						break;
					}

					else{
						y += randomOne(y);
						if(planszaPrzeciwnika.getPole(y,x).getStatus().equals(P))
							continue;
						break;
					}
				}
				else
				if(x==xx){
					System.out.println("X==XX");
					y = verticalAttack(x,y,yy);
					break;
				}
				else
				if(y==yy){
					System.out.println("Y=YY");
					x = horizontalAttack(x,y,xx);
					break;
				}

			}
		else{
			do{
				x = randomNumber();
				y = randomNumber();}
			while(wasAttacked(x,y));}


		if(enemy.beAttacked(x, y)){

			planszaPrzeciwnika.setPole(y, x ,T);
			if(enemy.getPlansza().isDrowned(x, y)){
				planszaPrzeciwnika.surroundDrownedShip(x, y);
				prevWasHit = false;
			}
			else{
				prevWasHit = true;
				prevX = x;
				prevY = y;
			}

			return true;

		}
		//if(planszaPrzeciwnika.getPole(y, x).getStatus().equals(N))

		planszaPrzeciwnika.setPole(y, x ,P);
		System.out.println("Pud³o! x-"+x+"y-"+y);
		return false;
	}
	private int setLowerBorderY(int x, int y) {
		int yy = y;
		while(planszaPrzeciwnika.checkLower(yy, x, T)&&!planszaPrzeciwnika.checkLower(y, x, P)) yy++;
		return yy;
	}
	private int setUpperBorderY(int x, int y) {
		int yy = y;
		while(planszaPrzeciwnika.checkUpper(yy, x, T)&&!planszaPrzeciwnika.checkUpper(y, x, P)) yy--;
		return yy;
	}

	private int setLeftBorderX(int x, int y) {
		int xx = x;
		while(planszaPrzeciwnika.checkLeft(y, xx, T)&&!planszaPrzeciwnika.checkLeft(y, x, P)) xx--;
		return xx;
	}
	private int setRightBorderX(int x, int y) {
		int xx = x;
		while(planszaPrzeciwnika.checkRight(y, xx, T)&&!planszaPrzeciwnika.checkRight(y, x, P)) xx++;
		return xx;
	}
	public int randomOne(int x){
		if(x==0)
			return 1;
		if(x==SIZE-1)
			return -1;
		int random = generator.nextInt();
		return random/Math.abs(random);
	}

	public boolean wasAttacked(int x, int y){
		if(planszaPrzeciwnika.getPole(y, x).getStatus().equals(T)||planszaPrzeciwnika.getPole(y, x).getStatus().equals(P)) return true;
		return false;
	}

	public int randomNumber(){
		return Math.abs(generator.nextInt()%10);
	}
	public int areaCheck(){
		return 1;
	};

	public int horizontalAttack(int x, int y, int xx){
		System.out.println(x);
		System.out.println(y);
		System.out.println(xx);
		System.out.println(planszaPrzeciwnika.checkLeft(y, x, B));
//	System.out.println(planszaPrzeciwnika.getPole(y, x-1).getStatus());
		System.out.println(planszaPrzeciwnika.checkRight(y, xx, B));
//	System.out.println(planszaPrzeciwnika.getPole(y, xx+1).getStatus());
		if((planszaPrzeciwnika.checkRight(y, xx, B))&&planszaPrzeciwnika.checkLeft(y, x, B)){
			if(randomNumber()%2==1)
				return x-1;
			else return xx+1;
		}
		if(planszaPrzeciwnika.checkLeft(y, x, B)){
			return x-1;
		}
		return xx+1;
	}

	public int verticalAttack(int x,int y, int yy){
		System.out.println(x);
		System.out.println(y);
		System.out.println(yy);
		System.out.println(planszaPrzeciwnika.checkLower(y, x, B));
//	System.out.println(planszaPrzeciwnika.getPole(y-1, x).getStatus());
		System.out.println(planszaPrzeciwnika.checkUpper(y, x, B));
//	System.out.println(planszaPrzeciwnika.getPole(yy+1, x).getStatus());
		if((planszaPrzeciwnika.checkUpper(y, x, B))&&planszaPrzeciwnika.checkLower(yy, x, B)){
			if(randomNumber()%2==1)
				return y-1;
			else return yy+1;
		}
		if(planszaPrzeciwnika.checkUpper(y, x, B)){
			return y-1;
		}
		return yy+1;
	}




	void addShip(int size){
		int x, y, xx, yy;
		while(true){

			if(generator.nextInt()%2==1){
				x = Math.abs(generator.nextInt()%10);
				while(((y = Math.abs(generator.nextInt()%10))+size)>SIZE-1);
				xx = x;
				yy = y + size;
			}
			else{
				y = Math.abs(generator.nextInt()%10);
				while(((x = Math.abs(generator.nextInt()%10))+size)>SIZE-1);
				yy = y;
				xx = x + size;

			}

			if(plansza.addShip(x, y, xx, yy, size)) return;
		}

	}
	void setupShip(int size){

	}

	@Override
	public boolean beAttacked(int x, int y) {
		System.out.println("Przeciwnik pod ostrza³em!");
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


	@Override
	public void displayPlansza() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBattlefield() {
		{
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

	}



	@Override
	public void copyPlansza(Plansza plansza) {


	}

	@Override
	public Plansza getPlansza() {
		return plansza;

	}

	@Override
	public Plansza getEnemyPlansza() {
		return planszaPrzeciwnika;
	}

	@Override
	public void setPrevious(boolean x) {

	}

	@Override
	public boolean getPrev() {
		// TODO Auto-generated method stub
		return false;
	}
}

