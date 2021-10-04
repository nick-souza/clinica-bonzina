package Model;

import DAO.MedicoDAO;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class Medico extends Pessoa {

    //Usando CRM em String por causa da mascara do JFormattedTextField
    private String crm;
    private String espec;
    private String turno;

    private final MedicoDAO dao;

    public Medico() {
        this.dao = new MedicoDAO();
    }

    public Medico(String nome, String crm, String espec, String telefone, String turno) {
        super(nome, telefone);
        this.crm = crm;
        this.espec = espec;
        this.turno = turno;
        this.dao = new MedicoDAO();
    }

    public Medico(int id, String nome, String crm, String espec, String telefone, String turno) {
        super(id, nome, telefone);
        this.crm = crm;
        this.espec = espec;
        this.turno = turno;
        this.dao = new MedicoDAO();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    //Carregar Tabela Medico
    public ArrayList getMedicoListModel() {
        return dao.getMedicoListDAO();
    }

    //Inserir Medico
    public boolean InsertMedicoModel(Medico objeto) {
        if (dao.InsertMedicoDAO(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Deletar Medico
    public boolean DeleteMedicoModel(int id) {
        if (dao.DeleteMedicoDAO(id)){
            return true;
        } else {
            return false;
        }
    }

    //Editar Medico
    public boolean UpdateMedicoModel(Medico objeto) {
        if (dao.UpdateMedicoDAO(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Carregar Tabela Medico com a Pesquisa
    public ArrayList<Medico> getSearchMedicoModel(String nome) {
        if (dao.getSearchMedicoDAO(nome) == null) {
            return null;
        } else {
            ArrayList<Medico> getSearchMedicoModel = dao.getSearchMedicoDAO(nome);
            return getSearchMedicoModel;
        }
    }

}
