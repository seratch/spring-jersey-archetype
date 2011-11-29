package example.resource;

import example.RollbackTestCase;
import example.resource.RootResource.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.view.Viewable;
import example.database.Member;
import example.database.MemberDao;
import example.view.MembersViewModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class RootResourceTest extends RollbackTestCase {

	@Resource
	RootResource resource;

	@Test
	public void instantiation() throws Exception {
		assertThat(resource, notNullValue());
	}

	@Test
	public void index() throws Exception {
		String actual = resource.index();
		assertThat(actual, is(equalTo("Hello, JAX-RS(Jersey) with Spring!")));
	}

}
