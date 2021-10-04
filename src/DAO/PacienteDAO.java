package DAO;

import Model.Paciente;
import View.MessageDialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class PacienteDAO {

    public static ArrayList<Paciente> pacienteList = new ArrayList<Paciente>();

    public PacienteDAO() {

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
                System.out.println("Status: NãO CONECTADO!");
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

    //Inserir Paciente
    public boolean InsertPacienteDAO(Paciente objeto) throws ParseException {
        String sql = "INSERT INTO paciente(paciente_nome,paciente_telefone,paciente_endereco,paciente_bDate) VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getEndereco());

            //Pegando os dados de Date em string e convertendo apenas no DAO para poder puxar os dados formatados da DB
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            //Convertendo para DATE
            java.util.Date dataHora = format.parse(objeto.getData_Nasc());
            stmt.setObject(4, dataHora);

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            //Verificando por Dados Duplicados
            return false;
        } catch (SQLException se) {
            System.out.println("Erro InsertPacienteDAO");
        }
        return false;
    }

    //Deletar Paciente
    public boolean DeletePacienteDAO(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM paciente WHERE paciente_id = " + id);
            stmt.close();

            return true;
        } catch (SQLException erro) {
            //Catch caso o Paciente tenha alguma consulta marcada, nao deixando assim ser apagado
            return false;
        }
    }

    //Editar Paciente
    public boolean UpdatePacienteDAO(Paciente objeto) throws ParseException {

        String sql = "UPDATE paciente set paciente_nome = ? ,paciente_telefone = ? ,paciente_endereco = ? ,paciente_bDate = ? WHERE paciente_id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(5, objeto.getId());

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getEndereco());

            //Pegando os dados de Date em string e convertendo apenas no DAO para poder puxar os dados formatados da DB
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            //Convertendo para DATE
            java.util.Date dataHora = format.parse(objeto.getData_Nasc());
            stmt.setObject(4, dataHora);

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            //Verificando por Dados Duplicados
            return false;
        } catch (SQLException erro) {
            System.out.println("Erro UpdatePacienteDAO");
            return false;
        }
    }

    //Carregar Tabela Paciente
    public ArrayList getPacienteListDAO() {

        pacienteList.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT paciente_id, paciente_nome, paciente_telefone, paciente_endereco, "
                    + "DATE_FORMAT(paciente_bDate, '%d/%m/%Y' )AS BDate "
                    + "FROM paciente");

            while (res.next()) {

                int id = res.getInt("paciente_id");
                String nome = res.getString("paciente_nome");
                String telefone = res.getString("paciente_telefone");
                String endereco = res.getString("paciente_endereco");
                String bDate = res.getString("BDate");

                Paciente objeto = new Paciente(id, nome, telefone, endereco, bDate);

                pacienteList.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return pacienteList;
    }

    //Carregar Tabela Paciente com a Pesquisa
    public ArrayList<Paciente> getSearchPacienteDAO(String nome) {

        pacienteList.clear();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement("SELECT paciente_id, paciente_nome, paciente_telefone, paciente_endereco, "
                    + "DATE_FORMAT(paciente_bDate, '%d/%m/%Y' )AS BDate "
                    + "FROM paciente WHERE paciente_nome=?");

            stmt.setString(1, nome);
            ResultSet res = stmt.executeQuery();

            if (!res.isBeforeFirst()) {
                //Checar se o resulte set volta sem nenhum resultado. Se sim, o ponteiro estara antes do primeiro objeto.
                return null;
            } else {
                while (res.next()) {
                    int id = res.getInt("paciente_id");
                    String nome1 = res.getString("paciente_nome");
                    String telefone = res.getString("paciente_telefone");
                    String endereco = res.getString("paciente_endereco");
                    String bDate = res.getString("BDate");

                    Paciente objeto = new Paciente(id, nome1, telefone, endereco, bDate);
                    pacienteList.add(objeto);
                }
                stmt.close();
                return pacienteList;
            }
        } catch (SQLException se) {
            System.out.println("Erro getSearchPacienteDAO");
            return null;
        }
    }
}
