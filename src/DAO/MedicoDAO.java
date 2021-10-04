package DAO;

import Model.Medico;
import View.MessageDialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class MedicoDAO {

    //List
    public static ArrayList<Medico> medicoList = new ArrayList<Medico>();

    public MedicoDAO() {

    }

    //Criando Conexao
    public Connection getConexao() {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "clinicateste";
            String url = "jdbc:mysql://" + server + ":3307/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "NewPassword";

            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
            } else {
                System.out.println("Status: NAO CONECTADO!");
            }
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O driver nao foi encontrado.");
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            MessageDialog.chamarDialogCancel("Nao foi possivel conectar a base de dados.");
            System.exit(0);
            return null;
        }
    }

    //Inserir Medico
    public boolean InsertMedicoDAO(Medico objeto) {
        String sql = "INSERT INTO medico(medico_nome,medico_crm,medico_espec,medico_telefone,medico_turno) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getCrm());
            stmt.setString(3, objeto.getEspec());
            stmt.setString(4, objeto.getTelefone());
            stmt.setString(5, objeto.getTurno());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            //Check for duplicate entries
            return false;
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        }
        return false;
    }

    //Deletar Medico
    public boolean DeleteMedicoDAO(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM medico WHERE medico_id = " + id);
            stmt.close();

            return true;
        } catch (SQLException erro) {
            //Catch caso o Medico tenha alguma consulta marcada, nao deixando assim ser apagado
            return false;
        }

    }

    //Editar Medico
    public boolean UpdateMedicoDAO(Medico objeto) {

        String sql = "UPDATE medico set medico_nome = ? ,medico_crm = ? ,medico_espec = ? ,medico_telefone = ?, medico_turno = ? WHERE medico_id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getCrm());
            stmt.setString(3, objeto.getEspec());
            stmt.setString(4, objeto.getTelefone());
            stmt.setString(5, objeto.getTurno());
            stmt.setInt(6, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            //Check for duplicate entries
            return false;
        } catch (SQLException se) {

            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
                System.out.println("Erro InsertPacienteDAO");
            }
            return false;
        }
    }

    //Carregar Tabela Medico
    public ArrayList getMedicoListDAO() {

        medicoList.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM medico");
            while (res.next()) {

                int id = res.getInt("medico_id");
                String nome = res.getString("medico_nome");
                String crm = res.getString("medico_crm");
                String espec = res.getString("medico_espec");
                String telefone = res.getString("medico_telefone");
                String turno = res.getString("medico_turno");

                Medico objeto = new Medico(id, nome, crm, espec, telefone, turno);

                medicoList.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("erro getMedicoListDAO");
        }
        return medicoList;
    }

    //Carregar Tabela Medico com a Pesquisa
    public ArrayList<Medico> getSearchMedicoDAO(String nome) {

        ArrayList<Medico> medicoList = new ArrayList<Medico>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement("SELECT * FROM medico WHERE medico_nome=?");
            stmt.setString(1, nome);
            ResultSet res = stmt.executeQuery();

            if (!res.isBeforeFirst()) {
                //Checar se o resulte set volta sem nenhum resultado. Se sim, o ponteiro estara antes do primeiro objeto.

                return null;
            }

            while (res.next()) {

//                Another way of writing
//                medico = new Medico(res.getInt("medico_id"), res.getString("medico_nome"), res.getString("medico_crm"),
//                        res.getString("medico_espec"), res.getString("medico_telefone"), res.getString("medico_turno"));
//                medicoList.add(medico);
//                return medicoList;
                int id = res.getInt("medico_id");
                String nome1 = res.getString("medico_nome");
                String crm = res.getString("medico_crm");
                String espec = res.getString("medico_espec");
                String telefone = res.getString("medico_telefone");
                String turno = res.getString("medico_turno");

                Medico objeto = new Medico(id, nome1, crm, espec, telefone, turno);

                medicoList.add(objeto);
            }
            stmt.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println("Erro getSearchMedicoDAO");
        }
        return medicoList;
    }
}
