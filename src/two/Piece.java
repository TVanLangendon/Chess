package two;

import javafx.event.EventHandler;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;

public class Piece extends ImageView {

	public int tilelocation; //used by removepiece and pawnpromotion to find the piece 
	public int xaxis; //tracks the column of the piece
	public int yaxis; //tracks the row of the piece

	
	//constructs the piece with the given x and y coordinates
	public Piece(int x, int y){
		
		setX(x);
		setY(y);
		this.xaxis = Math.round(x / UserInterface.TILE_SIZE);
		this.yaxis = Math.round(y / UserInterface.TILE_SIZE);
		this.tilelocation = (1 + x / UserInterface.TILE_SIZE + y / UserInterface.TILE_SIZE * 8);
	}

	
	
	//called by the dragHandler to keep the piece underneath the mouse. Doesnt share with movePiece so that the x/yaxis are not changed
	public void dragPiece(double x, double y){
		
		relocate(x, y);
	}
	
	
	//called after moves are validated or to reset the piece after a failed move.
	public void movePiece(double x, double y){
		
		//stores the new column/row position of the piece
		this.xaxis = (int)(Math.round(x / UserInterface.TILE_SIZE));
		this.yaxis = (int)(Math.round(y / UserInterface.TILE_SIZE));
		relocate (UserInterface.TILE_SIZE * this.xaxis, UserInterface.TILE_SIZE * this.yaxis);

	}
	
	
	//called by the displayBoard to move the pieces to their appropriate tile when resizing
	public void adjustPiece(int x, int y){
		
		relocate (UserInterface.TILE_SIZE * x, UserInterface.TILE_SIZE * y);
	}
	
	
	public int getTileLocation(){
		return this.tilelocation;
	}
	
	
	public void setTileLocation(int location){
		this.tilelocation = location;
	}
	
	
	public int getXAxis(){
		return this.xaxis;
	}
	
	
	public int getYAxis(){
		return this.yaxis;
	}

	
}
