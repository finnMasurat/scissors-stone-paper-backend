package test.scissorsstonepaper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import test.scissorsstonepaper.exception.PlayerNotFoundException;
import test.scissorsstonepaper.model.Player;
import test.scissorsstonepaper.repo.PlayerRepo;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService implements UserDetailsService {
    private final PlayerRepo playerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws PlayerNotFoundException {
        Optional<Player> player = playerRepo.findPlayerByEmail(email);
        if(player.isEmpty()) {
            System.out.println("Player not found in the database");
            throw new PlayerNotFoundException("User not found in the database");
        } else {
            System.out.println("User found in the database: {}");
            return new org.springframework.security.core.userdetails.User(
                    player.get().getEmail(),
                    player.get().getPassword(),
                    new ArrayList<>()
            );
        }
    }

    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player updatePlayer(Player player) {
        return playerRepo.save(player);
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player by id " + id + "was not found"));
    }

    public Player findPlayerByEmail(String email) {
        return playerRepo.findPlayerByEmail(email)
                .orElseThrow(() -> new PlayerNotFoundException("Player by email " + email + "was not found"));
    }

    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);
    }
}
