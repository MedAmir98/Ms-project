package serviceImpl;

import dto.TeamDTO;
import entity.Team;
import mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TeamRepository;
import service.TeamService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public TeamDTO getTeamById(String id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        return teamMapper.toDTO(team);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream().map(teamMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        team = teamRepository.save(team);
        return teamMapper.toDTO(team);
    }

    @Override
    public TeamDTO updateTeam(String id, TeamDTO teamDTO) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        team.setName(teamDTO.getName());
        team.setCity(teamDTO.getCity());

        team = teamRepository.save(team);
        return teamMapper.toDTO(team);
    }

    @Override
    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }
}
