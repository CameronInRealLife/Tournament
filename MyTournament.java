import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MyTournament {
    private ArrayList<String> players;
    private String[][] winners;
    private int byes;
    private int rounds;
    private int round_2;
    private boolean lock; // Tournament has not started

    MyTournament() {
        players = new ArrayList();
        byes = 0;
        lock = false;
        rounds = 0;
    }

    public boolean add(String name) {
        if ( lock ){
            System.out.println( "Tournament started, can not add." );
            return false ;
        }

        if (!players.add(name)) {
            System.out.println("Player already added: " + name);
            return false;
        }
        return true;
    }

    // To remove players before tournament starts.
    public boolean remove(String name) {
        if ( lock ){
            System.out.println( "Tournament started, can not remove." );
            return false ;
        }

        if (!players.remove(name)) {
            System.out.println("Player never added: " + name);
            return false;
        }

        return true;
    }

    public boolean printBrackets() {
        int i = 1;
        int sz = players.size();

        while (i < sz) {
            i <<= 1;                            // assigns the i value 2x
            rounds++;
        }

        round_2 = i >> 1;
        int j = byes = i - sz;
        players.trimToSize();                   // ensure no null references from remove.

        while (i > 0) {
            i -= 2;                            // decrementing by 2

            if (j-- > 0) {
                System.out.println(players.get(--sz));
                System.out.println("--Bye--");
                System.out.println();
                continue;
            }

            System.out.println(players.get(--sz));
            System.out.println(players.get(--sz));
            System.out.println();
        }

        System.out.println("--End Round 1--");
        if ( lock ) {
            System.out.println();

            for ( i = 0 ; i < rounds ; i++ ) {
                System.out.print("--Round ");
                System.out.print( i + 2 );
                System.out.println("--");
                System.out.println();

                for ( j = 0; j < round_2; j++ ) {
                    if (winners[i][j] == null) {
                        break;
                    }

                    System.out.println(winners[i][j]);

                    if (( j & 1 ) == 1 ) {
                        System.out.println();
                    }
                }

                System.out.println();
                System.out.print( "--End Round " );
                System.out.print( i + 2 );
                System.out.println( "--" );

            }
        }

        System.out.println();
    }

    public boolean addWinner( int round, String name ){
        int i ;

        if ( ! lock ){
            lock = true ; // first winner locks players, allocates winners array

            i = 1;
            int sz = players.size();

            while (i < sz) {
                i <<= 1;                            // assigns the i value 2x
                rounds++;
            }

            round_2 = i >> 1;
            winners = new String[ rounds ][ round_2 ];
            int j = byes = i - sz;
            i = 0 ;

            while ( j-- > 0 ){
                winners[ 0 ][ i++ ] = players.get(--sz);
            }
        }

        if ( round < 1 || round > ( rounds + 1 )){
            System.out.printf( "Add winner round not in range, min 1, max %d.\n", rounds + 1 );
            return false ;
        }

        if ( round == 1 ){
            if ( ! players.contains( name )) {
                System.out.println( "No such name exists in this tournament: " + name ) ;
                return false ;
            }
        } else {
            for ( i = 0 ; i < round_2 ; i++ ){
                if ( winners[ round - 2 ][ i ] == null ){
                    System.out.printf( "No person named '%s' made it to round %d.\n", name, round );
                    return false ;
                }

                if ( winners[ round - 2 ][ i ].equals( name )) {
                    break;
                }
            }
        }

        for ( i = 0 ; i < round_2 ; i++ ){
            if ( winners[ round - 1 ][ i ] == null ){
                break ;
            }
        }

        if ( i >= ( round_2 >> ( round - 1 ))){     // Number of winners.
            System.out.printf( "Round %d already has %d winners.\n",
                    round, round_2 >> ( round - 1 ));
            return false ;
        }

        winners[ round - 1 ][ i ] = name ;
    }

    public boolean removeWinner( int round, String name ){
        int i ;

        if ( ! lock ){
            System.out.println( "No winners yet entered." );
            return false ;
        }

        if ( round < 1 || round > ( rounds + 1 )){
            System.out.printf( "Remove winner round not in range, min 1, max %d.\n", rounds + 1 );
            return false ;
        }

        for ( i = 0 ; i < round_2 ; i++ ){
            if ( winners[ round - 1 ][ i ] == null ) {
                System.out.printf("No person named '%s' made it to round %d.\n", name, round);
                return false;
            }

            if ( winners[ round - 1 ][ i ].equals( name )) {
                break;
            }
        }

        if ( round == 1 && i == ( round_2 - 1 )){   // Removing top element, no null above.
            winners[ round - 1 ][ i ] = null ;
            return true ;
        }

        do {
            winners[ round - 1 ][ i ] =  winners[ round - 1 ][ i + 1 ];  //
        } while ( winners[ round - 1 ][ i++ ] != null );

        return true ;
    }

}


/* Wish List
Sort by seed
Round Robin
Creating an interface for both
Scene builder
Brackets for single elim if possible

 */