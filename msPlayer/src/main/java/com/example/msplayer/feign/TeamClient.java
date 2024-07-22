package com.example.msplayer.feign;

import com.example.msplayer.dto.TeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msTeam")
public interface TeamClient {
    @GetMapping("/api/teams/{id}")
    TeamDTO getTeamById(@PathVariable String id);
}