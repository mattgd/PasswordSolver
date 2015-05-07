package me.mattd.passwordsolver;

import java.util.Scanner;

public class PasswordSolver {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//sc.useDelimiter("^[A-Za-z0-9_.]+$");
		
		System.out.print("Enter a password: ");
		String password = sc.next();
		
		// 62 valid characters to test
		char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		String guess = "";
		int index = 0, firstCharIndex = 0, currentChar = 0, startIndex = 1;
		while (!guess.equalsIgnoreCase(password)) {
			
			// Add characters to match the password length
			while (guess.length() < password.length()) {
				guess += 'a';
				startIndex = password.length() - (password.length() - 1);
				index = startIndex;
			}
			
			// If the current paste character is 9
			if (currentChar == 61) {
				currentChar = 0;
				
				// If the character being edited is the last character
				if (isLastCharacter(guess, index)) {
					
					// If the first character is not a 9
					if (firstCharIndex < 61) {
						guess = validChars[++firstCharIndex] + guess.substring(1); // Change the first character to the next character
					} else {
						break;
					}
					index = startIndex;
				} else {
					index++;
					guess = validChars[++firstCharIndex] + guess.substring(1);
				}
			} else {
				currentChar++;
				System.out.printf("%d and %d%n", index, guess.length() - 1);
				if (index <= guess.length() - 1) {
					guess = guess.substring(0, index + 1) + validChars[currentChar] + guess.substring(index + 2);
				} else {
					guess = guess.substring(0, index + 1) + validChars[currentChar];
				}
			}
			
			

			System.out.println(guess);
			
			/*if (currentChar == 62) {
				currentChar = 0;
				if (index < guess.length() - 2) {
					index++;
					System.out.println(index);
				} else {
					if (saveIndex < 61) {
						saveIndex++;
					} else {
						saveIndex = 0;
					}
					guess = guess.substring(0, index) + validChars[saveIndex] + guess.substring(index + 2);
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		System.out.printf("The password is '%s'.", guess);
		sc.close();
	}
	
	public static boolean isLastCharacter(String s, int i) {
		if (i == s.length() - 1) return true;
		return false;
	}
}
