//Set Package
package chessmen;


public class King extends Chessman{
    public final String type = "King"; 
    private final Team team;

    public King(Team team){
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