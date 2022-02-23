package test.scissorsstonepaper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.scissorsstonepaper.model.Statistic;
import test.scissorsstonepaper.requests.UpdateStatisticRequest;
import test.scissorsstonepaper.service.StatisticService;
import test.scissorsstonepaper.utils.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticResource {
    private final StatisticService statisticService;
    private final JwtUtil jwtUtil;

    public StatisticResource(StatisticService statisticService, JwtUtil jwtUtil) {
        this.statisticService = statisticService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/currentuser")
    public ResponseEntity<Statistic> getStatisticForCurrentUser(@RequestHeader("Authorization") String token) {
        Long playerId = this.jwtUtil.getPlayerIdFromHeader(token);
        Statistic statistic = statisticService.findStatisticByPlayerId(playerId);
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Statistic>> getAllStatistics() {
        List<Statistic> statistics = statisticService.findAllStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Statistic> reportStatistic(@RequestBody UpdateStatisticRequest request) {
        Statistic statistic = statisticService.updateStatistic(request.getPlayerId(), request.getType());
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
