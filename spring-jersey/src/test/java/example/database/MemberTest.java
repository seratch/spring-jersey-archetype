package example.database;

import example.BasicSpringTestCase;
import example.database.Member.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;

public class MemberTest extends BasicSpringTestCase {

	@Resource 
	Member member;

	@Test
	public void instantiation() throws Exception {
		assertThat(member, notNullValue());
	}

}
