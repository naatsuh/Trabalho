package Controller;

import Models.Agenda;
import Models.SQL.AgendaDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.util.Optional;


public class Contato_Controller {
    @FXML
    void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("ContatoScene"))
                    update();
            }
        });
        update();
    }

    @FXML
    private ListView<Agenda> lvContatos;

    @FXML
    void btEncerra(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btVolta(ActionEvent event) {
        Main.changeScreen("MainScene");
    }
    @FXML
    void btDelete(ActionEvent event) {
        ObservableList<Agenda> ol = lvContatos.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            Agenda v = ol.get(0);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Dejesa deletar?");
            alert.setContentText(v.toString());

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                AgendaDAO.getInstance().removeById(v.get_id());
                update();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum contato selecionado");
            alert.setContentText("Selecione algum contato da lista");
            alert.showAndWait();
        }

    }

    @FXML
    void btEditar(ActionEvent event) {
        ObservableList<Agenda> ol = lvContatos.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()) {
            Agenda v = ol.get(0);
            Main.changeScreen("MainScene",v);
        }
    }

    private void update (){
        lvContatos.getItems().clear();
        for(Agenda agenda : AgendaDAO.getInstance().findAll()){
            lvContatos.getItems().add(agenda);
        }
    }

}
