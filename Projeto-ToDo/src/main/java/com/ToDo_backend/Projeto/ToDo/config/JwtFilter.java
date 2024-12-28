package com.ToDo_backend.Projeto.ToDo.config;



import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import com.ToDo_backend.Projeto.ToDo.services.TokenService;
import com.ToDo_backend.Projeto.ToDo.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // (1) tentamos extrair o token
        Optional<String> token = extractToken(request);

        // (2) verificamos se ele existe
        if (token.isPresent()) {

            // (3) se existir, validamos o token
            String subject = tokenService.validateToken(token.get());
            UserModel userModel = userRepository.findByEmail(subject)
                    .orElseThrow(() -> new UsernameNotFoundException(subject));
            // (4) se o token for válido (não houve exceção), encontramos a pessoa associada
           // UserDetails userDetails = userService.loadUserByUsername(subject);

            // (5) informamos o Spring Security que a pessoa está autenticada
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userModel, null, userModel.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("userModel", userModel);
        }

        // (6) continuamos com a cadeia de filtros de qualquer forma
        filterChain.doFilter(request, response);


    }
    private Optional<String> extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return Optional.empty();
        }

        return Optional.of(
                authHeader.replace("Bearer ", "")
        );
    }

}
