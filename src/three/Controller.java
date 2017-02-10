package three;

import four.RunGame;
import one.Board;
import two.Piece;
import two.UserInterface;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener.Change;


public class Controller {


	private UserInterface theview;
	private Board themodel;

	
	public Controller(UserInterface view, Board model){
		this.theview = view;
		this.themodel = model;
		
		//establishes the piece(s) as event source objects, so theyll fire MouseEvents to the Handler 
		theview.createSourceObjects(new DraggedHandler(), new ReleasedHandler(), new PressedHandler());

	}
	
	class DraggedHandler implements EventHandler<MouseEvent> {
		
		//this is where the Controller will contact the Model with information from the View
		public void handle(MouseEvent e) {
			
			//Moves the source piece by calling movePiece()
			//Casted e.getSource() to Piece so I can call movePiece()
			((Piece) e.getSource()).dragPiece(e.getSceneX() - (UserInterface.TILE_SIZE / 2), e.getSceneY() - (UserInterface.TILE_SIZE / 2));	
		}
	}
	
	class PressedHandler implements EventHandler<MouseEvent> {
		
		//this is where the Controller will contact the Model with information from the View
		public void handle(MouseEvent e) {
			
			//sets the starting x and y locations in grid format to be used to find the location of the starting piece
			theview.setStartingXLoc(Math.round((int)e.getSceneX() / UserInterface.TILE_SIZE) + 1); //getSceneX/Y is not working because its assigning at the mouse release
			theview.setStartingYLoc(Math.round((int)e.getSceneY() / UserInterface.TILE_SIZE));
			
			theview.createListener(new Resize());
		}
	}
	
	class ReleasedHandler implements EventHandler<MouseEvent>{
		
		//this is where the Controller will contact the Model with information from the View
		public void handle(MouseEvent e) {
			
			//sets the destination x and y locations in grid format to be used to find the location of the destination piece
			theview.setDestinationXLoc((Math.round((int)e.getSceneX() / UserInterface.TILE_SIZE)) + 1);
			theview.setDestinationYLoc(Math.round((int)e.getSceneY() / UserInterface.TILE_SIZE));
			
			
			if (themodel.validMovement(themodel.getTile(theview.startingXLoc + (theview.startingYLoc * 8)), theview.startingXLoc + theview.startingYLoc * 8, theview.destinationXLoc + theview.destinationYLoc * 8)){
				
				theview.removePiece(theview.destinationXLoc + (theview.destinationYLoc * 8)); //moves image of killed piece to the side
				((Piece) e.getSource()).movePiece(e.getSceneX() - (UserInterface.TILE_SIZE / 2), e.getSceneY() - (UserInterface.TILE_SIZE / 2)); //moves imaged of active piece
				((Piece) e.getSource()).setTileLocation(theview.destinationXLoc + (theview.destinationYLoc * 8));
				
				//checks if the movement was a castling. If so, calls theview.castling to move the rook
				if (themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)) instanceof one.King){
					if (((one.King)themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8))).getCastling() == 1){
						theview.castle(theview.destinationXLoc + (theview.destinationYLoc * 8));
					}
				}
				
				
				themodel.updateArrays(); //updates arrays before calling for pawn promotion so that the potentially killed piece is account for
				themodel.updateHasMoved(themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)));
				
				if (themodel.checkForPawnPromotion(themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)))){
					theview.pawnPromotion(new QueenHandler(), new RookHandler(), new KnightHandler(), new BishopHandler());	
				}
				
				themodel.switchPlayer(themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)));
		
				themodel.displayBoard();
/*				System.out.println("White dead array: " + themodel.whitepiecesdead);
				System.out.println("White alive array: " + themodel.whitepiecesalive);
				System.out.println("Black dead array: " + themodel.blackpiecesdead);
				System.out.println("Black alive array: " + themodel.blackpiecesalive);*/
				System.out.println("Threatening piece: " + themodel.threat);
				//System.out.println("Temp piece: " + themodel.temp);
			}
			else{
				System.out.println("Try Again");
				((Piece)e.getSource()).movePiece(((theview.getStartingXLoc() - 1) * UserInterface.TILE_SIZE), (theview.getStartingYLoc() * UserInterface.TILE_SIZE));
			}

			//checks to see if the next player is in check and/or checkmate
			if (themodel.isInCheck()) {
				System.out.println("Threatening piece: " + themodel.threat);
				System.out.println("The location of threat: " + themodel.threat.getUnitPosition());
				System.out.println("The black king location is: " + themodel.blackkingposition);
				System.out.println("The white king location is: " + themodel.whitekingposition);
				if (themodel.isCheckmate(themodel.getKing(), themodel.threat)) {
					theview.endGame(themodel.getOpposingPlayer(themodel.currentplayer), new CloseApp());
				}
				System.out.println(themodel.currentplayer + " is in check!");
			}

		}
	}
	
	class QueenHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			themodel.promotePawn('Q', theview.startingXLoc + (theview.startingYLoc * 8), theview.destinationXLoc + (theview.destinationYLoc * 8));
			theview.promotePawn('Q', theview.destinationXLoc + (theview.destinationYLoc * 8), themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)).getPlayer().toString());
			theview.promotionStage.close();
		}
	}
	
	class RookHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			themodel.promotePawn('R', theview.startingXLoc + (theview.startingYLoc * 8), theview.destinationXLoc + (theview.destinationYLoc * 8));
			theview.promotePawn('R', theview.destinationXLoc + (theview.destinationYLoc * 8), themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)).getPlayer().toString());
			theview.promotionStage.close();
		}
	}
	
	class KnightHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			themodel.promotePawn('K', theview.startingXLoc + (theview.startingYLoc * 8), theview.destinationXLoc + (theview.destinationYLoc * 8));
			theview.promotePawn('K', theview.destinationXLoc + (theview.destinationYLoc * 8), themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)).getPlayer().toString());
			theview.promotionStage.close();
		}
	}
	
	class BishopHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			themodel.promotePawn('B', theview.startingXLoc + (theview.startingYLoc * 8), theview.destinationXLoc + (theview.destinationYLoc * 8));
			theview.promotePawn('B', theview.destinationXLoc + (theview.destinationYLoc * 8), themodel.getTile(theview.destinationXLoc + (theview.destinationYLoc * 8)).getPlayer().toString());
			theview.promotionStage.close();
		}
	}
	
	class NewHumanGame implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			RunGame.main(null);
		}
	}
	
	class CloseApp implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			theview.primaryStage.close();
		}
	}
	
	class Resize implements ChangeListener<Number>{
		
		public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue){
			if (theview.root.getHeight() > theview.root.getWidth()){
				theview.setTileSize((int)(theview.root.getWidth() / 8));
			}
			else if (theview.root.getHeight() < theview.root.getWidth()){
				theview.setTileSize((int)(theview.root.getHeight() / 8));
			}
			theview.drawBoard();
		}
	}
	
}
