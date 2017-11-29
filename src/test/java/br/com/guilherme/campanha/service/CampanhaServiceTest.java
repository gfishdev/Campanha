package br.com.guilherme.campanha.service;

import br.com.guilherme.campanha.CampanhaApplicationTests;
import br.com.guilherme.campanha.services.CampanhaService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CampanhaServiceTest extends CampanhaApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CampanhaService campanhaService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(campanhaService).build();
    }

    @Test
    public void testGetCampanhaService() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/campanha")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetCampanhaServiceWithParameter() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/campanha/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPostCampanhaService() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/campanha")
                .content("{\"nome\": \"Teste jaac\",\"time\": {\"id\": 1},\"vigenciaInicio\": \"2017-11-25\",\"vigenciaFim\": \"2017-11-26\"}")
                .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteCampanhaService() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/campanha/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

