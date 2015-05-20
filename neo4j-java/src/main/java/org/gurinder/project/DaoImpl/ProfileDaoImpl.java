package org.gurinder.project.DaoImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.gurinder.project.Dao.ProfileDao;
import org.gurinder.project.Entity.Profile;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by gurinder on 20/5/15.
 */
public class ProfileDaoImpl implements ProfileDao {

    private String serverRootUrl;

    public ProfileDaoImpl(String serverRootUrl) {
        this.serverRootUrl = serverRootUrl;
        checkDatabaseIsRunning();
    }

    @Override
    public boolean createUser(Profile profile) {
        String createUser = "merge (`" + profile.getProfileId() + "`: User {profileid:" + profile.getProfileId() + "})";
        boolean response = sendTransactionalCypherQuery(createUser);
        return response;
    }

    @Override
    public List<Profile> getBlocked(String profileId) {
        List<Profile> profile = findDetails("MATCH (me:User {profileid:" + profileId + "})-[b:BLOCKED]->(other:User) RETURN other");
        return profile;
    }


    @Override
    public List<Profile> getBlockedBy(String profileId) {
        List<Profile> profile = findDetails("MATCH (me:User)-[b:BLOCKED]->(other:User {profileid:" + profileId + "}) RETURN me");
        return profile;
    }

    @Override
    public List<Profile> getFavorite(String profileId) {
        List<Profile> profile = findDetails("MATCH (me:User {profileid:" + profileId + "})-[b:FAVORITE]->(other:User) RETURN other");
        return profile;
    }

    @Override
    public List<Profile> getFavoriteBy(String profileId) {
        List<Profile> profile = findDetails("MATCH (me:User)-[b:FAVORITE]->(other:User {profileid:" + profileId + "}) RETURN me");
        return profile;
    }


    private void checkDatabaseIsRunning() {
        // START SNIPPET: checkServer
        WebResource resource = Client.create()
                .resource(serverRootUrl);
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader()).get(ClientResponse.class);

        System.out.println(String.format("GET on [%s], status code [%d]",
                serverRootUrl, response.getStatus()));
        response.close();
        // END SNIPPET: checkServer
    }


    private List findDetails(String query) {

        // START SNIPPET: queryAllNodes
        final String txUri = serverRootUrl + "transaction/commit";
        WebResource resource = Client.create().resource(txUri);

        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity(payload)
                .post(ClientResponse.class);
        String body = (String) response.getEntity(String.class);
        System.out.println(String.format(
                "POST [%s] to [%s], status code [%d], returned data: "
                        + System.getProperty("line.separator") + "%s",
                payload, txUri, response.getStatus(),
                body));


        ObjectMapper mapper = new ObjectMapper();
        List list1 = null;
        try {
            Map m = mapper.readValue(body, Map.class);
            List list = (ArrayList) m.get("results");
            Map m1 = (Map) list.get(0);
            list1 = (List) m1.get("data");
//            Map m2 = (Map) list1.get(0);
//            list2 = (List) m2.get("row");
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.close();
        return list1;
    }

    private boolean sendTransactionalCypherQuery(String query) {
        // START SNIPPET: queryAllNodes
        final String txUri = serverRootUrl + "transaction/commit";
        WebResource resource = Client.create().resource(txUri);

        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity(payload)
                .post(ClientResponse.class);

        System.out.println(String.format(
                "POST [%s] to [%s], status code [%d], returned data: "
                        + System.getProperty("line.separator") + "%s",
                payload, txUri, response.getStatus(),
                response.getEntity(String.class)));

        if (response.getProperties().get("errors") == null) {
            //System.out.println(response.getProperties().get("errors"));
            response.close();
            return true;
        } else {
            response.close();
            return false;
        }
    }

    private static Object getAuthorizationHeader() {
        return "Basic " + new String(Base64.encodeBase64(String.format("%s:%s", "neo4j", "admin").getBytes()));

    }
}
