package four;

import javafx.application.*;
import one.Board;
import three.Controller;
import two.UserInterface;

/*
 * To Be Added:
 * En Pessant
 * AI algorithm
 * 
 * To Be Updated:
 * Make board resizable (DONE - except it only works after first mousepress)
 * Add UI elements (dead pieces, player turn, forfeit/new game button, text box with moves, color squares highlighting most recent move)
 * Fix "new game" button
 * Change the static/non static nature of methods in Board
 * Fix comments and cleanup code
 */

public class RunGame{

	public static void main(String[] args){
		
		UserInterface theview = new UserInterface();
		Board themodel = new Board();
		
		Controller thecontroller = new Controller(theview, themodel);
		
		Application.launch(UserInterface.class, args);
		
	}
}
