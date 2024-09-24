package src;
import java.util.*;

public class Solitaire {
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    public enum Suit {
        CLUBS, SPADES, HEARTS, DIAMONDS
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Card> deck = new ArrayList<Card>();   //The starting deck
        for (int j = 0; j<=3; j+=1){    //fill with all cards
            for (int i = 1; i<=13; i+=1){
                deck.add(new Card(i,j));
            }
        }
        Collections.shuffle(deck);
        var myTableau = new Tableau();
        for(int i = 0; i<7; i++){
            for(int j = 0; j<=i; j++){
                myTableau.addCard(i,deck.getFirst());
                deck.removeFirst();
            }
        }
        for (int i = 0; i<7; i++){
            myTableau.flipCard(i);
        }
        System.out.println(myTableau);
    }
    public static int rankToInt(Rank rank) {
        return switch (rank) {
            case ACE -> 0;
            case TWO -> 1;
            case THREE -> 2;
            case FOUR -> 3;
            case FIVE -> 4;
            case SIX -> 5;
            case SEVEN -> 6;
            case EIGHT -> 7;
            case NINE -> 8;
            case TEN -> 9;
            case JACK -> 10;
            case QUEEN -> 11;
            case KING -> 12;
        };
    }
    public static int suitToInt(Suit suit){
        return switch (suit) {
            case Solitaire.Suit.CLUBS -> 0;
            case Solitaire.Suit.SPADES -> 1;
            case Solitaire.Suit.HEARTS -> 2;
            case Solitaire.Suit.DIAMONDS -> 3;
        };
    }
}
