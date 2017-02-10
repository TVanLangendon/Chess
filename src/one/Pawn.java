package one;

import one.Tile.Player;

public class Pawn extends Tile {

	Player player;
	int unitnumber;
	int unitposition;
	int hasmoved;
	int isattacking;

	public Pawn(Player player, int unitnumber, int unitposition) {
		this.player = player;
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
	}

	public Pawn(int unitnumber) {
		this.unitnumber = unitnumber;
	}

	public boolean isPiece() {
		return true;
	}

	public String displayTile() {

		switch (player) {
		case BLACK:
			return "p ";

		case WHITE:
			return "P ";

		default:
			return "Error";
		}
	}

	public boolean isBlocked(Tile piece, int starting,
			int destination) {

		// checks to make sure the piece at the destination is not a friendly
		// piece
		if (Board.getTile(destination).getPlayer() == piece
				.getPlayer()) {
			return true;
		}

		// checking for blockage of black pawns
		if (piece.getPlayer() == Player.BLACK) {

			// checking blockage of pawns that are attempting to move one space
			// down
			if (destination == starting + 8) {
				if (Board.getTile(destination).isPiece() == true) {
					System.out
							.println("The movement (1 space down) is blocked");
					return true;
				}
			}

			// checking blockage of pawns that are attempting to move two spaces
			// down
			else if (destination == starting + 16) {
				if (Board.getTile(destination - 8).isPiece() == true) {
					System.out
							.println("The space in between current and destination is blocked");
					return true;
				} else if (Board.getTile(destination).isPiece() == true) {
					System.out
							.println("The destination (2 space down) is blocked");
					return true;
				}
			}

			// checking blockage of attacking pawns
			else if (this.isattacking == 1) {
				// checks to make sure the space to be attacked is occupied
				if (Board.getTile(destination).isPiece() == false) {
					System.out
							.println("Pawns must attack an opponent to move diagonally");
					return true;
				}
				if (Board.getTile(destination).getPlayer() == piece
						.getPlayer()) {
					System.out
							.println("Friendly fire is prohibited, especially by pawns!");
					return true;
				}
			}
			return false;
		}

		// checking for blockage of White pawns
		else if (piece.getPlayer() == Player.WHITE) {

			// checking blockage of pawns that are attempting to move one space
			// up
			if (destination == starting - 8) {
				if (Board.getTile(destination).isPiece() == true) {
					System.out
							.println("The movement (1 space down) is blocked");
					return true;
				}
			}

			// checking blockage of pawns that are attempting to move two spaces
			// up
			else if (destination == starting - 16) {
				if (Board.getTile(destination + 8).isPiece() == true) {
					System.out
							.println("The space in between current and destination is blocked");
					return true;
				} else if (Board.getTile(destination).isPiece() == true) {
					System.out
							.println("The destination (2 space down) is blocked");
					return true;
				}
			}

			// checking blockage of attacking pawns
			else if (this.isattacking == 1) {

				// checks to make sure the space to be attacked is occupied
				if (Board.getTile(destination).isPiece() == false) {
					// System.out.println("Pawns must attack an opponent to move diagonally");
					return true;
				}

				if (Board.getTile(destination).getPlayer() == piece
						.getPlayer()) {
					// System.out.println("Friendly fire is prohibited, especially by pawns!");
					return true;
				}
			}
			return false;
		}
		System.out.println("The piece was not black or white");
		return false;
	}

	public boolean possibleMovement(int starting, int destination) {

		// resets attacking value to notattack
		this.isattacking = 0;

		// checks for movement of black pawns
		if (this.player == Player.BLACK) {

			// non-attacking movement if its the pawn's first move
			if (this.hasmoved == 0) {

				// checks if the pawn is moving straight (not attacking) either
				// one or two spaces
				for (int i = starting; i <= starting + 16; i += 8) {
					if (i == destination) {
						return true;
					}
				}
			}

			// non-attacking movement if the pawn has previously moved
			else if (this.hasmoved == 1) {

				// checks to see if piece is moving one space down
				if (starting + 8 == destination) {
					return true;
				}
			}

			// the following IF statements check for diagonal movement and sets
			// attacking value

			// checks to make sure the pawn is not on either edge of the board
			if (starting % 8 != 0 && starting % 8 != 1) {
				// checks both diagonal spaces
				if (starting + 7 == destination || starting + 9 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}

			// checks if the pawn is on the right edge of the board
			else if (starting % 8 == 0) {
				// checks diagonal down-left
				if (starting + 7 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}

			// checks if pawn is on the left edge of the board
			else if (starting % 8 == 1) {
				// checks diagonal down-right
				if (starting + 9 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}
			return false;
		}

		else if (this.player == Player.WHITE) {

			// checks for valid destination if this is the pawn's first move
			if (this.hasmoved == 0) {
				for (int i = starting; i >= starting - 16; i -= 8) {
					if (i == destination) {
						return true;
					}
				}
			}

			// checks for valid destination if the pawn previously moved
			else if (this.hasmoved == 1) {
				if (starting - 8 == destination) {
					return true;
				}
			}

			// the following IF statements check for diagonal movement and sets
			// attacking value

			// checks to make sure the pawn is not on either edge of the board
			if (starting % 8 != 0 && starting % 8 != 1) {
				// checks both diagonal spaces
				if (starting - 7 == destination || starting - 9 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}
			// checks if the pawn is on the right edge of the board
			else if (starting % 8 == 0) {
				// checks diagonal upper-left
				if (starting - 9 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}

			// checks if pawn is on the left edge of the board
			else if (starting % 8 == 1) {
				// checks diagonal upper-right
				if (starting - 7 == destination) {
					// System.out.println("The pawn is attacking");
					this.isattacking = 1;
					return true;
				}
			}
			return false;
		}

		return false;
	}

	public String toString() {
		return "Pawn" + this.getUnitNumber();
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

}
