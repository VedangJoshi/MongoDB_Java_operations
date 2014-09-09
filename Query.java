/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Random;

/**
 *
 * @author vedangjoshi
 */
public class JavaApplication15 {

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        
        MongoClient client = new MongoClient("localhost", 27017);
        DB myDB = client.getDB("m101");
        DBCollection collection = myDB.getCollection("Sample2");

        collection.drop();
        
        BasicDBObject query = new BasicDBObject("x",new BasicDBObject("$gt",10).
                append("$lt",50)).append("y",new BasicDBObject("$gt",10).append("$lt",50));
        
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(100)).append("y",new Random().nextInt(100)));
        }

//        DBObject one = collection.findOne(query);
//        System.out.println(one);

        DBCursor cursor = collection.find(query);
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
