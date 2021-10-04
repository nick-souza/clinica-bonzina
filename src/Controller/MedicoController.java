package Controller;

import Model.Medico;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class MedicoController {

    //Model
    private final Medico med;

    public MedicoController() {
        this.med = new Medico();
    }

    //Carregar a Tabela Medico
    public ArrayList getMedicoListController() {
        return med.getMedicoListModel();
    }

    //Carregar a Tabela Medico com a Pesquisa
    public ArrayList<Medico> getSearchMedicoController(String nome) {
        if (med.getSearchMedicoModel(nome) == null) {
            return null;
        } else {
            ArrayList<Medico> getSearchMedicoController = med.getSearchMedicoModel(nome);
            return getSearchMedicoController;
        }
    }

    //Inserir Medico
    public boolean InsertMedicoController(String nome, String crm, String espec, String telefone, String turno) throws SQLException {

        Medico objeto = new Medico(nome, crm, espec, telefone, turno);

        if (med.InsertMedicoModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Editar Medico
    public boolean UpdateMedicoController(int id, String nome, String crm, String espec, String telefone, String turno) {
        Medico objeto = new Medico(id, nome, crm, espec, telefone, turno);
        if (med.UpdateMedicoModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Deletar Medico
    public boolean DeleteMedicoController(int id) {
        if (med.DeleteMedicoModel(id)) {
            return true;
        } else {
            return false;
        }
    }
}
