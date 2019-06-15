
//Java default API controls
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;

import chessmen.*;

public class Board extends GridPane{
    public static final int SquareSize = 65;
    public static final int COLUMNS = 8;
    public static final int ROWS = 8;
    public static final int SIZE = COLUMNS * ROWS;

    private Coordinate coordinate;
    private BoardSquare square;
    private Chessman chessman;
    private Picture picture;

    
    public Board(){
        super();
        
        picture = new Picture("Board");
        new BackgroundImage(picture.picture(),null,null,null,null);//BackgroundFill(new ImagePattern(picture.picture())))));

        //Create every 64 board spots
        //@Params treeMap(Key, Value) --Key = Integer -> Board coordinate position 
        //                             --Value = BoardSquare -> Squart Coordinated
        //@Params BoardSquare(Coordinate, Chessman)
        //@Params Coordinate(i) --i = board coordinate position "(Matrix)Vector"
        for(int i = 0; i < SIZE; i ++){
            chessman = Game.UnboxChessmen(i);
            coordinate = new Coordinate(i);
            square = new BoardSquare(coordinate, chessman);
            square.setOnAction(e -> squareCatch(e));
            square.setMinSize(SquareSize, SquareSize);
            square.SquareTransparent();
            super.add(square, coordinate.getColumn(), coordinate.getRow());
        }

        coordinate = new Coordinate(0);
        setGridLinesVisible(true);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(1));
        setHgap(1.3);
        setVgap(1.3);
    }

    //Catch onAction event
    public void squareCatch(ActionEvent e){
        BoardSquare square = (BoardSquare) e.getSource();
        System.out.println(square.getChessman());
    }

    //Get all BoardSquares occupied
    public GridPane onBoard(){
        GridPane grid = new GridPane();
        
        ObservableList<Node> childrens = super.getChildren();

        childrens.remove(childrens.size()-1);
        for (Node node : childrens)
            if(node instanceof BoardSquare){
                square = (BoardSquare) node;
                coordinate = square.getCoordinate();

                if(square.getChessman() != null){
                    picture = new Picture(square.getChessman().getType());
                    grid.add(picture.getView(SquareSize, SquareSize), coordinate.getColumn(), coordinate.getRow());
                }
            }

            grid.setGridLinesVisible(true);
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(1));
            grid.setHgap(1.3);
            grid.setVgap(1.3);

        return grid;  
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
      
        //for (Node node : childrens) 
          //  System.out.println(node);
          int a = childrens.size();
        System.out.println(a);
      
        return result;
      }
}