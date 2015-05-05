package me.mattd.passwordsolver;

import java.util.Scanner;

public class PasswordSolver {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//sc.useDelimiter("^[A-Za-z0-9_.]+$");
		
		System.out.print("Enter a password: ");
		String password = sc.next();
		
		char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		String guess = "";
		int index = 0;
		int currentChar = 0;
		while (!guess.equalsIgnoreCase(password)) {
			while (guess.length() < password.length()) {
				guess += 'a';
			}
			
			guess = guess.substring(0, index) + validChars[currentChar] + guess.substring(index + 1);
			currentChar++;
			
			if (currentChar == 62) {
				currentChar = 0;
				index++;
			}
			
			System.out.println(guess);
		}
		
		sc.close();
	}
	
}

/*
 * aa
 * ab
 * ba
 * bb
 * */
