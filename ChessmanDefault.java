import javafx.scene.layout.GridPane;

public abstract class ChessmanDefault extends Chessman{
    protected static final int SquareRows = 8;
    private BoardSquare shouldTry;  // BoardSquare that would catch by newSquarePos
    private final String type;
    private final Team team;
    private GridPane board;         // All board to try square by square until target

    public ChessmanDefault(Team team, String type){ 
        this.team = team;
        this.type = type;
    }

    @Override
    public Team getTeam(){
        return team;
    }

    @Override
    public String getType(){
        return type;
    }
    
    @Override
    public String toString(){
        String toString = "";
        if(team == Team.WHITE)
            toString = "White" + type;
        else if (team == Team.BLACK)
            toString = "Black" + type;
            else
        toString = "Null";
        
        return toString;
    }

    
    @Override
    public boolean tryMoveNext(int newSquarePos, int nPosInt){
        shouldTry = (BoardSquare) board.getChildren().get(newSquarePos);
        if(shouldTry.getChessman() != null && newSquarePos != nPosInt)
                return false;
        return true;
    }

    @Override
    public void setBoard(GridPane board){
        this.board = board;
    }

    @Override
    public abstract boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board);
}

