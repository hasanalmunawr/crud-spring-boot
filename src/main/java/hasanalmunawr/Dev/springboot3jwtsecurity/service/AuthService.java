package hasanalmunawr.Dev.springboot3jwtsecurity.service;

import hasanalmunawr.Dev.springboot3jwtsecurity.dto.AuthRequest;
import hasanalmunawr.Dev.springboot3jwtsecurity.dto.AuthResponse;
import hasanalmunawr.Dev.springboot3jwtsecurity.dto.RegisterRequest;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.Role;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.Token;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.TokenType;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.UserEntity;
import hasanalmunawr.Dev.springboot3jwtsecurity.exception.TokenNotFoundException;
import hasanalmunawr.Dev.springboot3jwtsecurity.exception.UserAlreadyExistException;
import hasanalmunawr.Dev.springboot3jwtsecurity.repository.TokenRepository;
import hasanalmunawr.Dev.springboot3jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        try {
            log.info("[AuthService:registerUser] User Registration started with :: {}", request.getEmail());

            Optional<UserEntity> byEmail = userRepository
                    .findByEmail(request.getEmail());

            if (byEmail.isPresent()) {
                throw new UserAlreadyExistException("User Already Exist");
            }

            UserEntity user = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf(request.getRole()))
                    .build();

            UserEntity savedUser = userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(user, jwtToken);

            log.info("[AuthService:registerUser] User : {} Successfully registered", savedUser);
            return AuthResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();

        } catch (Exception e) {
            log.error("[AuthService:registerUser]Exception while registering the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    private void revokeAllUserTokens(UserEntity user) {
        List<Token> allValidTokensByUser = tokenRepository.findAllValidTokensByUser(user.getId());

        if (allValidTokensByUser.isEmpty()) {
            throw new TokenNotFoundException("Tokens Not Found");
        }

        allValidTokensByUser.forEach(token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
        tokenRepository.saveAll(allValidTokensByUser);

    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }
}
