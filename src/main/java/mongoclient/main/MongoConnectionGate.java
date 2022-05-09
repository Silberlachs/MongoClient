package mongoclient.main;

import com.mongodb.MongoClient;

public class MongoConnectionGate {

    static MongoClient instance = null;

    public static void instanciateObject(String host, int port)
    {
        instance = new MongoClient(host, port);
    }

    public static MongoClient getInstance(){
        return instance;
    }

}
