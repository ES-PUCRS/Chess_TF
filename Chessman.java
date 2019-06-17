import javafx.scene.layout.GridPane;

public class Chessman extends ChessmanDefault{
    protected static final int SquareRows = 8;
    private final String type;
    private final Team team;

    public Chessman(Team team, String type){ 
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
    public boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board){
        return false;
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
}

