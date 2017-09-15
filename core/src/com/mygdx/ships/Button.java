package com.mygdx.ships;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener{


	//
//		public static final String T = "trafiony";
//		public static final String ST = "statek";
//		public static final String P = "pudlo";
//		public static final String N = "neutralny";
//
	ImageIcon X, S, O, W,P,M,T,SM ,icon;
	int number;
	byte value = 0;
	Pole pole;
		/*
		0 - N as neutral
		1 - O as missed
		2 - S as ship
		3 - X as marked
		*/

	public Button(){

		X = new ImageIcon(this.getClass().getResource("effect/marked.png"));
		W = new ImageIcon(this.getClass().getResource("woda.png"));
		O = new ImageIcon(this.getClass().getResource("O.png"));
		SM = new ImageIcon(this.getClass().getResource("ship/mid.png"));
		setIcon(W);
	}

	public Button(int number){

		X = new ImageIcon(this.getClass().getResource("effect/marked.png"));
		SM = new ImageIcon(this.getClass().getResource("ship/mid.png"));
		//S = new ImageIcon(this.getClass().getResource("sm.png"));
		O = new ImageIcon(this.getClass().getResource("effect/O.png"));
		W = new ImageIcon(this.getClass().getResource("woda.png"));
		P = new ImageIcon(this.getClass().getResource("ship/front.png"));
		M = new ImageIcon(this.getClass().getResource("ship/mid.png"));
		T = new ImageIcon(this.getClass().getResource("ship/rear.png"));
		addActionListener(this);
		setIcon(W);
	}

	public Button(char iconName, int number){
		if(iconName == 'x')
			icon = new ImageIcon(this.getClass().getResource("chars/10.png"));
		else{
			System.out.print(iconName);
			System.out.print(this.getClass().getResource("chars/"+iconName+".png"));
			icon = new ImageIcon(this.getClass().getResource("chars/"+iconName+".png"));}
		setIcon(icon);
	}
	//funkcja
	void setPole(Pole pole){
		this.pole = pole;
	}

	void refreshIcon(){
		if (pole.getStatus().equals("statek"))
			setIcon(SM);
		if (pole.getStatus().equals("pudlo"))
			setIcon(O);
		if (pole.getStatus().equals("trafiony"))
			setIcon(X);
	}

	boolean isShip(){
		if(pole.getStatus().equals("statek"))
			return true;
		return false;
	}

	byte getValue(){
		return value;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		System.out.println("JEBUDUP!");
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		if(isShip()){
			setIcon(X);
			pole.setStatus("trafiony");
			System.out.println("BUM!");
			Statki.human.setPrevious(true);
		}
		else{
			setIcon(O);
			System.out.println("PLUM!");
			Statki.human.setPrevious(false);
		}

	}

	public Pole getPole() {

		return pole;
	}

	public void refreshIcon(String buttonPath) {
		System.out.println("hahahahahaha");
		ImageIcon x = new ImageIcon(this.getClass().getResource(buttonPath));
		setIcon(x);

	}



}
