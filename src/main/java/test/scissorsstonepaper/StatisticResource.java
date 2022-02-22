package test.scissorsstonepaper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.scissorsstonepaper.model.Statistic;
import test.scissorsstonepaper.requests.UpdateStatisticRequest;
import test.scissorsstonepaper.service.StatisticService;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticResource {
    private final StatisticService statisticService;

    public StatisticResource(StatisticService statisticService) {
        this.statisticService = statisticService;
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
