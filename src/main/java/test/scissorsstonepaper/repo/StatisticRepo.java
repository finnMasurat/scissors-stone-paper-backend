package test.scissorsstonepaper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.scissorsstonepaper.model.Statistic;

import java.util.Optional;

public interface StatisticRepo extends JpaRepository<Statistic, Long> {
    Optional<Statistic> findStatisticByPlayerId(Long id);
}
