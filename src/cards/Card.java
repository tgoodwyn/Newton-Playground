package cards;

import cardMgt.CardManager;
import javax.swing.JPanel;

/**
 The instance of <code>Card</code> will be the <code>JPanel</code> that holds
 the different 'screens' for the application. A <code>Card</code> has fields to
 hold a manager and a name. The name will also be used as the name of the
 component, the card object.

 Note: Since the class is not <code>final</code> it can be extended.

 @author dmr
 */
public class Card extends JPanel {

CardManager cm;
private final String cardName;

/**
 Constructs a <code>Card</code> for the <code>CardManger</code> with a given
 name. The name is used for the card and the component. Using a component name
 will make manipulating the cards easier.

 @param manager as a <code>CardManger</code>
 @param cn as a <code>String</code>
 */
public Card(CardManager manager, String cn) {
    cm = manager;
    cardName = cn;
    //TODO: The method setName can be overriden. Examine whether to simply use the field or change the method.
    setName(cn);
}

/**
 Returns the <code>CardManger</code> for this card. Other than in the
 constructor, the
 <code>CardManger</code> can not be set for the card object.

 @return the manager as a <code>CardManger</code>
 */
public CardManager getCm() {
    return cm;
}

/**
 Returns the name of the card. Note that the name of the component may be
 different. The name of the card as well as the name of the component are set by
 the constructor.

 @return the name as a <code>String</code>
 */
public String getCardName() {
    return cardName;
}
}
