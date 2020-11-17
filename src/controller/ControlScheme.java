/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Action;
import java.util.Hashtable;

/**
 *
 * @author team 2
 */
public class ControlScheme {

    InputController ic;
    Hashtable<Integer, Action> effects = new Hashtable<Integer, Action>();

    public void addAction(int kc, Action a) {
        effects.put(kc, a);
    }





}
