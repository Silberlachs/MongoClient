package mongoclient.main;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class DatabaseController implements Initializable {

    MongoIterable<String> dataBaseConnections;
    MongoDatabase database;
    private boolean alreadyCalled = false;

    @FXML
    private Label db_text;

    @FXML
    private ListView<String> collection_list;

    @FXML
    private TableView<ObservableList<StringProperty>> collection_items;

    @FXML
    private Button load_items, back;

    public void initialize() {
    }


    public void userDataHack() {
        if (alreadyCalled)
            return;

        String databaseName = db_text.getScene().getRoot().getUserData().toString();
        database = MongoConnectionGate.getInstance().getDatabase(databaseName);

        for (String name : database.listCollectionNames()) {
            collection_list.getItems().add(name);
        }

        alreadyCalled = true;
    }

    public void loadEntries(ActionEvent event) {

        if (collection_list.getSelectionModel().getSelectedItem() == null)
            return;

        String targetCollection = collection_list.getSelectionModel().getSelectedItem().toString();
        MongoCollection<Document> collection = database.getCollection(targetCollection);

        ArrayList<String> unifiedKeyCollection = new ArrayList<String>();

        //extract table column names from data model in database
        for (Document entries : collection.find()) {
            Set keySet = entries.keySet();

            for (Object key : keySet) {
                if (!unifiedKeyCollection.contains(key.toString())) {
                    unifiedKeyCollection.add(key.toString());
                }
            }
        }

        for (String keyNameInTable : unifiedKeyCollection) {
            //TableColumn column = new TableColumn(keyNameInTable);
           // column.setCellValueFactory(new PropertyValueFactory<>(keyNameInTable));
            collection_items.getColumns().addAll(new TableColumn(keyNameInTable));
        }

        int counter = 0;
        for (Document element : collection.find()) {

            ObservableList<StringProperty> tableRow = FXCollections.observableArrayList();

            for (Object valueString : element.values()) {
                tableRow.add(counter,new SimpleStringProperty(element.toString()));
                counter++;

            }
            collection_items.getItems().add(tableRow);

        }

    }

    public void mainMenu(ActionEvent event) throws IOException {

        HelloApplication.getInstance().changeScene("hello-view.fxml", "");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
