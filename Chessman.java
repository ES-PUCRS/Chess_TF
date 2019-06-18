import javafx.scene.layout.GridPane;

public abstract class Chessman{
    public abstract boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board);
    public abstract boolean tryMoveNext(int newSquarePos, int nPosInt);
    public abstract void setBoard(GridPane board);
    public abstract String toString();
    public abstract String getType();
    public abstract Team getTeam();
}