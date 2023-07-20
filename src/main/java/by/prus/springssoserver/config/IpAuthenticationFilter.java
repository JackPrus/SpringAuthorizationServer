package by.prus.springssoserver.config;


import by.prus.springssoserver.service.CustomUserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;


public class IpAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String IP_PREFIX = "login-by-ip:";
    private CustomUserDetailsServiceImpl customUserDetailsService;

    public IpAuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsServiceImpl customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = "";
        if (username != null && username.startsWith(IP_PREFIX)) {
            UserDetails user = customUserDetailsService.loadUserByUsername(username);
            username = user.getUsername();
            password = user.getPassword().substring("{noop}".length());
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            super.setDetails(request, authRequest);

            Authentication authResult = this.getAuthenticationManager().authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            return authResult;
        } else {
            Authentication authResult = super.attemptAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            return authResult;
        }
    }
}
