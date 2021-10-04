package View;

import Controller.PacienteController;
import Model.Paciente;
import java.awt.Color;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class PacienteTable extends javax.swing.JDialog {

    //Controlador
    private PacienteController controladorP;

    //Criando as variaveis que serao passadas para a janela principal no final da selecao
    private String paciNome = null;
    private String paciId = null;

    public PacienteTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Centralizando pelo JFrame 
        setLocationRelativeTo(parent);

        this.controladorP = new PacienteController();

        //Metodos para carregar a tabela
        setTables();
        carregaTabelaPaciente();
    }

//----------------------------------------------SET THE VARIABLES--------------------------------------------//  
    private void doClose(String pacienteNome, String pacienteId) {
        //Metodo para fechar a janela
        paciNome = pacienteNome;
        paciId = pacienteId;

        setVisible(false);
        dispose();
    }
//----------------------------------------------SET THE VARIABLES--------------------------------------------//  

//----------------------------------------------GETTERS FOR THE VARIABLES------------------------------------//  
    public String getPaciNome() {
        return paciNome;
    }

    public String getPaciId() {
        return paciId;
    }
//----------------------------------------------GETTERS FOR THE VARIABLES------------------------------------//  

//----------------------------------------------SET TABLE----------------------------------------------------//  
    //Editando a JTable
    private void setTables() {
        this.jT_consulta_paciente.getTableHeader().setDefaultRenderer(new Dashboard.HeaderColor()); //change the header color
        this.jT_consulta_paciente.setFillsViewportHeight(true); //Make the table ocupy the entire scroll pane
        this.jScrollPane_consulta_paciente.setBorder(createEmptyBorder());
        this.jT_consulta_paciente.setSelectionBackground(new Color(107, 207, 206));
    }
//----------------------------------------------SET TABLE----------------------------------------------------//  

//----------------------------------------------SHOW TABLE PACIENTE------------------------------------------//  
    public void carregaTabelaPaciente() {
        DefaultTableModel modelo = (DefaultTableModel) this.jT_consulta_paciente.getModel();
        modelo.setNumRows(0);

        ArrayList<Paciente> minhalista = new ArrayList<>();
        minhalista = controladorP.getPacienteListController();

        for (Paciente a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getData_Nasc()
            });
        }
    }
//----------------------------------------------SHOW TABLE PACIENTE------------------------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane_consulta_paciente = new javax.swing.JScrollPane();
        jT_consulta_paciente = new javax.swing.JTable();
        jL_consulta_paciente = new javax.swing.JLabel();
        jL_btn_ok = new javax.swing.JLabel();
        jL_btn_cancelar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(240, 248, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 207, 206), 5));

        jT_consulta_paciente.setBackground(new java.awt.Color(196, 243, 251));
        jT_consulta_paciente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jT_consulta_paciente.setForeground(new java.awt.Color(0, 0, 0));
        jT_consulta_paciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Data Nasc."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT_consulta_paciente.setRowHeight(28);
        jT_consulta_paciente.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jT_consulta_paciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_consulta_pacienteMouseClicked(evt);
            }
        });
        jScrollPane_consulta_paciente.setViewportView(jT_consulta_paciente);

        jL_consulta_paciente.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jL_consulta_paciente.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_paciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consulta_paciente.setText("Selecione um Paciente");

        jL_btn_ok.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_ok.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
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

        jL_btn_cancelar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_cancelar.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL_consulta_paciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane_consulta_paciente))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jL_btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jL_btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jL_consulta_paciente)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_consulta_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_btn_ok)
                    .addComponent(jL_btn_cancelar))
                .addContainerGap(28, Short.MAX_VALUE))
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

        setBounds(0, 0, 693, 417);
    }// </editor-fold>//GEN-END:initComponents

//----------------------------------------------OK BUTTON----------------------------------------------------//
    private void jL_btn_okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_okMouseClicked
        //Repassando as variaveis
        doClose(paciNome, paciId);
    }//GEN-LAST:event_jL_btn_okMouseClicked
//----------------------------------------------OK BUTTON----------------------------------------------------//

//----------------------------------------------PACIENTE TABLE CLICKED---------------------------------------//
    private void jT_consulta_pacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_consulta_pacienteMouseClicked
        //Evento para pegar o nome e ID
        if (this.jT_consulta_paciente.getSelectedRow() != -1) {
            String nome = this.jT_consulta_paciente.getValueAt(this.jT_consulta_paciente.getSelectedRow(), 1).toString();
            String id = this.jT_consulta_paciente.getValueAt(this.jT_consulta_paciente.getSelectedRow(), 0).toString();

            this.paciNome = nome;
            this.paciId = id;
        }
    }//GEN-LAST:event_jT_consulta_pacienteMouseClicked
//----------------------------------------------PACIENTE TABLE CLICKED---------------------------------------//

//----------------------------------------------CANCELAR BUTTON----------------------------------------------//
    private void jL_btn_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_cancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_jL_btn_cancelarMouseClicked
//----------------------------------------------CANCELAR BUTTON----------------------------------------------//

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PacienteTable dialog = new PacienteTable(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jL_btn_cancelar;
    private javax.swing.JLabel jL_btn_ok;
    private javax.swing.JLabel jL_consulta_paciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane_consulta_paciente;
    private javax.swing.JTable jT_consulta_paciente;
    // End of variables declaration//GEN-END:variables
}
