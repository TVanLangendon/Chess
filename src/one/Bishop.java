package one;

import one.Tile.Player;

//import package1.Tile.Player;

public class Bishop extends Tile {

	Player player;
	int unitnumber;
	int unitposition;
	String movementdirection;

	public Bishop(Player player, int unitnumber, int unitposition) {
		this.player = player;
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
	}

	public String displayTile() {

		switch (player) {
		case BLACK:
			return "b ";

		case WHITE:
			return "B ";

		default:
			return "F";
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

		// searches for down-left blocked moves
		if (this.movementdirection == "down-left") {
			for (int i = starting + 7; i < destination; i += 7) {
				if (Board.getTile(i).isPiece() == true) {
					return true;
				}
			}
			return false;
		}

		// searches for down-right blocked moves
		else if (this.movementdirection == "down-right") {
			for (int i = starting + 9; i < destination; i += 9) {
				if (Board.getTile(i).isPiece() == true) {
					return true;
				}
			}
			return false;
		}

		// searches for up-left blocked moves
		else if (this.movementdirection == "up-left") {
			for (int i = starting - 9; i > destination; i -= 9) {
				if (Board.getTile(i).isPiece() == true) {
					return true;
				}
			}
			return false;
		}

		else if (this.movementdirection == "up-right") {
			for (int i = starting - 7; i > destination; i -= 7) {
				if (Board.getTile(i).isPiece() == true) {
					return true;
				}
			}
			return false;
		}

		System.out
				.println("ERROR: bishop isblocked did not find a movement direction!");
		return true;
	}

	public boolean isPiece() {
		return true;
	}

	public boolean possibleMovement(int starting, int destination) {

		// checking for possible movements downward
		if (destination > starting) {
			for (int i = starting; i <= 64; i += 7) { // searches down-left
				if (i % 8 == 1) { // checks to see if it encountered the edge
					if (i == destination) {
						this.movementdirection = "down-left";
						return true; // returns true if the destination is on
										// the edge
					}
					break; // breaks the for-loop if it found the edge, but not
							// the destination
				} else if (i == destination) { // returns true if it found the
												// destination (before finding
												// edge)
					this.movementdirection = "down-left";
					return true;
				}
			}
			for (int i = starting; i <= 64; i += 9) { // searches down-right
				if (i % 8 == 0) { // checks to see if it encountered the edge
					if (i == destination) { // if it found the destination at
											// the edge, return true
						this.movementdirection = "down-right";
						return true;
					}
					break; // breaks out if it hit the edge, but not the
							// destination
				} else if (i == destination) { // returns true if it found the
												// destination (before finding
												// edge)
					this.movementdirection = "down-right";
					return true;
				}
			}
			return false; // returns false if it didnt find the destination for
							// down-ward movement
		}

		// searches for upward movement
		else if (destination < starting) {
			for (int i = starting; i <= 64; i -= 9) { // searches up-left
				if (i % 8 == 1) { // checks to see if it encountered the edge
					if (i == destination) {
						this.movementdirection = "up-left";
						return true; // returns true if the destination is on
										// the edge
					}
					break; // breaks the for-loop if it found the edge, but not
							// the destination
				} else if (i == destination) { // returns true if it found the
												// destination (before finding
												// edge)
					this.movementdirection = "up-left";
					return true;
				}
			}
			for (int i = starting; i <= 64; i -= 7) { // searches up-right
				if (i % 8 == 0) { // checks to see if it encountered the edge
					if (i == destination) { // if it found the destination at
											// the edge, return true
						this.movementdirection = "up-right";
						return true;
					}
					break; // breaks out if it hit the edge, but not the
							// destination
				} else if (i == destination) { // returns true if it found the
												// destination (before finding
												// edge)
					this.movementdirection = "up-right";
					return true;
				}
			}
			return false; // returns false if it didn't find the destination for
							// down-ward movement
		}

		return false;
	}

	public String toString() {
		return "Bishop" + this.getPlayer();
	}

	public void setHasMoved() {
		System.out
				.println("ERROR: This function (setHasMoved) should not be called on this piece");
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

}