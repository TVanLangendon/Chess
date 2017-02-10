package one;

import java.util.List;
import java.util.ArrayList;

//import one.Tile.Player;
//import java.util.Scanner;

/*
 FEATURES TO ADD:

 -en pessant
 -castling

 ERRORS TO FIX:
 -improper input error

 */

public class PlayGame {

	//static Tile[][] gameboard = new Tile[8][8];


	
	public static void main(String[] args) {

	Board board = new Board();
	
		while (PromptMove.startingposition != 0) { // change to
													// While(!EndOfGame)
			board.displayBoard();
			// System.out.println("Black king position: " +
			// Board.blackkingposition + ", White king position: " +
			// Board.whitekingposition);
			if (board.isInCheck()) {
				if (board.isCheckmate(board.getKing(),
						board.threat)) {
					break;
				}
				System.out.println("You are in check!");
			}
			PromptMove.promptUserInput();
			while (board.executeMovement(board.getTile(PromptMove.startingposition),
					PromptMove.startingposition, PromptMove.endingposition) == false) {
				board.displayBoard();
				PromptMove.promptUserInput();
			}
			board.switchPlayer(board.getTile(PromptMove.endingposition));
		}

		System.out.println("Game finished!");



	}
}
