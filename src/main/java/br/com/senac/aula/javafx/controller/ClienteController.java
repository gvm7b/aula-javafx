package br.com.senac.aula.javafx.controller;

import br.com.senac.aula.javafx.model.Cliente;
import br.com.senac.aula.javafx.services.ClienteService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@FxmlView("/main.fxml")
public class ClienteController {

    @FXML
    private TextField nome;

    @FXML
    private TextField documento;

    @FXML
    private TextField rg;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaDocumento;

    @FXML
    private TableColumn<Cliente, Integer> colunaRg;

    private int index = -1;



    @FXML
    public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));

        this.carregarListaClientes();

        tabelaClientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if(evt.getClickCount()== 2){
                   Cliente cli = tabelaClientes.getSelectionModel().getSelectedItem();
                   nome.setText(cli.getNome());
                   documento.setText(cli.getDocumento());
                   rg.setText((cli.getRg()));

                   index = cli.getId();
                }
            }
        });
    }

    public void executarOk(){

            Cliente cli = new Cliente();
            cli.setDocumento(documento.getText());
            cli.setNome(nome.getText());
            cli.setRg(rg.getText());

        // atualizo item - resetar index
        if(index > -1){
           // tabelaClientes.getItems().set(index, cli);
            ClienteService.atualizarCliente(index, cli);
            index = -1;
        }else {
            // inclui novo registro
           // tabelaClientes.getItems().add(cli);
            if(ClienteService.buscarClienteByDocumento(cli.getDocumento())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alerta");
                alert.setHeaderText("Documento: " + documento.getText() + " já existe na base");
                alert.show();
            } else {
                ClienteService.inserirCliente(cli);
            }

        }
        this.carregarListaClientes();

        this.limparCampos();
    }

    public void executarExcluir(){
        Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaExcluir.setTitle("Confirmação de Exclusão");
        alertaExcluir.setHeaderText("Confirmar excluir");

        if(index > -1){

            //tabelaClientes.getItems().remove(index);
            ClienteService.deletarCliente(index);
            this.carregarListaClientes();
            index = -1;
            this.limparCampos();
        }
    }

    public void limparCampos(){
        nome.setText("");
        documento.setText("");
        rg.setText("");
    }

    public void carregarListaClientes(){

        tabelaClientes.getItems().remove(0, tabelaClientes.getItems().size());

        List<Cliente> cliList = ClienteService.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);
    }


}
