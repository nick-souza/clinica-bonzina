package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class Dashboard extends javax.swing.JFrame {

    //Variaveis para pegar a posicao do mouse para poder arrastar a janela undecorated
    private int mouseX;
    private int mouseY;

    //Card Layout para trocar da JPane
    CardLayout cardL;

    public Dashboard() {
        initComponents();

        this.addCards();
        this.data();
    }

//------------------------------------------------ADDING THE PANELS TO THE CARD------------------------------//
    private void addCards() {
        this.cardL = (CardLayout) (this.jP_cardPane.getLayout());
        this.jP_cardPane.add(new BemVindoDashboard(), "card1");
        this.jP_cardPane.add(new MedicoDashboard(), "card2");
        this.jP_cardPane.add(new PacienteDashboard(), "card3");
        this.jP_cardPane.add(new ConsultaDashboard(), "card4");

        clearSelected();
    }
//------------------------------------------------ADDING THE PANELS TO THE CARD------------------------------//

//------------------------------------------------METODOS PARA MOSTRAR TELA SLECIONADA-----------------------//
    private void clearSelected() {
        //Limpando a selecao
        this.medicoSelected.setVisible(false);
        this.pacienteSelected.setVisible(false);
        this.consultaSelected.setVisible(false);
    }

    private Component getVisibleCard() {
        //Metodo para pegar qual card esta selecionado
        for (Component c : this.jP_cardPane.getComponents()) {
            if (c.isVisible()) {
                return c;
            }
        }
        return null;
    }

    private void changeSelectedMenuColor() {
        Component visible = getVisibleCard();

        //If Else para mostrar somente quando certo card for selecionado
        if (visible.toString().contains("Medico")) {
            this.clearSelected();
            this.medicoSelected.setVisible(true);
        } else if (visible.toString().contains("Paciente")) {
            this.clearSelected();
            this.pacienteSelected.setVisible(true);
        } else if (visible.toString().contains("Consulta")) {
            this.clearSelected();
            this.consultaSelected.setVisible(true);
        }
    }
//------------------------------------------------METODOS PARA MOSTRAR TELA SLECIONADA-----------------------//

//------------------------------------------------LOOP PARA ATUALIZAR O RELOGIO------------------------------//
    private void data() {
        Runnable runnable = new Runnable() {
            //Formatter for java.time
            DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            
            @Override
            public void run() {
                while (true) {
                    //Having to use java.time to get the right timezone
                    
                    ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
                    String dateString = this.formatter.format(date);
                    
                    jL_data.setText(dateString);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t = new Thread(runnable);
        t.start();
    }
//------------------------------------------------LOOP PARA ATUALIZAR O RELOGIO------------------------------//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_turno = new javax.swing.ButtonGroup();
        jP_main = new javax.swing.JPanel();
        jP_menu = new javax.swing.JPanel();
        jL_logoMenu = new javax.swing.JLabel();
        jP_btn_medico = new javax.swing.JPanel();
        jL_medito_texto = new javax.swing.JLabel();
        jL_medico_icon = new javax.swing.JLabel();
        medicoSelected = new javax.swing.JLabel();
        jP_btn_paciente = new javax.swing.JPanel();
        jL_paciente_icon = new javax.swing.JLabel();
        jL_paciente_texto = new javax.swing.JLabel();
        pacienteSelected = new javax.swing.JLabel();
        jP_btn_consulta = new javax.swing.JPanel();
        jL_consulta_icon = new javax.swing.JLabel();
        jL_consulta_texto = new javax.swing.JLabel();
        consultaSelected = new javax.swing.JLabel();
        jP_btn_sair = new javax.swing.JPanel();
        jL_sair_icon = new javax.swing.JLabel();
        jL_sair_texto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jP_btn_consulta2 = new javax.swing.JPanel();
        jL_data = new javax.swing.JLabel();
        jP_cardPane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinica Bonzina");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/Img/medicine.png")));
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 1134, 678, 25, 25));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jP_main.setBackground(new java.awt.Color(240, 248, 255));
        jP_main.setForeground(new java.awt.Color(255, 255, 255));
        jP_main.setName(""); // NOI18N
        jP_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jP_menu.setBackground(new java.awt.Color(196, 243, 251));

        jL_logoMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_logoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/medicine.png"))); // NOI18N
        jL_logoMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_logoMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_logoMenuMouseClicked(evt);
            }
        });

        jP_btn_medico.setBackground(new java.awt.Color(107, 207, 206));
        jP_btn_medico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_btn_medico.setPreferredSize(new java.awt.Dimension(0, 64));
        jP_btn_medico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_btn_medicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jP_btn_medicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jP_btn_medicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jP_btn_medicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jP_btn_medicoMouseReleased(evt);
            }
        });

        jL_medito_texto.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_medito_texto.setForeground(new java.awt.Color(0, 0, 0));
        jL_medito_texto.setText("Médicos");

        jL_medico_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_medico_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doc48.png"))); // NOI18N

        medicoSelected.setBackground(new java.awt.Color(68, 133, 132));
        medicoSelected.setOpaque(true);

        javax.swing.GroupLayout jP_btn_medicoLayout = new javax.swing.GroupLayout(jP_btn_medico);
        jP_btn_medico.setLayout(jP_btn_medicoLayout);
        jP_btn_medicoLayout.setHorizontalGroup(
            jP_btn_medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_medicoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jL_medico_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_medito_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(medicoSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jP_btn_medicoLayout.setVerticalGroup(
            jP_btn_medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_medicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_medito_texto, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jL_medico_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_btn_medicoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(medicoSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jP_btn_paciente.setBackground(new java.awt.Color(107, 207, 206));
        jP_btn_paciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_btn_paciente.setPreferredSize(new java.awt.Dimension(0, 50));
        jP_btn_paciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_btn_pacienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jP_btn_pacienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jP_btn_pacienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jP_btn_pacienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jP_btn_pacienteMouseReleased(evt);
            }
        });

        jL_paciente_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_paciente_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/paci24.png"))); // NOI18N

        jL_paciente_texto.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_paciente_texto.setForeground(new java.awt.Color(0, 0, 0));
        jL_paciente_texto.setText("Pacientes");

        pacienteSelected.setBackground(new java.awt.Color(68, 133, 132));
        pacienteSelected.setOpaque(true);

        javax.swing.GroupLayout jP_btn_pacienteLayout = new javax.swing.GroupLayout(jP_btn_paciente);
        jP_btn_paciente.setLayout(jP_btn_pacienteLayout);
        jP_btn_pacienteLayout.setHorizontalGroup(
            jP_btn_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_pacienteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jL_paciente_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_paciente_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pacienteSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jP_btn_pacienteLayout.setVerticalGroup(
            jP_btn_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_btn_pacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_paciente_texto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jL_paciente_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_btn_pacienteLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(pacienteSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jP_btn_consulta.setBackground(new java.awt.Color(107, 207, 206));
        jP_btn_consulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_btn_consulta.setPreferredSize(new java.awt.Dimension(0, 64));
        jP_btn_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_btn_consultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jP_btn_consultaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jP_btn_consultaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jP_btn_consultaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jP_btn_consultaMouseReleased(evt);
            }
        });

        jL_consulta_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_consulta_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/calendar24.png"))); // NOI18N

        jL_consulta_texto.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_consulta_texto.setForeground(new java.awt.Color(0, 0, 0));
        jL_consulta_texto.setText("Consultas");

        consultaSelected.setBackground(new java.awt.Color(68, 133, 132));
        consultaSelected.setOpaque(true);

        javax.swing.GroupLayout jP_btn_consultaLayout = new javax.swing.GroupLayout(jP_btn_consulta);
        jP_btn_consulta.setLayout(jP_btn_consultaLayout);
        jP_btn_consultaLayout.setHorizontalGroup(
            jP_btn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_consultaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jL_consulta_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_consulta_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(consultaSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jP_btn_consultaLayout.setVerticalGroup(
            jP_btn_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_consultaLayout.createSequentialGroup()
                .addComponent(jL_consulta_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jP_btn_consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_consulta_texto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jP_btn_consultaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(consultaSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jP_btn_sair.setBackground(new java.awt.Color(107, 207, 206));
        jP_btn_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_btn_sair.setPreferredSize(new java.awt.Dimension(0, 64));
        jP_btn_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_btn_sairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jP_btn_sairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jP_btn_sairMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jP_btn_sairMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jP_btn_sairMouseReleased(evt);
            }
        });

        jL_sair_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_sair_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/exit24.png"))); // NOI18N

        jL_sair_texto.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_sair_texto.setForeground(new java.awt.Color(0, 0, 0));
        jL_sair_texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_sair_texto.setText("Sair");

        javax.swing.GroupLayout jP_btn_sairLayout = new javax.swing.GroupLayout(jP_btn_sair);
        jP_btn_sair.setLayout(jP_btn_sairLayout);
        jP_btn_sairLayout.setHorizontalGroup(
            jP_btn_sairLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_sairLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jL_sair_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_sair_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_btn_sairLayout.setVerticalGroup(
            jP_btn_sairLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_btn_sairLayout.createSequentialGroup()
                .addComponent(jL_sair_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jP_btn_sairLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_sair_texto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bonzina");

        jP_btn_consulta2.setBackground(new java.awt.Color(107, 207, 206));
        jP_btn_consulta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jP_btn_consulta2.setPreferredSize(new java.awt.Dimension(0, 64));
        jP_btn_consulta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_btn_consulta2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jP_btn_consulta2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jP_btn_consulta2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jP_btn_consulta2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jP_btn_consulta2MouseReleased(evt);
            }
        });

        jL_data.setBackground(new java.awt.Color(107, 207, 206));
        jL_data.setFont(new java.awt.Font("Malgun Gothic", 1, 17)); // NOI18N
        jL_data.setForeground(new java.awt.Color(0, 0, 0));
        jL_data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_data.setText("Data");
        jL_data.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jL_data.setOpaque(true);

        javax.swing.GroupLayout jP_btn_consulta2Layout = new javax.swing.GroupLayout(jP_btn_consulta2);
        jP_btn_consulta2.setLayout(jP_btn_consulta2Layout);
        jP_btn_consulta2Layout.setHorizontalGroup(
            jP_btn_consulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jL_data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jP_btn_consulta2Layout.setVerticalGroup(
            jP_btn_consulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jL_data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jP_menuLayout = new javax.swing.GroupLayout(jP_menu);
        jP_menu.setLayout(jP_menuLayout);
        jP_menuLayout.setHorizontalGroup(
            jP_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jL_logoMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jP_btn_medico, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
            .addComponent(jP_btn_paciente, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
            .addComponent(jP_btn_consulta, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
            .addComponent(jP_btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
            .addGroup(jP_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jP_btn_consulta2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        jP_menuLayout.setVerticalGroup(
            jP_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_menuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jL_logoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jP_btn_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jP_btn_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jP_btn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jP_btn_consulta2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jP_btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jP_main.add(jP_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        jP_cardPane.setBackground(new java.awt.Color(255, 204, 204));
        jP_cardPane.setLayout(new java.awt.CardLayout());
        jP_main.add(jP_cardPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 880, 660));

        getContentPane().add(jP_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 680));

        setSize(new java.awt.Dimension(1134, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//------------------------------------------------EXIT BUTTON------------------------------------------------//
    private void jP_btn_sairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_sairMouseClicked

        OptionDialog opDialog = new OptionDialog(new javax.swing.JFrame(), true, "Tem certeza que deseja Sair?");
        opDialog.setVisible(true);

        int resposta_usuario = opDialog.getReturnStatus();

        if (resposta_usuario == 1) {
            System.exit(0);
        } else {
        }
    }//GEN-LAST:event_jP_btn_sairMouseClicked
//------------------------------------------------EXIT BUTTON------------------------------------------------//

//------------------------------------------------BUTTON ANIMATIONS------------------------------------------//
    private void jP_btn_sairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_sairMouseEntered
        this.jP_btn_sair.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jP_btn_sairMouseEntered

    private void jP_btn_sairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_sairMouseExited
        this.jP_btn_sair.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_sairMouseExited

    private void jP_btn_sairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_sairMousePressed
        this.jP_btn_sair.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jP_btn_sairMousePressed

    private void jP_btn_sairMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_sairMouseReleased
        this.jP_btn_sair.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_sairMouseReleased

    private void jP_btn_medicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_medicoMouseEntered
        this.jP_btn_medico.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jP_btn_medicoMouseEntered

    private void jP_btn_medicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_medicoMouseExited
        this.jP_btn_medico.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_medicoMouseExited

    private void jP_btn_medicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_medicoMouseReleased
        this.jP_btn_medico.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_medicoMouseReleased

    private void jP_btn_medicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_medicoMousePressed
        this.jP_btn_medico.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jP_btn_medicoMousePressed

    private void jP_btn_pacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_pacienteMouseEntered
        this.jP_btn_paciente.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jP_btn_pacienteMouseEntered

    private void jP_btn_pacienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_pacienteMouseExited
        this.jP_btn_paciente.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_pacienteMouseExited

    private void jP_btn_pacienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_pacienteMousePressed
        this.jP_btn_paciente.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jP_btn_pacienteMousePressed

    private void jP_btn_pacienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_pacienteMouseReleased
        this.jP_btn_paciente.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_pacienteMouseReleased

    private void jP_btn_consultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consultaMouseEntered
        this.jP_btn_consulta.setBackground(new Color(87, 171, 170));
    }//GEN-LAST:event_jP_btn_consultaMouseEntered

    private void jP_btn_consultaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consultaMouseExited
        this.jP_btn_consulta.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_consultaMouseExited

    private void jP_btn_consultaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consultaMousePressed
        this.jP_btn_consulta.setBackground(new Color(114, 219, 218));
    }//GEN-LAST:event_jP_btn_consultaMousePressed

    private void jP_btn_consultaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consultaMouseReleased
        this.jP_btn_consulta.setBackground(new Color(107, 207, 206));
    }//GEN-LAST:event_jP_btn_consultaMouseReleased
//----------------------------------------------BUTTON ANIMATIONS--------------------------------------------//

//----------------------------------------------CHANGE CARD--------------------------------------------------//
    private void jP_btn_medicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_medicoMouseClicked
        this.cardL.show(this.jP_cardPane, "card2");
        this.changeSelectedMenuColor();
    }//GEN-LAST:event_jP_btn_medicoMouseClicked
//----------------------------------------------CHANGE CARD--------------------------------------------------//

//----------------------------------------------DRAG THE WINDOW----------------------------------------------//
    //Colocar como nova posicao o movimento do mouse dragged
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int coordinateX = evt.getXOnScreen();
        int coordinateY = evt.getYOnScreen();

        this.setLocation(coordinateX - mouseX, coordinateY - mouseY);
    }//GEN-LAST:event_formMouseDragged

    //Pegar a posicao na hora do click
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed
//----------------------------------------------DRAG THE WINDOW----------------------------------------------//

//----------------------------------------------CHANGE CARD--------------------------------------------------//
    private void jP_btn_pacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_pacienteMouseClicked
        this.cardL.show(jP_cardPane, "card3");
        this.changeSelectedMenuColor();
    }//GEN-LAST:event_jP_btn_pacienteMouseClicked

    private void jP_btn_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consultaMouseClicked
        this.cardL.show(jP_cardPane, "card4");
        this.changeSelectedMenuColor();
    }//GEN-LAST:event_jP_btn_consultaMouseClicked

    private void jL_logoMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_logoMenuMouseClicked
        this.cardL.show(jP_cardPane, "card1");
        this.clearSelected();
    }//GEN-LAST:event_jL_logoMenuMouseClicked

    private void jP_btn_consulta2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consulta2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_btn_consulta2MouseClicked

    private void jP_btn_consulta2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consulta2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_btn_consulta2MouseEntered

    private void jP_btn_consulta2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consulta2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_btn_consulta2MouseExited

    private void jP_btn_consulta2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consulta2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_btn_consulta2MousePressed

    private void jP_btn_consulta2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_btn_consulta2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_btn_consulta2MouseReleased
//----------------------------------------------CHANGE CARD--------------------------------------------------//

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
//----------------------------------------------CUSTOMIZING JTABLES-----------------------------------------//

    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable jTable1, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(jTable1, value, selected, focused, row, column);
            setBackground(new java.awt.Color(107, 207, 206));

            setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(255, 255, 255)));
            setOpaque(true);
            return this;
        }
    }
//----------------------------------------------CUSTOMIZING JTABLES------------------------------------------//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_turno;
    private javax.swing.JLabel consultaSelected;
    private javax.swing.JLabel consultaSelected1;
    private javax.swing.JLabel jL_consulta_icon;
    private javax.swing.JLabel jL_consulta_icon1;
    private javax.swing.JLabel jL_consulta_texto;
    private javax.swing.JLabel jL_consulta_texto1;
    private javax.swing.JLabel jL_data;
    private javax.swing.JLabel jL_logoMenu;
    private javax.swing.JLabel jL_medico_icon;
    private javax.swing.JLabel jL_medito_texto;
    private javax.swing.JLabel jL_paciente_icon;
    private javax.swing.JLabel jL_paciente_texto;
    private javax.swing.JLabel jL_sair_icon;
    private javax.swing.JLabel jL_sair_texto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_btn_consulta;
    private javax.swing.JPanel jP_btn_consulta1;
    private javax.swing.JPanel jP_btn_consulta2;
    private javax.swing.JPanel jP_btn_medico;
    private javax.swing.JPanel jP_btn_paciente;
    private javax.swing.JPanel jP_btn_sair;
    private javax.swing.JPanel jP_cardPane;
    private javax.swing.JPanel jP_main;
    private javax.swing.JPanel jP_menu;
    private javax.swing.JLabel medicoSelected;
    private javax.swing.JLabel pacienteSelected;
    // End of variables declaration//GEN-END:variables
}
