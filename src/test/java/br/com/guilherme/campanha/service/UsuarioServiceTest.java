package br.com.guilherme.campanha.service;

import br.com.guilherme.campanha.CampanhaApplicationTests;
import br.com.guilherme.campanha.services.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UsuarioServiceTest  extends CampanhaApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioService).build();
    }

    @Test
    public void testGetTimeService() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuario")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPostCampanhaService() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .content("{\"nome\": \"Guilherme\", \"time\":{\"id\":\"1\"}, \"email\":\"guilherme@email.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteCampanhaService() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

