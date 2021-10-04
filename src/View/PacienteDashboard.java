package View;

import Controller.PacienteController;
import Model.Paciente;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class PacienteDashboard extends javax.swing.JPanel {

    //Controlador
    private final PacienteController controladorP;

    //Editor to change the DateChooser Parameters
    JTextFieldDateEditor dateChooserEditor;

    public PacienteDashboard() {
        //Declarar TimeZone para nao ter problemas na hora de enviar a data para DB
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        initComponents();

        this.controladorP = new PacienteController();

        this.setTables();
        this.setJDateChooser();
        this.carregaTabelaPaciente();
        this.setMask();
    }

//----------------------------------------------LIMPAR CAMPOS-------------------------------------------------//
    private void limparCampos() {
        this.jT_paci_nomeInput.setText("");
        this.jT_paci_enderecoInput.setText("");
        this.jFT_paci_teleInput.setValue(null);
        this.setMask();
        this.jDateChooser_paci_bDate.setDate(null);
        this.jT_paciente.clearSelection();
        this.jT_paci_pesquisarInput.setText("Pesquisar");

        //Remove o foco de todos os objetos do jFrame
        this.getRootPane().requestFocus();
    }
//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//

//----------------------------------------------SETTING THE TABLES-------------------------------------------//
    private void setTables() {
        this.jT_paciente.getTableHeader().setDefaultRenderer(new Dashboard.HeaderColor()); //change the header color
        this.jT_paciente.setFillsViewportHeight(true); //Make the table ocupy the entire scroll pane
        this.jT_paciente.setSelectionBackground(new Color(107, 207, 206));

        //Removendo bordas
        this.jScrollPane_table_paci.setBorder(createEmptyBorder());
        //Mudando as cores da barra Vertical da tabela
        this.jScrollPane_table_paci.getVerticalScrollBar().setBackground(new Color(196, 243, 251));

        this.jScrollPane_table_paci.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                //Mudando a cor da barra Vertical da tabela
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
    private void carregaTabelaPaciente() {
        DefaultTableModel modelo = (DefaultTableModel) this.jT_paciente.getModel();
        modelo.setNumRows(0);

        ArrayList<Paciente> minhalista = new ArrayList<>();
        minhalista = this.controladorP.getPacienteListController();

        for (Paciente a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getTelefone(),
                a.getEndereco(),
                a.getData_Nasc()
            });
        }
    }
//----------------------------------------------CARREGA TABLE PACIENTE---------------------------------------//  

//----------------------------------------------METODO PARA PESQUISAR----------------------------------------//
    private void pesquisarPaciente() {
        String searchName = this.jT_paci_pesquisarInput.getText();
        try {
            if (this.controladorP.getSearchPacienteController(searchName) == null) {
                MessageDialog.chamarDialogCancel("Nenhum Paciente Encontrado com esse Nome");

            } else {
                ShowPacienteSearchJTable(searchName);
            }
        } catch (Exception e) {
        }
        this.jT_paci_pesquisarInput.setText("Pesquisar");
    }
//----------------------------------------------METODO PARA PESQUISAR----------------------------------------//

//----------------------------------------------CARREGA TABLE PESQUISA---------------------------------------//     
    private void ShowPacienteSearchJTable(String nome) {

        ArrayList<Paciente> list = this.controladorP.getSearchPacienteController(nome);
        DefaultTableModel model = (DefaultTableModel) this.jT_paciente.getModel();
        model.setNumRows(0);

        for (Paciente a : list) {
            model.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getTelefone(),
                a.getEndereco(),
                a.getData_Nasc(),});
        }
    }
//----------------------------------------------CARREGA TABLE PESQUISA---------------------------------------//     

//----------------------------------------------SETTING THE DATE CHOOSER-------------------------------------//
    private void setJDateChooser() {
        //Mudando o Icone do Botao
        this.jDateChooser_paci_bDate.setIcon(new ImageIcon(getClass().getResource("/Img/calendar.png")));

        this.dateChooserEditor = ((JTextFieldDateEditor) this.jDateChooser_paci_bDate.getDateEditor());

        for (Component c : this.jDateChooser_paci_bDate.getComponents()) {
            ((JComponent) c).setBackground(new Color(107, 207, 206));
        }
        /*
            Class JTextFieldDateEditor extends JFormattedTextField. Hence the foreground color is a bound property which means that you can listen for changes to it. 
            Therefore you can add a PropertyChangeListener. 
            If the new foreground color is black, simply change it to white.
            dateChooserEditor.setForeground(new Color(255, 255, 255)); DOES NOT WORK
         */
        this.dateChooserEditor.addPropertyChangeListener("foreground", event -> {
            if (Color.BLACK.equals(event.getNewValue())) {
                this.dateChooserEditor.setForeground(Color.BLACK);
            }
        });
        this.dateChooserEditor.setBorder(null);

        //Pegando o componente do Botao para poder mudar o cursor para HAND CURSOR
        ((JButton) this.jDateChooser_paci_bDate.getComponents()[0]).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Colocando a data maxima igual a data atual.
        this.jDateChooser_paci_bDate.setMaxSelectableDate(new java.util.Date());
    }
//----------------------------------------------SETTING THE DATE CHOOSER-------------------------------------//

//----------------------------------------------SETTING THE MASKS--------------------------------------------//
    private void setMask() {
        //Usando esse metodo para colocar as mascara e ainda colocar o PLACEHOLDER
        try {
            MaskFormatter newM = new MaskFormatter("(##)#####-####");
            newM.setPlaceholderCharacter('_');
            DefaultFormatterFactory factory = new DefaultFormatterFactory(newM);
            this.jFT_paci_teleInput.setFormatterFactory(factory);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }
//----------------------------------------------SETTING THE MASKS--------------------------------------------//

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_card3_paciente = new javax.swing.JPanel();
        jL_paci_titulo = new javax.swing.JLabel();
        jScrollPane_table_paci = new javax.swing.JScrollPane();
        jT_paciente = new javax.swing.JTable();
        jL_paci_nome = new javax.swing.JLabel();
        jL_paci_tel = new javax.swing.JLabel();
        jL_paci_endereco = new javax.swing.JLabel();
        jL_paci_dataNasc = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jT_paci_enderecoInput = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jL_btn_paci_limpar = new javax.swing.JLabel();
        jL_btn_paci_cadastrar = new javax.swing.JLabel();
        jL_btn_paci_editar = new javax.swing.JLabel();
        jL_btn_paci_excluir = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jT_paci_pesquisarInput = new javax.swing.JTextField();
        jL_btn_paci_pesquisar = new javax.swing.JLabel();
        jFT_paci_teleInput = new javax.swing.JFormattedTextField();
        jDateChooser_paci_bDate = new com.toedter.calendar.JDateChooser();
        jSeparator18 = new javax.swing.JSeparator();
        jL_btn_refresh = new javax.swing.JLabel();
        jT_paci_nomeInput = new javax.swing.JTextField();

        jP_card3_paciente.setBackground(new java.awt.Color(240, 248, 255));
        jP_card3_paciente.setFont(new java.awt.Font("Yu Gothic", 0, 15)); // NOI18N
        jP_card3_paciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_paci_titulo.setFont(new java.awt.Font("Malgun Gothic", 1, 35)); // NOI18N
        jL_paci_titulo.setForeground(new java.awt.Color(0, 0, 0));
        jL_paci_titulo.setText("Gerenciar Pacientes");
        jP_card3_paciente.add(jL_paci_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 390, 76));

        jScrollPane_table_paci.setBackground(new java.awt.Color(0, 93, 102));
        jScrollPane_table_paci.setBorder(null);
        jScrollPane_table_paci.setForeground(new java.awt.Color(0, 93, 102));
        jScrollPane_table_paci.setFocusable(false);
        jScrollPane_table_paci.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jScrollPane_table_paci.setName(""); // NOI18N
        jScrollPane_table_paci.setOpaque(false);

        jT_paciente.setBackground(new java.awt.Color(196, 243, 251));
        jT_paciente.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        jT_paciente.setForeground(new java.awt.Color(0, 0, 0));
        jT_paciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id               ▲", "Nome", "Telefone", "Endereco", "Data Nasc."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT_paciente.setFocusable(false);
        jT_paciente.setRowHeight(28);
        jT_paciente.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jT_paciente.getTableHeader().setReorderingAllowed(false);
        jT_paciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_pacienteMouseClicked(evt);
            }
        });
        jScrollPane_table_paci.setViewportView(jT_paciente);
        if (jT_paciente.getColumnModel().getColumnCount() > 0) {
            jT_paciente.getColumnModel().getColumn(0).setResizable(false);
            jT_paciente.getColumnModel().getColumn(0).setPreferredWidth(0);
            jT_paciente.getColumnModel().getColumn(1).setResizable(false);
            jT_paciente.getColumnModel().getColumn(2).setResizable(false);
            jT_paciente.getColumnModel().getColumn(3).setResizable(false);
            jT_paciente.getColumnModel().getColumn(4).setResizable(false);
        }

        jP_card3_paciente.add(jScrollPane_table_paci, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, 864, 290));

        jL_paci_nome.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_paci_nome.setForeground(new java.awt.Color(0, 0, 0));
        jL_paci_nome.setText("Nome:");
        jP_card3_paciente.add(jL_paci_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 113, -1));

        jL_paci_tel.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_paci_tel.setForeground(new java.awt.Color(0, 0, 0));
        jL_paci_tel.setText("Telefone:");
        jP_card3_paciente.add(jL_paci_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 113, -1));

        jL_paci_endereco.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_paci_endereco.setForeground(new java.awt.Color(0, 0, 0));
        jL_paci_endereco.setText("Endereco:");
        jP_card3_paciente.add(jL_paci_endereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, -1));

        jL_paci_dataNasc.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_paci_dataNasc.setForeground(new java.awt.Color(0, 0, 0));
        jL_paci_dataNasc.setText("Data Nasc.:");
        jP_card3_paciente.add(jL_paci_dataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 113, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jP_card3_paciente.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 265, 10));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jP_card3_paciente.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 265, 10));

        jT_paci_enderecoInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_paci_enderecoInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_paci_enderecoInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_paci_enderecoInput.setBorder(null);
        jP_card3_paciente.add(jT_paci_enderecoInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 265, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jP_card3_paciente.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 265, 10));

        jL_btn_paci_limpar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_paci_limpar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_paci_limpar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_paci_limpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_paci_limpar.setText("Limpar Campos");
        jL_btn_paci_limpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_paci_limpar.setOpaque(true);
        jL_btn_paci_limpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_paci_limparMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_paci_limparMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_paci_limparMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_paci_limparMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_paci_limparMouseReleased(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_paci_limpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 580, 170, 40));

        jL_btn_paci_cadastrar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_paci_cadastrar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_paci_cadastrar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_paci_cadastrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_paci_cadastrar.setText("Novo");
        jL_btn_paci_cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_paci_cadastrar.setOpaque(true);
        jL_btn_paci_cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_paci_cadastrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_paci_cadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_paci_cadastrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_paci_cadastrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_paci_cadastrarMouseReleased(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_paci_cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 130, 40));

        jL_btn_paci_editar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_paci_editar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_paci_editar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_paci_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_paci_editar.setText("Editar");
        jL_btn_paci_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_paci_editar.setOpaque(true);
        jL_btn_paci_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_paci_editarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_paci_editarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_paci_editarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_paci_editarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_paci_editarMouseReleased(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_paci_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 130, 40));

        jL_btn_paci_excluir.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_paci_excluir.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_paci_excluir.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_paci_excluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_paci_excluir.setText("Excluir");
        jL_btn_paci_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_paci_excluir.setOpaque(true);
        jL_btn_paci_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_paci_excluirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_paci_excluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_paci_excluirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_paci_excluirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_paci_excluirMouseReleased(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_paci_excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 530, 130, 40));

        jSeparator13.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jP_card3_paciente.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 200, 10));

        jT_paci_pesquisarInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_paci_pesquisarInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_paci_pesquisarInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_paci_pesquisarInput.setText("Pesquisar");
        jT_paci_pesquisarInput.setBorder(null);
        jT_paci_pesquisarInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_paci_pesquisarInputFocusGained(evt);
            }
        });
        jT_paci_pesquisarInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_paci_pesquisarInputKeyPressed(evt);
            }
        });
        jP_card3_paciente.add(jT_paci_pesquisarInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 200, -1));

        jL_btn_paci_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/loupe.png"))); // NOI18N
        jL_btn_paci_pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_paci_pesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_paci_pesquisarMouseClicked(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_paci_pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 30, -1));

        jFT_paci_teleInput.setBackground(new java.awt.Color(107, 207, 206));
        jFT_paci_teleInput.setBorder(null);
        jFT_paci_teleInput.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFT_paci_teleInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFT_paci_teleInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jP_card3_paciente.add(jFT_paci_teleInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 265, 24));

        jDateChooser_paci_bDate.setBackground(new java.awt.Color(216, 223, 230));
        jDateChooser_paci_bDate.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser_paci_bDate.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jDateChooser_paci_bDate.setMaxSelectableDate(new java.util.Date(1641009714000L));
        jDateChooser_paci_bDate.setMinSelectableDate(new java.util.Date(-2208973898000L));
        jDateChooser_paci_bDate.setOpaque(false);
        jP_card3_paciente.add(jDateChooser_paci_bDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, 265, 24));

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));
        jP_card3_paciente.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, 265, 10));

        jL_btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh1.png"))); // NOI18N
        jL_btn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_refreshMouseClicked(evt);
            }
        });
        jP_card3_paciente.add(jL_btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 380, -1, -1));

        jT_paci_nomeInput.setBackground(new java.awt.Color(107, 207, 206));
        jT_paci_nomeInput.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jT_paci_nomeInput.setForeground(new java.awt.Color(0, 0, 0));
        jT_paci_nomeInput.setBorder(null);
        jP_card3_paciente.add(jT_paci_nomeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 265, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card3_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card3_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
//----------------------------------------------CLICANDO JTABLE----------------------------------------------//
    private void jT_pacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_pacienteMouseClicked
        if (this.jT_paciente.getSelectedRow() != -1) {
            String nome = this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 1).toString();
            String telefone = this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 2).toString();
            String endereco = this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 3).toString();

            //Convertendo java.sql.Date para java.util.Date
            String date = this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 4).toString();
            java.util.Date date1;
            try {
                //Convertendo a variavel DATE do tipo string que vem da tabela em java.util.Date para colocar no JCalendar
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                this.jDateChooser_paci_bDate.setDate(date1);
            } catch (ParseException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.jT_paci_nomeInput.setText(nome);
            this.jFT_paci_teleInput.setText(telefone);
            this.jT_paci_enderecoInput.setText(endereco);

        }
    }//GEN-LAST:event_jT_pacienteMouseClicked
//----------------------------------------------CLICANDO JTABLE----------------------------------------------//

//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//
    private void jL_btn_paci_limparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_limparMouseClicked
        this.limparCampos();
    }//GEN-LAST:event_jL_btn_paci_limparMouseClicked
//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//

//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//
    private void jL_btn_paci_limparMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_limparMouseEntered
        this.jL_btn_paci_limpar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_paci_limparMouseEntered

    private void jL_btn_paci_limparMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_limparMouseExited
        this.jL_btn_paci_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_limparMouseExited

    private void jL_btn_paci_limparMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_limparMousePressed
        this.jL_btn_paci_limpar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_paci_limparMousePressed

    private void jL_btn_paci_limparMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_limparMouseReleased
        this.jL_btn_paci_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_limparMouseReleased
//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//

//----------------------------------------------CADASTRAR PACIENTE---------------------------------------------//
    private void jL_btn_paci_cadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_cadastrarMouseClicked
        try {
            String nome = "";
            String telefone;
            String endereco = "";
            String data_Nasc;

            if (this.jT_paci_nomeInput.getText().length() < 2) {
                MessageDialog.chamarDialogCancel("Nome deve conter ao menos 2 caracteres");
                this.jT_paci_nomeInput.requestFocus();

            } else {
                nome = this.jT_paci_nomeInput.getText();

                if (this.jFT_paci_teleInput.getText().replaceAll("\\D*", "").length() < 11) {
                    MessageDialog.chamarDialogCancel("Numero Invalido");
                    this.jFT_paci_teleInput.requestFocus();

                } else {
                    telefone = this.jFT_paci_teleInput.getText();

                    if (this.jT_paci_enderecoInput.getText().length() < 5) {
                        MessageDialog.chamarDialogCancel("Endereco Invalido");
                        this.jT_paci_enderecoInput.requestFocus();

                    } else {
                        endereco = this.jT_paci_enderecoInput.getText();

                        if (this.jDateChooser_paci_bDate.getDate() == null) {
                            MessageDialog.chamarDialogCancel("Data Invalida");
                            this.jDateChooser_paci_bDate.requestFocus();

                        } else {
                            //Pegando variavel do tipo DATE do JCalendar
                            java.util.Date dataTemp = this.jDateChooser_paci_bDate.getDate();
                            //Formatando para aparecer só a data, ja que a hora sera escolhida separadamente
                            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
                            //Colocando a data como string para dps poder manipular
                            data_Nasc = formatador.format(dataTemp);

                            if (this.controladorP.InsertPacienteController(nome, telefone, endereco, data_Nasc)) {
                                MessageDialog.chamarDialogOK("Paciente Cadastrado com sucesso!");

                                this.carregaTabelaPaciente();
                                this.limparCampos();

                            } else {
                                MessageDialog.chamarDialogCancel("Paciente ja Cadastrado");
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        } catch (ParseException ex) {
            //Catch para pegar erros na conversao da data.
        }
    }//GEN-LAST:event_jL_btn_paci_cadastrarMouseClicked
//----------------------------------------------CADASTRAR PACIENTE---------------------------------------------//

//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//
    private void jL_btn_paci_cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_cadastrarMouseEntered
        this.jL_btn_paci_cadastrar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_paci_cadastrarMouseEntered

    private void jL_btn_paci_cadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_cadastrarMouseExited
        this.jL_btn_paci_cadastrar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_cadastrarMouseExited

    private void jL_btn_paci_cadastrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_cadastrarMousePressed
        this.jL_btn_paci_cadastrar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_paci_cadastrarMousePressed

    private void jL_btn_paci_cadastrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_cadastrarMouseReleased
        this.jL_btn_paci_cadastrar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_cadastrarMouseReleased
//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//

//----------------------------------------------EDITAR PACIENTE------------------------------------------------//
    private void jL_btn_paci_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_editarMouseClicked
        try {
            int id = 0;
            String nome = "";
            String telefone;
            String endereco = "";
            String data_Nasc;

            if (this.jT_paciente.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione um Paciente para Alterar");

            } else {
                id = Integer.parseInt(this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 0).toString());

                if (this.jT_paci_nomeInput.getText().length() < 2) {
                    MessageDialog.chamarDialogCancel("Nome deve conter ao menos 2 caracteres");
                    this.jT_paci_nomeInput.requestFocus();
                } else {
                    nome = this.jT_paci_nomeInput.getText();

                    if (this.jFT_paci_teleInput.getText().replaceAll("\\D*", "").length() < 11) {
                        MessageDialog.chamarDialogCancel("Numero Invalido");
                        this.jFT_paci_teleInput.requestFocus();

                    } else {
                        telefone = this.jFT_paci_teleInput.getText();

                        if (this.jT_paci_enderecoInput.getText().length() < 5) {
                            MessageDialog.chamarDialogCancel("Endereco Invalido");
                            this.jT_paci_enderecoInput.requestFocus();

                        } else {
                            endereco = this.jT_paci_enderecoInput.getText();

                            if (this.jDateChooser_paci_bDate.getDate() == null) {
                                MessageDialog.chamarDialogCancel("Data Invalida");
                                this.jDateChooser_paci_bDate.requestFocus();

                            } else {
                                //Pegando variavel do tipo DATE do JCalendar
                                java.util.Date dataTemp = this.jDateChooser_paci_bDate.getDate();
                                //Formatando para aparecer só a data, ja que a hora sera escolhida separadamente
                                SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
                                //Colocando a data como string para dps poder manipular
                                data_Nasc = formatador.format(dataTemp);

                                if (this.controladorP.UpdatePacienteController(id, nome, telefone, endereco, data_Nasc)) {
                                    MessageDialog.chamarDialogOK("Paciente Alterado com Sucesso!");

                                    this.carregaTabelaPaciente();
                                    this.limparCampos();

                                } else {
                                    //Caso o usuario tente mudar o nome de um paciente para igual a de outro paciente (assumindo que o nome sera Unique Key do paciente)
                                    MessageDialog.chamarDialogCancel("Paciente com esse nome ja Cadastrado!");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jL_btn_paci_editarMouseClicked
//----------------------------------------------EDITAR PACIENTE------------------------------------------------//

//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//
    private void jL_btn_paci_editarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_editarMouseEntered
        this.jL_btn_paci_editar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_paci_editarMouseEntered

    private void jL_btn_paci_editarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_editarMouseExited
        this.jL_btn_paci_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_editarMouseExited

    private void jL_btn_paci_editarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_editarMousePressed
        this.jL_btn_paci_editar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_paci_editarMousePressed

    private void jL_btn_paci_editarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_editarMouseReleased
        this.jL_btn_paci_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_editarMouseReleased
//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//

//----------------------------------------------EXCLUIR PACIENTE-----------------------------------------------//
    private void jL_btn_paci_excluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_excluirMouseClicked
        try {
            int id = 0;
            if (this.jT_paciente.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione um Paciente para Apagar");

            } else {

                id = Integer.parseInt(this.jT_paciente.getValueAt(this.jT_paciente.getSelectedRow(), 0).toString());

                OptionDialog opDialog = new OptionDialog(new javax.swing.JFrame(), true, "Tem certeza que deseja apagar este Paciente?");
                opDialog.setVisible(true);

                int resposta_usuario = opDialog.getReturnStatus();

                if (resposta_usuario == 1) {
                    if (this.controladorP.DeletePacienteController(id)) {

                        this.limparCampos();

                        MessageDialog.chamarDialogOK("Paciente Apagado com Sucesso!");

                    } else {
                        MessageDialog.chamarDialogCancel2("Paciente tem uma consulta marcada.", "Cancele a consulta para apagar os dados.");
                    }
                } else {
                    this.jT_paciente.clearSelection();
                }
            }
        } finally {
            this.carregaTabelaPaciente();
        }
    }//GEN-LAST:event_jL_btn_paci_excluirMouseClicked
//----------------------------------------------EXCLUIR PACIENTE-----------------------------------------------//

//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//
    private void jL_btn_paci_excluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_excluirMouseEntered
        this.jL_btn_paci_excluir.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_paci_excluirMouseEntered

    private void jL_btn_paci_excluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_excluirMouseExited
        this.jL_btn_paci_excluir.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_excluirMouseExited

    private void jL_btn_paci_excluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_excluirMousePressed
        this.jL_btn_paci_excluir.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_paci_excluirMousePressed

    private void jL_btn_paci_excluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_excluirMouseReleased
        this.jL_btn_paci_excluir.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_paci_excluirMouseReleased

    private void jT_paci_pesquisarInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_paci_pesquisarInputFocusGained
        this.jT_paci_pesquisarInput.setText("");
    }//GEN-LAST:event_jT_paci_pesquisarInputFocusGained
//----------------------------------------------MUDANCA DOS BOTOES---------------------------------------------//

//----------------------------------------------PESQUISAR PACIENTE---------------------------------------------//
    private void jL_btn_paci_pesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_paci_pesquisarMouseClicked
        this.pesquisarPaciente();
    }//GEN-LAST:event_jL_btn_paci_pesquisarMouseClicked
//----------------------------------------------PESQUISAR PACIENTE--------------------------------------------//

//----------------------------------------------RECARREGA TABELA----------------------------------------------//
    private void jL_btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_refreshMouseClicked
        this.carregaTabelaPaciente();
    }//GEN-LAST:event_jL_btn_refreshMouseClicked
//----------------------------------------------RECARREGA TABELA----------------------------------------------//

//----------------------------------------------PESQUISAR PACIENTE--------------------------------------------//
    private void jT_paci_pesquisarInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_paci_pesquisarInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.pesquisarPaciente();
        }
    }//GEN-LAST:event_jT_paci_pesquisarInputKeyPressed
//----------------------------------------------PESQUISAR PACIENTE--------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser_paci_bDate;
    private javax.swing.JFormattedTextField jFT_paci_teleInput;
    private javax.swing.JLabel jL_btn_paci_cadastrar;
    private javax.swing.JLabel jL_btn_paci_editar;
    private javax.swing.JLabel jL_btn_paci_excluir;
    private javax.swing.JLabel jL_btn_paci_limpar;
    private javax.swing.JLabel jL_btn_paci_pesquisar;
    private javax.swing.JLabel jL_btn_refresh;
    private javax.swing.JLabel jL_paci_dataNasc;
    private javax.swing.JLabel jL_paci_endereco;
    private javax.swing.JLabel jL_paci_nome;
    private javax.swing.JLabel jL_paci_tel;
    private javax.swing.JLabel jL_paci_titulo;
    private javax.swing.JPanel jP_card3_paciente;
    private javax.swing.JScrollPane jScrollPane_table_paci;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jT_paci_enderecoInput;
    private javax.swing.JTextField jT_paci_nomeInput;
    private javax.swing.JTextField jT_paci_pesquisarInput;
    private javax.swing.JTable jT_paciente;
    // End of variables declaration//GEN-END:variables
}
