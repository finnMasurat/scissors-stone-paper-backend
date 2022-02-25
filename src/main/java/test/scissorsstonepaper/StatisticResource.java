package test.scissorsstonepaper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.scissorsstonepaper.model.HighScore;
import test.scissorsstonepaper.model.Statistic;
import test.scissorsstonepaper.requests.UpdateStatisticRequest;
import test.scissorsstonepaper.service.PlayerService;
import test.scissorsstonepaper.service.StatisticService;
import test.scissorsstonepaper.utils.JwtUtil;

import java.util.Collection;

@RestController
@RequestMapping("/statistic")
public class StatisticResource {
    private final StatisticService statisticService;
    private final PlayerService playerService;
    private final JwtUtil jwtUtil;

    public StatisticResource(StatisticService statisticService, PlayerService playerService, JwtUtil jwtUtil) {
        this.statisticService = statisticService;
        this.playerService = playerService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/currentuser")
    public ResponseEntity<Statistic> getStatisticForCurrentUser(@RequestHeader("Authorization") String token) {
        Long playerId = this.jwtUtil.getPlayerIdFromHeader(token);
        Statistic statistic = playerService.findPlayerById(playerId).getStatistic();
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }


    @GetMapping("/highscores")
    public ResponseEntity<Collection<HighScore>> getHighScores() {
        Collection<HighScore> highScores = this.playerService.getHighScoreResult();
        return new ResponseEntity<>(highScores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Statistic> reportStatistic(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateStatisticRequest request) {
        Long playerId = this.jwtUtil.getPlayerIdFromHeader(token);
        Statistic statistic = playerService.findPlayerById(playerId).getStatistic();
        Statistic updatedStatistic = statisticService.updateStatistic(statistic.getId(), request.getType());
        return new ResponseEntity<>(updatedStatistic, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Statistic>> getAllStatistics() {
//        List<Statistic> statistics = statisticService.findAllStatistics();
//        return new ResponseEntity<>(statistics, HttpStatus.OK);
//    }
}
