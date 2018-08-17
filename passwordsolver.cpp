#include <string.h>
#include <iostream>

using namespace std;

// 62 valid characters to test
char validChars[] = {
  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
  'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
  'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
  '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
};

bool isLastCharacter( string s, int i ) {
  return i == s.length() - 1;
}
    
int getCharIndex( char c ) {
  for ( int i = 0; i < 62; i++ ) {
    if ( c == validChars[ i ] ) {
      return i;
    }
  }

  return -1;
}

char getNextChar( char c )
{
  return validChars[getCharIndex(c) + 1];
}
    
string resetCharacters( string s ) {
  for (int i = 0; i < s.length(); i++) {
    s += "a";
  }
  
  return s;
}
    
string resetCharacters( int len ) {
  string s = "";

  for (int i = 0; i < len; i++) {
    s += "a";
  }

  return s;
}
    
string solvePassword( string password ) {
  string guess = "a";
  int index = 0;
  int len;
  bool addNewChar = true;
  bool changeFirstChar = true;

  // While the password does not match
  while ( guess != password && guess.length() < 51 ) {
    if ( guess[ index ] == '9' ) {
      len = guess.length();

      if ( index == 0 ) { 
        if ( getCharIndex( guess[0] ) == 61 ) {
          guess = "a" + guess.substr(1) + "a";
        } else {
          guess = getNextChar( guess[0] ) + guess.substr(1) + "a";
        }
      } else { 
        addNewChar = true;
          
        for (int i = 0; i < len; i++) {
          if ( guess[i] != '9' ) {
            addNewChar = false;
          }
        }
      if ( addNewChar ) {
        guess = "a";

        for ( int i = 0; i < len; i++ ) {
          guess += "a";
        }

        index = len - 1;
        addNewChar = false;
      } else {
        if ( len > 2 ) {
          changeFirstChar = true;
          for (int i = 1; i < len; i++) {
            if ( guess[ i ] != '9' ) {
              changeFirstChar = false;
            }
          }
            
          if ( changeFirstChar ) {
            guess = getNextChar( guess[ 0 ] ) + resetCharacters( len );
          }
        }
            
        if ( guess[ index - 1 ] == '9' ) {
          if (index > 1) {
              index--;
              
              if ( guess[ index - 1] == '9' ) {
                  guess = guess.substr(0, index - 1) + "9" + resetCharacters(guess.substr(index).length());
              } else {
                  guess = guess.substr(0, index - 1) + getNextChar( guess[ index - 1 ] ) + resetCharacters( guess.substr(index).length() );
              }
          }

          index = len - 1;
        } else {
          guess = guess.substr(0, index - 1) + getNextChar( guess[ index - 1 ] ) + validChars[ 0 ];
        }
      }
    }
    
    len = guess.length();

    if ( len < 4 ) {
      index = len - 1;
    } else {
      bool changeChar = true;

      for ( int i = 1; i < len; i++ ) {
        if ( guess[ i ] != '9' ) {
         changeChar = false;
        }
      }
        
      if (changeChar) {
        index = len - 2;
      }
    }
    } else {
      if ( isLastCharacter( guess, index ) ) {
        guess = guess.substr(0, index) + getNextChar( guess[ index ] );
      } else {
        guess = guess.substr(0, index) + getNextChar( guess[ index ] ) + guess.substr(index + 1);
      }
    }

    cout << guess << "\n";
  }
    
  return guess;
}

int main( int argc, char *argv[] ) {
  string password;
  cout << "Enter a password to test:\n"; 
  cin >> password;

  password = solvePassword( password );
  cout << "The password is: " << password << "\n";
  return EXIT_SUCCESS;
}