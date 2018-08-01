package com.mkyong.scheduler;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class xxxx {

	public static void main(String[] args) {
		
		
		System.out.println("STARTED............................");
		
		
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.maxConnectionIdleTime(60000);
        MongoClientOptions opts = builder.build();
		
		MongoCredential credential = MongoCredential.createCredential("ayantdev","zoom_details","AyantAstra422".toCharArray());
		ServerAddress serverAddress = new ServerAddress("ayant-shard-00-02-mn0tf.mongodb.net",27017);
		
		MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential),opts); 
		
		
	    SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient,"zoom_details");
	    MongoTemplate mongoTemplate = new MongoTemplate(simpleMongoDbFactory);
	    
	    
	    System.out.println("DATABASE: "+mongoTemplate.getDb().getName());
		
		

	}

}
