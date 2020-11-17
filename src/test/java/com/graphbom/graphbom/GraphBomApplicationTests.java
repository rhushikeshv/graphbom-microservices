package com.graphbom.graphbom;

import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class GraphBomApplicationTests {

	public static void main(String[] args) throws Exception {
		Driver driver = GraphDatabase.driver(
				"bolt://localhost", AuthTokens.basic("neo4j", "123"));

	}

}
