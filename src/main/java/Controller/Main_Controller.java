package Controller;

import Models.Agenda;
import Models.SQL.AgendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Main_Controller {

    private Agenda contatoAtual;

    @FXML
    void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("MainScene")){
                    if(userData != null){
                        contatoAtual = (Agenda)userData;

                        tfNome.setText(contatoAtual.getNome());
                        tfTelefone.setText(contatoAtual.getTelefone());
                        tfEndereco.setText(contatoAtual.getEndereco());
                        tfEmail.setText(contatoAtual.getEmail());
                    }else{
                        contatoAtual = null;

                        tfNome.setText("");
                        tfTelefone.setText("");
                        tfEndereco.setText("");
                        tfEmail.setText("");
                    }
                }

            }
        });
        update();
    }

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEndereco;

    @FXML
    void btContato(ActionEvent event) {
        Main.changeScreen("ContatoScene");
    }

    @FXML
    void btEncerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btSalvar(ActionEvent event) {

        try{

            if(tfNome.getText().isEmpty())
                throw new RuntimeException("Campo Nome esta vazio!");
            if(tfTelefone.getText().isEmpty())
                throw new RuntimeException("Campo Telefone esta vazio!");
            if(contatoAtual!=null) {
                contatoAtual.setNome(tfNome.getText());
                contatoAtual.setTelefone(tfTelefone.getText());
                contatoAtual.setEndereco(tfEndereco.getText());
                contatoAtual.setEmail(tfEmail.getText());
                AgendaDAO.getInstance().merge(contatoAtual);

            }else {
                Agenda agenda = new Agenda(tfNome.getText(), tfTelefone.getText(),
                        tfEndereco.getText(), tfEmail.getText());
                AgendaDAO.getInstance().persist(agenda);
            }

        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("ERRO ao adicionar contato!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void update(){
        tfNome.clear();
        tfTelefone.clear();
        tfEmail.clear();
        tfEndereco.clear();

    }

}
