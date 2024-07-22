package com.example.ms_team.services;

import com.example.ms_team.dto.TeamDTO;
import com.example.ms_team.mapper.TeamMapper;
import com.example.ms_team.repository.TeamRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;



import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private static final String TEAM_NOT_FOUND ="Team not found";

    @Override
    public TeamDTO getTeamById(String id) {
        var team = teamRepository.findById(String.valueOf(id)).orElseThrow(()->new RuntimeException(TEAM_NOT_FOUND)) ;
        return teamMapper.fromTeamToTeamDTO(team) ;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream().map(teamMapper::fromTeamToTeamDTO).toList();
    }

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        var team = teamRepository.save(teamMapper.fromTeamDtoToTeam(teamDTO)) ;
        return teamMapper.fromTeamToTeamDTO(team) ;
    }

    @Override
    public TeamDTO updateTeam(String id, TeamDTO teamDTO) {
        var teamToBeUpdated = teamRepository.findById(String.valueOf(id)).orElseThrow(()->new RuntimeException(TEAM_NOT_FOUND)) ;
        teamToBeUpdated.setName(teamDTO.getName());
        teamToBeUpdated.setCity(teamDTO.getCity());
        return teamMapper.fromTeamToTeamDTO(teamRepository.save(teamToBeUpdated)) ;
    }

    @Override
    public void deleteTeam(String id) {

        var teamToBeDeleted = teamRepository.findById(String.valueOf(id)).orElseThrow(()->new RuntimeException(TEAM_NOT_FOUND)) ;
        teamRepository.delete(teamToBeDeleted);

    }
}