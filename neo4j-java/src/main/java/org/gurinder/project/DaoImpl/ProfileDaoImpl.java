package org.gurinder.project.DaoImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.codec.binary.Base64;
import org.gurinder.project.Dao.ProfileDao;
import org.gurinder.project.Entity.Profile;

import javax.ws.rs.core.MediaType;
import java.util.List;


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
        findDetails("MATCH (me:User {profileid:" + profileId + "})-[b:BLOCKED]->(other:User) RETURN other");
        return null;
    }


    @Override
    public List<Profile> getBlockedBy(String profileId) {
        findDetails("MATCH (me:User)-[b:BLOCKED]->(other:User {profileid:" + profileId + "}) RETURN me");
        return null;
    }

    @Override
    public List<Profile> getFavorite(String profileId) {
        findDetails("MATCH (me:User {profileid:" + profileId + "})-[b:FAVORITE]->(other:User) RETURN other");
        return null;
    }

    @Override
    public List<Profile> getFavoriteBy(String profileId) {
        findDetails("MATCH (me:User)-[b:FAVORITE]->(other:User {profileid:" + profileId + "}) RETURN me");
        return null;
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

        System.out.println(String.format(
                "POST [%s] to [%s], status code [%d], returned data: "
                        + System.getProperty("line.separator") + "%s",
                payload, txUri, response.getStatus(),
                response.getEntity(String.class)));

        return null;
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
