package com.example.msplayer.services;


import com.example.msplayer.dto.PlayerAddResponse;
import com.example.msplayer.dto.PlayerDto;
import com.example.msplayer.dto.TeamDTO;
import com.example.msplayer.exception.ProjectException;
import com.example.msplayer.feign.TeamClient;
import com.example.msplayer.mapper.PlayerMapper;
import com.example.msplayer.repository.PlayerRepository;

import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements IPlayerService {
    private final PlayerRepository playerRepository;
    private final TeamClient teamClient;
    private final RestTemplate restTemplate;
    private final PlayerMapper playerMapper;

    private static final String TEAM_NOT_FOUND = "Team not found";
    private static final String MS_TEAM_URL = "http://localhost:8081/api/teams/";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "teamRequests";

    @Override
    public PlayerAddResponse addPlayer(PlayerDto playerDto) {
        ObjectId teamObjectId;
        try {
            teamObjectId = new ObjectId(playerDto.getTeamId());

        } catch (IllegalArgumentException e) {
            throw new ProjectException(HttpStatus.BAD_REQUEST, "Invalid Team ID format");
        }

        kafkaTemplate.send(TOPIC, teamObjectId.toString());

        var team = teamClient.getTeamById(teamObjectId.toString());
        if (team == null) {
            throw new ProjectException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
        }
        var playerJpa = playerRepository.save(playerMapper.playerDtoToPlayer(playerDto));
        return PlayerAddResponse.builder()
                .playerFirstName(playerJpa.getPlayerFirstName())
                .playerLastName(playerJpa.getPlayerLastName())
                .playerEmail(playerJpa.getPlayerEmail())
                .playerAge(playerJpa.getPlayerAge())
                .nationality(playerJpa.getNationality())
                .teamDTO(null)
                .build();

    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::playerToPlayerDto).toList();
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        var player = playerRepository.findById(id)
                .orElseThrow(() -> new ProjectException(HttpStatus.NOT_FOUND, "Player not found"));
        String teamId = player.getTeamId();
        kafkaTemplate.send(TOPIC, teamId);
        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);
        playerDto.setTeamDTO(null);
        return playerDto;
    }


    // récuperer le joueur et les informations de l'équipe avec feignClient
    //    @Override
//    public PlayerDto getPlayerById(Long id) {
//
//        var player = playerRepository.findById(id)
//                .orElseThrow(() -> new ProjectException(HttpStatus.NOT_FOUND, "Player not found"));
//
//
//        String teamId = player.getTeamId();
//        var team = teamClient.getTeamById(teamId);
//        if (team == null) {
//            throw new ProjectException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
//        }
//
//
//        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);
//        playerDto.setTeamDTO(team);
//
//        return playerDto;
//    }


    // récuperer le joueur et les informations de l'équipe avec RestTemplate

//    @Override
//    public PlayerDto getPlayerById(Long id) {
//
//        var player = playerRepository.findById(id)
//                .orElseThrow(() -> new ProjectException(HttpStatus.NOT_FOUND, "Player not found"));
//
//
//        String teamId = player.getTeamId();
//        var team = getTeamById(teamId);
//        if (team == null) {
//            throw new ProjectException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
//        }
//
//
//        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);
//        playerDto.setTeamDTO(team);
//
//        return playerDto;
//    }
//
//    private TeamDTO getTeamById(String id) {
//        try {
//            return restTemplate.getForObject(MS_TEAM_URL + id, TeamDTO.class);
//        } catch (Exception e) {
//            return null;
//        }
}


// récuperer le joueur et les informations de l'équipe avec Kafka




