package Controller;

import Model.Paciente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class PacienteController {

    //Model
    private final Paciente pac;

    public PacienteController() {
        this.pac = new Paciente();
    }

    //Carregar a Tabela Paciente
    public ArrayList getPacienteListController() {
        return pac.getPacienteListModel();
    }

    //Carregar a Tabela Paciente com a Pesquisa
    public ArrayList<Paciente> getSearchPacienteController(String nome) {
        if (pac.getSearchPacienteModel(nome) == null) {
            return null;
        } else {
            ArrayList<Paciente> getSearchAlunoController = pac.getSearchPacienteModel(nome);
            return getSearchAlunoController;
        }
    }

    //Inserir um novo Paciente
    public boolean InsertPacienteController(String nome, String telefone, String endereco, String data_Nasc) throws SQLException, ParseException {

        Paciente objeto = new Paciente(nome, telefone, endereco, data_Nasc);

        if (pac.InsertPacienteModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Editar um Paciente
    public boolean UpdatePacienteController(int id, String nome, String telefone, String endereco, String data_Nasc) throws ParseException {

        Paciente objeto = new Paciente(id, nome, telefone, endereco, data_Nasc);

        if (pac.UpdatePacienteModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Deletar um Paciente
    public boolean DeletePacienteController(int id) {
        if (pac.DeletePacienteModel(id)) {
            return true;
        } else {
            return false;
        }
    }

}
