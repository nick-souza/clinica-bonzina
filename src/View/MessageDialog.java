package View;

import javax.swing.ImageIcon;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class MessageDialog extends javax.swing.JDialog {

    public MessageDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public MessageDialog(java.awt.Frame parent, boolean modal, String msg, String iconPath) {
        //Construtor para passar uma mensagem e mudar o icone
        super(parent, modal);
        initComponents();

        this.jL_msg1.setText(msg);
        this.jL_msg2.setText("");

        this.icon.setIcon(new ImageIcon(getClass().getResource(iconPath)));
    }

    public MessageDialog(java.awt.Frame parent, boolean modal, String msg, String msg2, String iconPath) {
        //Construtor para passar duas mensagem e mudar o icone
        super(parent, modal);
        initComponents();

        this.jL_msg1.setText(msg);
        this.jL_msg2.setText(msg2);

        this.icon.setIcon(new ImageIcon(getClass().getResource(iconPath)));
    }

//------------------------------------METODOS PARA CHAMAR DIALOGS COM DIFERENTES ICONES------------------------------------//
    //Fazendo metodos Static para poder chamar direto
    public static void chamarDialogOK(String msg) {
        MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true, msg, "/Img/greenTick1.png");
        dialog.setVisible(true);
    }

    public static void chamarDialogCancel(String msg) {
        MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true, msg, "/Img/cancelx64.png");
        dialog.setVisible(true);
    }

    public static void chamarDialogCancel2(String msg, String msg2) {
        MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true, msg, msg2, "/Img/cancelx64.png");
        dialog.setVisible(true);
    }
//------------------------------------METODOS PARA CHAMAR DIALOGS COM DIFERENTES ICONES------------------------------------//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jL_msg1 = new javax.swing.JLabel();
        jL_btn_ok = new javax.swing.JLabel();
        jL_msg2 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("cadastrarPaciDialog"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(430, 190));

        jPanel1.setBackground(new java.awt.Color(196, 243, 251));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_msg1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jL_msg1.setForeground(new java.awt.Color(0, 0, 0));
        jL_msg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_msg1.setText("MENSAGEM APARECE AQUI");
        jPanel1.add(jL_msg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 414, 39));

        jL_btn_ok.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_ok.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_ok.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_ok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_ok.setText("OK");
        jL_btn_ok.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jL_btn_ok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_ok.setOpaque(true);
        jL_btn_ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_okMouseClicked(evt);
            }
        });
        jPanel1.add(jL_btn_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 160, -1));

        jL_msg2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jL_msg2.setForeground(new java.awt.Color(0, 0, 0));
        jL_msg2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_msg2.setText("MENSAGEM APARECE AQUI");
        jPanel1.add(jL_msg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 414, 39));

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 112, 82));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(430, 221));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jL_btn_okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_okMouseClicked
        this.dispose();
    }//GEN-LAST:event_jL_btn_okMouseClicked

    public static void main(String args[]) {
        /* Create and display the dialog */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jL_btn_ok;
    private javax.swing.JLabel jL_msg1;
    private javax.swing.JLabel jL_msg2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
