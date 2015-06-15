package me.mattd.passwordsolver;

import java.util.Scanner;

public class PasswordSolver {
	
	// 62 valid characters to test
	static char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a password: ");
		String password = sc.next();
		System.out.printf("The password is '%s'.", solvePassword(password));
		sc.close();
	}
	
	static String solvePassword(String password) {
		String guess = "a";
		int index = 0, length;
		boolean addNewChar = true;
		boolean changeFirstChar = true;
		
		// While the password does not match
		while (!guess.equals(password)) {
			
			if (guess.charAt(index) == '9') {
				
				if (index == 0) {
					
					if (getCharIndex(guess.charAt(0)) == 61) {
						guess = "a" + guess.substring(1) + "a";
					} else {
						guess = getNextChar(guess.charAt(0)) + guess.substring(1) + "a";
					}
					
				} else {
					
					length = guess.length();
					addNewChar = true;
					
					for (int i = 0; i < length; i++) {
						if (guess.charAt(i) != '9') {
							addNewChar = false;
						}
					}

					if (addNewChar) {
						guess = "a";
						for (int i = 0; i < length; i++) {
							guess += "a";
						}
						index = guess.length() - 1;
						addNewChar = false;

					} else {
						if (guess.length() > 2) {
							changeFirstChar = true;
							for (int i = 1; i < length; i++) {
								if (guess.charAt(i) != '9') {
									changeFirstChar = false;
								}
							}
							
							if (changeFirstChar) {
								guess = getNextChar(guess.charAt(0)) + resetCharacters(guess.length());
							}
						}
						
						if (guess.charAt(index - 1) == '9') {
							
							if (index > 1) {
								index--;
								
								if (guess.charAt(index - 1) == '9') {
									guess = guess.substring(0, index - 1) + "9" + resetCharacters(guess.substring(index).length());
								} else {
									guess = guess.substring(0, index - 1) + getNextChar(guess.charAt(index - 1)) + resetCharacters(guess.substring(index).length());
								}
							}
							index = guess.length() - 1;
							
						} else {
							guess = guess.substring(0, index - 1) + getNextChar(guess.charAt(index - 1)) + validChars[0];
						}
					}
				}
				
				if (guess.length() < 4) {
					index = guess.length() - 1;
				} else {
					boolean changeChar = true;
					
					length = guess.length();
					for (int i = 1; i < length; i++) {
						if (guess.charAt(i) != '9') {
							changeChar = false;
						}
					}
					
					if (changeChar) {
						index = guess.length() - 2;
					}
				}
			} else {
				if (isLastCharacter(guess, index)) {
					guess = guess.substring(0, index) + getNextChar(guess.charAt(index));
				} else {
					guess = guess.substring(0, index) + getNextChar(guess.charAt(index)) + guess.substring(index + 1);
				}
			}

			System.out.println(guess);
		}
		
		return guess;
	}
	
	static boolean isLastCharacter(String s, int i) {
		if (i == s.length() - 1) return true;
		return false;
	}
	
	static int getCharIndex(char c) {
		for (int i = 0; i < 62; i++) {
			if (c == validChars[i]) {
				return i;
			}
		}
		return -1;
	}
	
	static char getNextChar(char c) {
		return validChars[getCharIndex(c) + 1];
	}
	
	static String resetCharacters(String s) {
		int length = s.length();
		for (int i = 0; i < length; i++) {
			s += "a";
		}
		return s;
	}
	
	static String resetCharacters(int length) {
		String s = "";
		for (int i = 0; i < length; i++) {
			s += "a";
		}
		return s;
	}
}
