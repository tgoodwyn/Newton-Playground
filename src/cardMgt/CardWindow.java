package cardMgt;

import guiActions.NextAction;
import cards.Third;
import cards.Second;
import cards.First;
import java.awt.Dimension;
import view.ui.GameScreen;

/**
 This is offered as a pattern for using a <code>CardPanel</code> layout as well
 as getting it started. In the future a class like this should contain as little
 as possible. For now we can simply use it as a starting point.

 @author dmr
 */
public class CardWindow extends javax.swing.JFrame {

/**
 Constructs a new <code>CardWindow</code>. This relies on the tools in NetBeans.
 If you want to know what <code>initComponents</code> does, click the code-fold
 for the generated code. The positioning code is at the end of the fold and uses
 a variety of layouts. You didn't notice these if you used the graphical tools
 in NetBeans.
 */
public CardWindow() {
    initComponents();
}

/**
 Creates a new <code>CardManger</code> for a specified display area and adds
 cards to it. Note that the <code>addCard</code> message is sent to the
 <code>CardManger</code>.

 TODO: Consider returning the instance of "CardManger"
 */
public void addCardsToHolder() {
    CardManager cm = new CardManager(cardDisplayArea);
    cm.addCard(new First(cm, "first"));
    cm.addCard(new GameScreen(cm, "second"));
    cm.addCard(new Third(cm, "third"));
}

/**
 Creates a <code>CardWindow</code>, puts the managed cards in the window, and
 then makes the window visible.
 */
private static void createAndShow() {
    CardWindow w = new CardWindow();
    w.addCardsToHolder();
    w.setVisible(true);
}

/**
 This method is called from within the constructor to initialize the form.
 WARNING: Do NOT modify this code. The content of this method is always
 regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardDisplayArea = new cardMgt.CardPanel();
        firstButtonMain = new javax.swing.JButton();
        secondButtonMain = new javax.swing.JButton();
        nextButtonMain = new javax.swing.JButton(new NextAction("new next", cardDisplayArea));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(50, 50));
        setPreferredSize(new java.awt.Dimension(1000, 550));
        setResizable(false);

        firstButtonMain.setText("first");
        firstButtonMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonMainActionPerformed(evt);
            }
        });

        secondButtonMain.setText("second");
        secondButtonMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondButtonMainActionPerformed(evt);
            }
        });

        nextButtonMain.setText("Next");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardDisplayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(firstButtonMain)
                .addGap(18, 18, 18)
                .addComponent(secondButtonMain)
                .addGap(28, 28, 28)
                .addComponent(nextButtonMain)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardDisplayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstButtonMain)
                    .addComponent(secondButtonMain)
                    .addComponent(nextButtonMain))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

/**
 Makes the card named 'first' visible.

 @param evt as an <code>ActionEvent</code>
 */
    private void firstButtonMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonMainActionPerformed
        cardDisplayArea.showCard("first");
    }//GEN-LAST:event_firstButtonMainActionPerformed

/**
 Makes the card named 'second' visible.

 @param evt as an <code>ActionEvent</code>
 */
    private void secondButtonMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondButtonMainActionPerformed
        cardDisplayArea.showCard("second");
        System.out.println("yo");  
        //cardDisplayArea.requestFocus();
        //this.setPreferredSize(new Dimension(800,500));
        //this.pack();
    }//GEN-LAST:event_secondButtonMainActionPerformed

/**
 Attempts to set the look-and-feel for the application. In this case it is the
 laf named Nimbus. Press the code-fold to see what is happening. The application
 is made runnable and made available to be run. From the documentation:
 <i>"EventQueue is a platform-independent class that queues events, both from
 the underlying peer classes and from trusted application classes."</i><br>
 <i>"Causes runnable to have its run method called in the dispatch thread of the
 system EventQueue.  This will happen after all pending events are processed."
 </i><br> More on these two point later.

 @param args the command line arguments
 */
public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(CardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(CardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(CardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(CardWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        createAndShow();
    }
    });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cardMgt.CardPanel cardDisplayArea;
    private javax.swing.JButton firstButtonMain;
    private javax.swing.JButton nextButtonMain;
    private javax.swing.JButton secondButtonMain;
    // End of variables declaration//GEN-END:variables
}