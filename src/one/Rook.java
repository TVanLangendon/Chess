package one;

//import one.Tile.Player;

public class Rook extends Tile {

	Player player;
	int unitnumber;
	int unitposition;
	int hasmoved;
	String movementdirection;

	public Rook(Player player, int unitnumber, int unitposition) {
		this.player = player;
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
	}

	public Rook(Player player, int unitnumber) {
		this.player = player;
		this.unitnumber = unitnumber;
	}

	public String displayTile() {

		switch (player) {
		case BLACK:
			return "r ";

		case WHITE:
			return "R ";

		default:
			return "F";
		}
	}

	public boolean isPiece() {
		return true;
	}

	public boolean isBlocked(Tile piece, int starting,
			int destination) {

		// checks to make sure the piece at the destination is not a friendly
		// piece
		if (Board.getTile(destination).getPlayer() == piece
				.getPlayer()) {
			return true;
		}

		// checks if the a horizontal movement is being blocked
		if (this.movementdirection == "horizontal") {
			// searches for pieces to the right of the piece
			if (starting < destination) {
				for (int i = starting + 1; i < destination; i++) {
					if (Board.getTile(i).isPiece() == true) {
						return true;
					}
				}
				return false;
			}
			// searches for pieces to the left of the piece
			else if (starting > destination) {
				for (int i = starting - 1; i > destination; i--) {
					if (Board.getTile(i).isPiece() == true) {
						return true;
					}
				}
				return false;
			}
		}

		// Checks if vertical movement is blocked
		else if (this.movementdirection == "vertical") {
			// searches for pieces below itself
			if (starting < destination) {
				for (int i = starting + 8; i < destination; i += 8) {
					if (Board.getTile(i).isPiece() == true) {
						return true;
					}
				}
				return false;
			}

			// searches for pieces above itself
			else if (starting > destination) {
				for (int i = starting - 8; i > destination; i -= 8) {
					if (Board.getTile(i).isPiece() == true) {
						return true;
					}
				}
				return false;
			}
		}

		System.out
				.println("ERROR: Rook isblocked did not find a movement direction!");
		;
		return true;
	}

	// return true if the movement would be possible on an empty board. To be
	// called by validMovement()
	public boolean possibleMovement(int starting, int destination) {

		// check for possible vertical movements
		if (Math.abs(destination - starting) % 8 == 0) {
			this.movementdirection = "vertical";
			return true;
		}

		// check for possible horizontal movements
		// needs a separate statement if the piece is on the far right of the
		// row (% 8 == 0)

		if (starting % 8 == 0) {
			if (destination <= starting && // right-hand edge of map
					destination > (starting - 8)) { // left-hand edge of map
				// System.out.println("Horizontal movement");
				this.movementdirection = "horizontal";
				return true;
			}
		} else if (starting % 8 != 0) {
			if (destination <= (starting - starting % 8 + 8) && // right-hand
																// edge of map
					destination > (starting - starting % 8)) { // left-hand edge
																// of map
				// System.out.println("Horizontal movement");
				this.movementdirection = "horizontal";
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "Rook" + this.getPlayer();
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
