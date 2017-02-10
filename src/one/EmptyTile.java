package one;

import one.Tile.Player;

public class EmptyTile extends Tile {

	int unitnumber;
	int unitposition;
	Player player;

	public EmptyTile(Player player, int unitnumber, int unitposition) {
		this.unitnumber = unitnumber;
		this.unitposition = unitposition;
		this.player = player;
	}

	public boolean isBlocked(Tile piece, int starting,
			int destination) {
		return false;
	}

	public boolean isPiece() {
		return false;
	}

	public String displayTile() {
		return "- ";
	}

	public void setHasMoved() {
		System.out
				.println("ERROR: This function (setHasMoved) should not be called on this piece");
	}

	public boolean possibleMovement(int starting, int destination) {
		return false;
	}

	public String toString() {
		return "Rook" + this.getUnitNumber();
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
