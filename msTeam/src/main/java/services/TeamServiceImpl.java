package services;

import dto.PlayerDto;
import dto.TeamDto;
import entities.team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.TeamRepository;

@Service

public class TeamServiceImpl implements TeamService {


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TeamDto getTeamById(Long id) {
        team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        String playerServiceUrl = "http://localhost:8080/players/team/" + team.getId();
        PlayerDto[] players = restTemplate.getForObject(playerServiceUrl, PlayerDto[].class);

        TeamDto teamDTO = new TeamDto();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());

        return teamDTO;
    }
}
