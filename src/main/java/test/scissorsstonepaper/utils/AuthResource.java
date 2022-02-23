package test.scissorsstonepaper.utils;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.scissorsstonepaper.model.Player;
import test.scissorsstonepaper.requests.AuthenticationRequest;
import test.scissorsstonepaper.responses.AuthenticationResponse;
import test.scissorsstonepaper.service.PlayerService;

@RestController
@RequestMapping("/authenticate")
@AllArgsConstructor
public class AuthResource {
    private AuthenticationManager authenticationManager;
    private PlayerService playerService;
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }
        Player player = this.playerService.findPlayerByEmail(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateToken(player);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
