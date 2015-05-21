package org.gurinder.project.Api;


import java.net.URISyntaxException;
import org.gurinder.project.Entity.Profile;
import org.gurinder.project.Services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application {

    private static final Logger logger = LoggerFactory
            .getLogger(Application.class);

    public static void main(String[] args) throws URISyntaxException {
        ProfileService profileService = new ProfileService("http://localhost:7474/db/data/");
        Profile profile = new Profile();
        profile.setProfileId("9873136398");
       /* boolean response = profileService.addUserNode(profile);
        if (response) {
            System.out.println("User is created");
        } else {
            System.out.println("Error in User is creating");
        }*/

        List a = profileService.getBlocked("4");
        logger.info("output:", a);
        System.out.print(a);
        //      List b = profileService.getBlockedBy("6");

    }


}



















/*// START SNIPPET: insideAddMetaToProp
    private static void addMetadataToProperty(URI relationshipUri,
                                              String name, String value) throws URISyntaxException {
        URI propertyUri = new URI(relationshipUri.toString() + "/properties");
        String entity = toJsonNameValuePairCollection(name, value);
        WebResource resource = Client.create()
                .resource(propertyUri);
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity(entity)
                .put(ClientResponse.class);

        System.out.println(String.format(
                "PUT [%s] to [%s], status code [%d]", entity, propertyUri,
                response.getStatus()));
        response.close();
    }

    // END SNIPPET: insideAddMetaToProp

    private static String toJsonNameValuePairCollection(String name,
                                                        String value) {
        return String.format("{ \"%s\" : \"%s\" }", name, value);
    }

    private static URI createNode() {
        // START SNIPPET: createNode
        final String nodeEntryPointUri = SERVER_ROOT_URI + "node";
        // http://localhost:7474/db/data/node

        WebResource resource = Client.create()
                .resource(nodeEntryPointUri);
        // POST {} to the node entry point URI
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader()).accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity("{}")
                .post(ClientResponse.class);

        final URI location = response.getLocation();
        System.out.println(String.format(
                "POST to [%s], status code [%d], location header [%s]",
                nodeEntryPointUri, response.getStatus(), location.toString()));
        response.close();

        return location;
        // END SNIPPET: createNode
    }

    // START SNIPPET: insideAddRel
    private static URI addRelationship(URI startNode, URI endNode,
                                       String relationshipType, String jsonAttributes)
            throws URISyntaxException {
        URI fromUri = new URI(startNode.toString() + "/relationships");
        String relationshipJson = generateJsonRelationship(endNode,
                relationshipType, jsonAttributes);

        WebResource resource = Client.create()
                .resource(fromUri);
        // POST JSON to the relationships URI
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader()).accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity(relationshipJson)
                .post(ClientResponse.class);

        final URI location = response.getLocation();
        System.out.println(String.format(
                "POST to [%s], status code [%d], location header [%s]",
                fromUri, response.getStatus(), location.toString()));

        response.close();
        return location;
    }
    // END SNIPPET: insideAddRel

    private static String generateJsonRelationship(URI endNode,
                                                   String relationshipType, String... jsonAttributes) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"to\" : \"");
        sb.append(endNode.toString());
        sb.append("\", ");

        sb.append("\"type\" : \"");
        sb.append(relationshipType);
        if (jsonAttributes == null || jsonAttributes.length < 1) {
            sb.append("\"");
        } else {
            sb.append("\", \"data\" : ");
            for (int i = 0; i < jsonAttributes.length; i++) {
                sb.append(jsonAttributes[i]);
                if (i < jsonAttributes.length - 1) { // Miss off the final comma
                    sb.append(", ");
                }
            }
        }

        sb.append(" }");
        return sb.toString();
    }

    private static void addProperty(URI nodeUri, String propertyName,
                                    String propertyValue) {
        // START SNIPPET: addProp
        String propertyUri = nodeUri.toString() + "/properties/" + propertyName;
        // http://localhost:7474/db/data/node/{node_id}/properties/{property_name}

        WebResource resource = Client.create()
                .resource(propertyUri);
        ClientResponse response = resource.header("Authorization", getAuthorizationHeader()).accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .entity("\"" + propertyValue + "\"")
                .put(ClientResponse.class);

        System.out.println(String.format("PUT to [%s], status code [%d]",
                propertyUri, response.getStatus()));
        response.close();
        // END SNIPPET: addProp
    }


*/

// START SNIPPET: nodesAndProps
//        URI node = createNode();
//        addProperty(node, "name", "Gurinder singh");
//        URI secondNode = createNode();
//        addProperty(secondNode, "Company", "Xebia It Architects");
//        // END SNIPPET: nodesAndProps
//
//        // START SNIPPET: addRel
//        URI relationshipUri = addRelationship(firstNode, secondNode, "work for",
//                "{ \"from\" : \"2014\", \"until\" : \"1015\" }");
//        // END SNIPPET: addRel
//
//      //  addMetadataToProperty(relationshipUri, "stars", "5");
//