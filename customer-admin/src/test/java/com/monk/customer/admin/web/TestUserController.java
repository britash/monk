package com.monk.customer.admin.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.monk.customer.admin.web.controller.ContactController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-root-test.xml",
		"classpath:customer-admin-servlet.xml" })
@WebAppConfiguration
public class TestUserController {
	private static final Logger log = LoggerFactory.getLogger(TestUserController.class);

	private MockMvc mvc;
	@Autowired
	private ContactController controller;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	/**
	 * private Long id;// ID
	private String contents;
	private String attachment;  //short_name
	private Date createTime;//create_time;
	private Integer status;
	
	 * @throws Exception
	 */
			
//	@Test
	public void testAdd() throws Exception{
		mvc.perform(post("/contact/save").param("contents", "contents")
		.param("attachment","attachment")
		.param("client.id","63")
		)	.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	//@Test
	public void testDetail() throws Exception{
		mvc.perform(get("/contact/get").param("id", "8"))
			.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
	}

	//@Test
	public void testUpdate() throws Exception{
		mvc.perform(post("/contact/update").param("id","6").param("status", "1")
				)	.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	public void testList() throws Exception{
		mvc.perform(get("/contact/list").param("content", "c")
				)	.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}