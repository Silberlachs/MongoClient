package mongoDb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoConnection {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase = null;

    public MongoConnection(String remoteHost, int port){
        mongoClient = new MongoClient(remoteHost, port);
    }

    public MongoIterable<String> showAvailableDatabaseNames(){
        return mongoClient.listDatabaseNames();
    }

    public void setMongoDatabase (String databaseName) {
        try {
            mongoDatabase = mongoClient.getDatabase(databaseName);
        }
        catch (Exception failed){
            System.out.println("Exception, probably miscast due to wrong dataBasename / NULL");
        }
    }

    public MongoIterable<String> getCollections(){
        if(mongoDatabase != null)
            return mongoDatabase.listCollectionNames();

        return null;
    }

    public void testFunction()
    {
        System.out.println("Databases available:\n");

        for (String element:showAvailableDatabaseNames()) {
            System.out.println(element);
        }

        setMongoDatabase("Produkte");

        System.out.println("Collections for 'Produkte' are:\n");

        for (String element:getCollections()) {
            System.out.println(element);
        }
    }

    public static void main(String[]args){
        MongoConnection mc = new MongoConnection("localhost", 27017);
        mc.testFunction();
    }

}
