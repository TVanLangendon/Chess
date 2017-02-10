package two;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

	public Tile(boolean white, int x, int y) {
		setWidth(UserInterface.TILE_SIZE);
		setHeight(UserInterface.TILE_SIZE);

		setFill(white ? Color.rgb(228, 205, 180) : Color.rgb(134, 89, 45));

		relocate(x * UserInterface.TILE_SIZE, y * UserInterface.TILE_SIZE);

	}
}
