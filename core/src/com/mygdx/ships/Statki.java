package com.mygdx.ships;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class Statki {

	public static final String T = "trafiony";
	public static final String ST = "statek";
	public static final String P = "pudlo";
	public static final String N = "neutralny";
	public static final int SIZE = 10;



	Scanner in = new Scanner(System.in);
	static Player human = new HumanPlayer();
	static Player enemy = new AIPlayer();
	static Player generator = new AIPlayer();


	Statki(){
		human = new HumanPlayer();
		enemy = new AIPlayer();
		generator = new AIPlayer();
	}

	public void play(){
		//generator.addShips();
		//tester.addShips();
		human.copyPlansza(generator.getPlansza());
		Okno okno = new Okno();

//		int i=0;
//		generator.addShips();
//		human.copyPlansza(generator.getPlansza());
////		human.addShips();
//		tester.addShips();
////		human.copyPlansza(tester.getPlansza());
//		while(true){
//			if(human.getPlansza().allDrowned()) {System.out.println("Wygra³eœ");break;}
//			if(tester.getPlansza().allDrowned()) {System.out.println("Przegra³eœ");break;}
//			i++;
//			System.out.println("Tura: "+i);
//		while(human.attack(tester)){
//
//			human.displayBattlefield();
//		}
//		while(tester.attack(human))
//			human.displayBattlefield();
////		for(i = 0; i < 50; i++){
////
////			tester.attack(human);
////			tester.displayBattlefield();
////			}
////		human.displayBattlefield();
//
////		}
//		human.displayBattlefield();
//
//		}
	}
	public class Okno extends JFrame implements ActionListener{
		JPanel p = new JPanel();
		JFrame ramka = new JFrame();
		Button buttons[] = new Button[277];


		void setupLetters(){
			char iconName = 'A';
			for(int i = 1 ; i < 11 ; i++){
				buttons[i] = new Button(iconName,i);
				buttons[i+11] = new Button(iconName,11+1);
				buttons[i+253] = new Button(iconName,i+253);
				buttons[i+264] = new Button(iconName,i+264);
				iconName++;
			}
		}

		void setupNumber(){
			char iconName = '0';
			for(int i = 0, j = 0 ; j < 10 ; j++, i+=23){
				buttons[i] = new Button(iconName,i);
				buttons[i+11] = new Button(iconName,i+11);
				buttons[i+22] = new Button(iconName,i+22);
				iconName++;
			}
			buttons[253] = new Button('0',253);
			buttons[264] = new Button('0',253);
			buttons[275] = new Button('0',253);
			buttons[230] = new Button('x',230);
			buttons[241] = new Button('x',241);
			buttons[252] = new Button('x',252);
		}

		void setupEnemyField(){
			enemy.addShips();
			for(int i = 0 ; i < 10; i++){
				for(int j = 0 ; j < 10 ; j++){
					buttons[23*(i+1)+j+12] = new Button(i);
					buttons[23*(i+1)+j+12].setPole(enemy.getPlansza().getPole(i, j));
					buttons[23*(i+1)+j+12].addActionListener(this);
				}
			}
		}
		void setupHumanField(){
			generator.addShips();
			human.copyPlansza(generator.getPlansza());
			for(int i = 0 ; i < 10; i++){
				for(int j = 0 ; j < 10 ; j++){
					buttons[23*(i+1)+j+1] = new Button(i);
					buttons[23*(i+1)+j+1].setPole(human.getPlansza().getPole(i, j));
				}
			}
		}

		void setupButtons2(){
			setupLetters();
			setupNumber();
			setupEnemyField();
			setupHumanField();


			for(int i = 0 ; i < 276 ; i++){
				System.out.println(i);
				p.add(buttons[i]);}

			add(p);
		}
		void setupButtons(){
			char iconName = 'A';

			for(int i = 1; i < 11 ; i++){
				buttons[i] = new Button(iconName,i);
				buttons[i+11] = new Button(iconName,11);
				iconName++;

			}
			for(int i = 24; i < 253 ; i++){
				buttons[i] = new Button();

			}

			for(int i = 0 ; i < 10; i++){
				for(int j = 0 ; j < 10 ; j++){
					buttons[23*(i+1)+j+12] = new Button(i);
					buttons[23*(i+1)+j+1].setPole(human.getPlansza().getPole(i, j));
					buttons[23*(i+1)+j+12].addActionListener(this);
				}
			}

			iconName = '0';
			for(int i = 0 ; i < 210 ; i+=23){
				buttons[i] = new Button(iconName,i);
				buttons[i+11] = new Button(iconName,i+11);
				buttons[i+22] = new Button(iconName,i+22);
				iconName++;
			}
			buttons[230] = new Button('x',230);
			buttons[241] = new Button('x',241);
			buttons[252] = new Button('x',252);
			for(int i = 0 ; i < 253 ; i++){
				p.add(buttons[i]);}

			add(p);
		}
		String getButtonPath(int x, int y){
			if(human.getPlansza().checkLeft(x, y, "statek")&&human.getPlansza().checkRight(x, y, "statek"))
				return "ship/midH.png";
			if(!human.getPlansza().checkLeft(x, y, "statek")&&human.getPlansza().checkRight(x, y, "statek"))
				return "ship/rearH.png";
			if(human.getPlansza().checkLeft(x, y, "statek")&&!human.getPlansza().checkRight(x, y, "statek"))
				return "ship/frontH.png";
			if(human.getPlansza().checkUpper(x, y, "statek")&&human.getPlansza().checkLower(x, y, "statek"))
				return "ship/midV.png";
			if(!human.getPlansza().checkUpper(x, y, "statek")&&human.getPlansza().checkLower(x, y, "statek"))
				return "ship/frontV.png";
			if(human.getPlansza().checkUpper(x, y, "statek")&&!human.getPlansza().checkLower(x, y, "statek"))
				return "ship/rearV.png";
			return "ship/single.png";
		}
		void addShips(){
			enemy.addShips();
			for(int i = 0 ; i < 10; i++){
				for(int j = 0 ; j < 10 ; j++){
					buttons[23*(i+1)+j+12].setPole(enemy.getPlansza().getPole(i, j));

				}
			}
			generator.displayBattlefield();
		}
		Okno(){
			super("Statki");
			setSize(1150, 600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			//ramka.setVisible(true);
			setResizable(false);
			p.setLayout(new GridLayout(12,23));
			setupButtons2();
			//addShips();

			setVisible(true);

		}


		@Override
		public void actionPerformed(ActionEvent arg) {
	boolean bool = false;
			if(!human.getPrev())
				do{

					for(int i = 0 ; i < 10; i++){
						for(int j = 0 ; j < 10 ; j++){
							buttons[23*(i+1)+j+1].setPole(human.getPlansza().getPole(i, j));
							if(buttons[23*(i+1)+j+1].getPole().getStatus().equals("statek"))
								buttons[23*(i+1)+j+1].refreshIcon(getButtonPath(i,j));
							else
								buttons[23*(i+1)+j+1].refreshIcon();
						}
					}
				}while(enemy.attack(human));
		}





	}
	public static void main(String[] args) {
		Statki s = new Statki();

		s.play();
	}

}
