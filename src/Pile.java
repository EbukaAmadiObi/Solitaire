package src;
import java.util.ArrayList;

public class Pile {
    private ArrayList<Card> cards;
    private int length;

    public Pile(){
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
        length++;
    }

    public Card getCardAt(int index) {
        if (index >= length){
            return null;
        }
        return cards.get(index);
    }

    public void flipCard() {
        if (cards.get(length-1).isFlipped()){
            System.out.println("Card already flipped!");
        }
        else{
            cards.get(length-1).flip();
        }

    }

    @Override
    public String toString(){
        return cards.toString();
    }
}
