package me.mattd.passwordsolver;

import java.util.Scanner;

public class PasswordSolver {
	
	static char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//sc.useDelimiter("^[A-Za-z0-9_.]+$");
		
		System.out.print("Enter a password: ");
		String password = sc.next();
		
		// 62 valid characters to test
		
		
		String guess = "";
		int index = 0, firstCharIndex = 0, currentChar = 0, startIndex = 1;
		
		// While the password does not match
		while (!guess.equalsIgnoreCase(password)) {
			
			// Add characters to match the password length
			while (guess.length() < password.length()) {
				guess += 'a';
				startIndex = password.length() - (password.length() - 1);
				index = guess.length() - 1;
			}
			
			/* If the current paste character is 9 (#61),
			 * else add one to the current character index
			 * in validChars Array. If the index of the guess character that is
			 * currently being edited is equal to the length is less and the length
			 * of the guess String - 1, set guess equal to the first character to the index character,
			 * the current character in the validChars Array, and the rest of the word from the letter
			 * after the character being edited.
			 */
			if (currentChar == 62) {
				
				System.out.println("CURRENT CHAR: " + currentChar);
				
				currentChar = 0;
				
				/* If the character being edited is the last character,
				 * else add one to the character index, and set the guess
				 * equal to the +1 the index of the first character in the
				 * validChars Array, and the rest of the existing guess
				 * String.
				*/
				if (isLastCharacter(guess, index)) {
					
					System.out.println("LAST CHARACTER");
					
					/*int twoChar = 0;
					int threeChar = 61;
					
					while (twoChar < 61 && threeChar > 0) {
						guess = guess.substring(0, 1) + validChars[twoChar++] + validChars[threeChar--];
						System.out.println(guess);
					}*/
					
					
					// If the first character is not a 9 (#61)
					if (firstCharIndex < 61) {
						guess = validChars[firstCharIndex] + guess.substring(1); // Change the first character to the next character
					} else {
						break;
					}
					index = startIndex;
				} else {
					
					
					System.out.println("INDEX: " + index);
					System.out.println("Current char: " + currentChar);
					
					guess = validChars[firstCharIndex] + guess.substring(1, index) + validChars[currentChar] + guess.substring(guess.length() - index);
					index--;
					
					System.out.println("GUESS: " + guess);
					
				}
			} else {
				
				if (isLastCharacter(guess, index)) {
					guess = guess.substring(0, index) + validChars[currentChar];
				} else {
					guess = guess.substring(0, index) + validChars[currentChar] + guess.substring(index + 1);
				}
				currentChar++;
			}

			System.out.println(guess);
		}
		
		System.out.printf("The password is '%s'.", guess);
		sc.close();
	}
	
	public static boolean isLastCharacter(String s, int i) {
		if (i == s.length() - 1) return true;
		return false;
	}
	
	public static int getCharIndex(char c) {
		for (int i = 0; i < 62; i++) {
			if (c == validChars[i]) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * aa
	 * ab
	 * ac
	 * ad
	 * ae
	 * af
	 * ag
	 * ah
	 * ai
	 * ...
	 * ba
	 * bb
	 * bc
	 * bd
	 * be
	 * ...
	 * za
	 * zb
	 * ....
	 * 9a
	 * 9a
	 * ...
	 * 
	 * 
	 * aaa
	 * aab
	 * aac
	 * aad
	 * aae
	 * aaf
	 * ....
	 * aba
	 * abb
	 * ....
	 * a99
	 * baa
	 * bab
	 * bac
	 * 
	 */
}
