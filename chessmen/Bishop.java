//Set Package
package chessmen;


public class Bishop extends Chessman{
    public final String type = "Bishop"; 
    private final Team team;

    public Bishop(Team team){
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
}