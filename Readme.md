# Aplicação de cadastro de cliente
descrição de javafx para cadastro de cliente
## Tecnologia
* Java 20
* Java Fx
* Postgress

| Coluna 1 | Coluna 2 |
|----------|----------|
| valor    | valor 2  |

```Java
public void carregarListaClientes(){

        tabelaClientes.getItems().remove(0, tabelaClientes.getItems().size());

        List<Cliente> cliList = ClienteService.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);
        }
```