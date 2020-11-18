package guiActions;

import cardMgt.CardPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 An example of how to build an action that might be common to several controls.
 This example is a bit forced, but think of a case where a menu item and button
 are to perform the same task.

 @author dmr
 */
public class NextAction extends AbstractAction {

private final CardPanel target;

/**
 Constructs the <code>NextAction</code> with a specific name for the specified
 <code>CardPanel</code>.

 @param name as a String
 @param holder as a CardPanel
 */
public NextAction(String name, CardPanel holder) {
    //TODO: Consider having this go through the CardManger
    //TODO: examine non-overridable call to set the value associated with the specified key. 
    putValue(Action.NAME, name);
    target = holder;
}

/**
 The actual action to perform. In this case it is simple, but it could be more
 complex.

 @param ae as an <code>ActionEvent</code>
 */
@Override
public void actionPerformed(ActionEvent ae) {
    target.showNextCard();
}
}
