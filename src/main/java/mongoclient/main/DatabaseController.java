package mongoclient.main;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;

public class DatabaseController implements Initializable {

    MongoIterable<String> dataBaseConnections;
    MongoDatabase database;
    private boolean alreadyCalled = false;

    @FXML
    private Label db_text;

    @FXML
    private ListView collection_list;

    @FXML
    private TableView collection_items;

    public void initialize() {}


    public void userDataHack()
    {
        if(alreadyCalled)
            return;

        String databaseName = db_text.getScene().getRoot().getUserData().toString();
        database = MongoConnectionGate.getInstance().getDatabase(databaseName);

        for (String name  : database.listCollectionNames()) {
            collection_list.getItems().add(name);
        }

        alreadyCalled = true;
    }

    public void loadEntries(ActionEvent event){


       String targetCollection = collection_list.getSelectionModel().getSelectedItem().toString();
       MongoCollection<Document> collection = database.getCollection(targetCollection);

       ArrayList<String> unifiedKeyCollection = new ArrayList<String>();

       //extract table column names from data model in database
       for(Document entries : collection.find()){
           Set keySet = entries.keySet();

           for (Object key : keySet)
           {

               if(!unifiedKeyCollection.contains(key.toString())){
                   unifiedKeyCollection.add(key.toString());
               }
           }
       }

        for(String keyNameInTable : unifiedKeyCollection)
        {
            collection_items.getColumns().addAll(new TableColumn(keyNameInTable));
        }


/*
        TableColumn producer = new TableColumn("Hersteller");
        producer.getColumns().addAll(
                new TableColumn("Name"),
                new TableColumn("Preis")
        );

        collection_items.getColumns().addAll(
                new TableColumn("Name"),
                new TableColumn("Kategorie"),
                new TableColumn("Preis"),
                producer,
                new TableColumn("Tags")
        );
*/

       for (Document element: collection.find()){

            for (Object valueString : element.values()){

                collection_items.getItems().addAll(new TableColumn(valueString.toString()));
            }

       }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
