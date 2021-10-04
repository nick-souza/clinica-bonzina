package View;

import Controller.ConsultaController;
import Model.Consulta;
import View.Dashboard.HeaderColor;
import java.awt.Color;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class ConsultaDashboard extends javax.swing.JPanel {

    //Editor to change the DateChooser Parameters
    JTextFieldDateEditor dateChooserEditor;
    private final ConsultaController controladorC;

    public ConsultaDashboard() {
        //Declarar TimeZone para nao ter problemas na hora de enviar a data para DB
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        initComponents();
        this.controladorC = new ConsultaController();

        this.setTables();
        this.setJDateChooser();
        this.setJCombobox();

        this.carregaTabelaConsulta();
    }

//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//
    private void limparCampos() {
        this.jL_consulta_paciNome.setText("");
        this.jL_consulta_paci_Id.setText("");
        this.jL_consulta_medNome.setText("");
        this.jL_consulta_med_Id.setText("");

        this.jComboBox_sala.setSelectedItem(null);
        this.jComboBox_hora.setSelectedItem(null);

        this.jDateChooser_data.setDate(null);

        this.jT_consulta.clearSelection();

        //Removes the focus from every object in the frame.
        this.getRootPane().requestFocus();
    }
//----------------------------------------------LIMPAR CAMPOS------------------------------------------------//

//----------------------------------------------SETTING THE TABLES-------------------------------------------//
    private void setTables() {
        this.jT_consulta.getTableHeader().setDefaultRenderer(new HeaderColor());
        this.jT_consulta.setFillsViewportHeight(true);
        this.jT_consulta.setSelectionBackground(new Color(107, 207, 206));

        this.jScrollPane_consulta.setBorder(createEmptyBorder());

        //Mudando as cores da barra Vertical da tabela
        this.jScrollPane_consulta.getVerticalScrollBar().setBackground(new Color(196, 243, 251));

        this.jScrollPane_consulta.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

//      Para fazer as duas ultimas colunas da jTable nao aparecerem para o usuario, mas para sempre podermos ter os ids
        TableColumnModel tcm = jT_consulta.getColumnModel();
        tcm.removeColumn(tcm.getColumn(6));
        tcm.removeColumn(tcm.getColumn(6));
    }
//----------------------------------------------SETTING THE TABLES-------------------------------------------//

//----------------------------------------------CARREGA TABLE PACIENTE---------------------------------------//
    public void carregaTabelaConsulta() {
        DefaultTableModel modelo = (DefaultTableModel) this.jT_consulta.getModel();
        modelo.setNumRows(0);

        ArrayList<Consulta> minhalista = new ArrayList<>();
        if (this.controladorC.getConsultaListController() == null) {
            System.out.println("Error carregarTabelaConsulta");

        } else {
            minhalista = this.controladorC.getConsultaListController();

            for (Consulta a : minhalista) {
                modelo.addRow(new Object[]{
                    a.getConsultaId(),
                    a.getConsultaMedNome(),
                    a.getConsultaPaciNome(),
                    a.getConsultaData(),
                    a.getConsultaHora(),
                    a.getConsultaSala(),
                    //Fazendo duas colunas extras para ter od ids guardados, mas nao vai aparecer na view
                    a.getConsultaMedId(),
                    a.getConsultaPaciId(),});
            }
        }

    }
//----------------------------------------------CARREGA TABLE PACIENTE---------------------------------------//  

//----------------------------------------------SETTING THE DATE CHOOSER-------------------------------------//
    private void setJDateChooser() {
        this.jDateChooser_data.setIcon(new ImageIcon(getClass().getResource("/Img/calendar.png")));
        this.dateChooserEditor = ((JTextFieldDateEditor) this.jDateChooser_data.getDateEditor());

        for (Component c : this.jDateChooser_data.getComponents()) {
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
        ((JButton) this.jDateChooser_data.getComponents()[0]).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //Colocando a data maxima igual a data atual.
        this.jDateChooser_data.setMinSelectableDate(new java.util.Date());
    }
//----------------------------------------------SETTING THE DATE CHOOSER-------------------------------------//

//----------------------------------------------SETTING THE COMBOBOXES---------------------------------------//
    private void setJCombobox() {
        this.jComboBox_sala.setSelectedItem(null);
        this.jComboBox_hora.setSelectedItem(null);

        loopThroughCombo(this.jComboBox_sala);
        loopThroughCombo(this.jComboBox_hora);
    }

    private JComboBox loopThroughCombo(JComboBox combBoxName) {
        //Loop para poder pegar todos os itens do JComboBox e mudar a cor de fundo
        Object child = combBoxName.getAccessibleContext().getAccessibleChild(0);

        BasicComboPopup popup = (BasicComboPopup) child;
        JList list = popup.getList();
        //Fazer uma JList para poder mudar a cor de todos os itens/
        list.setSelectionBackground(new Color(196, 243, 251));
        return null;
    }
//----------------------------------------------SETTING THE COMBOBOXES---------------------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_card4_consulta = new javax.swing.JPanel();
        jL_consu_titulo = new javax.swing.JLabel();
        jScrollPane_consulta = new javax.swing.JScrollPane();
        jT_consulta = new javax.swing.JTable();
        jL_consu_medico = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jL_btn_consu_limpar = new javax.swing.JLabel();
        jL_btn_consu_novo = new javax.swing.JLabel();
        jL_btn_consu_editar = new javax.swing.JLabel();
        jL_btn_consu_cancelar = new javax.swing.JLabel();
        jL_consu_paciente = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jDateChooser_data = new com.toedter.calendar.JDateChooser();
        jL_consu_data = new javax.swing.JLabel();
        jComboBox_hora = new javax.swing.JComboBox<>();
        jL_consu_hora = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jL_consulta_paci_Id = new javax.swing.JLabel();
        jL_consulta_paciNome = new javax.swing.JLabel();
        jL_consulta_medNome = new javax.swing.JLabel();
        jL_consulta_med_Id = new javax.swing.JLabel();
        jComboBox_sala = new javax.swing.JComboBox<>();
        jL_consu_sala = new javax.swing.JLabel();

        jP_card4_consulta.setBackground(new java.awt.Color(240, 248, 255));
        jP_card4_consulta.setLayout(null);

        jL_consu_titulo.setFont(new java.awt.Font("Malgun Gothic", 1, 35)); // NOI18N
        jL_consu_titulo.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consu_titulo.setText("Gerenciar Consultas");
        jP_card4_consulta.add(jL_consu_titulo);
        jL_consu_titulo.setBounds(8, 8, 864, 76);

        jScrollPane_consulta.setBackground(new java.awt.Color(0, 93, 102));
        jScrollPane_consulta.setBorder(null);
        jScrollPane_consulta.setForeground(new java.awt.Color(0, 93, 102));
        jScrollPane_consulta.setFocusable(false);
        jScrollPane_consulta.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jScrollPane_consulta.setName(""); // NOI18N
        jScrollPane_consulta.setOpaque(false);

        jT_consulta.setBackground(new java.awt.Color(196, 243, 251));
        jT_consulta.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        jT_consulta.setForeground(new java.awt.Color(0, 0, 0));
        jT_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Medico", "Paciente", "Data           ▲", "Hora          ▲", "Sala", "Med_Id", "Paci_Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT_consulta.setFocusable(false);
        jT_consulta.setRowHeight(28);
        jT_consulta.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jT_consulta.getTableHeader().setReorderingAllowed(false);
        jT_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_consultaMouseClicked(evt);
            }
        });
        jScrollPane_consulta.setViewportView(jT_consulta);
        if (jT_consulta.getColumnModel().getColumnCount() > 0) {
            jT_consulta.getColumnModel().getColumn(0).setResizable(false);
            jT_consulta.getColumnModel().getColumn(0).setPreferredWidth(0);
            jT_consulta.getColumnModel().getColumn(1).setResizable(false);
            jT_consulta.getColumnModel().getColumn(2).setResizable(false);
            jT_consulta.getColumnModel().getColumn(3).setResizable(false);
            jT_consulta.getColumnModel().getColumn(3).setPreferredWidth(0);
            jT_consulta.getColumnModel().getColumn(4).setResizable(false);
            jT_consulta.getColumnModel().getColumn(4).setPreferredWidth(0);
            jT_consulta.getColumnModel().getColumn(5).setResizable(false);
            jT_consulta.getColumnModel().getColumn(5).setPreferredWidth(0);
            jT_consulta.getColumnModel().getColumn(6).setResizable(false);
            jT_consulta.getColumnModel().getColumn(7).setResizable(false);
        }

        jP_card4_consulta.add(jScrollPane_consulta);
        jScrollPane_consulta.setBounds(8, 90, 864, 310);

        jL_consu_medico.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consu_medico.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_medico.setText("Médico:");
        jP_card4_consulta.add(jL_consu_medico);
        jL_consu_medico.setBounds(30, 470, 90, 24);

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        jP_card4_consulta.add(jSeparator15);
        jSeparator15.setBounds(130, 550, 265, 10);

        jL_btn_consu_limpar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_consu_limpar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_consu_limpar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_consu_limpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_consu_limpar.setText("Limpar Campos");
        jL_btn_consu_limpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_consu_limpar.setOpaque(true);
        jL_btn_consu_limpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_consu_limparMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_consu_limparMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_consu_limparMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_consu_limparMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_consu_limparMouseReleased(evt);
            }
        });
        jP_card4_consulta.add(jL_btn_consu_limpar);
        jL_btn_consu_limpar.setBounds(670, 590, 170, 40);

        jL_btn_consu_novo.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_consu_novo.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_consu_novo.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_consu_novo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_consu_novo.setText("Novo ");
        jL_btn_consu_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_consu_novo.setOpaque(true);
        jL_btn_consu_novo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_consu_novoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_consu_novoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_consu_novoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_consu_novoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_consu_novoMouseReleased(evt);
            }
        });
        jL_btn_consu_novo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jL_btn_consu_novoKeyPressed(evt);
            }
        });
        jP_card4_consulta.add(jL_btn_consu_novo);
        jL_btn_consu_novo.setBounds(710, 430, 130, 40);

        jL_btn_consu_editar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_consu_editar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_consu_editar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_consu_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_consu_editar.setText("Editar");
        jL_btn_consu_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_consu_editar.setOpaque(true);
        jL_btn_consu_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_consu_editarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_consu_editarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_consu_editarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_consu_editarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_consu_editarMouseReleased(evt);
            }
        });
        jP_card4_consulta.add(jL_btn_consu_editar);
        jL_btn_consu_editar.setBounds(710, 480, 130, 40);

        jL_btn_consu_cancelar.setBackground(new java.awt.Color(107, 207, 206));
        jL_btn_consu_cancelar.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_btn_consu_cancelar.setForeground(new java.awt.Color(0, 0, 0));
        jL_btn_consu_cancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_btn_consu_cancelar.setText("Cancelar");
        jL_btn_consu_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_btn_consu_cancelar.setOpaque(true);
        jL_btn_consu_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_btn_consu_cancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_btn_consu_cancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_btn_consu_cancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_btn_consu_cancelarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jL_btn_consu_cancelarMouseReleased(evt);
            }
        });
        jP_card4_consulta.add(jL_btn_consu_cancelar);
        jL_btn_consu_cancelar.setBounds(710, 530, 130, 40);

        jL_consu_paciente.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consu_paciente.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_paciente.setText("Paciente:");
        jP_card4_consulta.add(jL_consu_paciente);
        jL_consu_paciente.setBounds(30, 420, 90, 24);

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        jP_card4_consulta.add(jSeparator16);
        jSeparator16.setBounds(130, 450, 265, 10);

        jDateChooser_data.setBackground(new java.awt.Color(107, 207, 206));
        jDateChooser_data.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser_data.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jDateChooser_data.setMaxSelectableDate(new java.util.Date(1641009714000L));
        jDateChooser_data.setMinSelectableDate(new java.util.Date(1619841714000L));
        jDateChooser_data.setOpaque(false);
        jP_card4_consulta.add(jDateChooser_data);
        jDateChooser_data.setBounds(130, 520, 265, 24);

        jL_consu_data.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consu_data.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_data.setText("Data:");
        jP_card4_consulta.add(jL_consu_data);
        jL_consu_data.setBounds(30, 520, 80, 24);

        jComboBox_hora.setBackground(new java.awt.Color(107, 207, 206));
        jComboBox_hora.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jComboBox_hora.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox_hora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00" }));
        jComboBox_hora.setToolTipText("");
        jComboBox_hora.setBorder(null);
        jComboBox_hora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_card4_consulta.add(jComboBox_hora);
        jComboBox_hora.setBounds(130, 570, 100, 24);

        jL_consu_hora.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consu_hora.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_hora.setText("Horário:");
        jP_card4_consulta.add(jL_consu_hora);
        jL_consu_hora.setBounds(30, 570, 80, 24);

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));
        jP_card4_consulta.add(jSeparator17);
        jSeparator17.setBounds(130, 500, 265, 10);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/refresh1.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jP_card4_consulta.add(jLabel1);
        jLabel1.setBounds(840, 400, 40, 36);

        jL_consulta_paci_Id.setBackground(new java.awt.Color(107, 207, 206));
        jL_consulta_paci_Id.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consulta_paci_Id.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_paci_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consulta_paci_Id.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jL_consulta_paci_Id.setOpaque(true);
        jP_card4_consulta.add(jL_consulta_paci_Id);
        jL_consulta_paci_Id.setBounds(130, 420, 50, 24);

        jL_consulta_paciNome.setBackground(new java.awt.Color(107, 207, 206));
        jL_consulta_paciNome.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consulta_paciNome.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_paciNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_consulta_paciNome.setOpaque(true);
        jL_consulta_paciNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_consulta_paciNomeMouseClicked(evt);
            }
        });
        jP_card4_consulta.add(jL_consulta_paciNome);
        jL_consulta_paciNome.setBounds(185, 420, 210, 24);

        jL_consulta_medNome.setBackground(new java.awt.Color(107, 207, 206));
        jL_consulta_medNome.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consulta_medNome.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_medNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_consulta_medNome.setOpaque(true);
        jL_consulta_medNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_consulta_medNomeMouseClicked(evt);
            }
        });
        jP_card4_consulta.add(jL_consulta_medNome);
        jL_consulta_medNome.setBounds(185, 470, 210, 24);

        jL_consulta_med_Id.setBackground(new java.awt.Color(107, 207, 206));
        jL_consulta_med_Id.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consulta_med_Id.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_med_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consulta_med_Id.setOpaque(true);
        jP_card4_consulta.add(jL_consulta_med_Id);
        jL_consulta_med_Id.setBounds(130, 470, 50, 24);

        jComboBox_sala.setBackground(new java.awt.Color(107, 207, 206));
        jComboBox_sala.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jComboBox_sala.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox_sala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sala 01", "Sala 02" }));
        jComboBox_sala.setToolTipText("");
        jComboBox_sala.setBorder(null);
        jComboBox_sala.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_card4_consulta.add(jComboBox_sala);
        jComboBox_sala.setBounds(284, 570, 110, 24);

        jL_consu_sala.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jL_consu_sala.setForeground(new java.awt.Color(0, 0, 0));
        jL_consu_sala.setText("Sala:");
        jP_card4_consulta.add(jL_consu_sala);
        jL_consu_sala.setBounds(240, 570, 40, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card4_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jP_card4_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

//----------------------------------------------BUTTON ANIMATIONS--------------------------------------------//
    private void jL_btn_consu_limparMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_limparMouseEntered
        this.jL_btn_consu_limpar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_consu_limparMouseEntered

    private void jL_btn_consu_limparMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_limparMouseExited
        this.jL_btn_consu_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_limparMouseExited

    private void jL_btn_consu_limparMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_limparMousePressed
        this.jL_btn_consu_limpar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_consu_limparMousePressed

    private void jL_btn_consu_limparMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_limparMouseReleased
        this.jL_btn_consu_limpar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_limparMouseReleased

    private void jL_btn_consu_novoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoMouseEntered
        this.jL_btn_consu_novo.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_consu_novoMouseEntered

    private void jL_btn_consu_novoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoMouseExited
        this.jL_btn_consu_novo.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_novoMouseExited

    private void jL_btn_consu_novoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoMousePressed
        this.jL_btn_consu_novo.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_consu_novoMousePressed

    private void jL_btn_consu_novoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoMouseReleased
        this.jL_btn_consu_novo.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_novoMouseReleased

    private void jL_btn_consu_editarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_editarMouseEntered
        this.jL_btn_consu_editar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_consu_editarMouseEntered

    private void jL_btn_consu_editarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_editarMouseExited
        this.jL_btn_consu_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_editarMouseExited

    private void jL_btn_consu_editarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_editarMousePressed
        this.jL_btn_consu_editar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_consu_editarMousePressed

    private void jL_btn_consu_editarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_editarMouseReleased
        this.jL_btn_consu_editar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_editarMouseReleased

    private void jL_btn_consu_cancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_cancelarMouseEntered
        this.jL_btn_consu_cancelar.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jL_btn_consu_cancelarMouseEntered

    private void jL_btn_consu_cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_cancelarMouseExited
        this.jL_btn_consu_cancelar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_cancelarMouseExited

    private void jL_btn_consu_cancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_cancelarMousePressed
        this.jL_btn_consu_cancelar.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jL_btn_consu_cancelarMousePressed

    private void jL_btn_consu_cancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_cancelarMouseReleased
        this.jL_btn_consu_cancelar.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jL_btn_consu_cancelarMouseReleased

    private void jL_consulta_paciNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_consulta_paciNomeMouseClicked
        PacienteTable pacienteTable = new PacienteTable(new javax.swing.JFrame(), true);
        pacienteTable.setLocationRelativeTo(this);
        pacienteTable.setVisible(true);
        String pacienteNome = pacienteTable.getPaciNome();
        String pacienteId = pacienteTable.getPaciId();
        if (pacienteNome == null) {
            this.jL_consulta_paciNome.setText("");
            this.jL_consulta_paci_Id.setText("");
        } else {
            this.jL_consulta_paciNome.setText(pacienteNome);
            this.jL_consulta_paci_Id.setText(pacienteId);
        }
    }//GEN-LAST:event_jL_consulta_paciNomeMouseClicked

    private void jL_consulta_medNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_consulta_medNomeMouseClicked
        MedicoTable medicoTable = new MedicoTable(new javax.swing.JFrame(), true);
        medicoTable.setLocationRelativeTo(this);
        medicoTable.setVisible(true);
        String medicoNome = medicoTable.getMedNome();
        String medicoId = medicoTable.getMedId();
        if (medicoNome == null) {
            this.jL_consulta_medNome.setText("");
            this.jL_consulta_med_Id.setText("");
        } else {
            this.jL_consulta_medNome.setText(medicoNome);
            this.jL_consulta_med_Id.setText(medicoId);
        }
    }//GEN-LAST:event_jL_consulta_medNomeMouseClicked

    private void jL_btn_consu_novoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jL_btn_consu_novoKeyPressed

    private void jL_btn_consu_novoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_novoMouseClicked
        try {
            int medicoId;
            int pacienteId;
            String sala;
            String data;
            String hora;

            if (this.jL_consulta_paciNome.getText().length() < 1) {
                MessageDialog.chamarDialogCancel("Selecione um paciente");

            } else {
                pacienteId = Integer.parseInt(this.jL_consulta_paci_Id.getText());

                if (this.jL_consulta_medNome.getText().length() < 1) {
                    MessageDialog.chamarDialogCancel("Selecione um médico");

                } else {
                    medicoId = Integer.parseInt(this.jL_consulta_med_Id.getText());
                    if (this.jDateChooser_data.getDate() == null) {
                        MessageDialog.chamarDialogCancel("Data inválida");

                    } else {
                        Date dataTemp = this.jDateChooser_data.getDate();
                        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");

                        data = formatador.format(dataTemp);

                        if (this.jComboBox_hora.getSelectedIndex() == -1) {
                            MessageDialog.chamarDialogCancel("Selecione uma sala");
                        } else {
                            hora = this.jComboBox_hora.getSelectedItem().toString();

                            if (this.jComboBox_sala.getSelectedIndex() == -1) {
                                MessageDialog.chamarDialogCancel("Selecione um horário");

                            } else {
                                sala = this.jComboBox_sala.getSelectedItem().toString();

                                if (this.controladorC.InsertConsultaController(medicoId, pacienteId, sala, data, hora)) {
                                    MessageDialog.chamarDialogOK("Consulta agendada com sucesso!");

                                    this.limparCampos();
                                } else {
                                    MessageDialog.chamarDialogCancel("Data/Sala não disponível!");
                                }

                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {

        } finally {
            this.carregaTabelaConsulta();
        }
    }//GEN-LAST:event_jL_btn_consu_novoMouseClicked

    private void jL_btn_consu_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_editarMouseClicked

        try {
            int medicoId;
            int pacienteId;
            String sala;
            String data;
            String hora;
            int consultaId;

            if (this.jT_consulta.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione uma Consulta para Alterar");

            } else {
                consultaId = Integer.parseInt(this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 0).toString());

                if (this.jL_consulta_paciNome.getText().length() < 1) {
                    MessageDialog.chamarDialogCancel("Selecione um paciente");

                } else {
                    pacienteId = Integer.parseInt(this.jL_consulta_paci_Id.getText());

                    if (this.jL_consulta_medNome.getText().length() < 1) {
                        MessageDialog.chamarDialogCancel("Selecione um médico");

                    } else {
                        medicoId = Integer.parseInt(this.jL_consulta_med_Id.getText());
                        if (this.jDateChooser_data.getDate() == null) {
                            MessageDialog.chamarDialogCancel("Data inválida");

                        } else {
                            Date dataTemp = this.jDateChooser_data.getDate();
                            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");

                            data = formatador.format(dataTemp);

                            if (this.jComboBox_hora.getSelectedIndex() == -1) {
                                MessageDialog.chamarDialogCancel("Selecione uma sala");
                            } else {
                                hora = this.jComboBox_hora.getSelectedItem().toString();

                                if (this.jComboBox_sala.getSelectedIndex() == -1) {
                                    MessageDialog.chamarDialogCancel("Selecione um horário");

                                } else {
                                    sala = this.jComboBox_sala.getSelectedItem().toString();

                                    if (this.controladorC.UpdateConsultaController(consultaId, medicoId, pacienteId, sala, data, hora)) {
                                        MessageDialog.chamarDialogOK("Consulta editada com sucesso!");

                                        this.limparCampos();
                                        this.carregaTabelaConsulta();

                                    } else {
                                        MessageDialog.chamarDialogCancel("Data/Sala não disponível!");
                                    }

                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jL_btn_consu_editarMouseClicked

    private void jT_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_consultaMouseClicked
        if (this.jT_consulta.getSelectedRow() != -1) {
            String id = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 0).toString();

            String medico = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 1).toString();
            this.jL_consulta_medNome.setText(medico);

            String paciente = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 2).toString();
            this.jL_consulta_paciNome.setText(paciente);

            String date = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 3).toString();
            java.util.Date date1;
            try {
                //Convertendo a variavel DATE do tipo string que vem da tabela em java.util.Date para colocar no JCalendar
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                this.jDateChooser_data.setDate(date1);
            } catch (ParseException ex) {
            }

            String hora = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 4).toString();
            this.jComboBox_hora.setSelectedItem(hora);
            String sala = this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 5).toString();
            try {
                if (sala.equals("Sala 01")) {
                    this.jComboBox_sala.setSelectedItem("Sala 01");
                }
                if (sala.equals("Sala 02")) {
                    this.jComboBox_sala.setSelectedItem("Sala 02");
                }
            } catch (Exception e) {
                System.out.println("Erro popular jComboBoxSala");
            }

            String med_id = this.jT_consulta.getModel().getValueAt(this.jT_consulta.getSelectedRow(), 6).toString();
            this.jL_consulta_med_Id.setText(med_id);

            String paci_id = this.jT_consulta.getModel().getValueAt(this.jT_consulta.getSelectedRow(), 7).toString();
            this.jL_consulta_paci_Id.setText(paci_id);
        }
    }//GEN-LAST:event_jT_consultaMouseClicked

    private void jL_btn_consu_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_cancelarMouseClicked
        int consultaId;

        try {

            if (this.jT_consulta.getSelectedRow() == -1) {
                MessageDialog.chamarDialogCancel("Primeiro Selecione uma Consulta para Alterar");

            } else {
                consultaId = Integer.parseInt(this.jT_consulta.getValueAt(this.jT_consulta.getSelectedRow(), 0).toString());

                OptionDialog opDialog = new OptionDialog(new javax.swing.JFrame(), true, "Tem certeza que deseja cancelar esta consulta?");
                opDialog.setVisible(true);

                int resposta_usuario = opDialog.getReturnStatus();

                if (resposta_usuario == 1) {
                    if (this.controladorC.DeleteConsultaController(consultaId)) {

                        MessageDialog.chamarDialogOK("Consulta cancelada com sucesso!");
                    } else {
                    }
                } else {
                    this.jT_consulta.clearSelection();
                }
            }
        } finally {
            this.carregaTabelaConsulta();
            this.limparCampos();
        }
    }//GEN-LAST:event_jL_btn_consu_cancelarMouseClicked

    private void jL_btn_consu_limparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_btn_consu_limparMouseClicked
        limparCampos();
    }//GEN-LAST:event_jL_btn_consu_limparMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        carregaTabelaConsulta();
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_hora;
    private javax.swing.JComboBox<String> jComboBox_sala;
    private com.toedter.calendar.JDateChooser jDateChooser_data;
    private javax.swing.JLabel jL_btn_consu_cancelar;
    private javax.swing.JLabel jL_btn_consu_editar;
    private javax.swing.JLabel jL_btn_consu_limpar;
    private javax.swing.JLabel jL_btn_consu_novo;
    private javax.swing.JLabel jL_consu_data;
    private javax.swing.JLabel jL_consu_hora;
    private javax.swing.JLabel jL_consu_medico;
    private javax.swing.JLabel jL_consu_paciente;
    private javax.swing.JLabel jL_consu_sala;
    private javax.swing.JLabel jL_consu_titulo;
    private javax.swing.JLabel jL_consulta_medNome;
    private javax.swing.JLabel jL_consulta_med_Id;
    private javax.swing.JLabel jL_consulta_paciNome;
    private javax.swing.JLabel jL_consulta_paci_Id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_card4_consulta;
    private javax.swing.JScrollPane jScrollPane_consulta;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JTable jT_consulta;
    // End of variables declaration//GEN-END:variables
}
