package com.example.msplayer.kafkaConfig;

import com.example.msplayer.dto.TeamDTO;
import com.example.msplayer.mapper.PlayerMapper;
import com.example.msplayer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerConsumer {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TEAM_RESPONSE_TOPIC = "teamResponses";

    @KafkaListener(topics = "teamResponses", groupId = "player-group")
    public void consume(TeamDTO teamDTO) {
        System.out.println("Received team response: " + teamDTO);


        var players = playerRepository.findAllByTeamId(teamDTO.getId());

        for (var player : players) {
            var playerDto = playerMapper.playerToPlayerDto(player);
            playerDto.setTeamDTO(teamDTO);

            playerRepository.save(playerMapper.playerDtoToPlayer(playerDto));
        }
    }
}