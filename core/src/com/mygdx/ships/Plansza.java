package com.mygdx.ships;



public class Plansza {

    public static final String T = "trafiony";
    public static final String ST = "statek";
    public static final String P = "pudlo";
    public static final String B = "pole bia³e";
    public static final int SIZE = 10;

    private
    Pole[][] plansza;
    int size;

    public
    Plansza(int size){
        this.size = size;
        plansza = new Pole[size][size];
        fillEmpty();
    }

    boolean addShip(int x, int y,int xx, int yy, int size){


        int minX = Math.min(x, xx);
        int minY = Math.min(y, yy);
        if(isAvailable(x,y,xx,yy,size)) {
            if(x==xx){
                for(int i = 0; i < size ; i++)
                    plansza[minY+i][x].setStatus(ST);
                return true;
            }
            if(y==yy){
                for(int i = 0; i < size ; i++)
                    plansza[y][minX+i].setStatus(ST);
                return true;
            }
        }
        return false;
    }
    boolean setPole(int x, int y, String status){
        if(x<SIZE||y<SIZE)
        {plansza[x][y].setStatus(status); return true;}
        return false;
    }
    boolean setPole(int x, int y, Pole pole){
        if(x<SIZE||y<SIZE)
        {plansza[x][y].setStatus(pole.getStatus()); return true;}
        return false;
    }
    public Pole getPole(int x, int y){
        return plansza[x][y];
    }
    boolean isAvailable(int x, int y,int xx, int yy, int size){
        int upperBorder = Math.min(y, yy);
        int leftBorder = Math.min(x, xx);
        int lowerBorder = Math.max(y, yy);
        int rightBorder = Math.max(x, xx);
        if(checkLeft(leftBorder)) leftBorder--;
        if(checkRight(rightBorder)) rightBorder++;
        if(checkUpper(upperBorder)) upperBorder--;
        if(checkLower(lowerBorder)) lowerBorder++;

        for(int i = leftBorder ; i <= rightBorder ; i++){
            for(int j = upperBorder ; j <= lowerBorder ; j++){
                if(plansza[j][i].getStatus().equals(ST)) return false;
            }
        }

        return true;
    }
    boolean isDrowned(int x, int y){
        int maxX=x;
        int maxY=y;
        int minX=x;
        int minY=y;

        while(checkLower(maxY, x, T)) maxY++;
        while(checkLeft(y, minX, T)) minX--;
        while(checkUpper(minY, x, T)) minY--;

        if(checkRight(y, maxX, ST)) return false;
        if(checkLower(maxY, x,ST)) return false;
        if(checkLeft(y, minX,ST)) return false;
        if(checkUpper(minY, x,ST)) return false;

        return true;
    }
    boolean allDrowned(){
        for(int i = 0 ; i < SIZE ; i++)
            for(int j = 0 ; j < SIZE ; j++)
                if(plansza[i][j].getStatus().equals(ST)) return false;
        return true;
    }
    public boolean attack(int x,int y){

        if(plansza[y][x].getStatus().equals(ST)) {
            plansza[y][x].setStatus(T);

            return true;
        }
        plansza[y][x].setStatus(P);
        return false;
    }
    void surroundDrownedShip(int x, int y){
        int maxX=x;
        int maxY=y;
        int minX=x;
        int minY=y;

        while(checkRight(y, maxX, T)) maxX++;
        while(checkLower(maxY, x, T)) maxY++;
        while(checkLeft(y, minX, T)) minX--;
        while(checkUpper(minY, x, T)) minY--;

        if(minY!=0)
            minY--;
        if(minX!=0)
            minX--;
        if(maxY!=SIZE-1)
            maxY++;
        if(maxX!=SIZE-1)
            maxX++;
        for(int i = minY; i <= maxY; i++)
            for(int j = minX ; j<=maxX ; j++)
                if(!plansza[i][j].getStatus().equals(T))
                    plansza[i][j].setStatus(P);



    }

    boolean checkLeft(int x, int y, String status){
        if((y-1)<0) return false;

        return plansza[x][y-1].getStatus().equals(status);
    }
    boolean checkLeft(int y){
        if((y-1)<0) return false;
        return true;
    }
    boolean checkRight(int x, int y, String status){
        if((y+1)==SIZE) return false;
        return plansza[x][y+1].getStatus().equals(status);
    }
    boolean checkRight(int y){
        if(y+1==SIZE) return false;
        return true;
    }
    boolean checkUpper(int x, int y, String status){
        if((x-1)<0) return false;
        return plansza[x-1][y].getStatus().equals(status);
    }
    boolean checkUpper(int x){
        if(x-1<0) return false;
        return true;
    }
    boolean checkLower(int x, int y, String status){
        if((x+1)==SIZE) return false;
        return plansza[x+1][y].getStatus().equals(status);
    }
    boolean checkLower(int x){
        if(x+1==SIZE) return false;
        return true;
    }

    void fillEmpty(){
        for(int i = 0; i < SIZE ; i++)
            for(int j = 0; j < SIZE ; j++){
                plansza[i][j] = new Pole();
            }

    }

    public void displayPlansza(){
        displayLetterIndexes();
        for(int i = 0; i < SIZE ; i++){
            drawIndex(i);
            for(int j = 0; j < SIZE ; j++){
                drawElement(i,j);
            }
            System.out.println("|");
        }
        displayLetterIndexes();
    }
    void displayLetterIndexes(){

        System.out.print("  ");
        A2Jloop();
        System.out.print("     ");
        A2Jloop();
        System.out.println();
    }
    void drawIndex(int i){
        if(i==9)
            System.out.print((i+1)+"|");
        else
            System.out.print((i+1)+" |");
    }
    void drawIndex(int i, boolean x){

        System.out.print(" "+(i+1));
    }
    void A2Jloop(){
        char currentLetter = 'A';
        for(int i = 0 ; i < SIZE ; i++){
            System.out.print(" "+currentLetter);
            currentLetter++;
        }
    }
    void drawElement(int i, int j){
        String stan = plansza[i][j].getStatus();
        if(stan.equals(B))System.out.print(" .");
        if(stan.equals(ST))System.out.print("I.");
        if(stan.equals(P))System.out.print("O.");
        if(stan.equals(T))System.out.print("X.");
    }
}






