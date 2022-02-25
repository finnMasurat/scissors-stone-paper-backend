package test.scissorsstonepaper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.scissorsstonepaper.model.HighScore;
import test.scissorsstonepaper.model.Player;

import java.util.Collection;
import java.util.Optional;

public interface PlayerRepo  extends JpaRepository<Player, Long> {
    Optional<Player> findPlayerByEmail(String email);

    @Query("select new test.scissorsstonepaper.model.HighScore(p.name, s.winCount, s.lossCount, s.drawCount) from Player p inner join p.statistic s where s.id = p.id ORDER BY s.winCount DESC")
    Collection<HighScore> getHighScores();
}
