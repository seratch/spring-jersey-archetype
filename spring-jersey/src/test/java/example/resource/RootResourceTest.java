package example.resource;

import example.RollbackTestCase;
import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
