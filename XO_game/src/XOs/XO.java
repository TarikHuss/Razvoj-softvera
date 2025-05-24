package XOs;
import java.util.Scanner;

public class XO {
	
public static void main(String[] args) {

char[][] board = new char[3][3]; 
Scanner unos = new Scanner(System.in);

for(int i = 0; i<3; ++i) {
	for(int j = 0; j<3; j++) {
		board[i][j] = ' ';
	}
}

for(int i = 0; i<3; ++i) {
	for(int j = 0; j<3; j++) {
		System.out.println("Unesite koordinate (red i kolona).");
		System.out.println("Unesite red (1-3) : ");
		int red = unos.nextInt();
		System.out.println("Unesite kolonu (1-3) : ");
		int kolona = unos.nextInt();
		
		while(red<1 || red>3 || kolona<1 || kolona>3 || board[red-1][kolona-1] != ' ') {
			System.out.println("Nevalidan unos. Koordinate su izvan granica ili je polje vec popunjeno. Molimo unesite ponovo!");
			System.out.println("Unesite red (1-3) : ");
			red = unos.nextInt();
			System.out.println("Unesite kolonu (1-3) : ");
			kolona = unos.nextInt();
			}
		
		System.out.println("Unesite X ili O : ");
		char polje = unos.next().charAt(0);
		
		while(polje != 'X' && polje != 'O') {
			System.out.println("Pogresan unos. Molimo unesite X ili O! ");
			polje = unos.next().charAt(0);
		}
		
		board[red-1][kolona-1] = polje;
	}
}

System.out.println("Ispis ploce : ");
for(int i = 0; i<3; ++i) {
	for(int j = 0; j<3; j++) {
		System.out.print(board[i][j] + " ");
	}
	System.out.println();
}

unos.close();

}
}
