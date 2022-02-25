package test.scissorsstonepaper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.scissorsstonepaper.model.Statistic;

public interface StatisticRepo extends JpaRepository<Statistic, Long> {
}
