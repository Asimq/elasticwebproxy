package eu.tib.umbiko;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.PutMapping;

import java.io.IOException;

public  class JestServices {

	public static String queryES(String serverUrl, String indexName, String query,
								 String user, String pass){

		JestClientFactory factory = new JestClientFactory();
		HttpClientConfig.Builder builder = new HttpClientConfig
				.Builder(serverUrl)
				.multiThreaded(true);

		if(user.compareTo("*")!=0 && pass.compareTo("*")!=0)
			builder.defaultCredentials(user,pass);
		factory.setHttpClientConfig(builder.build());
		JestClient client = factory.getObject();
		Search search = new Search.Builder(query)
				// multiple index or types can be added.
				.addIndex(indexName)
				.build();

		SearchResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
			  return e.getMessage();
		}
		return result.getJsonString();
	}

}
