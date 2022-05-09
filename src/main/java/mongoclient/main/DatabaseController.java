package mongoclient.main;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseController implements Initializable {

    MongoIterable<String> dataBaseConnections;
    MongoDatabase database;
    private boolean alreadyCalled = false;

    @FXML
    private Label db_text;

    @FXML
    private ListView collection_list;

    public void initialize() {}



    public void userDataHack()
    {
        if(alreadyCalled)
            return;

        String databaseName = db_text.getScene().getRoot().getUserData().toString();
        database = MongoConnectionGate.getInstance().getDatabase(databaseName);

        for (String name  : database.listCollectionNames()) {
            System.out.println(name);
            collection_list.getItems().add(name);
        }

        alreadyCalled = true;
    }

    public void loadEntries(ActionEvent event){


       String targetCollection = collection_list.getSelectionModel().getSelectedItem().toString();
       MongoCollection<Document> collection = database.getCollection(targetCollection);

       collection_list.getItems().removeAll();

       for (Document element: collection.find()){
           collection_list.getItems().add(element.toString());
       }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
