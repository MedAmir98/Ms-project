package com.example.ms_team.kafkaConfig;

import com.example.ms_team.dto.TeamDTO;
import com.example.ms_team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeamConsumer {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TEAM_REQUEST_TOPIC = "teamRequests";
    private static final String TEAM_RESPONSE_TOPIC = "teamResponses";

    @KafkaListener(topics = TEAM_REQUEST_TOPIC, groupId = "team-group")
    public void consume(String teamId) {
        System.out.println("Received team request for ID: " + teamId);

        var team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            TeamDTO teamDTO = new TeamDTO();

            teamDTO.setId(team.get().getId());
            teamDTO.setName(team.get().getName());
            teamDTO.setCity(team.get().getCity());


            kafkaTemplate.send(TEAM_RESPONSE_TOPIC, teamDTO);
        } else {
            System.out.println("Team not found for ID: " + teamId);

        }
    }
}