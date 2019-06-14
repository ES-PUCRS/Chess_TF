//Set Package
package chessmen;



public class Rook extends Chessman{
    public final String type = "Rook"; 
    private final Team team;

    public Rook(Team team){
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