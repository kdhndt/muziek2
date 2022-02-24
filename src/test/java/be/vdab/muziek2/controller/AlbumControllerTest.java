package be.vdab.muziek2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Sql({"/insertArtiest.sql", "/insertAlbum.sql"})
class AlbumControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final MockMvc mvc;

    private long idVanTestAlbum() {
        return jdbcTemplate.queryForObject("select id from albums where naam = 'test'", Long.class);
    }

    AlbumControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void onbestaandAlbumLezen() throws Exception {
        mvc.perform(get("/albums/{id}", -1))
                .andExpect(status().isNotFound());
    }

    @Test void albumLezen() throws Exception {
        mvc.perform(get("/albums/{id}", idVanTestAlbum()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("artiest").value("test"));
    }

}