package mongoclient.main;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mongoDb.MongoConnection;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    @FXML
    private Label info_text;

    @FXML
    private TextField remote_host,port;

    @FXML
    private Button connect;

    @FXML
    private ListView databases_list;

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

            for(String output: mongoDatabases)
            {
                databases_list.getItems().add(output);
            }
        }

        System.out.println("this is a triumph, I'm making a note here, huge successs!");
    }

}