public class Stadium {
    private String stadiumName ;
    private boolean full ;

    public Stadium( String name ){
        this.stadiumName = name ;
        this.full = false ;
    }

    public String getStadiumName(){
        return stadiumName ;
    }

    public void setStadiumName( String name ){
        this.stadiumName = name ;
    }

    public boolean isFull(){
        return full ;
    }

    public void setFull( boolean full ){
        this.full = full ;
    }
}
