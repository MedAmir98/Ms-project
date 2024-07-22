package com.example.ms_team.controller;


import com.example.ms_team.dto.TeamDTO;
import com.example.ms_team.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/allTeams")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable String  id) {
        return ResponseEntity.ok(teamService.getTeamById((id)));
    }

    @PostMapping("/addTeam")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }
}