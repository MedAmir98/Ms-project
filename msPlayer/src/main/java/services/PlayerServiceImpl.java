package services;


import dto.PlayerAddResponse;
import dto.PlayerDto;
import exception.ProjectException;
import feign.TeamClient;
import lombok.AllArgsConstructor;
import mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.PlayerRepository;
import java.util.List;


@Service
@AllArgsConstructor
public class PlayerServiceImpl implements IPlayerService {

    private final PlayerMapper playerMapper;

    @Autowired

    private PlayerRepository playerRepository;

    @Autowired
    private TeamClient teamClient;

    private static final String TEAM_NOT_FOUND ="Team not found";


    @Override
    public PlayerAddResponse addPlayer(PlayerDto playerDto) {
        var team = teamClient.getTeamById(playerDto.getTeamId()) ;
        if(team ==null) {
            throw new ProjectException(HttpStatus.NOT_FOUND,TEAM_NOT_FOUND) ;
        }
        var playerJpa = playerRepository.save(playerMapper.playerDtoToPlayer(playerDto));
        return PlayerAddResponse.builder()
                .playerFirstName(playerJpa.getPlayerFirstName())
                .playerLastName(playerJpa.getPlayerLastName())
                .playerEmail(playerJpa.getPlayerEmail())
                .playerAge(playerJpa.getPlayerAge())
                .nationality(playerJpa.getNationality())
                .teamDto(team)
                .build();

    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::playerToPlayerDto).toList();
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
//        player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
//        TeamDTO team = teamClient.getTeamById(player.getIdTeam());
//
//        PlayerDto playerDTO = new PlayerDto();
//        playerDTO.setIdPlayer(player.getIdPlayer());
//        playerDTO.setPlayerFirstName(player.getPlayerFirstName());
//        playerDTO.setPlayerLastName(player.getPlayerLastName());
//        playerDTO.setPlayerEmail(player.getPlayerEmail());
//        playerDTO.setNationality(player.getNationality());
//        playerDTO.setPlayerAge(player.getPlayerAge());
//        playerDTO.setTeam(team);
//
//        return playerDTO;
        return null ;
    }
}
