package example.jerseytest;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import javax.ws.rs.core.MultivaluedMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RootResourceTest extends JerseyTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder("restful.server.resource")
				.contextParam("contextConfigLocation", "classpath:/applicationContext.xml")
				.contextPath("/").servletClass(SpringServlet.class)
				.contextListenerClass(ContextLoaderListener.class)
				.requestListenerClass(RequestContextListener.class)
				.build();
	}

	@Test
	public void index() throws Exception {
		WebResource webResource = resource();
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("q", "Java");
		String response = webResource.path("/").queryParams(params).get(String.class);
		assertThat(response, is(equalTo("Hello, JAX-RS(Jersey) with Spring!")));
	}

	@Test
	public void postSubmit() throws Exception {
		WebResource webResource = resource();
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("id", "iii");
		params.add("password", "ppp");
		String response1 = webResource.path("/post/submit").post(String.class, params);
		String response2 = webResource.path("/post/submit/").post(String.class, params);
		String expected = "Posted: id=iii,password=ppp";
		assertThat(response1, is(equalTo(expected)));
		assertThat(response2, is(equalTo(expected)));
	}

	@Test(expected = UniformInterfaceException.class)
	public void postSubmit_ValidationError() throws Exception {
		WebResource webResource = resource();
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("id", "");
		params.add("password", "ppp");
		try {
			webResource.path("/post/submit").post(String.class, params);
		} catch (UniformInterfaceException e) {
			log.info(e.getLocalizedMessage());
			throw e;
		}
	}

}
