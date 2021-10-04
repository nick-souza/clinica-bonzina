package DAO;

import Model.Consulta;
import View.MessageDialog;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class ConsultaDAO {

    //List
    public static ArrayList<Consulta> consultaList = new ArrayList<Consulta>();

    public ConsultaDAO() {
    }

    //Criando a Conexao
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
                System.out.println("Status: Nao CONECTADO!");
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

    //Criar Consulta
    public boolean InsertConsultaDAO(Consulta objeto) throws ParseException {
        String sql = "INSERT INTO consulta(consulta_paciente,consulta_medico, consulta_sala, consulta_dataHora) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getConsultaPaciId());
            stmt.setInt(2, objeto.getConsultaMedId());
            stmt.setString(3, objeto.getConsultaSala());

            //Pegando os dados de Date e Time em string e convertendo eles apenas no DAO para poder puxar os dados formatados da DB
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            //Juntando as Strings para fazer o objeto java.util.Date
            String dataHoraStr = objeto.getConsultaData() + " " + objeto.getConsultaHora();
            //Convertendo para DATE
            java.util.Date dataHora = format.parse(dataHoraStr);
            //Colocando na DB como Timestamp
            stmt.setTimestamp(4, new Timestamp(dataHora.getTime()));

            stmt.execute();

            stmt.close();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            //Verificando por Dados Duplicados
            return false;
        } catch (SQLException se) {
            System.out.println("Erro InsertConsultaDAO");
            return false;
        }
    }

    //Deletar Consulta
    public boolean DeleteConsultaDAO(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM consulta WHERE consulta_id = " + id);
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro DeleteConsultaDAO");
            return false;
        }
    }

    //Editar Consulta
    public boolean UpdateConsultaDAO(Consulta objeto) throws ParseException {

        String sql = "UPDATE consulta set consulta_paciente = ? ,consulta_medico = ? ,consulta_sala = ? ,consulta_dataHora = ? WHERE consulta_id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getConsultaPaciId());
            stmt.setInt(2, objeto.getConsultaMedId());
            stmt.setObject(3, objeto.getConsultaSala());

            //Pegando os dados de Date e Time em string e convertendo eles apenas no DAO para poder puxar os dados formatados da DB
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            //Juntando as Strings para fazer o objeto java.util.Date
            String dataHoraStr = objeto.getConsultaData() + " " + objeto.getConsultaHora();
            //Convertendo para DATE
            java.util.Date dataHora = format.parse(dataHoraStr);
            //Colocando na DB como Timestamp
            stmt.setTimestamp(4, new Timestamp(dataHora.getTime()));

            stmt.setInt(5, objeto.getConsultaId());
            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            //Verificando por Dados Duplicados
            return false;
        } catch (SQLException erro) {
            System.out.println("Erro UpdateConsultaDAO");
            throw new RuntimeException(erro);
        }
    }

    //Carregar Tabela Consulta
    public ArrayList getConsultaListDAO() {

        consultaList.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            //Usando: DATE_FORMAT(c.consulta_dataHora, '%d/%m/%Y' )AS Data, DATE_FORMAT(c.consulta_dataHora, '%H:%i' )AS Hora
            //Para poder puxar os dados formatados. No sql quando e formatado a variavel vira string 
            ResultSet res = stmt.executeQuery("SELECT c.consulta_id, p.paciente_nome, p.paciente_id, m.medico_id, m.medico_nome,c.consulta_sala, "
                    + "DATE_FORMAT(c.consulta_dataHora, '%d/%m/%Y' ) AS Data, DATE_FORMAT(c.consulta_dataHora, '%H:%i' ) AS Hora\n"
                    + "FROM consulta c \n"
                    + "INNER JOIN paciente p\n"
                    + "ON c.consulta_paciente = p.paciente_id \n"
                    + "INNER JOIN medico m\n"
                    + "ON c.consulta_medico = m.medico_id\n"
                    + "ORDER BY c.consulta_dataHora");

            while (res.next()) {

                int id = res.getInt("consulta_id");
                String paciente = res.getString("paciente_nome");
                String medico = res.getString("medico_nome");
                String sala = res.getString("consulta_sala");
                String data = res.getString("Data");
                String hora = res.getString("hora");
                int medId = res.getInt("medico_id");
                int paciId = res.getInt("paciente_id");

                Consulta objeto = new Consulta(id, medico, paciente, sala, data, hora, medId, paciId);

                consultaList.add(objeto);
            }

            stmt.close();

        } catch (SQLException se) {
            System.out.println("Erro InsertConsultaDAO");
        }
        return consultaList;
    }
}
