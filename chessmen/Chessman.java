//Set Package
package chessmen;


public class Chessman extends ChessmanDefault{
    public final String type; 
    private final Team team;

    public Chessman(Team team){
        this.team = team;
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
        return type;
    }
}

