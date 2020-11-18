package cardMgt;

import cards.Card;
import java.util.ArrayList;

/**
 The <code>CardManger</code> facilitates operations on cards using the
 <code>CardPanel</code>. It also facilitates the use of <code>Actions</code> for
 GUI controls. The manager holds a <code>CardPanel</code> referred to as the
 deck and a list of <code>Card</code> objects called the cards. You might wonder
 why this is the case. The difficulty is with the <code>CardPanel</code>.

 @author dmr
 */
public class CardManager {

CardPanel deck;
ArrayList<Card> cards = new ArrayList<>();

/**
 Constructs a <code>CardManger</code> that holds (has-a) <code>CardPanel</code>.
 The wrapping of the
 <code>CardPanel</code> provides additional functionality and maintains a degree
 of loose coupling through composition.

 @param cp as a <code>CardPanel</code>
 */
public CardManager(CardPanel cp) {
    deck = cp;
}

/**
 Gets the manager for the deck of cards.

 @return the deck as a <code>CardPanel</code>
 */
public CardPanel getManager() {
    return deck;
}

/**
 Gets the cards in the deck

 @return cards as an <code>ArrayList</code> of <code>Cards</code>
 */
public ArrayList<Card> getCards() {
    return cards;
}

/**
 Adds a card to the deck (manager) and the card collection. Uses the specified
 card name when the card is added to the deck.

 @param card as a<code>Card</code>
 */
public void addCard(Card card) {
    cards.add(card);
    deck.add(card.getName(), card);
}

/**
 Provides a formatted list of the names of the card in the card collection.
 Since cards can only be added, they cannot be deleted, the cards in the deck
 and the cards collection should refer to the same cards.

 @return a formatted list of card names as a <code>String</code>
 */
public String formatCardNames() {
    StringBuilder sb = new StringBuilder();
    for (Card card : cards) {
        sb.append("[")
                .append(card.getCardName())
                .append("] ");
    }
    return sb.toString();
}

/**
 Tests whether the card is in the cards collection. This is a poor method
 definition in many ways. The method name might be better named as
 '<code>isCardInCards</code>' or perhaps '<code>isCardInDeck</code>'. Th latter
 might make sense since cards can only be added, they cannot be deleted, the
 cards in the deck and the cards collection should refer to the same cards. In
 any case the return value should be a <code>boolean</code>. 

 @param t the card to be tested.
 */
public void cardTest(Card t) {
    //TODO: Change name and make a boolean return value.
    if (cards.contains(t)) {
        System.out.println(t.getName() + " is in the deck");
    } else {
        System.out.println(t.getName() + " is NOT in the deck");
    }
}
}
