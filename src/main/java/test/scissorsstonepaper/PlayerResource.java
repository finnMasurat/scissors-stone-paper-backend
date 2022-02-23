package test.scissorsstonepaper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.scissorsstonepaper.model.Player;
import test.scissorsstonepaper.model.Statistic;
import test.scissorsstonepaper.service.PlayerService;
import test.scissorsstonepaper.service.StatisticService;
import test.scissorsstonepaper.utils.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerResource {
    private final PlayerService playerService;
    private final StatisticService statisticService;
    private final JwtUtil jwtUtil;

    public PlayerResource(PlayerService playerService, StatisticService statisticService, JwtUtil jwtUtil) {
        this.playerService = playerService;
        this.statisticService = statisticService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/userdata")
    public ResponseEntity<Player> getPlayersData(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.getEmailFromHeader(token);
        Player player = playerService.findPlayerByEmail(email);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id) {
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player newPlayer = playerService.addPlayer(player);
        statisticService.addStatistic(new Statistic(null, newPlayer.getId(), 0, 0,0));
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
