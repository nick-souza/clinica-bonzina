package Model;

import DAO.ConsultaDAO;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class Consulta {

    private int consultaId;

    //Usando uma variavel para o nome em String e o ID em int
    private String consultaMedNome;
    private int consultaMedId;

    //Usando uma variavel para o nome em String e o ID em int
    private String consultaPaciNome;
    private int consultaPaciId;

    private String consultaSala;

    //Usando data e hora em String para poder formatar os dados da DataBase para dd/MM/yyyy
    private String consultaData;
    private String consultaHora;

    private final ConsultaDAO dao;

    public Consulta() {

        this.dao = new ConsultaDAO();
    }

    public Consulta(int consultaId, String consultaMedNome, String consultaPaciNome, String consultaSala, String consultaData, String consultaHora, int consultaMedId, int consultaPaciId) {
        //Used by getConsultaListDAO
        this.dao = new ConsultaDAO();

        this.consultaId = consultaId;
        this.consultaMedNome = consultaMedNome;
        this.consultaMedId = consultaMedId;
        this.consultaPaciNome = consultaPaciNome;
        this.consultaPaciId = consultaPaciId;
        this.consultaData = consultaData;
        this.consultaHora = consultaHora;
        this.consultaSala = consultaSala;
    }

    public Consulta(int consultaMedId, int consultaPaciId, String consultaSala, String consultaData, String consultaHora) {
        //Used by InsertConsultaController
        this.dao = new ConsultaDAO();

        this.consultaMedId = consultaMedId;
        this.consultaPaciId = consultaPaciId;
        this.consultaData = consultaData;
        this.consultaHora = consultaHora;
        this.consultaSala = consultaSala;
    }

    public Consulta(int consultaId, int consultaMedId, int consultaPaciId, String consultaSala, String consultaData, String consultaHora) {
        //Used by UpdateConsultaController
        this.dao = new ConsultaDAO();

        this.consultaId = consultaId;
        this.consultaMedId = consultaMedId;
        this.consultaPaciId = consultaPaciId;
        this.consultaData = consultaData;
        this.consultaHora = consultaHora;
        this.consultaSala = consultaSala;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public int getConsultaMedId() {
        return consultaMedId;
    }

    public void setConsultaMedId(int consultaMedId) {
        this.consultaMedId = consultaMedId;
    }

    public int getConsultaPaciId() {
        return consultaPaciId;
    }

    public void setConsultaPaciId(int consultaPaciId) {
        this.consultaPaciId = consultaPaciId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }

    public String getConsultaMedNome() {
        return consultaMedNome;
    }

    public void setConsultaMedNome(String consultaMedNome) {
        this.consultaMedNome = consultaMedNome;
    }

    public String getConsultaPaciNome() {
        return consultaPaciNome;
    }

    public void setConsultaPaciNome(String consultaPaciNome) {
        this.consultaPaciNome = consultaPaciNome;
    }

    public String getConsultaData() {
        return consultaData;
    }

    public void setConsultaData(String consultaData) {
        this.consultaData = consultaData;
    }

    public String getConsultaHora() {
        return consultaHora;
    }

    public void setConsultaHora(String consultaHora) {
        this.consultaHora = consultaHora;
    }

    public String getConsultaSala() {
        return consultaSala;
    }

    public void setConsultaSala(String consultaSala) {
        this.consultaSala = consultaSala;
    }

    //Criar uma nova Consulta
    public boolean InsertConsultaModel(Consulta objeto) throws ParseException {

        if (dao.InsertConsultaDAO(objeto)) {
            return true;
        }
        return false;
    }

    //Deletar uma Consulta
    public boolean DeleteConsultaModel(int id) {
        if (dao.DeleteConsultaDAO(id)) {
            return true;
        } else {
            return false;
        }
    }

    //Editar uma Consulta
    public boolean UpdateConsultaModel(Consulta objeto) throws ParseException {
        if (dao.UpdateConsultaDAO(objeto)) {
            return true;
        }
        return false;
    }

    //Carregar Tabela Consulta
    public ArrayList getConsultaListModel() {
        if (dao.getConsultaListDAO() == null) {
            return null;
        } else {
            return dao.getConsultaListDAO();
        }
    }
}
