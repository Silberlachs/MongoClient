package mongoclient.main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mongoDb.MongoConnection;

import java.util.Objects;

public class HelloController {

    MongoConnection mongoConnection;

    @FXML
    private Label greeting_text,info_text;

    @FXML
    private TextField remote_host,port;

    @FXML
    private Button connect;

    @FXML
    private ListView databases;

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
            mongoConnection = new MongoConnection(hostAdress, portNumber);
        }

        System.out.println("this is a triumph, I'm making a note here, huge successs!");
    }

}