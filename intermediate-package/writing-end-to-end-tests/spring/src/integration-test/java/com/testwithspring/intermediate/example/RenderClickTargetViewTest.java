package com.testwithspring.intermediate.example;

import com.testwithspring.intermediate.IntegrationTest;
import com.testwithspring.intermediate.config.ExampleApplicationContext;
import com.testwithspring.intermediate.config.Profiles;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExampleApplicationContext.class})
//@ContextConfiguration(locations = {"classpath:example-context.xml"})
@WebAppConfiguration
@ActiveProfiles(Profiles.INTEGRATION_TEST)
@Category(IntegrationTest.class)
public class RenderClickTargetViewTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void configureSystemUnderTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    public void shouldReturnHttpStatusCodeOk() throws Exception {
        renderClickTargetView()
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRenderClickTargetView() throws Exception {
        renderClickTargetView()
                .andExpect(view().name(WebTestConstants.Views.CLICK_TARGET_VIEW));
    }

    @Test
    public void shouldForwardUserToClickTargetPage() throws Exception {
        renderClickTargetView()
                .andExpect(forwardedUrl("/WEB-INF/jsp/click/target.jsp"));
    }

    private ResultActions renderClickTargetView() throws Exception {
        return mockMvc.perform(get("/click-target"));
    }
}
