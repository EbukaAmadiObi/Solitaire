package src;

public class Foundation {
    private Card currentCard;

    public Foundation(){
        currentCard = null;
    }

    public boolean addCard(Card card){
        if (currentCard == null && card.isEqual(new Card(1,1))){
            currentCard = new Card(1,1);
            return true;
        }
        else if(card.canGoOn(currentCard)){
            currentCard = card;
            return true;
        }
        else{
            return false;
        }
    }
}
