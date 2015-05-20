package org.gurinder.project.Api;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.gurinder.project.DaoImpl.UserDaoImplRest;
import org.gurinder.project.Entity.User;
import org.gurinder.project.Services.UserService;
import org.neo4j.graphdb.*;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class UserController {
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
    private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";

    /**
     * Driver application to create the user nodes
     */
    public static void main(String[] args) {


        UserService userService = new UserService();
        User user = new User("John", "25", "New York");
        System.out.println("Creating User");
        if (userService.addUserNode(user) == null)
            System.out.println("Error creating user");
        else
            System.out.println("Created sucessfully");


/*
        if (userService.updateUserNode(user) == null)
			System.out.println("Error updating user");
		else
			System.out.println("Updated sucessfully");
		
		User user2=userService.getUserByUserName("Bradley");
		if(user2==null)
		{
			System.out.println("Could not find User");
		}
		
		else
			System.out.println(user);*/

        //List<User> list=userService.getFavorite("Bradley");
        //List<User> list=userService.getBlocked("Bradley");*/
        /*List<User> list=userService.getFavoriteBy("John");
        for(User user: list)
			System.out.println(user);*/

//        UserDaoImplRest userDAO = new UserDaoImplRest();
//        //userDAO.getAllFavorites();
//        long startTime = System.currentTimeMillis();
//        System.out.println("Start time:" + startTime);
//        userDAO.getFavoritesById();
//        long endTime = System.currentTimeMillis();
//        System.out.println("End time:" + endTime);
//        System.out.println("Turnaround time:" + (endTime - startTime) / 1000 + "seconds");

    }


}
