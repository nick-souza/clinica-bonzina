package View;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class OptionDialog extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 1;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 0;

    public OptionDialog(java.awt.Frame parent, boolean modal, String msg) {
        super(parent, modal);
        initComponents();

        this.jL_msg.setText(msg);

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return this.returnStatus;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jL_msg = new javax.swing.JLabel();
        jL_btn_nao = new javax.swing.JLabel();
        jL_btn_sim = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(430, 205));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(196, 243, 251));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jL_msg.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jL_msg.setForeground(new java.awt.Color(0, 0, 0));
        jL_msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_msg.setText("MENSAGEM AQUI");

        jL_btn_nao.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_nao.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_nao.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_nao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_nao.setText("NAO");
        jL_btn_nao.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jL_btn_nao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_nao.setOpaque(true);
        jL_btn_nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_naoMouseClicked(evt);
            }
        });

        jL_btn_sim.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_sim.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_sim.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_sim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_sim.setText("SIM");
        jL_btn_sim.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jL_btn_sim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_sim.setOpaque(true);
        jL_btn_sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_simMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/questionMark.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jL_btn_sim, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(68, 68, 68)
                .addComponent(jL_btn_nao, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jL_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_btn_nao)
                    .addComponent(jL_btn_sim))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(440, 213));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void jL_btn_naoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_naoMouseClicked
        doClose(RET_OK);
    }//GEN-LAST:event_jL_btn_naoMouseClicked

    private void jL_btn_simMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_simMouseClicked
        doClose(RET_CANCEL);
    }//GEN-LAST:event_jL_btn_simMouseClicked

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OptionDialog dialog = new OptionDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_btn_nao;
    private javax.swing.JLabel jL_btn_sim;
    private javax.swing.JLabel jL_msg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
