package src;
import java.util.*;

public class Solitaire {
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    public enum Suit {
        CLUBS, SPADES, HEARTS, DIAMONDS
    }
    public static void main(String[] args){
        ArrayList<Card> deck = new ArrayList<Card>();   //The starting deck
        for (int j = 0; j<=3; j+=1){    //fill with cards
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
        System.out.println(myTableau);
    }

}
