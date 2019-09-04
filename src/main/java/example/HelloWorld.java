package example;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("/helloworld")
public class HelloWorld {
    @GET
    @Produces("text/plain")
    @Path("/hello")
  public String sayHelloWorldFrom() {
    String result = "Hello, world, sss";
    System.out.println(result);
    return result;
  }
  public static void main(String[] argv) throws IOException {
   // Object implementor = new HelloWorld ();
      
    String address = "http://localhost:8200";
    HttpServer server = HttpServerFactory.create(address);
      server.start();
    System.out.println(address);
  //  Endpoint.publish(address, implementor);
  }
}
