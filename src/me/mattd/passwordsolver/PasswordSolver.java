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
				currentChar = 0;
				
				/* If the character being edited is the last character,
				 * else add one to the character index, and set the guess
				 * equal to the +1 the index of the first character in the
				 * validChars Array, and the rest of the existing guess
				 * String.
				*/
				
				if (firstCharIndex >= 61) break;
				if (guess.charAt(startIndex) == '9') {
					firstCharIndex++;
					guess = validChars[firstCharIndex] + ""; 
					while (guess.length() < password.length() - 1) {
						guess += 'a';
					}
				}
				if (guess.charAt(index - 1) == '9') index--;
				
				if (isLastCharacter(guess, index)) {
					guess = validChars[firstCharIndex] + guess.substring(1, index - 1) + validChars[getCharIndex(guess.charAt(index - 1)) + 1] + validChars[currentChar];
				} else {
					if (getCharIndex(guess.charAt(index - 1)) == 61) {
						guess = validChars[firstCharIndex] + guess.substring(1, index - 1) + validChars[0] + validChars[currentChar] + guess.substring(index + 2);
					} else {
						guess = validChars[firstCharIndex] + guess.substring(1, index - 1) + validChars[getCharIndex(guess.charAt(index - 1)) + 1] + validChars[currentChar] + guess.substring(index);
					}
				}				
			} else {
				if (isLastCharacter(guess, index)) {
					guess = guess.substring(0, index) + validChars[currentChar];
				} else {
					guess = guess.substring(0, index) + validChars[currentChar] + guess.substring(index + 2);
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
	 * Get string length set all to a
	 *--
	 * Cycle last character a-9
	 * Change startIndex character to next character (a > b)
	 * Repeat until startIndex character = 9
	 */
}
