package org.example.controller;

import org.example.exception.DataLoadException;
import org.example.model.Club;
import org.example.model.Fixture;
import org.example.service.ClubService;
import org.example.service.FixtureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PremierLeagueController.class)
class PremierLeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClubService clubService;

    @MockitoBean
    private FixtureService fixtureService;

    private Club arsenal() {
        return new Club("Arsenal", "ARS", 9, 2, 2, 8);
    }

    @Test
    void getAllClubs_returnsClubsFromService() throws Exception {
        when(clubService.findClubsArray()).thenReturn(List.of(arsenal()));

        mockMvc.perform(get("/api/clubs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Arsenal"))
                .andExpect(jsonPath("$[0].clubCode").value("ARS"));
    }

    @Test
    void getClubsByGoalsScored_delegatesToService() throws Exception {
        when(clubService.findClubsByGoalsScored()).thenReturn(List.of(arsenal()));

        mockMvc.perform(get("/api/clubs/goals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].goalsScored").value(8));
    }

    @Test
    void getClubsByLosses_delegatesToService() throws Exception {
        when(clubService.findClubsByLosses()).thenReturn(List.of(arsenal()));

        mockMvc.perform(get("/api/clubs/losses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].losses").value(2));
    }

    @Test
    void getClubsByWins_delegatesToService() throws Exception {
        when(clubService.findClubsByWins()).thenReturn(List.of(arsenal()));

        mockMvc.perform(get("/api/clubs/wins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].wins").value(2));
    }

    @Test
    void getClubsByPosition_delegatesToService() throws Exception {
        when(clubService.findClubsByPosition()).thenReturn(List.of(arsenal()));

        mockMvc.perform(get("/api/clubs/position"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].position").value(9));
    }

    @Test
    void getAllFixtures_returnsFixturesFromService() throws Exception {
        Club home = arsenal();
        Club away = new Club("Chelsea", "CHE", 4, 3, 1, 9);
        when(fixtureService.getFixturesList())
                .thenReturn(List.of(new Fixture(home, away, 2, 1, 1)));

        mockMvc.perform(get("/api/clubs/fixtures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].homeTeam.name").value("Arsenal"))
                .andExpect(jsonPath("$[0].awayTeam.name").value("Chelsea"))
                .andExpect(jsonPath("$[0].homeTeamGoals").value(2));
    }

    @Test
    void getAllClubs_whenServiceFailsToLoadData_returnsStructuredServerError() throws Exception {
        when(clubService.findClubsArray())
                .thenThrow(new DataLoadException("Failed to load club data from data.json", new java.io.IOException("boom")));

        mockMvc.perform(get("/api/clubs"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Failed to load club data from data.json"));
    }
}
