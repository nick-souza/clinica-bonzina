package Model;

import DAO.PacienteDAO;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class Paciente extends Pessoa {

    //Usando data_Nasc como String para poder formatar os dados da DB
    private String data_Nasc;
    private String endereco;

    private final PacienteDAO dao;

    public Paciente() {
        this.dao = new PacienteDAO();
    }

    public Paciente(String endereco, String data_Nasc) {
        this.endereco = endereco;
        this.data_Nasc = data_Nasc;
        this.dao = new PacienteDAO();
    }

    public Paciente(String nome, String telefone, String endereco, String data_Nasc) {
        super(nome, telefone);

//        this.endereco = endereco;
//        this.data_Nasc = data_Nasc;
        //Outro jeito de declarar. Fazendo assim os setter ficarem privados
        this.setData_Nasc(data_Nasc);
        this.setEndereco(endereco);

        this.dao = new PacienteDAO();
    }

    public Paciente(int id, String nome, String telefone, String endereco, String data_Nasc) {
        super(id, nome, telefone);

//        this.endereco = endereco;
//        this.data_Nasc = data_Nasc;
        //Outro jeito de declarar. Fazendo assim os setter ficarem privados
        this.setData_Nasc(data_Nasc);
        this.setEndereco(endereco);

        this.dao = new PacienteDAO();
    }

    public String getEndereco() {
        return endereco;
    }

    private void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_Nasc() {
        return data_Nasc;
    }

    private void setData_Nasc(String data_Nasc) {
        this.data_Nasc = data_Nasc;
    }

    //Carregar Tabela Paciente
    public ArrayList getPacienteListModel() {
        return dao.getPacienteListDAO();
    }

    //Inserir Paciente
    public boolean InsertPacienteModel(Paciente objeto) throws ParseException {
        if (dao.InsertPacienteDAO(objeto)) {
            return true;
        }
        return false;
    }

    //Deletar Paciente
    public boolean DeletePacienteModel(int id) {
        if (dao.DeletePacienteDAO(id)) {
            return true;
        } else {
            return false;
        }
    }

    //Editar Paciente
    public boolean UpdatePacienteModel(Paciente objeto) throws ParseException {
        if (dao.UpdatePacienteDAO(objeto)) {
            return true;
        }
        return false;
    }

    //Carrega Tabela Paciente com a Pesquisa
    public ArrayList<Paciente> getSearchPacienteModel(String nome) {
        if (dao.getSearchPacienteDAO(nome) == null) {
            return null;
        } else {
            ArrayList<Paciente> getSearchAlunoModel = dao.getSearchPacienteDAO(nome);
            return getSearchAlunoModel;
        }
    }

}
