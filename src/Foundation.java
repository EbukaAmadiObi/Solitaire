package src;

import javax.swing.*;

import static src.Solitaire.*;

public class Foundation {
    private Card currentCard;
    private int suit;

    public Foundation(int initSuit){
        currentCard = null;
        suit = initSuit;
    }

    public boolean placeCard(Card card){
        if (currentCard == null && card.isEqual(new Card(1,suit))){
            currentCard = new Card(1,suit);
            currentCard.flip();
            return true;
        }
        else if(card.canGoOn(currentCard, true)){
            currentCard = card;
            currentCard.flip();
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
        if (currentCard == null) {
            if (intToSuit(suit) == Suit.DIAMONDS || intToSuit(suit) == Suit.HEARTS) {
                return ANSI_RED + "EMPTY "+ intToSuit(suit).toString() +" FOUNDATION"+ Solitaire.ANSI_RESET;
            }
            else{
                return ANSI_BLACK + "EMPTY "+ intToSuit(suit).toString() +" FOUNDATION"+ Solitaire.ANSI_RESET;
            }

        }
        else{
            return currentCard.getColour() + currentCard.toString() + Solitaire.ANSI_RESET;
        }
    }
}
