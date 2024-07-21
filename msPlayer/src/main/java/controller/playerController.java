package controller;

import dto.PlayerAddResponse;
import dto.PlayerDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import services.IPlayerService;


@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class playerController {

    @Autowired
    private IPlayerService playerService;


    @GetMapping("/players/{id}")
    public PlayerDto getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/allPlayers")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @PostMapping("/add")
    public ResponseEntity<PlayerAddResponse> addPlayer(@RequestBody PlayerDto playerDto) {
        return ResponseEntity.ok(playerService.addPlayer(playerDto));
    }
}
