package mapper;

import dto.TeamDTO;
import entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamDTO toDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCity(team.getCity());


        return teamDTO;
    }

    public Team toEntity(TeamDTO teamDTO) {
        if (teamDTO == null) {
            return null;
        }

        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setCity(teamDTO.getCity());


        return team;
    }
}