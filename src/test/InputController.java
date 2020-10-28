/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static java.awt.event.KeyEvent.*;
import java.util.HashSet;
import java.util.Set;
import test.Action;
import test.Camera;
import test.ControlScheme;

/**
 *
 * @author team 2
 */
public class InputController {

    public ControlScheme scheme;
    Set<Integer> activePressedKeys = new HashSet<Integer>();
    Set<Integer> activeReleasedKeys = new HashSet<Integer>();
    private final IControllable pc;

    public InputController(IControllable pc) {
        this.pc = pc;
        scheme = new ControlScheme();
        scheme.addAction(VK_UP, new Action(pc));
        scheme.addAction(VK_DOWN, new Action(pc));
        scheme.addAction(VK_LEFT, new Action(pc));
        scheme.addAction(VK_RIGHT, new Action(pc));
    }

    public void keyPressed(int kc) {
        for (Integer a : scheme.effects.keySet()) {
            if (kc == a) {
                scheme.effects.get(kc).notify(kc, Action.ActionType.PRESSED);
            }
        }
    }

    public void keyReleased(int kc) {
        for (Integer a : scheme.effects.keySet()) {
            if (kc == a) {
                scheme.effects.get(kc).notify(kc, Action.ActionType.RELEASED);
            }
        }
    }

}

