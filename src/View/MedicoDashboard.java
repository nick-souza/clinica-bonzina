package View;

import Controller.MedicoController;
import Model.Medico;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class MedicoDashboard extends javax.swing.JPanel {

    //Controlador
    private final MedicoController controladorM;

    //Editor para mudar os paramentros do DateChooser 
    JTextFieldDateEditor dateChooserEditor;

    public MedicoDashboard() {
        initComponents();

        this.controladorM = new MedicoController();

        this.setTables();
        this.carregaTabelaMedico();
        this.setMask();

    }

//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//
    private void limparCampos() {
        this.jT_medico_nomeInput.setText("");
        this.jFT_medico_crmInput.setValue(null);
        this.jT_medico_especiaInput.setText("");
        this.jFT_medico_teleInput.setText("");
        this.buttonGroup.clearSelection();
        this.jT_medico.clearSelection();
        this.jT_medico_pesquisarInput.setText("Pesquisar");

        //Remove o foco de todos os objetos do jFrame
        this.getRootPane().requestFocus();
    }
//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//

//----------------------------------------------SETTING THE TABLES-------------------------------------------//
    private void setTables() {
        this.jT_medico.getTableHeader().setDefaultRenderer(new Dashboard.HeaderColor()); //change the header color
        this.jT_medico.setFillsViewportHeight(true); //Make the table ocupy the entire scroll pane
        this.jT_medico.setSelectionBackground(new Color(107, 207, 206));

        //Removendo bordas
        this.jScrollPane_table_medico.setBorder(createEmptyBorder());
        //Mudando as cores da barra Vertical da tabela
        this.jScrollPane_table_medico.getVerticalScrollBar().setBackground(new Color(196, 243, 251));

        this.jScrollPane_table_medico.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                //Mudando as cores da barra Vertical da tabela
                this.thumbColor = new Color(107, 207, 206);
            }

            //Removendo os boto4s
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            //Criando botoes com 0x0 de dimensao para remover
            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
    }
//----------------------------------------------SETTING THE TABLES-------------------------------------------//  

//----------------------------------------------CARREGA TABLE PACIENTE---------------------------------------//  
    private void carregaTabelaMedico() {
        DefaultTableModel modelo = (DefaultTableModel) this.jT_medico.getModel();
        modelo.setNumRows(0);

        ArrayList<Medico> minhalista = new ArrayList<>();
        minhalista = this.controladorM.getMedicoListController();

        for (Medico a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getCrm(),
                a.getEspec(),
                a.getTelefone(),
                a.getTurno()
            });
        }
    }
//----------------------------------------------CARREGA TABLE PACIENTE---------------------------------------// 

//----------------------------------------------METODO PARA PESQUISAR----------------------------------------//
    private void pesquisarMedico() {
        String searchName = this.jT_medico_pesquisarInput.getText();
        try {
            if (this.controladorM.getSearchMedicoController(searchName) == null) {
//                MessageDialog dialog = new MessageDialog(new javax.swing.JFrame(), true, "Nenhum Médico Encontrado com esse Nome");
                MessageDialog.chamarDialogCancel("Nenhum Médico Encontrado com esse Nome");
            } else {
                ShowMedicoSearchJTable(searchName);
            }
        } catch (Exception e) {
        }
        this.jT_medico_pesquisarInput.setText("Pesquisar");
    }
//----------------------------------------------METODO PARA PESQUISAR----------------------------------------//

//----------------------------------------------CARREGA TABLE PESQUISA---------------------------------------//     
    private void ShowMedicoSearchJTable(String nome) {

        ArrayList<Medico> list = this.controladorM.getSearchMedicoController(nome);
        DefaultTableModel model = (DefaultTableModel) this.jT_medico.getModel();
        model.setNumRows(0);

        for (Medico a : list) {
            model.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getCrm(),
                a.getEspec(),
                a.getTelefone(),
                a.getTurno(),});
        }
    }
//----------------------------------------------CARREGA TABLE PESQUISA---------------------------------------//     

//----------------------------------------------SETTING THE MASKS--------------------------------------------//
    private void setMask() {
        //Usando esse metodo para colocar as mascara e ainda colocar o PLACEHOLDER
        try {
            MaskFormatter teleMask = new MaskFormatter("(##)#####-####");
            teleMask.setPlaceholderCharacter('_');
            DefaultFormatterFactory factoryTele = new DefaultFormatterFactory(teleMask);

            MaskFormatter crmMask = new MaskFormatter("########-#/BR");
            crmMask.setPlaceholderCharacter('_');
            DefaultFormatterFactory factoryCRM = new DefaultFormatterFactory(crmMask);

            this.jFT_medico_teleInput.setFormatterFactory(factoryTele);
            this.jFT_medico_crmInput.setFormatterFactory(factoryCRM);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

    }
//----------------------------------------------SETTING THE MASKS--------------------------------------------//

//----------------------------------------------METODO PARA PEGAR O TURNO SELECIONADO------------------------//
    private String getTurnoSelected() {
        String turno = null;
        if (this.jR_btn_matutino.isSelected()) {
            turno = "Matutino";
        }
        if (this.jR_btn_vespertino.isSelected()) {
            turno = "Vespertino";
        }
        return turno;
    }
//----------------------------------------------METODO PARA PEGAR O TURNO SELECIONADO------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jP_card2_medico = new javax.swing.JPanel();
        jL_medico_titulo = new javax.swing.JLabel();
        jScrollPane_table_medico = new javax.swing.JScrollPane();
        jT_medico = new javax.swing.JTable();
        jL_medico_nome = new javax.swing.JLabel();
        jL_medico_crm = new javax.swing.JLabel();
        jL_medico_especi = new javax.swing.JLabel();
        jL_medico_tel = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jT_medico_nomeInput = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jT_medico_especiaInput = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jL_btn_medico_limpar = new javax.swing.JLabel();
        jL_btn_medico_cadastrar = new javax.swing.JLabel();
        jL_btn_medico_editar = new javax.swing.JLabel();
        jL_btn_medico_excluir = new javax.swing.JLabel();
        jT_medico_pesquisarInput = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jL_btn_medico_pesquisar = new javax.swing.JLabel();
        jL_medico_turno = new javax.swing.JLabel();
        jFT_medico_crmInput = new javax.swing.JFormattedTextField();
        jFT_medico_teleInput = new javax.swing.JFormattedTextField();
        jR_btn_vespertino = new javax.swing.JRadioButton();
        jR_btn_matutino = new javax.swing.JRadioButton();
        reloadTable = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 248, 255));

        jP_card2_medico.setBackground(new java.awt.Color(240, 248, 255));
        jP_card2_medico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_medico_titulo.setFont(new java.awt.Font("Malgun Gothic", 1, 35)); // NOI18N
        jL_medico_titulo.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_titulo.setText("Gerenciar Médicos");
        jP_card2_medico.add(jL_medico_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 340, 76));

        jScrollPane_table_medico.setBackground(new java.awt.Color(0, 93, 102));
        jScrollPane_table_medico.setBorder(null);
        jScrollPane_table_medico.setForeground(new java.awt.Color(0, 93, 102));
        jScrollPane_table_medico.setFocusable(false);
        jScrollPane_table_medico.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jScrollPane_table_medico.setName(""); // NOI18N
        jScrollPane_table_medico.setOpaque(false);

        jT_medico.setBackground(new java.awt.Color(196, 243, 251));
        jT_medico.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        jT_medico.setForeground(new java.awt.Color(0, 0, 0));
        jT_medico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id           ▲", "Nome", "CRM", "Especialidade", "Telefone", "Turno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT_medico.setFocusable(false);
        jT_medico.setRowHeight(28);
        jT_medico.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jT_medico.getTableHeader().setReorderingAllowed(false);
        jT_medico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_medicoMouseClicked(evt);
            }
        });
        jScrollPane_table_medico.setViewportView(jT_medico);
        if (jT_medico.getColumnModel().getColumnCount() > 0) {
            jT_medico.getColumnModel().getColumn(0).setResizable(false);
            jT_medico.getColumnModel().getColumn(0).setPreferredWidth(0);
            jT_medico.getColumnModel().getColumn(1).setResizable(false);
            jT_medico.getColumnModel().getColumn(2).setResizable(false);
            jT_medico.getColumnModel().getColumn(3).setResizable(false);
            jT_medico.getColumnModel().getColumn(4).setResizable(false);
            jT_medico.getColumnModel().getColumn(5).setResizable(false);
            jT_medico.getColumnModel().getColumn(5).setPreferredWidth(0);
        }

        jP_card2_medico.add(jScrollPane_table_medico, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, 864, 230));

        jL_medico_nome.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medico_nome.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_nome.setText("Nome:");
        jP_card2_medico.add(jL_medico_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 113, -1));

        jL_medico_crm.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medico_crm.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_crm.setText("CRM:");
        jP_card2_medico.add(jL_medico_crm, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 113, -1));

        jL_medico_especi.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medico_especi.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_especi.setText("Especialidade:");
        jP_card2_medico.add(jL_medico_especi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jL_medico_tel.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medico_tel.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_tel.setText("Telefone:");
        jP_card2_medico.add(jL_medico_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 113, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jP_card2_medico.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 265, 10));

        jT_medico_nomeInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_medico_nomeInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_medico_nomeInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_medico_nomeInput.setBorder(null);
        jP_card2_medico.add(jT_medico_nomeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 265, 24));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jP_card2_medico.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 265, 10));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jP_card2_medico.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 265, 10));

        jT_medico_especiaInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_medico_especiaInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_medico_especiaInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_medico_especiaInput.setBorder(null);
        jP_card2_medico.add(jT_medico_especiaInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 265, 24));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jP_card2_medico.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 265, 10));

        jL_btn_medico_limpar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_medico_limpar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_medico_limpar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_medico_limpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_medico_limpar.setText("Limpar Campos");
        jL_btn_medico_limpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_limpar.setOpaque(true);
        jL_btn_medico_limpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_limparMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_medico_limparMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_medico_limparMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_medico_limparMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_medico_limparMouseReleased(evt);
            }
        });
        jP_card2_medico.add(jL_btn_medico_limpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 170, 40));

        jL_btn_medico_cadastrar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_medico_cadastrar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_medico_cadastrar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_medico_cadastrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_medico_cadastrar.setText("Novo");
        jL_btn_medico_cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_cadastrar.setOpaque(true);
        jL_btn_medico_cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_cadastrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_medico_cadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_medico_cadastrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_medico_cadastrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_medico_cadastrarMouseReleased(evt);
            }
        });
        jP_card2_medico.add(jL_btn_medico_cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 130, 40));

        jL_btn_medico_editar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_medico_editar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_medico_editar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_medico_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_medico_editar.setText("Editar");
        jL_btn_medico_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_editar.setOpaque(true);
        jL_btn_medico_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_editarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_medico_editarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_medico_editarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_medico_editarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_medico_editarMouseReleased(evt);
            }
        });
        jP_card2_medico.add(jL_btn_medico_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 450, 130, 40));

        jL_btn_medico_excluir.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_medico_excluir.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_medico_excluir.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_medico_excluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_medico_excluir.setText("Excluir");
        jL_btn_medico_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_excluir.setOpaque(true);
        jL_btn_medico_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_excluirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_medico_excluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_medico_excluirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_medico_excluirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_medico_excluirMouseReleased(evt);
            }
        });
        jP_card2_medico.add(jL_btn_medico_excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, 130, 40));

        jT_medico_pesquisarInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_medico_pesquisarInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_medico_pesquisarInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_medico_pesquisarInput.setText("Pesquisar");
        jT_medico_pesquisarInput.setBorder(null);
        jT_medico_pesquisarInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_medico_pesquisarInputFocusGained(evt);
            }
        });
        jT_medico_pesquisarInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_medico_pesquisarInputKeyPressed(evt);
            }
        });
        jP_card2_medico.add(jT_medico_pesquisarInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 200, -1));

        jSeparator11.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jP_card2_medico.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 200, 10));

        jL_btn_medico_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/loupe.png"))); // NOI18N
        jL_btn_medico_pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_medico_pesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_medico_pesquisarMouseClicked(evt);
            }
        });
        jP_card2_medico.add(jL_btn_medico_pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 30, -1));

        jL_medico_turno.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medico_turno.setForeground(new java.awt.Color(0, 0, 0));
        jL_medico_turno.setText("Turno:");
        jP_card2_medico.add(jL_medico_turno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 113, -1));

        jFT_medico_crmInput.setBackground(new java.awt.Color(107, 207, 206));
        jFT_medico_crmInput.setBorder(null);
        jFT_medico_crmInput.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFT_medico_crmInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFT_medico_crmInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jP_card2_medico.add(jFT_medico_crmInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 265, 24));

        jFT_medico_teleInput.setBackground(new java.awt.Color(107, 207, 206));
        jFT_medico_teleInput.setBorder(null);
        jFT_medico_teleInput.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFT_medico_teleInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFT_medico_teleInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jP_card2_medico.add(jFT_medico_teleInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 265, 24));

        jR_btn_vespertino.setBackground(new java.awt.Color(240, 248, 255));
        buttonGroup.add(jR_btn_vespertino);
        jR_btn_vespertino.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jR_btn_vespertino.setForeground(new java.awt.Color(0, 0, 0));
        jR_btn_vespertino.setText("Vespertino");
        jR_btn_vespertino.setBorder(null);
        jR_btn_vespertino.setFocusPainted(false);
        jP_card2_medico.add(jR_btn_vespertino, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, 120, -1));

        jR_btn_matutino.setBackground(new java.awt.Color(240, 248, 255));
        buttonGroup.add(jR_btn_matutino);
        jR_btn_matutino.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jR_btn_matutino.setForeground(new java.awt.Color(0, 0, 0));
        jR_btn_matutino.setText("Matutino");
        jR_btn_matutino.setBorder(null);
        jR_btn_matutino.setFocusPainted(false);
        jP_card2_medico.add(jR_btn_matutino, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, -1, -1));

        reloadTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh1.png"))); // NOI18N
        reloadTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reloadTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadTableMouseClicked(evt);
            }
        });
        jP_card2_medico.add(reloadTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card2_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card2_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//
    private void jL_btn_medico_limparMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_limparMouseEntered
        this.jL_btn_medico_limpar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_medico_limparMouseEntered

    private void jL_btn_medico_limparMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_limparMouseExited
        this.jL_btn_medico_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_limparMouseExited

    private void jL_btn_medico_limparMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_limparMousePressed
        this.jL_btn_medico_limpar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_medico_limparMousePressed

    private void jL_btn_medico_limparMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_limparMouseReleased
        this.jL_btn_medico_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_limparMouseReleased

    private void jL_btn_medico_cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_cadastrarMouseEntered
        this.jL_btn_medico_cadastrar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_medico_cadastrarMouseEntered

    private void jL_btn_medico_cadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_cadastrarMouseExited
        this.jL_btn_medico_cadastrar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_cadastrarMouseExited

    private void jL_btn_medico_cadastrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_cadastrarMousePressed
        this.jL_btn_medico_cadastrar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_medico_cadastrarMousePressed

    private void jL_btn_medico_cadastrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_cadastrarMouseReleased
        this.jL_btn_medico_cadastrar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_cadastrarMouseReleased

    private void jL_btn_medico_editarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_editarMouseEntered
        this.jL_btn_medico_editar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_medico_editarMouseEntered

    private void jL_btn_medico_editarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_editarMouseExited
        this.jL_btn_medico_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_editarMouseExited

    private void jL_btn_medico_editarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_editarMousePressed
        this.jL_btn_medico_editar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_medico_editarMousePressed

    private void jL_btn_medico_editarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_editarMouseReleased
        this.jL_btn_medico_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_editarMouseReleased

    private void jL_btn_medico_excluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_excluirMouseEntered
        this.jL_btn_medico_excluir.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_medico_excluirMouseEntered

    private void jL_btn_medico_excluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_excluirMouseExited
        this.jL_btn_medico_excluir.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_excluirMouseExited

    private void jL_btn_medico_excluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_excluirMousePressed
        this.jL_btn_medico_excluir.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_medico_excluirMousePressed

    private void jL_btn_medico_excluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_excluirMouseReleased
        this.jL_btn_medico_excluir.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_medico_excluirMouseReleased

    private void jT_medico_pesquisarInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_medico_pesquisarInputFocusGained
        this.jT_medico_pesquisarInput.setText("");
    }//GEN-LAST:event_jT_medico_pesquisarInputFocusGained
//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//

//----------------------------------------------CADASTRAR MEDICO---------------------------------------------//   
    private void jL_btn_medico_cadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_cadastrarMouseClicked
        try {
            String nome = "";
            String crm = "";
            String espec = "";
            String telefone = "";
            String turno = "";

            if (this.jT_medico_nomeInput.getText().length() < 2) {
                MessageDialog.chamarDialogCancel("Nome deve conter ao menos 2 caracteres");

                //REQUESTING THE FOCUS
                this.jT_medico_nomeInput.requestFocus();

            } else {
                nome = this.jT_medico_nomeInput.getText();

                if (this.jFT_medico_crmInput.getText().replaceAll("\\D*", "").length() < 9) {
                    MessageDialog.chamarDialogCancel("CRM Invalido");

                    this.jFT_medico_crmInput.requestFocus();

                } else {
                    crm = this.jFT_medico_crmInput.getText();

                    if (this.jT_medico_especiaInput.getText().length() < 5 || this.jT_medico_especiaInput.getText().length() > 20) {
                        MessageDialog.chamarDialogCancel("Especialidade Invalida");

                        this.jT_medico_especiaInput.requestFocus();

                    } else {
                        espec = this.jT_medico_especiaInput.getText();

                        if (this.jFT_medico_teleInput.getText().replaceAll("\\D*", "").length() < 11) {
                            MessageDialog.chamarDialogCancel("Telefone Invalido");

                            this.jFT_medico_teleInput.requestFocus();

                        } else {
                            telefone = this.jFT_medico_teleInput.getText();

                            if (this.getTurnoSelected() == null) {
                                MessageDialog.chamarDialogCancel("Selecione um Turno");

                            } else {
                                turno = getTurnoSelected();

                                if (this.controladorM.InsertMedicoController(nome, crm, espec, telefone, turno)) {
                                    MessageDialog.chamarDialogOK("Medico Cadastrado com Secesso!");

                                    this.limparCampos();

                                } else {
                                    MessageDialog.chamarDialogCancel("Medico com esse CRM ja Cadastrado");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        } finally {
            this.carregaTabelaMedico();
        }
    }//GEN-LAST:event_jL_btn_medico_cadastrarMouseClicked
//----------------------------------------------CADASTRAR MEDICO---------------------------------------------//   

//----------------------------------------------CLICANDO JTABLE----------------------------------------------//
    private void jT_medicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_medicoMouseClicked
        if (this.jT_medico.getSelectedRow() != -1) {

            String nome = this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 1).toString();
            String crm = this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 2).toString();
            String especialidade = this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 3).toString();
            String telefone = this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 4).toString();
            String turno = this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 5).toString();

            this.jT_medico_nomeInput.setText(nome);
            this.jFT_medico_crmInput.setText(crm);
            this.jT_medico_especiaInput.setText(especialidade);
            this.jFT_medico_teleInput.setText(telefone);

            this.buttonGroup.clearSelection();
            if (turno.equals("Vespertino")) {
                this.jR_btn_vespertino.setSelected(true);
            } else {
                this.jR_btn_matutino.setSelected(true);
            }
        }
    }//GEN-LAST:event_jT_medicoMouseClicked
//----------------------------------------------CLICANDO JTABLE----------------------------------------------//

//----------------------------------------------EDITAR MEDICO------------------------------------------------//
    private void jL_btn_medico_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_editarMouseClicked
        try {
            int id = 0;
            String nome = "";
            String crm = "";
            String espec = "";
            String telefone = "";
            String turno = "";

            if (this.jT_medico.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione um Médico para Alterar");

            } else {
                id = Integer.parseInt(this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 0).toString());

                if (this.jT_medico_nomeInput.getText().length() < 2) {
                    MessageDialog.chamarDialogCancel("Nome deve conter ao menos 2 caracteres");
                    this.jT_medico_nomeInput.requestFocus();

                } else {
                    nome = this.jT_medico_nomeInput.getText();

                    if (this.jFT_medico_crmInput.getText().replaceAll("\\D*", "").length() < 9) {
                        MessageDialog.chamarDialogCancel("CRM Invalido");
                        this.jFT_medico_crmInput.requestFocus();

                    } else {
                        crm = this.jFT_medico_crmInput.getText();

                        if (this.jT_medico_especiaInput.getText().length() < 5 || this.jT_medico_especiaInput.getText().length() > 15) {
                            MessageDialog.chamarDialogCancel("Especialidade Invalida");
                            this.jT_medico_especiaInput.requestFocus();

                        } else {
                            espec = this.jT_medico_especiaInput.getText();

                            if (this.jFT_medico_teleInput.getText().replaceAll("\\D*", "").length() < 11) {
                                MessageDialog.chamarDialogCancel("Telefone Invalido");
                                this.jFT_medico_teleInput.requestFocus();

                            } else {
                                telefone = this.jFT_medico_teleInput.getText();

                                if (this.getTurnoSelected() == null) {
                                    MessageDialog.chamarDialogCancel("Selecione um Turno");

                                } else {
                                    turno = this.getTurnoSelected();
                                }

                                if (this.controladorM.UpdateMedicoController(id, nome, crm, espec, telefone, turno)) {
                                    MessageDialog.chamarDialogOK("Medico Alterado com Secesso!");

                                    this.carregaTabelaMedico();
                                    this.limparCampos();

                                } else {
                                    MessageDialog.chamarDialogCancel("Medico com esse CRM já Cadastrado");
                                }
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jL_btn_medico_editarMouseClicked
//----------------------------------------------EDITAR MEDICO------------------------------------------------//

//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//
    private void jL_btn_medico_limparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_limparMouseClicked
        this.limparCampos();
    }//GEN-LAST:event_jL_btn_medico_limparMouseClicked
//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//

//----------------------------------------------EXCLUIR MEDICO-----------------------------------------------//
    private void jL_btn_medico_excluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_excluirMouseClicked
        try {
            int id = 0;
            if (this.jT_medico.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione um Médico para Apagar");
            } else {

                id = Integer.parseInt(this.jT_medico.getValueAt(this.jT_medico.getSelectedRow(), 0).toString());

                OptionDialog opDialog = new OptionDialog(new javax.swing.JFrame(), true, "Tem certeza que deseja apagar este Médico ?");
                opDialog.setVisible(true);

                int resposta_usuario = opDialog.getReturnStatus();

                if (resposta_usuario == 1) {
                    if (this.controladorM.DeleteMedicoController(id)) {

                        this.limparCampos();

                        MessageDialog.chamarDialogOK("Médico Apagado com Sucesso!");
                    } else {
                        MessageDialog.chamarDialogCancel2("Médico tem uma consulta marcada.", "Cancele a consulta para apagar os dados.");
                    }
                } else {
                    this.jT_medico.clearSelection();
                }
            }
        } finally {
            this.carregaTabelaMedico();
        }
    }//GEN-LAST:event_jL_btn_medico_excluirMouseClicked
//----------------------------------------------EXCLUIR MEDICO-----------------------------------------------//

//----------------------------------------------PESQUISAR MEDICO---------------------------------------------//
    private void jT_medico_pesquisarInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_medico_pesquisarInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.pesquisarMedico();
        }
    }//GEN-LAST:event_jT_medico_pesquisarInputKeyPressed

    private void jL_btn_medico_pesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_medico_pesquisarMouseClicked
        this.pesquisarMedico();
    }//GEN-LAST:event_jL_btn_medico_pesquisarMouseClicked
//----------------------------------------------PESQUISAR MEDICO---------------------------------------------//

//----------------------------------------------RECARREGA TABELA---------------------------------------------//
    private void reloadTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reloadTableMouseClicked
        this.carregaTabelaMedico();
    }//GEN-LAST:event_reloadTableMouseClicked
//----------------------------------------------RECARREGA TABELA---------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JFormattedTextField jFT_medico_crmInput;
    private javax.swing.JFormattedTextField jFT_medico_teleInput;
    private javax.swing.JLabel jL_btn_medico_cadastrar;
    private javax.swing.JLabel jL_btn_medico_editar;
    private javax.swing.JLabel jL_btn_medico_excluir;
    private javax.swing.JLabel jL_btn_medico_limpar;
    private javax.swing.JLabel jL_btn_medico_pesquisar;
    private javax.swing.JLabel jL_medico_crm;
    private javax.swing.JLabel jL_medico_especi;
    private javax.swing.JLabel jL_medico_nome;
    private javax.swing.JLabel jL_medico_tel;
    private javax.swing.JLabel jL_medico_titulo;
    private javax.swing.JLabel jL_medico_turno;
    private javax.swing.JPanel jP_card2_medico;
    private javax.swing.JRadioButton jR_btn_matutino;
    private javax.swing.JRadioButton jR_btn_vespertino;
    private javax.swing.JScrollPane jScrollPane_table_medico;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jT_medico;
    private javax.swing.JTextField jT_medico_especiaInput;
    private javax.swing.JTextField jT_medico_nomeInput;
    private javax.swing.JTextField jT_medico_pesquisarInput;
    private javax.swing.JLabel reloadTable;
    // End of variables declaration//GEN-END:variables
}
