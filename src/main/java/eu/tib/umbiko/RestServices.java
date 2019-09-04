package eu.tib.umbiko;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Path("/api")
public class RestServices {

    Context ctx = new InitialContext();
    Context env = (Context) ctx.lookup("java:comp/env");
    final String serverUrl = (String) env.lookup("es-host");
    final String serverUser= (String) env.lookup("es-user");
    final String serverPass= (String) env.lookup("es-pass");

    public RestServices() throws NamingException {
    }


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(){

        return Response.status(200).entity("{\"status\":\" Works!!!\"}")
                .build();
    }


//    @Path("/queryES")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response queryElasticSearch(@QueryParam("index") String indexName,
//                                     @QueryParam("q") String query){
//        return Response.status(200).entity(JestServices.queryES(
//                serverUrl,indexName, query, serverUser, serverPass)).build();
//    }


    @Path("/queryESJ")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response queryElasticSearch(JSONObject json){
        String index = "";
        String query = "";
        String error = "{\"error\":\"Invalid JSON Structure\"," +
                "\"must-define\":[\"index\",\"q\"]," +
                "\"example-format\":{\"index\":\"INDEX-NAME\"," +
                "\"q\":{\"query\":{\"match\":{\"FIELD\":\"VALUE\"}}}}}";

        if(json.has("index")) {
            try {
                index = json.getString("index").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            return Response.status(400).entity(error).build();

        if(json.has("q")) {
            try {
                query = json.getJSONObject("q").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            return Response.status(400).entity(error).build();


        return Response.status(200).entity(JestServices.queryES(
                serverUrl,index,
                query, serverUser, serverPass))
                .build();
    }

}
