package one;

import one.Tile.Player;

//import one.Tile.Player;

public class King extends Tile {

	Player player;
	int unitnumber;
	int unitposition;
	int hasmoved;
	int castling;
	int kingposition;

	public King(Player player, int unitnumber, int unitposition) {
		this.player = player;
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
	}

	public Boolean canCastle(int destination) {

		if (getNearestRook(destination).hasmoved == 0 && this.hasmoved == 0) {
			return true;
		}

		return false;
	}

	public String displayTile() {

		switch (player) {
		case BLACK:
			return "k ";

		case WHITE:
			return "K ";

		default:
			return "F";
		}

	}

	public boolean isBlocked(Tile piece, int starting,
			int destination) {

		// Makes sure destination is not a occupied by a friendly
		if (Board.getTile(destination).getPlayer() == piece
				.getPlayer()) {
			return true;
		}

		// Checks for blockages/incheck if castling. ALSO executes the movement
		// of the rook. This is possible because isBlocked also checks for
		// inCheck
		else if (this.castling == 1) {
			int tempwhite = Board.whitekingposition; // saves white king
														// position to be reset
														// after checking for
														// castling moves
			int tempblack = Board.blackkingposition; // saves black king
														// position to be reset
														// after checking for
														// castling moves

			// Makes sure you cant castle out of check
			if (Board.isInCheck() == true) {
				System.out.println("You cannot castle out of check");
				return true;
			}

			// checks for king moving to his right
			if (starting < destination) {
				if (this.getPlayer() == Player.WHITE) {
					for (int i = starting + 1; i <= destination; i++) {
						Board.whitekingposition += 1;
						if (Board.isInCheck() == true) {
							Board.whitekingposition = tempwhite;
							System.out
									.println("Castling is blocked by a potential 'check'");
							return true;
						}
						if (Board.getTile(Board.whitekingposition)
								.isPiece() == true) {
							Board.whitekingposition = tempwhite;
							System.out
									.println("Castling is blocked by a piece");
							return true;
						}
					}
					Board.whitekingposition = tempwhite;
					Board.board[7][5] = Board.rookkingwhite;
					Board.board[7][7] = new EmptyTile(Player.EMPTY, 0, 64);
					Board.rookkingwhite.setUnitPosition(62);
					return false;
				} else if (this.getPlayer() == Player.BLACK) {
					for (int i = starting + 1; i <= destination; i++) {
						Board.blackkingposition += 1;
						if (Board.isInCheck() == true) {
							Board.blackkingposition = tempblack;
							System.out
									.println("Castling is blocked by a potential 'check'");
							return true;
						}
						if (Board.getTile(Board.blackkingposition)
								.isPiece() == true) {
							Board.blackkingposition = tempblack;
							System.out
									.println("Castling is blocked by a piece");
							return true;
						}
					}
					Board.blackkingposition = tempblack;
					Board.board[0][5] = Board.rookkingblack;
					Board.board[0][7] = new EmptyTile(Player.EMPTY, 0, 8);
					Board.rookkingblack.setUnitPosition(6);
					return false;
				}
			}

			// checks for king moving to his left
			else if (starting > destination) {
				if (this.getPlayer() == Player.WHITE) {
					for (int i = starting - 1; i >= destination; i -= 1) {
						Board.whitekingposition -= 1;
						if (Board.isInCheck() == true) {
							Board.whitekingposition = tempwhite;
							System.out
									.println("Castling is blocked by a potential 'check'");
							return true;
						}
						if (Board.getTile(Board.whitekingposition)
								.isPiece() == true) {
							Board.whitekingposition = tempwhite;
							System.out
									.println("Castling is blocked by a piece");
							return true;
						}
					}
					Board.whitekingposition = tempwhite;
					Board.board[7][3] = Board.rookqueenwhite;
					Board.board[7][0] = new EmptyTile(Player.EMPTY, 0, 57);
					Board.rookqueenwhite.setUnitPosition(60);
					return false;
				} else if (this.getPlayer() == Player.BLACK) {
					for (int i = starting - 1; i >= destination; i -= 1) {
						Board.blackkingposition -= 1;
						if (Board.isInCheck() == true) {
							Board.blackkingposition = tempblack;
							System.out
									.println("Castling is blocked by a potential 'check'");
							return true;
						}
						if (Board.getTile(Board.blackkingposition)
								.isPiece() == true) {
							Board.blackkingposition = tempblack;
							System.out
									.println("Castling is blocked by a piece");
							return true;
						}
					}
					Board.blackkingposition = tempblack;
					Board.board[0][3] = Board.rookqueenblack;
					Board.board[0][0] = new EmptyTile(Player.EMPTY, 0, 1);
					Board.rookqueenblack.setUnitPosition(4);
					return false;
				}
			}
		}
		return false;
	}

	public boolean isPiece() {
		return true;
	}

	public boolean possibleMovement(int starting, int destination) {

		this.castling = 0; // resets the check for castling

		// checks for castling
		if ((starting == 5 && (destination == 3 || destination == 7))
				|| (starting == 61 && (destination == 59 || destination == 63))) {
			if (this.canCastle(destination) == true) {
				this.castling = 1;
				return true;
			}
			return false;
		} else if (starting % 8 != 0 && starting % 8 != 1) { // makes sure king
																// is not in
																// column "a" or
																// "h"
			if (destination == starting + 9 || destination == starting + 8
					|| destination == starting + 7
					|| destination == starting + 1
					|| destination == starting - 1
					|| destination == starting - 9
					|| destination == starting - 8
					|| destination == starting - 7) {
				return true;
			}
			return false;
		} else if (starting % 8 == 1) { // checks for king in column "a"
			if (destination == starting + 8 || destination == starting + 9
					|| destination == starting + 1
					|| destination == starting - 7
					|| destination == starting - 8) {
				return true;
			}
			return false;
		} else if (starting % 8 == 0) { // checks for king in column "h"
			if (destination == starting + 8 || destination == starting + 7
					|| destination == starting - 1
					|| destination == starting - 8
					|| destination == starting - 9) {
				return true;
			}
			return false;
		}

		else if (canCastle(destination))
			System.out
					.println("ERROR: King did not find its column for movement");
		return false;
	}

	public String toString() {
		return "King" + this.getPlayer();
	}

	public Rook getNearestRook(int destination) {
		if (destination == 63) {
			return Board.rookkingwhite;
		} else if (destination == 59) {
			return Board.rookqueenwhite;
		} else if (destination == 7) {
			return Board.rookkingblack;
		} else if (destination == 3) {
			return Board.rookqueenblack;
		} else {
			System.out.println("ERROR: getNearestRook could not find a rook!");
			return Board.rookcantcastle;
		}
	}

	public int getUnitNumber() {
		return this.unitnumber;
	}

	public int getUnitPosition() {
		return this.unitposition;
	}

	public void setUnitPosition(int position) {
		this.unitposition = position;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setHasMoved() {
		this.hasmoved = 1;
	}
	
	public int getCastling(){
		return this.castling;
	}

}
