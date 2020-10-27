/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
// la dee la
/**
 *
 * @author tgood
 */
public class Action {

    enum ActionType {
        PRESSED,
        RELEASED
    }
    
    Boolean registered;
    IControllable controlledObject;
//    Action.ActionType type;

    public Action(IControllable i) {
        this.controlledObject = i;
    }
    
    public void notify(int keycode, ActionType a) {
        controlledObject.update(keycode, a);
    }
    public void register(){
        registered = true;
    }

    public void deregister(){
        registered = false;
    }
}
