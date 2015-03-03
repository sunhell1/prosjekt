package main;
import java.awt.Point;

import enums.*;
import myownlol.*;


public class main {
	public static void main(String[] args){
		
		Board b = new Board();
		Player player = new Player("Fredrik", Direction.SOUTH, new Point(10,10), b);
		
		System.out.println(player.getLocation());
		System.out.println("Players life:" + player.getLives());
		player.move(player.getLocation(), player.getDirection());
		System.out.println(player.getLocation());
		System.out.println("Players life:" + player.getLives());
		player.move(player.getLocation(), player.getDirection());
		System.out.println(player.getLocation());
		System.out.println("Players life:" + player.getLives());
		player.move(player.getLocation(), Direction.EAST);
		System.out.println(player.getLocation());
		System.out.println("Players life:" + player.getLives());
		player.move(player.getLocation(), Direction.EAST);
		System.out.println(player.getLocation());
		System.out.println("Players life:" + player.getLives());
		player.move(player.getLocation(), Direction.EAST);
		System.out.println(player.isAlive());
		player.move(player.getLocation(), Direction.EAST);
		System.out.println(player.isAlive());
		player.move(player.getLocation(), Direction.EAST);
		System.out.println(player.isAlive());
		System.out.println(player.getName() + " died a horrible death.");
			
	}

}
