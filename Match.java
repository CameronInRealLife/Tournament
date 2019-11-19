// Round Robin matches

import java.util.Date;

public class Match {
    private Player p1 ;
    private Player p2 ;
    private Player winner ;
    private boolean played ;
    private Date matchDate ;
    private Stadium stadium ;

    public Match(){
        this.p1 = null ;
        this.p2 = null ;
        this.played = false ;
        this.winner = null ;
    }

    public Match( Player p1, Player p2, Stadium stadium, Date matchDate ){
        super();
        this.p1 = p1 ;
        this.p2 = p2 ;
        this.stadium = stadium ;
        this.matchDate = matchDate ;
        this.played = false ;
        this.winner = null ;
    }

    public Player[] getPlayers()
    {
        Player[] players = new  Player[ 2 ] ;
        players[ 0 ] = p1 ;
        players[ 1 ] = p2 ;
        return players ;
    }

    public void setPlayers ( Player p1, Player p2 ){
        this.p1 = p1 ;
        this.p2 = p2 ;
    }

    public Date getMatchDate(){
        return matchDate ;
    }

    public Stadium getStadium(){
        return stadium ;
    }

    public void setStadium( Stadium stadium ){
        this.stadium = stadium ;
    }

    public boolean isPlayer(){
        return played ;
    }

    public Player getWinner()
    {
        return winner ;
    }

    @Override
    public String toString() {
        return p1.getPlayerName() + " and " + p2.getPlayerName() + " on " + matchDate + " at " + stadium.getStadiumName() ;
    }
}
