package one;

import one.Tile.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	static Tile[][] board = new Tile[8][8];
	
	static Rook rookqueenblack = new Rook(Player.BLACK, 1, 1);
	static Rook rookkingblack = new Rook(Player.BLACK, 2, 8);
	static Knight knightqueenblack = new Knight(Player.BLACK, 1, 2);
	static Knight knightkingblack = new Knight(Player.BLACK, 2, 7);
	static Bishop bishopqueenblack = new Bishop(Player.BLACK, 1, 3);
	static Bishop bishopkingblack = new Bishop(Player.BLACK, 2, 6);
	static Queen queenblack = new Queen(Player.BLACK, 1, 4);
	static King kingblack = new King(Player.BLACK, 1, 5);

	static Pawn pawnoneblack = new Pawn(Player.BLACK, 1, 9);
	static Pawn pawntwoblack = new Pawn(Player.BLACK, 2, 10);
	static Pawn pawnthreeblack = new Pawn(Player.BLACK, 3, 11);
	static Pawn pawnfourblack = new Pawn(Player.BLACK, 4, 12);
	static Pawn pawnfiveblack = new Pawn(Player.BLACK, 5, 13);
	static Pawn pawnsixblack = new Pawn(Player.BLACK, 6, 14);
	static Pawn pawnsevenblack = new Pawn(Player.BLACK, 7, 15);
	static Pawn pawneightblack = new Pawn(Player.BLACK, 8, 16);

	static Rook rookqueenwhite = new Rook(Player.WHITE, 3, 57);
	static Rook rookkingwhite = new Rook(Player.WHITE, 4, 64);
	static Knight knightqueenwhite = new Knight(Player.WHITE, 3, 58);
	static Knight knightkingwhite = new Knight(Player.WHITE, 4, 63);
	static Bishop bishopqueenwhite = new Bishop(Player.WHITE, 3, 59);
	static Bishop bishopkingwhite = new Bishop(Player.WHITE, 4, 62);
	static Queen queenwhite = new Queen(Player.WHITE, 2, 60);
	static King kingwhite = new King(Player.WHITE, 2, 61);

	static Pawn pawnonewhite = new Pawn(Player.WHITE, 9, 49);
	static Pawn pawntwowhite = new Pawn(Player.WHITE, 10, 50);
	static Pawn pawnthreewhite = new Pawn(Player.WHITE, 11, 51);
	static Pawn pawnfourwhite = new Pawn(Player.WHITE, 12, 52);
	static Pawn pawnfivewhite = new Pawn(Player.WHITE, 13, 53);
	static Pawn pawnsixwhite = new Pawn(Player.WHITE, 14, 54);
	static Pawn pawnsevenwhite = new Pawn(Player.WHITE, 15, 55);
	static Pawn pawneightwhite = new Pawn(Player.WHITE, 16, 56);

	static public Player currentplayer = Player.WHITE;

	static public int blackkingposition = 5;
	static public int whitekingposition = 61;
	

	static Rook rookcantcastle = new Rook(Player.EMPTY, 0, 30); // prevents a
																// bug in
																// King.getNearestRook

	static public Tile temp; // temp tile placeholder to undo invalid moves (used for
						// isInCheck)
	static public Tile threat; // stores the piece that is threatening the King (used
						// for Checkmate)

	static public List<Tile> whitepiecesalive = new ArrayList<Tile>();
	static public List<Tile> whitepiecesdead = new ArrayList<Tile>();

	static public List<Tile> blackpiecesalive = new ArrayList<Tile>();
	static public List<Tile> blackpiecesdead = new ArrayList<Tile>();

	// Fills the board[][] with the pieces and emptytiles to start the game
	public Board() {

		board[0][0] = rookqueenblack;
		board[0][1] = knightqueenblack;
		board[0][2] = bishopqueenblack;
		board[0][3] = queenblack;
		board[0][4] = kingblack;
		board[0][5] = bishopkingblack;
		board[0][6] = knightkingblack;
		board[0][7] = rookkingblack;

		board[1][0] = pawnoneblack;
		board[1][1] = pawntwoblack;
		board[1][2] = pawnthreeblack;
		board[1][3] = pawnfourblack;
		board[1][4] = pawnfiveblack;
		board[1][5] = pawnsixblack;
		board[1][6] = pawnsevenblack;
		board[1][7] = pawneightblack;

		board[7][0] = rookqueenwhite;
		board[7][1] = knightqueenwhite;
		board[7][2] = bishopqueenwhite;
		board[7][3] = queenwhite;
		board[7][4] = kingwhite;
		board[7][5] = bishopkingwhite;
		board[7][6] = knightkingwhite;
		board[7][7] = rookkingwhite;

		board[6][0] = pawnonewhite;
		board[6][1] = pawntwowhite;
		board[6][2] = pawnthreewhite;
		board[6][3] = pawnfourwhite;
		board[6][4] = pawnfivewhite;
		board[6][5] = pawnsixwhite;
		board[6][6] = pawnsevenwhite;
		board[6][7] = pawneightwhite;

		// the following for loops fill in empty tiles
		for (int i = 2; i <= 2; i++) {
			for (int j = 0; j <= 7; j++) {
				board[i][j] = new EmptyTile(Player.EMPTY, j + 1, 15 + i + j);
			}
		}

		for (int i = 3; i <= 3; i++) {
			for (int j = 0; j <= 7; j++) {
				board[i][j] = new EmptyTile(Player.EMPTY, j + 9, 22 + i + j);
			}
		}

		for (int i = 4; i <= 4; i++) {
			for (int j = 0; j <= 7; j++) {
				board[i][j] = new EmptyTile(Player.EMPTY, j + 17, 29 + i + j);
			}
		}

		for (int i = 5; i <= 5; i++) {
			for (int j = 0; j <= 7; j++) {
				board[i][j] = new EmptyTile(Player.EMPTY, j + 25, 36 + i + j);
			}
		}

		blackpiecesalive.add(bishopqueenblack);
		blackpiecesalive.add(bishopkingblack);
		blackpiecesalive.add(queenblack);
		blackpiecesalive.add(knightqueenblack);
		blackpiecesalive.add(knightkingblack);
		blackpiecesalive.add(rookqueenblack);
		blackpiecesalive.add(rookkingblack);
		blackpiecesalive.add(pawnoneblack);
		blackpiecesalive.add(pawntwoblack);
		blackpiecesalive.add(pawnthreeblack);
		blackpiecesalive.add(pawnfourblack);
		blackpiecesalive.add(pawnfiveblack);
		blackpiecesalive.add(pawnsixblack);
		blackpiecesalive.add(pawnsevenblack);
		blackpiecesalive.add(pawneightblack);
		blackpiecesalive.add(kingblack);

		whitepiecesalive.add(bishopqueenwhite);
		whitepiecesalive.add(bishopkingwhite);
		whitepiecesalive.add(queenwhite);
		whitepiecesalive.add(knightqueenwhite);
		whitepiecesalive.add(knightkingwhite);
		whitepiecesalive.add(rookqueenwhite);
		whitepiecesalive.add(rookkingwhite);
		whitepiecesalive.add(pawnonewhite);
		whitepiecesalive.add(pawntwowhite);
		whitepiecesalive.add(pawnthreewhite);
		whitepiecesalive.add(pawnfourwhite);
		whitepiecesalive.add(pawnfivewhite);
		whitepiecesalive.add(pawnsixwhite);
		whitepiecesalive.add(pawnsevenwhite);
		whitepiecesalive.add(pawneightwhite);
		whitepiecesalive.add(kingwhite);

		//return board;
	}

	public static Tile[][] checkmateTest() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new EmptyTile(Player.EMPTY, i * 8 + j, i * 8
						+ j + 1);
			}
		}

		board[7][7] = kingwhite;
		kingwhite.setUnitPosition(64);
		whitepiecesalive.add(kingwhite);
		board[7][6] = rookkingwhite;
		rookkingwhite.setUnitPosition(63);
		whitepiecesalive.add(rookkingwhite);
		board[6][7] = queenwhite;
		queenwhite.setUnitPosition(56);
		whitepiecesalive.add(queenwhite);
		board[6][6] = pawnsevenwhite;
		whitepiecesalive.add(pawnsevenwhite);
		board[5][7] = bishopkingwhite;
		bishopkingwhite.setUnitPosition(48);
		whitepiecesalive.add(bishopkingwhite);
		board[5][6] = bishopqueenwhite;
		bishopqueenwhite.setUnitPosition(47);
		whitepiecesalive.add(bishopqueenwhite);
		board[7][0] = knightkingwhite;
		knightkingwhite.setUnitPosition(57);
		whitepiecesalive.add(knightkingwhite);
		board[7][1] = rookqueenwhite;
		rookqueenwhite.setUnitPosition(58);
		whitepiecesalive.add(rookqueenwhite);

		blackpiecesalive.add(bishopqueenblack);
		blackpiecesalive.add(bishopkingblack);
		blackpiecesalive.add(queenblack);
		blackpiecesalive.add(knightqueenblack);
		blackpiecesalive.add(knightkingblack);
		blackpiecesalive.add(rookqueenblack);
		blackpiecesalive.add(rookkingblack);
		blackpiecesalive.add(kingblack);
		blackpiecesalive.add(pawnfiveblack);

		board[0][0] = rookqueenblack;
		board[0][1] = knightqueenblack;
		board[0][2] = bishopqueenblack;
		board[0][3] = queenblack;
		board[0][4] = kingblack;
		board[0][5] = bishopkingblack;
		board[0][6] = knightkingblack;
		board[0][7] = rookkingblack;
		board[1][4] = pawnfiveblack;

		Board.whitekingposition = 64;

		board[6][7] = kingwhite;
		kingwhite.setUnitPosition(56);
		whitepiecesalive.add(kingwhite);
		board[7][0] = pawnonewhite;
		pawnonewhite.setUnitPosition(57);
		whitepiecesalive.add(pawnonewhite);
		board[7][1] = rookkingwhite;
		rookkingwhite.setUnitPosition(58);
		whitepiecesalive.add(rookkingwhite);

		board[0][1] = rookkingblack;
		rookkingblack.setUnitPosition(2);
		blackpiecesalive.add(rookkingblack);
		board[0][2] = rookqueenblack;
		rookqueenblack.setUnitPosition(3);
		blackpiecesalive.add(rookqueenblack);
		board[0][0] = queenblack;
		queenblack.setUnitPosition(1);
		blackpiecesalive.add(queenblack);

		Board.blackkingposition = 1;
		Board.whitekingposition = 56;

		return board;

	}

	// Prints the current state of the board[][]
	public static void displayBoard() {

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				System.out.print(board[i][j].displayTile());
			}
			System.out.print(Math.abs(i + 1 - 9));
			System.out.println();
		}
		System.out.println("a b c d e f g h");
		System.out.println(Board.currentplayer + "'s turn");
	}

	// Given a piece and destination, checks if the movement is valid (possible,
	// not blocked, not check). If true: updates hasmoved and arrays.
	public static boolean executeMovement(Tile piece,
			int starting, int destination) {

		if (validMovement(piece, starting, destination)) {
			updateHasMoved(piece);
			updateArrays();
			return true;
		}

		/*
		 * //checks to see if movement is blocked else if
		 * (piece.isBlocked(gameboard, piece, piece.getUnitPosition(),
		 * destination)){ System.out.println("is blocked = true"); return false;
		 * }
		 * 
		 * //if the destination is not valid, then movement fails else if
		 * (!piece.possibleMovement(piece.getUnitPosition(), destination)){
		 * System.out.println("Possible Movement failed"); return false; }
		 * 
		 * System.out.println(
		 * "ERROR: executeMovement could not find possibleMovement");
		 */

		return false;
	}

	// option 1: loops through every possible move (of next player) to see if
	// check is defendable
	// option 2: notes the threatening piece. Check to see if that piece's
	// threat is defendable. Then calls isCheck() again and repeats.
	// note the threatening piece
	// attempt to move the king
	// find a kill/block: loops each piece through all the spaces in-between the
	// king and the threat
	// fail: Checkmate!
	// succeed: update the board. Call check again. Repeat.

	public static boolean isCheckmate(Tile king, Tile threat) {

		Tile savedthreat = new EmptyTile(threat.getPlayer(),
				threat.getUnitNumber(), threat.getUnitPosition()); // prevents
																	// the error
																	// of not
																	// finding
																	// the local
																	// variable
																	// savedthreat

		// the following if statements create a new object for the threat, so
		// that when validMovement calls isCheck when attempting to find moves
		// to determine isCheckmate, the threat will not be changed
		if (threat instanceof Rook) {
			savedthreat = new Rook(threat.getPlayer(), threat.getUnitNumber(),
					threat.getUnitPosition());
		}

		else if (threat instanceof Knight) {
			savedthreat = new Knight(threat.getPlayer(),
					threat.getUnitNumber(), threat.getUnitPosition());
		}

		else if (threat instanceof Bishop) {
			savedthreat = new Bishop(threat.getPlayer(),
					threat.getUnitNumber(), threat.getUnitPosition());
		}

		else if (threat instanceof Queen) {
			savedthreat = new Queen(threat.getPlayer(), threat.getUnitNumber(),
					threat.getUnitPosition());
		}

		else if (threat instanceof King) {
			savedthreat = new King(threat.getPlayer(), threat.getUnitNumber(),
					threat.getUnitPosition());
		}

		else if (threat instanceof Pawn) {
			savedthreat = new Pawn(threat.getPlayer(), threat.getUnitNumber(),
					threat.getUnitPosition());
		}

		// attempts to move the king out of check
		if (validMovementForCM(king, king.getUnitPosition(),
				king.getUnitPosition() - 9)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() + 9)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() - 8)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() + 8)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() - 7)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() + 7)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() - 1)
				|| validMovementForCM(king, king.getUnitPosition(),
						king.getUnitPosition() + 1)) {
			return false;
		}

		System.out.println("savedthreatening piece is: " + savedthreat
				+ savedthreat.getUnitPosition());

		if (king.getPlayer() == Player.WHITE) {

			if (savedthreat instanceof Knight || savedthreat instanceof Pawn) {
				for (int i = 0; i < whitepiecesalive.size(); i++) {
					if (validMovementForCM(whitepiecesalive.get(i),
							whitepiecesalive.get(i).getUnitPosition(),
							savedthreat.getUnitPosition())) {
						return false;
					}
				}
				System.out.println("Checkmate! The Black army is victorious!!");
				return true;
			}

			else if (savedthreat instanceof Rook) { // challenges threats from
													// Rooks
				if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition()) < 8)) { // rook attacking
															// horizontally
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // if
																					// king
																					// is
																					// to
																					// the
																					// left
																					// of
																					// threat
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 1) { // iterates
																	// through
																	// each
																	// space
																	// from
																	// threat up
																	// to king
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // king is to the right of
													// threat
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 1) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out.println("Checkmate! Black army is victorious!!");
					return true;
				} else if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition())) >= 8) { // rook attacking
															// vertically
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // determines
																					// if
																					// the
																					// kings
																					// unit
																					// position
																					// is
																					// higher
																					// or
																					// lower
																					// than
																					// the
																					// threats,
																					// for
																					// setting
																					// the
																					// incrementation
																					// of
																					// the
																					// following
																					// for
																					// loop
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 8) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 8) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out
							.println("Checkmate! The Black army is victorious!!");
					return true;
				}
			} else if (savedthreat instanceof Bishop) {
				System.out.println("Threatening piece is Bishop: "
						+ savedthreat + savedthreat.displayTile());
				if ((king.getUnitPosition() - savedthreat.getUnitPosition()) % 7 == 0) { // checks
																							// to
																							// see
																							// if
																							// bishop
																							// moving
																							// right
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// the
																					// king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 7) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // checks to see if bishop is
													// above king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 7) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				} else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 9 == 0) { // checks to see if
														// bishop moving left
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 9) { // iterate
																	// through
																	// each
																	// defendable
																	// space
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 9) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				}
				System.out.println("Checkmate! Black army is victorious!!");
				return true;
			}

			else if (savedthreat instanceof Queen) {
				System.out.println("Threatening piece is Queen: " + savedthreat
						+ savedthreat.getUnitPosition());
				// queen is moving horizontally. Its so long because it needs to
				// avoid the case where the queen is diagonally attacking the
				// king who is only 7 spaces away
				if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition()) < 7)
						|| // this is a catch to ensure that that king - threat
							// == 7 is not actually a diagonal attack
						((Math.abs(savedthreat.getUnitPosition()
								- king.getUnitPosition()) == 7) && (((savedthreat
								.getUnitPosition() % 8 == 1) && (king
								.getUnitPosition() % 8 == 0)) || ((savedthreat
								.getUnitPosition() % 8 == 0) && (king
								.getUnitPosition() % 8 == 1))))) { // queen
																	// attacking
																	// horizontally

					System.out.println("Black Queen attacking horizontally");
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // if
																					// king
																					// is
																					// to
																					// the
																					// left
																					// of
																					// threat
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 1) { // iterates
																	// through
																	// each
																	// space
																	// from
																	// threat up
																	// to king
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // king is to the right of
													// threat
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 1) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out.println("Checkmate! Black army is victorious!!");
					return true;
				}

				else if ((savedthreat.getUnitPosition() - king
						.getUnitPosition()) % 8 == 0) { // queen attacking
														// vertically
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // determines
																					// if
																					// the
																					// kings
																					// unit
																					// position
																					// is
																					// higher
																					// or
																					// lower
																					// than
																					// the
																					// threats,
																					// for
																					// setting
																					// the
																					// incrementation
																					// of
																					// the
																					// following
																					// for
																					// loop
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 8) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 8) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out
							.println("Checkmate! The Black army is victorious!!");
					return true;
				}

				else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 7 == 0) { // checks to see if
														// bishop moving right
					System.out.println("Black Queen attacking diagonally");
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// the
																					// king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 7) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // checks to see if bishop is
													// above king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 7) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				} else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 9 == 0) { // checks to see if
														// bishop moving left
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// king
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 9) { // iterate
																	// through
																	// each
																	// defendable
																	// space
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < whitepiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 9) {
								if (validMovementForCM(whitepiecesalive.get(i),
										whitepiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				}
				System.out.println("Checkmate! Black army is victorious!!");
				return true;
			}
		}

		else if (king.getPlayer() == Player.BLACK) {

			System.out.println("Threatening piece is: " + savedthreat
					+ savedthreat.displayTile());

			if (savedthreat instanceof Knight || savedthreat instanceof Pawn) {
				System.out.println("Threatening piece is Pawn or Knight: "
						+ savedthreat + savedthreat.displayTile());
				for (int i = 0; i < blackpiecesalive.size(); i++) {
					if (validMovementForCM(blackpiecesalive.get(i),
							blackpiecesalive.get(i).getUnitPosition(),
							savedthreat.getUnitPosition())) {
						return false;
					}
				}
				System.out.println("Checkmate! The White army is victorious!!");
				return true;
			} else if (savedthreat instanceof Rook) { // challenges threats from
														// Rooks
				System.out.println("Threatening piece is Rook: " + savedthreat);
				if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition()) < 8)) { // rook attacking
															// horizontally
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // if
																					// king
																					// is
																					// to
																					// the
																					// left
																					// of
																					// threat
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 1) { // iterates
																	// through
																	// each
																	// space
																	// from
																	// threat up
																	// to king
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // king is to the right of
													// threat
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 1) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out.println("Checkmate! White army is victorious!!");
					return true;
				} else if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition())) >= 8) { // rook attacking
															// vertically
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // determines
																					// if
																					// the
																					// kings
																					// unit
																					// position
																					// is
																					// higher
																					// or
																					// lower
																					// than
																					// the
																					// threats,
																					// for
																					// setting
																					// the
																					// incrementation
																					// of
																					// the
																					// following
																					// for
																					// loop
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 8) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 8) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out
							.println("Checkmate! The White army is victorious!!");
					return true;
				}
			} else if (savedthreat instanceof Bishop) {
				System.out.println("Threatening piece is Bishop: "
						+ savedthreat + savedthreat.displayTile());
				if ((king.getUnitPosition() - savedthreat.getUnitPosition()) % 7 == 0) { // checks
																							// to
																							// see
																							// if
																							// bishop
																							// moving
																							// right
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// the
																					// king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 7) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // checks to see if bishop is
													// above king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 7) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				} else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 9 == 0) { // checks to see if
														// bishop moving left
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 9) { // iterate
																	// through
																	// each
																	// defendable
																	// space
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 9) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				}
				System.out.println("Checkmate! White army is victorious!!");
				return true;
			}

			else if (savedthreat instanceof Queen) {
				System.out
						.println("Threatening piece is Queen: " + savedthreat);
				// queen is moving horizontally. Its so long because it needs to
				// avoid the case where the queen is diagonally attacking the
				// king who is only 7 spaces away
				if ((Math.abs(savedthreat.getUnitPosition()
						- king.getUnitPosition()) < 7)
						|| // this is a catch to ensure that that king - threat
							// == 7 is not actually a diagonal attack
						((Math.abs(savedthreat.getUnitPosition()
								- king.getUnitPosition()) == 7) && (((savedthreat
								.getUnitPosition() % 8 == 1) && (king
								.getUnitPosition() % 8 == 0)) || ((savedthreat
								.getUnitPosition() % 8 == 0) && (king
								.getUnitPosition() % 8 == 1))))) { // queen
																	// attacking
																	// horizontally

					System.out.println("Queen is attacking horizontally");
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // if
																					// king
																					// is
																					// to
																					// the
																					// left
																					// of
																					// threat
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 1) { // iterates
																	// through
																	// each
																	// space
																	// from
																	// threat up
																	// to king
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // king is to the right of
													// threat
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 1) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out.println("Checkmate! White army is victorious!!");
					return true;
				}

				else if ((savedthreat.getUnitPosition() - king
						.getUnitPosition()) % 8 == 0) { // queen attacking
														// vertically
					System.out.println("Queen is attacking vertically");
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // determines
																					// if
																					// the
																					// kings
																					// unit
																					// position
																					// is
																					// higher
																					// or
																					// lower
																					// than
																					// the
																					// threats,
																					// for
																					// setting
																					// the
																					// incrementation
																					// of
																					// the
																					// following
																					// for
																					// loop
						System.out.println(savedthreat
								+ "position is below king");
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 8) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterates
																			// through
																			// each
																			// living
																			// friendly
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 8) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
					System.out
							.println("Checkmate! The White army is victorious!!");
					return true;
				}

				else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 7 == 0) { // checks to see if
														// queen attacking
														// diagonally
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// the
																					// king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 7) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) { // checks to see if queen is
													// above king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 7) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				} else if ((king.getUnitPosition() - savedthreat
						.getUnitPosition()) % 9 == 0) { // checks to see queen
														// attacking diagonally
					if (savedthreat.getUnitPosition() > king.getUnitPosition()) { // checks
																					// to
																					// see
																					// if
																					// bishop
																					// is
																					// below
																					// king
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j > king
									.getUnitPosition(); j -= 9) { // iterate
																	// through
																	// each
																	// defendable
																	// space
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					} else if (savedthreat.getUnitPosition() < king
							.getUnitPosition()) {
						for (int i = 0; i < blackpiecesalive.size(); i++) { // iterate
																			// through
																			// each
																			// white
																			// piece
							for (int j = savedthreat.getUnitPosition(); j < king
									.getUnitPosition(); j += 9) {
								if (validMovementForCM(blackpiecesalive.get(i),
										blackpiecesalive.get(i)
												.getUnitPosition(), j)) {
									return false;
								}
							}
						}
					}
				} else {
					System.out
							.println("Did not find attack direction. threatposition: "
									+ savedthreat.getUnitPosition());
				}
				System.out.println("Checkmate! White army is victorious!!");
				return true;
			}
		}

		System.out.println("ERROR: isCheckmate didn't find the king's color");
		return true;
	}

	// Determines the current player. Then checks every opposing living piece to
	// see if the current player's king is a valid target for possibleMovement
	// and isBlocked
	public static boolean isInCheck() {
		if (Board.currentplayer == Player.WHITE) {
			for (int i = 0; i < blackpiecesalive.size(); i++) { // iterates through the List of remaining black pieces
				if (blackpiecesalive.get(i).possibleMovement(
						blackpiecesalive.get(i).getUnitPosition(),
						Board.whitekingposition)) { // runs possiblemovement on
													// the black piece, with the
													// destination being the
													// white king's position
					if (!blackpiecesalive.get(i).isBlocked(blackpiecesalive.get(i),
							blackpiecesalive.get(i).getUnitPosition(),
							whitekingposition)) { // runs isBlocked on the black
													// piece
						System.out.println("Your King is in CHECK via "
								+ blackpiecesalive.get(i).toString());
						Board.threat = blackpiecesalive.get(i);
						return true;
					}
				}
			}
			return false;
		} else if (Board.currentplayer == Player.BLACK) {
			for (int i = 0; i < whitepiecesalive.size(); i++) {
				if (whitepiecesalive.get(i).possibleMovement(
						whitepiecesalive.get(i).getUnitPosition(),
						Board.blackkingposition)) {
					if (!whitepiecesalive.get(i).isBlocked(whitepiecesalive.get(i),
							whitepiecesalive.get(i).getUnitPosition(),
							Board.blackkingposition)) {
						System.out.println("Your King is in CHECK via "
								+ whitepiecesalive.get(i).toString());
						Board.threat = whitepiecesalive.get(i);
						return true;
					}
				}
			}
			return false;
		}
		System.out.println("ERROR: isInCheck did not match a player color");
		return true;
	}

	/*
	public static Tile promotePawn(Tile promotedpiece) {

		String pawnpromotion;
		Scanner pawninput = new Scanner(System.in);
		System.out.print("Well done pawn! What would you like to be promoted to (valid input: r, n, b, q): ");
		pawnpromotion = pawninput.next();
		while (!pawnpromotion.equals("r") && !pawnpromotion.equals("n")
				&& !pawnpromotion.equals("b") && !pawnpromotion.equals("q")) {
			System.out.println("You entered: " + pawnpromotion);
			System.out.println("INVALID input. Try again. Your choices are: r, n, b, q: ");
			pawnpromotion = pawninput.next();
		}

		if (Board.currentplayer == Player.BLACK) {
			blackpiecesalive.remove(promotedpiece);
		}

		else if (Board.currentplayer == Player.WHITE) {
			whitepiecesalive.remove(promotedpiece);
		}

		if (pawnpromotion.equals("r")) {
			promotedpiece = new Rook(promotedpiece.getPlayer(),
					promotedpiece.getUnitNumber(),
					promotedpiece.getUnitPosition());
			if (Board.currentplayer == Player.BLACK) {
				blackpiecesalive.add(promotedpiece);
				System.out.println(blackpiecesalive.toString());
			} else if (Board.currentplayer == Player.WHITE) {
				whitepiecesalive.add(promotedpiece);
				System.out.println(whitepiecesalive.toString());
			}
			return promotedpiece;
		}

		else if (pawnpromotion.equals("n")) {
			promotedpiece = new Knight(promotedpiece.getPlayer(),
					promotedpiece.getUnitNumber(),
					promotedpiece.getUnitPosition());
			if (Board.currentplayer == Player.BLACK) {
				blackpiecesalive.add(promotedpiece);
				System.out.println(blackpiecesalive.toString());
			} else if (Board.currentplayer == Player.WHITE) {
				whitepiecesalive.add(promotedpiece);
				System.out.println(whitepiecesalive.toString());
			}
			return promotedpiece;
		}

		else if (pawnpromotion.equals("b")) {
			promotedpiece = new Bishop(promotedpiece.getPlayer(),
					promotedpiece.getUnitNumber(),
					promotedpiece.getUnitPosition());
			if (Board.currentplayer == Player.BLACK) {
				blackpiecesalive.add(promotedpiece);
				System.out.println(blackpiecesalive.toString());
			} else if (Board.currentplayer == Player.WHITE) {
				whitepiecesalive.add(promotedpiece);
				System.out.println(whitepiecesalive.toString());
			}
			return promotedpiece;
		}

		else if (pawnpromotion.equals("q")) {
			promotedpiece = new Queen(promotedpiece.getPlayer(),
					promotedpiece.getUnitNumber(),
					promotedpiece.getUnitPosition());
			if (Board.currentplayer == Player.BLACK) {
				blackpiecesalive.add(promotedpiece);
				System.out.println(blackpiecesalive.toString());
			} else if (Board.currentplayer == Player.WHITE) {
				whitepiecesalive.add(promotedpiece);
				System.out.println(whitepiecesalive.toString());
			}
			return promotedpiece;
		}

		System.out.println("ERROR: Pawn promotion did not find a piece");
		return promotedpiece;

	}
	*/

	// checks for the Player of the current piece, then sets currentplayer to
	// opposing faction
	public static void switchPlayer(Tile piece) {
		if (piece.getPlayer() == Player.WHITE) {
			Board.currentplayer = Player.BLACK;
		} else if (piece.getPlayer() == Player.BLACK) {
			Board.currentplayer = Player.WHITE;
		} else {
			System.out
					.println("ERROR: currentplayer was neither white or black");
		}
	}

	// Sets the variables to note that a piece (Rook, King, or Pawn) has moved.
	// Used for en pessant and castling
	public static void updateHasMoved(Tile piece) {

		if ((piece instanceof Rook) || (piece instanceof Pawn)
				|| (piece instanceof King)) {
			piece.setHasMoved();
		}
	}

	// searches through each tile of the board to find the starting and
	// destination tiles. Inserts the "moving" piece into starting. Inserts the
	// saved Board.temp piece into destination. Reverts kingpositions
	public static void undoMovement(Tile piece,
			int starting, int destination) {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (board[i][j].getUnitPosition() == starting) {
					board[i][j] = piece;
				}

				else if (board[i][j].getUnitPosition() == destination) {
					board[i][j] = Board.temp;
					board[i][j].setUnitPosition(destination);
				}
			}
		}
		piece.setUnitPosition(starting);

		// undoes kingposition
		if (piece instanceof King) {
			if (piece.getPlayer() == Player.WHITE) {
				Board.whitekingposition = starting;
			} else if (piece.getPlayer() == Player.BLACK) {
				Board.blackkingposition = starting;
			}
		}
	}

	// Inserts the moving piece into its destination slot in the gameboard and
	// inserts an emptytile into the previous position. Also updates
	// kingpositions if applicable.
	public static void updateBoard(Tile piece, int starting, int destination) {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				// checks to find the Tile at the starting position. When found,
				// inserts a new EmpytTile.
				if (board[i][j].getUnitPosition() == starting) {
					board[i][j] = new EmptyTile(Player.EMPTY, 1, starting);
				}
				// checks to find the Tile at the ending position. When found,
				// inserts the moving piece.
				if (board[i][j].getUnitPosition() == destination) {
					// stores the tile at the destination, to be reintegrated if
					// the move is undone
					Board.temp = Board.getTile(destination);
					// sets the dead units position to 0, to prevent bugs
					board[i][j].setUnitPosition(0);
					// sets the tile at the destination to the attacking piece
					board[i][j] = piece;
				}
			}
		}

		// updates King position
		if (piece instanceof King) {
			if (piece.getPlayer() == Player.WHITE) {
				Board.whitekingposition = destination;
			} else if (piece.getPlayer() == Player.BLACK) {
				Board.blackkingposition = destination;
			}
		}

		piece.setUnitPosition(destination); // updates the moving piece's
											// unitposition to its new position
	}

	// Updates killed/alive arrays if any pieces were killed
	public static void updateArrays() {

		if (Board.temp.isPiece() == true) { // checks to see if the destination tile (temp) was filled
			if (Board.currentplayer == Player.WHITE) {
				blackpiecesalive.remove(Board.temp);
				blackpiecesdead.add(Board.temp);
			} else if (Board.currentplayer == Player.BLACK) {
				whitepiecesalive.remove(Board.temp);
				whitepiecesdead.add(Board.temp);
			}
		}
	}

	// Verifies the move is possible, unblocked, not moving into check.
	// Otherwise returns false. If true, updates board.
	public static boolean validMovement(Tile piece, int starting, int destination) {

		// Checks to make sure the correct army is selected
		if (piece.getPlayer() != Board.currentplayer) {
			System.out.println("Wrong army. The current move belongs to: " + Board.currentplayer);
			return false;
		}

		// runs a check to make sure movement is possible, unblocked, and not
		// moving into check. Promotes pawn as well
		if (piece.possibleMovement(piece.getUnitPosition(), destination)) { // makes sure the movement is possible depending on the piece type
			if (!piece.isBlocked(piece, piece.getUnitPosition(), destination)) { // makes sure movement isnt blocked
				updateBoard(piece, piece.getUnitPosition(), destination); // updates board with new movement, to run isInCheck
				if (Board.isInCheck()) { // prevents moving into check
					undoMovement(piece, starting, destination); // undoes the boardUpdate if check was found														
					return false; // rejects the movement if check was found
				}
				
				
				return true;
			}
		}
		return false;
	}

	// Same as validMovement, but it undoes the movement. Used for running moves
	// in isCheckmate.
	public static boolean validMovementForCM(Tile piece,
			int starting, int destination) {

		if (piece.getPlayer() != Board.currentplayer) {
			System.out.println("Wrong army. The current move belongs to: "
					+ Board.currentplayer);
			return false;
		}

		if (piece.possibleMovement(piece.getUnitPosition(), destination)) {
			if (!piece.isBlocked(piece, piece.getUnitPosition(),
					destination)) {
				updateBoard(piece, piece.getUnitPosition(),
						destination);
				if (Board.isInCheck()) { // prevents moving into check
					undoMovement(piece, starting, destination);
					return false;
				}
				undoMovement(piece, starting, destination);
				return true;
			}
		}
		return false;
	}

	// Finds the current king. To be a parameter in isCheckmate
	public static Tile getKing() {
		if (Board.currentplayer == Player.WHITE) {
			return Board.getTile(Board.whitekingposition);
		} else if (Board.currentplayer == Player.BLACK) {
			return Board.getTile(Board.blackkingposition);
		}
		System.out.println("ERROR: getKing couldn't find a currentplayer!");
		return Board.getTile(65);
	}

	// iterates through the gameboard to return the tile at a specified location
	public static Tile getTile(int location) {

		if (location > 0 && location < 65) {
			for (int i = 0; i <= 7; i++) { // iterates through rows
				for (int j = 0; j <= 7; j++) { // iterates through columns
					if (board[i][j].getUnitPosition() == location) { // checks
																			// to
																			// see
																			// if
																			// current
																			// space
																			// is
																			// the
																			// location
																			// to
																			// be
																			// found
						return board[i][j];
					}
				}
			}
		}

		// Returns an empty tile with location 100, hopefully unreachable
		return new EmptyTile(Board.currentplayer, 0, 100);

	}
	
	public String getOpposingPlayer(Player currentplayer){
		if (currentplayer.equals(Player.WHITE)){
			return "BLACK";
		}
		else {
			return "WHITE";
		}

	}
	
	public boolean checkForPawnPromotion(Tile piece){
		
		if (piece instanceof Pawn && ((piece.getUnitPosition() > 0 && piece.getUnitPosition() < 9) || (piece.getUnitPosition() > 56 && piece.getUnitPosition() < 65))) { // checks for pawn promotion
			return true;

		}
		else{
			return false;
		}
		
	}
	
	//called by the EventHandler in controller when a choice is selected
	public static void promotePawn(char choice, int starting, int destination){
 
		Tile pawnforpromotion = getTile(destination);
		
		if (Board.currentplayer == Player.BLACK) {
			blackpiecesalive.remove(pawnforpromotion);
		}

		else if (Board.currentplayer == Player.WHITE) {
			whitepiecesalive.remove(pawnforpromotion);
		}
		
		switch(choice){
			case 'Q':
				pawnforpromotion = new Queen(pawnforpromotion.getPlayer(), pawnforpromotion.getUnitNumber(), pawnforpromotion.getUnitPosition());
				
				if (Board.currentplayer == Player.BLACK) {
					blackpiecesalive.add(pawnforpromotion);
					System.out.println(blackpiecesalive.toString());
				} else if (Board.currentplayer == Player.WHITE) {
					whitepiecesalive.add(pawnforpromotion);
					System.out.println(whitepiecesalive.toString());
				}
				break;
				
			case 'R':
				pawnforpromotion = new Rook(pawnforpromotion.getPlayer(), pawnforpromotion.getUnitNumber(), pawnforpromotion.getUnitPosition());
				
				if (Board.currentplayer == Player.BLACK) {
					blackpiecesalive.add(pawnforpromotion);
					System.out.println(blackpiecesalive.toString());
				} else if (Board.currentplayer == Player.WHITE) {
					whitepiecesalive.add(pawnforpromotion);
					System.out.println(whitepiecesalive.toString());
				}
				break;
				
			case 'B':
				pawnforpromotion = new Bishop(pawnforpromotion.getPlayer(), pawnforpromotion.getUnitNumber(), pawnforpromotion.getUnitPosition());
				
				if (Board.currentplayer == Player.BLACK) {
					blackpiecesalive.add(pawnforpromotion);
					System.out.println(blackpiecesalive.toString());
				} else if (Board.currentplayer == Player.WHITE) {
					whitepiecesalive.add(pawnforpromotion);
					System.out.println(whitepiecesalive.toString());
				}
				break;
				
			case 'K':
				pawnforpromotion = new Knight(pawnforpromotion.getPlayer(), pawnforpromotion.getUnitNumber(), pawnforpromotion.getUnitPosition());
				
				if (Board.currentplayer == Player.BLACK) {
					blackpiecesalive.add(pawnforpromotion);
					System.out.println(blackpiecesalive.toString());
				} else if (Board.currentplayer == Player.WHITE) {
					whitepiecesalive.add(pawnforpromotion);
					System.out.println(whitepiecesalive.toString());
				}
				break;
				
			default:
				System.out.println("Default");
				break;
		}
		
		updateBoard(pawnforpromotion, starting, destination); // updates the board with new promoted piece

		

		
	}
	


}