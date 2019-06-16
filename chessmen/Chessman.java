//Set Package
package chessmen;


public class Chessman extends ChessmanDefault{
    private final Team team;
    private final String type;

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
    public boolean moveFx(){
        return true;
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

