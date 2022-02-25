package test.scissorsstonepaper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.scissorsstonepaper.exception.StatisticNotFoundException;
import test.scissorsstonepaper.model.Statistic;
import test.scissorsstonepaper.repo.StatisticRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepo statisticRepo;

    public List<Statistic> findAllStatistics() {
        return this.statisticRepo.findAll();
    }

    public Statistic updateStatistic(Long statisticId, String type) {
        Statistic statistic = this.statisticRepo.findById(statisticId)
                .orElseThrow(() -> new StatisticNotFoundException("Statistic with ID #" + statisticId + " was not found"));
        switch (type) {
            case "win":
                statistic.setWinCount(statistic.getWinCount() + 1);
                break;
            case "loss":
                statistic.setLossCount(statistic.getLossCount() + 1);
                break;
            case "draw":
                statistic.setDrawCount(statistic.getDrawCount() + 1);
        }

        return this.statisticRepo.save(statistic);
    }
}
