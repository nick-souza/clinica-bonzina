package View;

import Controller.MedicoController;
import Model.Medico;
import java.awt.Color;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class MedicoTable extends javax.swing.JDialog {

    //Controlador
    private final MedicoController controladorM;

    //Criando as variaveis que serao passadas para a janela principal no final da selecao
    private String medNome = null;
    private String medId = null;

    public MedicoTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //Centralizando pelo JFrame 
        setLocationRelativeTo(parent);
        this.controladorM = new MedicoController();

        //Metodos para carregar a tabela
        setTables();
        carregaTabelaMedico();
    }

//----------------------------------------------METHOD TO SERACH---------------------------------------------//
    private void pesquisarMedico() {
        String searchName = this.jT_medico_pesquisa.getText();
        try {
            if (controladorM.getSearchMedicoController(searchName) == null) {
                MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true, "Nenhum Medico Encontrado", "com esse nome");
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);

            } else {
                ShowMedicoSearchJTable(searchName);
            }
        } catch (Exception e) {
        }
    }
//----------------------------------------------METHOD TO SERACH---------------------------------------------//

//----------------------------------------------SEARCHING JTABLE_PACIENTE------------------------------------//     
    public void ShowMedicoSearchJTable(String nome) {

        ArrayList<Medico> list = controladorM.getSearchMedicoController(nome);
        DefaultTableModel model = (DefaultTableModel) this.jT_consulta_medico.getModel();
        model.setNumRows(0);

        for (Medico a : list) {
            model.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getCrm(),});
        }
    }
//----------------------------------------------SEARCHING JTABLE_PACIENTE------------------------------------//

//----------------------------------------------SET THE VARIABLES--------------------------------------------//  
    private void doClose(String medicoNome, String medicoId) {
        //Metodo para fechar a janela
        medNome = medicoNome;
        medId = medicoId;

        setVisible(false);
        dispose();
    }
//----------------------------------------------SET THE VARIABLES--------------------------------------------//  

//----------------------------------------------GETTERS FOR THE VARIABLES------------------------------------//  
    public String getMedNome() {
        return medNome;
    }

    public String getMedId() {
        return medId;
    }
//----------------------------------------------GETTERS FOR THE VARIABLES------------------------------------//  

//----------------------------------------------SET TABLE----------------------------------------------------//  
    //Editando a JTable
    private void setTables() {
        this.jT_consulta_medico.getTableHeader().setDefaultRenderer(new Dashboard.HeaderColor()); //change the header color
        this.jT_consulta_medico.setFillsViewportHeight(true); //Make the table ocupy the entire scroll pane
        this.jScrollPane_consulta_medico.setBorder(createEmptyBorder());
        this.jT_consulta_medico.setSelectionBackground(new Color(107, 207, 206));
    }
//----------------------------------------------SET TABLE----------------------------------------------------//  

//----------------------------------------------SHOW TABLE PACIENTE------------------------------------------//  
    public void carregaTabelaMedico() {
        DefaultTableModel modelo = (DefaultTableModel) this.jT_consulta_medico.getModel();
        modelo.setNumRows(0);

        ArrayList<Medico> minhalista = new ArrayList<>();
        minhalista = controladorM.getMedicoListController();

        for (Medico a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getCrm()
            });
        }
    }
//----------------------------------------------SHOW TABLE PACIENTE------------------------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane_consulta_medico = new javax.swing.JScrollPane();
        jT_consulta_medico = new javax.swing.JTable();
        jL_consulta_medico = new javax.swing.JLabel();
        jL_btn_ok = new javax.swing.JLabel();
        jL_btn_cancelar = new javax.swing.JLabel();
        jL_btn_medico_pesquisar = new javax.swing.JLabel();
        jT_medico_pesquisa = new javax.swing.JTextField();
        btn_pesquisa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(240, 248, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 207, 206), 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jT_consulta_medico.setBackground(new java.awt.Color(196, 243, 251));
        jT_consulta_medico.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jT_consulta_medico.setForeground(new java.awt.Color(0, 0, 0));
        jT_consulta_medico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "CRM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT_consulta_medico.setRowHeight(28);
        jT_consulta_medico.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jT_consulta_medico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_consulta_medicoMouseClicked(evt);
            }
        });
        jScrollPane_consulta_medico.setViewportView(jT_consulta_medico);

        jPanel1.add(jScrollPane_consulta_medico, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 89, 671, 238));

        jL_consulta_medico.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jL_consulta_medico.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_medico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consulta_medico.setText("Selecione um Medico");
        jPanel1.add(jL_consulta_medico, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 28, 671, -1));

        jL_btn_ok.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_ok.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jL_btn_ok.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_ok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_ok.setText("OK");
        jL_btn_ok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_ok.setOpaque(true);
        jL_btn_ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_okMouseClicked(evt);
            }
        });
        jPanel1.add(jL_btn_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 110, 30));

        jL_btn_cancelar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_cancelar.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jL_btn_cancelar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_cancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_cancelar.setText("Cancelar");
        jL_btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_cancelar.setOpaque(true);
        jL_btn_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_cancelarMouseClicked(evt);
            }
        });
        jPanel1.add(jL_btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 110, 30));

        jL_btn_medico_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/loupe.png"))); // NOI18N
        jL_btn_medico_pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_pesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_pesquisarMouseClicked(evt);
            }
        });
        jPanel1.add(jL_btn_medico_pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 30, 30));

        jT_medico_pesquisa.setBackground(new java.awt.Color(107, 207, 206));
        jT_medico_pesquisa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jT_medico_pesquisa.setForeground(new java.awt.Color(0, 0, 0));
        jT_medico_pesquisa.setBorder(null);
        jPanel1.add(jT_medico_pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 210, 30));

        btn_pesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/loupe.png"))); // NOI18N
        btn_pesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pesquisaMouseClicked(evt);
            }
        });
        jPanel1.add(btn_pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 693, 417);
    }// </editor-fold>//GEN-END:initComponents

//----------------------------------------------OK BUTTON----------------------------------------------------//
    private void jL_btn_okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_okMouseClicked
        doClose(medNome, medId);
    }//GEN-LAST:event_jL_btn_okMouseClicked
//----------------------------------------------OK BUTTON----------------------------------------------------//

//----------------------------------------------MEDICO TABLE CLICKED-----------------------------------------//
    //Evento para pegar o nome e ID
    private void jT_consulta_medicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_consulta_medicoMouseClicked
        if (this.jT_consulta_medico.getSelectedRow() != -1) {
            String nome = this.jT_consulta_medico.getValueAt(this.jT_consulta_medico.getSelectedRow(), 1).toString();
            String id = this.jT_consulta_medico.getValueAt(this.jT_consulta_medico.getSelectedRow(), 0).toString();

            this.medNome = nome;
            this.medId = id;
        }
    }//GEN-LAST:event_jT_consulta_medicoMouseClicked
//----------------------------------------------MEDICO TABLE CLICKED-----------------------------------------//

//----------------------------------------------CANCELAR BUTTON----------------------------------------------//
    private void jL_btn_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_cancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_jL_btn_cancelarMouseClicked
//----------------------------------------------CANCELAR BUTTON----------------------------------------------//

    private void jL_btn_medico_pesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_pesquisarMouseClicked
    }//GEN-LAST:event_jL_btn_medico_pesquisarMouseClicked

    private void btn_pesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesquisaMouseClicked
        pesquisarMedico();
    }//GEN-LAST:event_btn_pesquisaMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MedicoTable dialog = new MedicoTable(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btn_pesquisa;
    private javax.swing.JLabel jL_btn_cancelar;
    private javax.swing.JLabel jL_btn_medico_pesquisar;
    private javax.swing.JLabel jL_btn_ok;
    private javax.swing.JLabel jL_consulta_medico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane_consulta_medico;
    private javax.swing.JTable jT_consulta_medico;
    private javax.swing.JTextField jT_medico_pesquisa;
    // End of variables declaration//GEN-END:variables
}
