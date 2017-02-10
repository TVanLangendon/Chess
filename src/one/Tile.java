package one;

public abstract class Tile extends Object {

	public enum Player {
		BLACK, WHITE, EMPTY
	}

	public String movementdirection;

	public abstract String displayTile();

	public abstract boolean isBlocked(Tile piece,
			int starting, int destination);

	public abstract boolean isPiece();

	public abstract boolean possibleMovement(int starting, int destination);

	public abstract void setHasMoved();

	public abstract Player getPlayer();

	public abstract int getUnitPosition();

	public abstract void setUnitPosition(int position);

	public abstract int getUnitNumber();

	// public abstract int getUnitNumber();

}