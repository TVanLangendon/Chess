package two;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class UserInterface extends Application {
	
	

	public static int TILE_SIZE = 100;
	public static int WIDTH = 8;
	public static int HEIGHT = 8;


	
	//variables to hold the starting and destination axis
	public int startingXLoc;
	public int destinationXLoc;
	public int startingYLoc;
	public int destinationYLoc;
	
	//static variable so that a second copy of the variable isn't created when RunGame calls launch
	private static Piece whitepawn1 = new Piece(0 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn2 = new Piece(1 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn3 = new Piece(2 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn4 = new Piece(3 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn5 = new Piece(4 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn6 = new Piece(5 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn7	= new Piece(6 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whitepawn8 = new Piece(7 * TILE_SIZE, 6 * TILE_SIZE);
	private static Piece whiterook1 = new Piece(0 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whiterook2 = new Piece(7 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whiteknight1 = new Piece(1 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whiteknight2 = new Piece(6 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whitebishop1 = new Piece(2 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whitebishop2 = new Piece(5 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whitequeen = new Piece(3 * TILE_SIZE, 7 * TILE_SIZE);
	private static Piece whiteking = new Piece(4 * TILE_SIZE, 7 * TILE_SIZE);
	
	private static Piece blackpawn1 = new Piece(0 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn2 = new Piece(1 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn3 = new Piece(2 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn4 = new Piece(3 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn5 = new Piece(4 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn6 = new Piece(5 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn7	= new Piece(6 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackpawn8 = new Piece(7 * TILE_SIZE, 1 * TILE_SIZE);
	private static Piece blackrook1 = new Piece(0 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackrook2 = new Piece(7 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackknight1 = new Piece(1 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackknight2 = new Piece(6 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackbishop1 = new Piece(2 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackbishop2 = new Piece(5 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackqueen = new Piece(3 * TILE_SIZE, 0 * TILE_SIZE);
	private static Piece blackking = new Piece(4 * TILE_SIZE, 0 * TILE_SIZE);
	
	public static Group tileGroup = new Group();
	public static Piece[] allpieces  = new Piece[32];
	public static Stage primaryStage;
	public static Stage promotionStage;
	public static Pane root;
	public static Scene scene;
	
	//static variables so they can be accessed for changing the image in pawnpromotion
	private static Image whtrook;
	private static Image whtknight;
	private static Image whtbishop;
	private static Image whtqueen;
	private static Image blkrook;
	private static Image blkknight;
	private static Image blkbishop;
	private static Image blkqueen;
	
	
	public void start(Stage stage) {

		Image whtpawn = new Image("/resources/white-pawn.png", TILE_SIZE, TILE_SIZE, false, false);
		whtrook = new Image("/resources/white-rook.png", TILE_SIZE, TILE_SIZE, false, false);
		whtknight = new Image("/resources/white-knight.png", TILE_SIZE, TILE_SIZE, false, false);
		whtbishop = new Image("/resources/white-bishop.png", TILE_SIZE, TILE_SIZE, false, false);
		whtqueen = new Image("/resources/white-queen.png", TILE_SIZE, TILE_SIZE, false, false);
		Image whtking = new Image("/resources/white-king.png", TILE_SIZE, TILE_SIZE, false, false);
		
		Image blkpawn = new Image("/resources/black-pawn.png", TILE_SIZE, TILE_SIZE, false, false);
		blkrook = new Image("/resources/black-rook.png", TILE_SIZE, TILE_SIZE, false, false);
		blkknight = new Image("/resources/black-knight.png", TILE_SIZE, TILE_SIZE, false, false);
		blkbishop = new Image("/resources/black-bishop.png", TILE_SIZE, TILE_SIZE, false, false);
		blkqueen = new Image("/resources/black-queen.png", TILE_SIZE, TILE_SIZE, false, false);
		Image blkking = new Image("/resources/black-king.png", TILE_SIZE, TILE_SIZE, false, false);
		
		whitepawn1.setImage(whtpawn);
		whitepawn2.setImage(whtpawn);
		whitepawn3.setImage(whtpawn);
		whitepawn4.setImage(whtpawn);
		whitepawn5.setImage(whtpawn);
		whitepawn6.setImage(whtpawn);
		whitepawn7.setImage(whtpawn);
		whitepawn8.setImage(whtpawn);
		whiterook1.setImage(whtrook);
		whiterook2.setImage(whtrook);
		whiteknight1.setImage(whtknight);
		whiteknight2.setImage(whtknight);
		whitebishop1.setImage(whtbishop);
		whitebishop2.setImage(whtbishop);
		whitequeen.setImage(whtqueen);
		whiteking.setImage(whtking);
		
		blackpawn1.setImage(blkpawn);
		blackpawn2.setImage(blkpawn);
		blackpawn3.setImage(blkpawn);
		blackpawn4.setImage(blkpawn);
		blackpawn5.setImage(blkpawn);
		blackpawn6.setImage(blkpawn);
		blackpawn7.setImage(blkpawn);
		blackpawn8.setImage(blkpawn);
		blackrook1.setImage(blkrook);
		blackrook2.setImage(blkrook);
		blackknight1.setImage(blkknight);
		blackknight2.setImage(blkknight);
		blackbishop1.setImage(blkbishop);
		blackbishop2.setImage(blkbishop);
		blackqueen.setImage(blkqueen);
		blackking.setImage(blkking);
		
		

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {

				Tile tile = new Tile(((i + j) % 2 == 0), i, j);
				tileGroup.getChildren().add(tile);
			}
		}

		root = new Pane();
		root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
		root.getChildren().addAll(tileGroup);
		root.getChildren().addAll(allpieces);


		scene = new Scene(root);
		primaryStage = stage;
		stage.setTitle("Chess by trevor");
		stage.setScene(scene);
		stage.show();
	}

	//to be called by the changelistener so that the board is re-shown when the tiles are resized
	public void drawBoard(){
		
		tileGroup.getChildren().clear();
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {

				Tile tile = new Tile(((i + j) % 2 == 0), i, j);
				tileGroup.getChildren().add(tile);
			}
		}
		
		//Why doesnt scaleX/scaleY work? I think its because TILE_SIZE is an int, not a double
		/*tileGroup.setScaleX(this.getTileSize() / 100.0);
		tileGroup.setScaleY(this.getTileSize() / 100.0);*/

		
		for (int i = 0; i < allpieces.length; i++){
			allpieces[i].setFitHeight(TILE_SIZE);
			allpieces[i].setFitWidth(TILE_SIZE);
			allpieces[i].adjustPiece(allpieces[i].getXAxis(), allpieces[i].getYAxis());
		}

		primaryStage.show();
	}


	
	public void createSourceObjects(EventHandler draghandler, EventHandler releasehandler, EventHandler pressedhandler) {		
		
		//fills the allpieces[]. Done here (instead of start()) because this method is called before launch()
		allpieces[0] = whitepawn1;
		allpieces[1] = whitepawn2;
		allpieces[2] = whiterook1;
		allpieces[3] = whiteknight1;
		allpieces[4] = whitebishop1;
		allpieces[5] = whitequeen;
		allpieces[6] = whitepawn3;
		allpieces[7] = whitepawn4;
		allpieces[8] = whitepawn5;
		allpieces[9] = whitepawn6; 
		allpieces[10] = whitepawn7;
		allpieces[11] = whitepawn8;
		allpieces[12] = whiterook2;
		allpieces[13] = whiteknight2;
		allpieces[14] = whitebishop2;
		allpieces[15] = whiteking;
		allpieces[16] = blackpawn1;
		allpieces[17] = blackpawn2;
		allpieces[18] = blackrook1;
		allpieces[19] = blackknight1;
		allpieces[20] = blackbishop1;
		allpieces[21] = blackqueen;
		allpieces[22] = blackpawn3;
		allpieces[23] = blackpawn4;
		allpieces[24] = blackpawn5;
		allpieces[25] = blackpawn6; 
		allpieces[26] = blackpawn7;
		allpieces[27] = blackpawn8;
		allpieces[28] = blackrook2;
		allpieces[29] = blackknight2;
		allpieces[30] = blackbishop2;
		allpieces[31] = blackking;
		
		
		for (int i = 0; i < allpieces.length; i++){
			allpieces[i].setOnMouseDragged(draghandler);
			allpieces[i].setOnMousePressed(pressedhandler);
			allpieces[i].setOnMouseReleased(releasehandler);
			
		}		
	}
	
	public void createListener(ChangeListener resize){
		primaryStage.heightProperty().addListener(resize);
		primaryStage.widthProperty().addListener(resize);
	}
	
	
	public void endGame(String player, EventHandler closegame){
	
		Text text = new Text("Checkmate! " + player + " is victorious!");
		
		Button newPVPBtn = new Button("New Game vs Human");
		Button newPVEBtn = new Button("New Game vs AI");
		Button closeAppBtn = new Button("Exit to Desktop");
		closeAppBtn.setOnAction(closegame);
		
		BorderPane borderpane = new BorderPane();
		borderpane.setPrefSize(6 * TILE_SIZE, 4 * TILE_SIZE);
		borderpane.setAlignment(text, Pos.CENTER);
		borderpane.setAlignment(closeAppBtn, Pos.CENTER);
		borderpane.setTop(text);
		borderpane.setLeft(newPVPBtn);
		borderpane.setRight(newPVEBtn);
		borderpane.setBottom(closeAppBtn);
				
		Scene scene = new Scene(borderpane);
		
		Stage endStage = new Stage();
		endStage.initModality(Modality.APPLICATION_MODAL);
		endStage.initOwner(primaryStage);
		endStage.setScene(scene);
		endStage.show();
	}
	
	public void pawnPromotion(EventHandler queenhandler, EventHandler rookhandler, EventHandler knighthandler, EventHandler bishophandler){
		Label label = new Label("Congratulations little guy! Choose your desired role: ");
		label.setPrefSize(6 * TILE_SIZE, TILE_SIZE);
	
		Button queen = new Button("Queen");
		queen.setOnAction(queenhandler);
		Button rook = new Button("Rook");
		rook.setOnAction(rookhandler);
		Button knight = new Button("Knight");
		knight.setOnAction(knighthandler);
		Button bishop = new Button("Bishop");
		bishop.setOnAction(bishophandler);
	
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(6 * TILE_SIZE, 4 * TILE_SIZE);
		flowpane.setHgap(TILE_SIZE);
		flowpane.setVgap(TILE_SIZE);
		flowpane.getChildren().addAll(label, queen, rook, knight, bishop);
		
		Scene scene = new Scene(flowpane);
		
		promotionStage = new Stage();
		promotionStage.initModality(Modality.APPLICATION_MODAL);
		promotionStage.initOwner(primaryStage);
		promotionStage.setScene(scene);
		promotionStage.showAndWait();
	
	}
	
	public void removePiece(int location){
		for (int i = 0; i < allpieces.length; i++){
			if (allpieces[i].getTileLocation() == location){
				allpieces[i].movePiece(9 * TILE_SIZE,  4 * TILE_SIZE);
				allpieces[i].setTileLocation(0);
			}
		}
	}
	
	public void promotePawn(char choice, int location, String color){
		
		Piece promotedpiece = null;
		
		for (int i = 0; i < allpieces.length; i++){
			if (allpieces[i].getTileLocation() == location){
				promotedpiece = allpieces[i];
			}
		}
		
		switch(choice){
			case 'Q': 
				if (color.equalsIgnoreCase("white")){
					promotedpiece.setImage(whtqueen);
				}
				else if (color.equalsIgnoreCase("black")){
					promotedpiece.setImage(blkqueen);
				}
				break;
				
			case 'R': 
				if (color.equalsIgnoreCase("white")){
					promotedpiece.setImage(whtrook);
				}
				else if (color.equalsIgnoreCase("black")){
					promotedpiece.setImage(blkrook);
				}
				break;
				
			case 'K': 
				if (color.equalsIgnoreCase("white")){
					promotedpiece.setImage(whtknight);
				}
				else if (color.equalsIgnoreCase("black")){
					promotedpiece.setImage(blkknight);
				}
				break;
				
			case 'B': 
				if (color.equalsIgnoreCase("white")){
					promotedpiece.setImage(whtbishop);
				}
				else if (color.equalsIgnoreCase("black")){
					promotedpiece.setImage(blkbishop);
				}
				break;
		}
	}
	
	public void castle(int kinglocation){
		
		switch(kinglocation){
			case 3: blackrook1.movePiece(3 * TILE_SIZE, 0 * TILE_SIZE);
					break;
			
			case 7: blackrook2.movePiece(5 * TILE_SIZE, 0 * TILE_SIZE);
					break;
					
			case 59: whiterook1.movePiece(3 * TILE_SIZE, 7 * TILE_SIZE);
					break;
		
			case 63: whiterook2.movePiece(5 * TILE_SIZE, 7 * TILE_SIZE);
					break;
		}
	}
	
	
	public int getTileSize(){
		return TILE_SIZE;
	}
	
	public void setTileSize(int newsize){
		TILE_SIZE = newsize;
	}
	
	
	public int getStartingXLoc(){
		return this.startingXLoc;
	}
	
	public int getDestinationXLoc(){
		return this.destinationXLoc;
	}
	
	public int getStartingYLoc(){
		return this.startingYLoc;
	}
	
	public int getDestinationYLoc(){
		return this.destinationYLoc;
	}
	
	public void setStartingXLoc(int location){
		this.startingXLoc = location;
	}
	
	public void setDestinationXLoc(int location){
		this.destinationXLoc = location;
	}
	
	public void setStartingYLoc(int location){
		this.startingYLoc = location;
	}
	
	public void setDestinationYLoc(int location){
		this.destinationYLoc = location;
	}
}