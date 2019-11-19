public class Player {
    private String playerName ;
    private int points ;
    private boolean bye ;

    public Player( String name )
    {
    this.playerName = name ;
    this.points = 0 ;
    this.bye = true ;

    }
    public String getPlayerName() {
        return playerName ;
    }

    public void setPlayerName(String name ) {
        this.playerName = name ;
    }

    public int getPoints(){
        return points ;
    }

    public void setPoints(int p)  {
        this.points = this.points + p ;
    }

    public boolean isBye() {
        return bye ;
    }

    public void setBye(boolean bye){
        this.bye = bye ;
    }
}
