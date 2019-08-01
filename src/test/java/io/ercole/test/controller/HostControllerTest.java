package io.ercole.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.ercole.controller.HostController;
import io.ercole.model.CurrentHost;
import io.ercole.repositories.CurrentHostRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(HostController.class)
@ContextConfiguration
public class HostControllerTest {

	@Configuration
	static class HostControllerTestContextConfiguration {
		@Bean
		public CurrentHostRepository hostRepository() {
			return Mockito.mock(CurrentHostRepository.class);
		}
	}

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Autowired
	private CurrentHostRepository repository;

	@Test
	public void testGetHosts() throws Exception {
		CurrentHost ch = new CurrentHost();
		ch.setId(1L);

		Pageable mock = Mockito.mock(Pageable.class);
		Page<CurrentHost> mockPage = Mockito.mock(Page.class);
		mockPage.getContent().add(ch);

		given(this.repository.findByDbOrByHostnameOrBySchema("astring", new Date(), mock)).willReturn(mockPage);
		

		RequestBuilder rb = request(HttpMethod.GET,"/hosts").param("ricerca", "astring")//
				.param("days", "0")
				.param("page", "0").param("size", "20");
		
		MvcResult andReturn = this.mockMvc.perform(rb).andReturn();
		
		// assertEquals(andReturn.getResponse().getStatus(), 200);

	}

}
