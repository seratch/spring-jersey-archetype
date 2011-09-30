package example.resource;

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

@Component
@Path("/")
public class RootResource {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Context
	HttpServletRequest request;

	@Resource
	MemberDao memberDao;

	@GET
	@Path("/")
	public Object index() {
		log.info("index called!");
		return "Hello, JAX-RS(Jersey) with Spring!";
	}

	@GET
	@Path("/members")
	public Object members() throws Exception {
		MembersViewModel model = new MembersViewModel();
		model.setMembers(memberDao.findAll());
		for (Member member : model.getMembers()) {
			log.info("member:" + member.toString());
		}
		// Using JSP
		Viewable viewable = new Viewable("/members.jsp", model);
		return Response.ok(viewable).build();
	}

	@GET
	@Path("/echo")
	public Object echo(
			@QueryParam("param") @DefaultValue("default") String param) {
		log.info("request.getParameter(param): "
				+ request.getParameter("param"));
		log.info("@QueryParam(\"param\"): " + param);
		return "Received: " + param;
	}

	@GET
	@Path("/post/input")
	public Object postInput() {
		// Using JSP
		return Response.ok(new Viewable("/post/input.jsp")).build();
	}

	@POST
	@Path("/post/submit")
	public Object postSubmit(@FormParam("id") String id,
			@FormParam("password") String password) {
		log.info("@FormParam(\"id\"): " + id);
		log.info("@FormParam(\"password\"): " + password);
		return "Posted: id=" + id + ",password=" + password;
	}

}
