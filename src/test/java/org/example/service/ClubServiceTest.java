package org.example.service;

import org.example.model.Club;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClubServiceTest {

    private final ClubService clubService = new ClubService();

    @Test
    void findClubsArray_loadsAllClubsFromDataFile() {
        List<Club> clubs = clubService.findClubsArray();

        assertThat(clubs).hasSize(20);
        assertThat(clubs).anySatisfy(club -> {
            assertThat(club.getName()).isEqualTo("Arsenal");
            assertThat(club.getClubCode()).isEqualTo("ARS");
            assertThat(club.getPosition()).isEqualTo(9);
            assertThat(club.getWins()).isEqualTo(2);
            assertThat(club.getLosses()).isEqualTo(2);
            assertThat(club.getGoalsScored()).isEqualTo(8);
        });
    }

    @Test
    void findClubsByGoalsScored_sortsDescending() {
        List<Club> clubs = clubService.findClubsByGoalsScored();

        for (int i = 0; i < clubs.size() - 1; i++) {
            assertThat(clubs.get(i).getGoalsScored()).isGreaterThanOrEqualTo(clubs.get(i + 1).getGoalsScored());
        }
    }

    @Test
    void findClubsByLosses_sortsDescending() {
        List<Club> clubs = clubService.findClubsByLosses();

        for (int i = 0; i < clubs.size() - 1; i++) {
            assertThat(clubs.get(i).getLosses()).isGreaterThanOrEqualTo(clubs.get(i + 1).getLosses());
        }
    }

    @Test
    void findClubsByWins_sortsDescending() {
        List<Club> clubs = clubService.findClubsByWins();

        for (int i = 0; i < clubs.size() - 1; i++) {
            assertThat(clubs.get(i).getWins()).isGreaterThanOrEqualTo(clubs.get(i + 1).getWins());
        }
    }

    @Test
    void findClubsByPosition_sortsAscending() {
        List<Club> clubs = clubService.findClubsByPosition();

        for (int i = 0; i < clubs.size() - 1; i++) {
            assertThat(clubs.get(i).getPosition()).isLessThanOrEqualTo(clubs.get(i + 1).getPosition());
        }
    }
}