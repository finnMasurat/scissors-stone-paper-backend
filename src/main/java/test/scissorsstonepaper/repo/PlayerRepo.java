package test.scissorsstonepaper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.scissorsstonepaper.model.Player;

import java.util.Optional;

public interface PlayerRepo  extends JpaRepository<Player, Long> {
    Optional<Player> findPlayerByEmail(String email);
}
