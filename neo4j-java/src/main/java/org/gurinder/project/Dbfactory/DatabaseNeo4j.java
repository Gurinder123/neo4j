package org.gurinder.project.Dbfactory;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


public class DatabaseNeo4j {
	private static final String DB_PATH = "/home/gurinder/neodb";
	private static GraphDatabaseService db = null;
	
	protected DatabaseNeo4j() {
		// To defeat instantiation
	}
	
	
	public static GraphDatabaseService getDatabase() {
		if (db == null)
			return new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		else
			return db;
	}

}
