package src;
import java.util.ArrayList;

public class Pile {
    private ArrayList<Card> cards;
    private int length;
    private Card lastCard;

    public Pile(){
        cards = new ArrayList<Card>();
    }

    public boolean placeCard(Card heldCard, boolean ignoreRules) {
        //if pile is empty and heldCard is a King of any suit
        // or if the held card can go on the last card
        // or if rules are being ignored (for setup at game start)
        if(length == 0 && heldCard.isEqual(new Card(13, Solitaire.suitToInt(heldCard.getSuit()))) ||
                (heldCard.canGoOn(lastCard,false)) ||
                ignoreRules){
            cards.add(heldCard);
            lastCard = heldCard;
            length++;
            return true;
        }
        else{
            return false;
        }
    }

    public void removeCardAt(int index){
        cards.remove(index);
        length--;
        if (length == 0){
            lastCard = null;
        }
        else{
            lastCard = cards.getLast();
            flipLastCard();
        }
    }

    public boolean removeLastCard() {
        if (length==0){
            return false;
        }
        else {
            cards.removeLast();
            length--;
            if (length == 0){
                lastCard = null;
            }
            else{
                lastCard = cards.getLast();
                flipLastCard();
            }
            return true;
        }
    }

    public Card getCardAt(int index) {
        if (index >= length){
            return null;
        }
        return cards.get(index);
    }

    public Card getLastCard(){
        return lastCard;
    }

    public int getLength(){
        return length;
    }

    public void flipLastCard() {
        if (cards.get(length-1).isFlipped()){
            System.out.println("Card already flipped!");
        }
        else{
            cards.get(length-1).flip();
        }
    }

    @Override
    public String toString(){
        if (cards == null){
            return "*EMPTY PILE*";
        }
        else{
            return cards.toString();
        }

    }
}
