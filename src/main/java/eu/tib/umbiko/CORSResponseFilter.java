package eu.tib.umbiko;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;


public class CORSResponseFilter
        implements ContainerResponseFilter {

    public ContainerResponse filter(ContainerRequest creq,
                                    ContainerResponse cresp) {

        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, OPTIONS, HEAD");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

        return cresp;
    }

}