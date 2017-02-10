package one;

import one.Tile.Player;

public class Knight extends Tile {

	Player player;
	int unitnumber;
	int unitposition;

	public Knight(Player player, int unitnumber, int unitposition) {
		this.player = player;
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
	}

	public String displayTile() {

		switch (player) {
		case BLACK:
			return "n ";

		case WHITE:
			return "N ";

		default:
			return "F";
		}

	}

	public boolean isBlocked(Tile piece, int starting,
			int destination) {
		// returns true if the destination is a friendly target
		if (Board.getTile(destination).getPlayer() == piece
				.getPlayer()) {
			return true;
		}
		return false;
	}

	public boolean isPiece() {
		return true;
	}

	public boolean possibleMovement(int starting, int destination) {

		// checks for possible movements on the right-hand side of the map
		if (starting % 8 == 0 || starting % 8 == 7) {
			if (destination == starting + 6 || destination == starting - 10
					|| destination == starting + 15
					|| destination == starting - 17) {
				return true;
			}
			// checks the 2 additional locations if the knight is one space from
			// the edge
			else if ((starting % 8 == 7 && destination == starting - 15)
					|| (starting % 8 == 7 && destination == starting + 17)) {
				return true;
			}
			return false;
		}

		// checks the possible movements on the left-hand side of the map
		else if (starting % 8 == 1 || starting % 8 == 2) {
			if (destination == starting - 6 || destination == starting + 10
					|| destination == starting - 15
					|| destination == starting + 17) {
				return true;
			}
			// checks the two additional spaces if the knight is one column over
			// from the left-hand side of map
			else if ((starting % 8 == 2 && destination == starting - 17)
					|| (starting % 8 == 2 && destination == starting + 15)) {
				return true;
			}
			return false;
		}
		// checks the 8 possible locations a knight could move if hes not near
		// the edge of the map
		else if (starting % 8 > 2 && starting % 8 < 7) {
			if (destination == starting + 6 || destination == starting - 6
					|| destination == starting + 10
					|| destination == starting - 10
					|| destination == starting + 15
					|| destination == starting - 15
					|| destination == starting + 17
					|| destination == starting - 17) {
				return true;
			}
			return false;
		}
		return true;
	}

	public String toString() {
		return "Knight" + this.getPlayer();
	}

	public void setHasMoved() {
		System.out
				.println("ERROR: This function (setHasMoved) should not be called on this piece");
	}

	public Player getPlayer() {
		return this.player;
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

}
