package com.psl.smartdataservice.generators;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.LongStream;

public class DataGenerators {
	Random rand = new Random();
	int min;
	int max;
	List<String> list = new ArrayList<String>();
	int randomInt ;
	List minList;
	
	/**
	 * Generates Data 
	 * @param size
	 * @return
	 */
	public List<String> Auto(int size) {
		min=100;
		max=999;
		for(int i=0;i<size;i++) {
			 list.add(("AA-100"+(int)(rand.nextFloat() * (max - min) + min)));
		}
	    return list;  	
	}
	
	public List<String> Number(int size) {
		minList = List.of("+91","+01");
		min=1000000;
		max=9999999;
		for(int i=0;i<size;i++) {
			randomInt = (int) (rand.nextFloat() * (max - min) + min);
			list.add(minList.get(rand.nextInt(minList.size()))+""+randomInt);
		}
		return list;
	}
	
	public List AlphaNum(int size) {
		int x;
		int y;
		int z,z1;
		min=65;
		max=90;
		for(int i=0;i<size;i++) {
			x = (int) (rand.nextFloat() * (max - min) + min);
			y = (int) (rand.nextFloat() * (max - min) + min);
			z = (int) (rand.nextFloat() * (max - min) + min);
			z1 = (int) (rand.nextFloat() * (max - min) + min);
			list.add((char)x+""+(char)y+""+(char)z+""+
							   (int)(rand.nextFloat()*(999-100)+100)+""+(char)z1+""+(int)(rand.nextFloat()*(999-100)+100));}
		return list;
	}
	
	public List Date(int size) {
		for(int i=0;i<size;i++) {
			list.add((int) (rand.nextFloat() * (31 - 1) + 1)+"/"+
							   (int) (rand.nextFloat() * (12 - 1) + 1)+"/"+
							   (int) (rand.nextFloat() * (2022 - 1950) + 1950));
		}
		return list;
	}
	
	public List Time(int size) {
		for(int i=0;i<size;i++) {
			list.add((int) (rand.nextFloat() * (13 - 1) + 1)+":"+
							   (int) (rand.nextFloat() * (60 - 1) + 1)+":"+
							   (int) (rand.nextFloat() * (60 - 1) + 1));
		}
		return list;
	}
	
	public List booLean(int size) {
		minList = List.of("true","false");
		for(int i=0;i<size;i++) {
			list.add((String) minList.get(rand.nextInt(minList.size())));
		} 
		return list;
	}

}   
	