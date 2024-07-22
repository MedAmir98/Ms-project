package com.example.ms_team.mapper;


import com.example.ms_team.dto.TeamDTO;
import com.example.ms_team.entities.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team fromTeamDtoToTeam(TeamDTO teamDTO) ;
    TeamDTO fromTeamToTeamDTO(Team team);

}
