package by.prus.springssoserver;

import by.prus.springssoserver.entity.CustomUser;
import by.prus.springssoserver.entity.IpCustomUser;
import by.prus.springssoserver.repository.AuthorityRepository;
import by.prus.springssoserver.repository.CustomUserRepository;
import by.prus.springssoserver.service.CustomUserDetailsServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestJpaBehaviorTests {


    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    CustomUserRepository customUserRepository;
    @Autowired
    CustomUserDetailsServiceImpl userDetailsService;

    /**
     * Before this test CustomUser must be created in test SpringssoserverApplicationTests class
     * DISABLE THIS TEST IN PRODUCTION !!!!
     * @see SpringssoserverApplicationTests
     */
    @Test
    @Disabled
    void addIpTest() {

        CustomUser customUser = customUserRepository.findByUsername("qwer").get();

        IpCustomUser ip1 = new IpCustomUser();
        ip1.setIp("127.2.15.100");
        ip1.setUserId(customUser.getId());

        IpCustomUser ip2 = new IpCustomUser();
        ip2.setIp("127.0.0.1");
        ip2.setUserId(customUser.getId());

        userDetailsService.saveIpForUser(ip1);
        userDetailsService.saveIpForUser(ip2);

        CustomUser customUser2 = customUserRepository.findByIp(ip1.getIp()).get();
        CustomUser customUser3 = customUserRepository.findByIp(ip2.getIp()).get();

        assertEquals(customUser2.getUsername(), customUser3.getUsername());
        assertEquals(customUser2.getUsername(), customUser.getUsername());


    }

    @Test
    @Disabled
    void saveExistingIp(){

        CustomUser customUser = customUserRepository.findByUsername("admin").get();

        IpCustomUser ip = new IpCustomUser();
        ip.setIp("222.2.22.200");
        ip.setUserId(customUser.getId());

        //change user for IP - working just user will be hanged
        userDetailsService.saveIpForUser(ip);

        CustomUser customUser1 = customUserRepository.findByIp(ip.getIp()).get();
        assertEquals(customUser1.getUsername(), customUser.getUsername());

    }

    /**
     * Test shows that when user deleted, IPS deleted as well. And roles of user are stayed.
     * Before the test create user in SpringssoserverApplicationTests.class
     * @see SpringssoserverApplicationTests
     */
    @Test
    @Disabled
    void deleteUserTest(){


    }

}
