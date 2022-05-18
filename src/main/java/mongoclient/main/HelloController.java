package mongoclient.main;

import com.mongodb.client.MongoIterable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label info_text;

    @FXML
    private TextField remote_host,port;

    @FXML
    private Button connect,load;

    @FXML
    private ListView databases;

    public void initialize() {

        remote_host.setText("localhost");
        port.setText("27017");

    }

    public void handleButtonPress(ActionEvent event)
    {
        String hostAdress = remote_host.getText();
        int portNumber = -1;
        try{
            portNumber = Integer.parseInt(port.getText());
        }catch(Exception failed)
        {
            info_text.setText("Invalid Input! Only Numbers allowed");
            return;
        }

        if(!hostAdress.equals("") && portNumber != -1)
        {
            MongoConnectionGate.instanciateObject(hostAdress, portNumber);

            MongoIterable<String> mongoDatabases = MongoConnectionGate.getInstance().listDatabaseNames();

            databases.getItems().removeAll();

            for(String output: mongoDatabases)
            {
                databases.getItems().add(output);
            }
            databases.getSelectionModel().select(0);
        }
    }

    public void handleSceneSwitch(ActionEvent event) throws IOException {

        if(databases.getSelectionModel().getSelectedItem() == null){
            return;
        }
        HelloApplication.getInstance().changeScene(
                "collection-view.fxml", databases.getSelectionModel().getSelectedItem().toString()
        );
    }
}