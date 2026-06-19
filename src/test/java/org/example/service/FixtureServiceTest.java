package org.example.service;

import org.example.model.Fixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureServiceTest {

    private final FixtureService fixtureService = new FixtureService(new ClubService());

    @Test
    void getFixturesList_loadsAllFixturesWithResolvedClubs() {
        List<Fixture> fixtures = fixtureService.getFixturesList();

        assertThat(fixtures).hasSize(70);
        assertThat(fixtures).allSatisfy(fixture -> {
            assertThat(fixture.getHomeTeam()).isNotNull();
            assertThat(fixture.getAwayTeam()).isNotNull();
            assertThat(fixture.getMatchDay()).isBetween(1, 38);
        });
    }

    @Test
    void getFixturesList_firstMatchDayOneFixtureHasCorrectTeamsAndScore() {
        List<Fixture> fixtures = fixtureService.getFixturesList();

        Fixture firstFixture = fixtures.get(0);

        assertThat(firstFixture.getMatchDay()).isEqualTo(1);
        assertThat(firstFixture.getHomeTeam().getName()).isEqualTo("Manchester United");
        assertThat(firstFixture.getAwayTeam().getName()).isEqualTo("Leicester City");
        assertThat(firstFixture.getHomeTeamGoals()).isEqualTo(2);
        assertThat(firstFixture.getAwayTeamGoals()).isEqualTo(1);
    }
}
