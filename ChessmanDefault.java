import javafx.scene.layout.GridPane;

public abstract class ChessmanDefault{
    public abstract boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board);
    public abstract String toString();
    public abstract String getType();
    public abstract Team getTeam();
}