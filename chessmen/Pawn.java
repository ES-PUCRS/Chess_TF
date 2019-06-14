//Set Package
package chessmen;



public class Pawn extends Chessman{
    public final String type = "Pawn"; 
    private final Team team;

    public Pawn(Team team){
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