package com.example.ms_team.services;


import com.example.ms_team.dto.TeamDTO;

import java.util.List;

public interface TeamService {

    TeamDTO getTeamById(String id);

    List<TeamDTO> getAllTeams();

    TeamDTO createTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(String id, TeamDTO teamDTO);

    void deleteTeam(String id);
}
