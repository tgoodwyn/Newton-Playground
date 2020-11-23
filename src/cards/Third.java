package cards;

import cardMgt.CardManager;
import guiActions.NextAction;
import view.ui.GameScreen;

/**
 * This class provides a card with a specific appearance and functionality.
 *
 * @author dmr
 */
public class Third extends Card {

    /**
     * Creates new instance of <code> Third</code> Third. The implementation
     * details are very similar to the
     * <code>First</code> class.
     *
     * TODO: Establish a clear style and format for the Card subclass javadocs
     *
     * @param cm as a <code>CardManger</code>
     * @param name as a <code>String</code>
     */
    public Third(CardManager cm, String name) {
        super(cm, name);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        testButtonCard = new javax.swing.JButton(new NextAction("managed next",cm.getManager()));
        nameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ancestorButtonTextDisplay = new javax.swing.JTextArea();
        NameField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 102));

        testButtonCard.setText("Back");

        nameButton.setText("Set Name");
        nameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("This is the third card in the deck");

        ancestorButtonTextDisplay.setColumns(20);
        ancestorButtonTextDisplay.setLineWrap(true);
        ancestorButtonTextDisplay.setRows(5);
        ancestorButtonTextDisplay.setWrapStyleWord(true);
        jScrollPane1.setViewportView(ancestorButtonTextDisplay);

        NameField.setText("Default");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameButton)
                    .addComponent(testButtonCard)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(testButtonCard)
                        .addGap(18, 18, 18)
                        .addComponent(nameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gets and displays a text representation of the parent of this card.
     *
     * @param evt as an Action Event
     */
    private void nameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameButtonActionPerformed
        String name = NameField.getText();
        try {
            // IMPORTANT: ".get(1)" in the line below should always have index
            // of whichever card the GameScreen is on
            GameScreen gs = (GameScreen) this.getCm().getCards().get(1);
            gs.getRenderer().setName(name);
        } catch (Exception e) {
            System.out.println("failed to get gs");
        }
    }//GEN-LAST:event_nameButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameField;
    private javax.swing.JTextArea ancestorButtonTextDisplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nameButton;
    private javax.swing.JButton testButtonCard;
    // End of variables declaration//GEN-END:variables
}
