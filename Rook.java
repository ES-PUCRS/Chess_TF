import javafx.scene.layout.GridPane;

public class Rook extends ChessmanDefault {
    public final String type = "Rook";    //Chessman type
    
    private Coordinate cPosCoord;   // Current coordinate
    private Coordinate nPosCoord;   // New coordinate
    


    public Rook(Team team){
        super(team, "Rook");
    }
    
    @Override 
    public boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board){
        cPosCoord = cPos.getCoordinate();
        nPosCoord = nPos.getCoordinate();
        super.setBoard(board);
        
        if(cPosCoord.getColumn() != nPosCoord.getColumn() && cPosCoord.getRow() != nPosCoord.getRow())
            return false;

        return true;
    }
}