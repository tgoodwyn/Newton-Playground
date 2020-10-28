/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author team 2
 */
public interface IControllable {

    public final Action.ActionType PRESSED = Action.ActionType.PRESSED;
    public final Action.ActionType RELEASED = Action.ActionType.RELEASED;

    public void update(int keycode, Action.ActionType a);
}
