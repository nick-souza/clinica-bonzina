package View;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class BemVindoDashboard extends javax.swing.JPanel {

    public BemVindoDashboard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_card1_dashboard = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jP_card1_dashboard.setBackground(new java.awt.Color(240, 248, 255));
        jP_card1_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Malgun Gothic", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Valorizamos sua Saúde");
        jP_card1_dashboard.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 880, -1));
        jP_card1_dashboard.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 390, 60));

        jLabel26.setFont(new java.awt.Font("Malgun Gothic", 1, 50)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Clinica Bonzina");
        jP_card1_dashboard.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 880, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/recepcionista_c.png"))); // NOI18N
        jP_card1_dashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 150, 880, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jP_card1_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jP_card1_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jP_card1_dashboard;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
