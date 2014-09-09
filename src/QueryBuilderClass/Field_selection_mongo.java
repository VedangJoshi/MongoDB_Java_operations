/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryBuilderClass;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import java.net.UnknownHostException;
import java.util.Random;

/**
 *
 * @author vedangjoshi
 */
public class Field_selection_mongo{

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        
        MongoClient client = new MongoClient("localhost", 27017);
        DB myDB = client.getDB("m101");
        DBCollection collection = myDB.getCollection("Sample3");
        
        /**
         * random_number field generates random numbers 
        */
        Random random_numbers = new Random();

        collection.drop();                 
        
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", random_numbers.nextInt(100)).
                                         append("y",random_numbers.nextInt(100)).
                                         append("z",random_numbers.nextInt(200)) );
        }
        
        DBObject query = QueryBuilder.start("x").greaterThan(10).
                             lessThan(70).and("y").greaterThan(10).lessThan(70).get();

        DBCursor cursor = collection.find(query,new BasicDBObject("y",true).
                                                        append("_id",false));
        
        try {
            while (cursor.hasNext()) {
                DBObject dBObject = cursor.next();
                System.out.println(dBObject);                
            }
        } finally {
            cursor.close();
        }

    }
}
