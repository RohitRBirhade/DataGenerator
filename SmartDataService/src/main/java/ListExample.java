
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListExample {
	static Random rand = new Random();
	private static final int minForDigits = 48;
	private static final int maxForDigits = 58;
	private static final int minForAlphaCap = 65;
	private static final int maxForAlphaCap = 91;
	private static final int minForAlphaSmall = 97;
	private static final int maxForAlphaSmall = 123;
	private static int randForAlphaSmall ;
	private static int randForAlphaCap ;
	private static int randForDigits ;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Uppercase,Braces,Hyphens");
		String responce = sc.nextLine();
		getResponse(responce);
	}
	
	
	
	 
	protected static int[] getResponse(String responce){
		int flag=0;
		if(responce.contains("Uppercase")) {
			++flag;
		}
		if(responce.contains("Uppercase")) {
			++flag;
		}
		if(responce.contains("Uppercase")) {
			++flag;
		}
		return null;
	} 
	 
	private void guid_Generator() {
		char main;
		String str;
		for(int j=0;j<10;j++) {
			str ="";
			for(int i=0;i<32;i++) {
				randForAlphaSmall = (int) (rand.nextFloat() * (maxForAlphaSmall - minForAlphaSmall) + minForAlphaSmall);
				randForAlphaCap   = (int) (rand.nextFloat() * (maxForAlphaCap - minForAlphaCap) + minForAlphaCap);
				randForDigits     = (int) (rand.nextFloat() * (maxForDigits - minForDigits) + minForDigits);
				int randoms_Array[]  = {randForAlphaCap,randForAlphaSmall,randForDigits}; 
				main = (char) randoms_Array[rand.nextInt(3)];
				str = str+main;
			}
			System.out.println(" "+str);
		}
		
	} 
	 
}
