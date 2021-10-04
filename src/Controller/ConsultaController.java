package Controller;

import Model.Consulta;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Nicola Sá de Souza & Gabriel da Silva Marques
 */
public class ConsultaController {

    //Model
    private final Consulta consu;

    public ConsultaController() {
        this.consu = new Consulta();
    }

    //Carregar a Tabela de Consulta
    public ArrayList getConsultaListController() {
        if (consu.getConsultaListModel() == null) {
            return null;
        } else {
            return consu.getConsultaListModel();
        }
    }

    //Criar uma nova Consulta
    public boolean InsertConsultaController(int consultaMed, int consultaPaci, String consultaSala, String consultaData, String consultaHora) throws ParseException {
        Consulta objeto = new Consulta(consultaMed, consultaPaci, consultaSala, consultaData, consultaHora);

        if (consu.InsertConsultaModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Editar uma consulta
    public boolean UpdateConsultaController(int consultaid, int consultaMed, int consultaPaci, String consultaSala, String consultaData, String consultaHora) throws ParseException {
        Consulta objeto = new Consulta(consultaid, consultaMed, consultaPaci, consultaSala, consultaData, consultaHora);

        if (consu.UpdateConsultaModel(objeto)) {
            return true;
        } else {
            return false;
        }
    }

    //Deletar uma consulta
    public boolean DeleteConsultaController(int id) {
        if (consu.DeleteConsultaModel(id)) {
            return true;
        } else {
            return false;
        }
    }
}
