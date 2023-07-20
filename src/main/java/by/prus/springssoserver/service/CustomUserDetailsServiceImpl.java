package by.prus.springssoserver.service;

import by.prus.springssoserver.entity.CustomUser;
import by.prus.springssoserver.entity.IpCustomUser;
import by.prus.springssoserver.repository.CustomUserRepository;
import by.prus.springssoserver.repository.IpCustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import by.prus.springssoserver.model.MyUserDetails;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;
    @Autowired
    private IpCustomUserRepository ipCustomUserRepository;

    private static final String IP_PREFIX = "login-by-ip:";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(IP_PREFIX)){
            String ip = username.substring(IP_PREFIX.length());
            Optional<CustomUser> user = customUserRepository.findByIp(ip);
            return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found, IP: "+ip));
        }
        Optional<CustomUser> user = customUserRepository.findByUsername(username);
        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found. Username: "+username));
    }

    public CustomUser saveCustomUser (CustomUser customUser){
        return customUserRepository.save(customUser);
    }

    public IpCustomUser saveIpForUser (IpCustomUser ipCustomUser){
        return ipCustomUserRepository.save(ipCustomUser);
    }

    public Optional<CustomUser> findUserByIp(String ip){
        return customUserRepository.findByIp(ip);
    }
}
