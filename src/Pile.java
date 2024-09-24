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

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCardAt(int index) {
        if (index >= length){
            return null;
        }
        return cards.get(index);
    }

    @Override
    public String toString(){
        return cards.toString();
    }
}
