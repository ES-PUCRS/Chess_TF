
//Java default API controls
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.TreeMap;
import java.util.Map;

import chessmen.*;

public class Board extends GridPane{
    public static final int squareSize = 65;
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
        for(int i = 0; i < 64; i ++){
            chessman = Game.UnboxChessmen(i);
            coordinate = new Coordinate(i);
            square = new BoardSquare(coordinate, chessman);
            square.setOnAction(e -> squareCatch(e));
            square.setMinSize(squareSize, squareSize);
            square.SquareTransparent();
            super.add(square, coordinate.getColumn(), coordinate.getRow());
        }

        setGridLinesVisible(true);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(1));
        setHgap(1.3);
        setVgap(1.3);
    }

    public void squareCatch(ActionEvent e){
        BoardSquare square = (BoardSquare) e.getSource();
        System.out.println(square.getCoordinate());
    }
    
}