package by.prus.springssoserver;

import by.prus.springssoserver.entity.Authority;
import by.prus.springssoserver.entity.CustomUser;
import by.prus.springssoserver.entity.UserAuthority;
import by.prus.springssoserver.repository.AuthorityRepository;
import by.prus.springssoserver.repository.CustomUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringssoserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringssoserverApplication.class, args);
	}


	//    Методы ниже сделаны в тестах. И очистка и создание юзеров в базе данных
	@Autowired
	CustomUserRepository customUserRepository;
	@Autowired
	AuthorityRepository authorityRepository;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {

		CustomUser user1 = customUserRepository.findByUsername("qwer").orElse(null);
		CustomUser user2 = customUserRepository.findByUsername("admin").orElse(null);

		Authority authority1 = authorityRepository.findByAuthority(UserAuthority.USER.getAuthority()).orElse(null);
		Authority authority2 = authorityRepository.findByAuthority(UserAuthority.ADMIN.getAuthority()).orElse(null);

		if (authority1 == null) {
			authority1 = new Authority(UserAuthority.USER.getAuthority());
			authorityRepository.save(authority1);
			System.out.println("Создан Authority " + authority1.getAuthority());
		}
		if (authority2 == null) {
			authority2 = new Authority(UserAuthority.ADMIN.getAuthority());
			authorityRepository.save(authority2);
			System.out.println("Создан Authority " + authority2.getAuthority());
		}

		if (user1 == null) {
			user1 = new CustomUser();
			user1.setUsername("qwer");
			user1.setPassword("qwer");
			user1.setFirstName("Dmitry");
			user1.setLastName("Prus");
			user1.setEmailAddress("test@test.com");
			user1.setBirthdate(LocalDate.of(1994, 6, 17));
			Set<Authority> authorities1 = new HashSet<>();
			authorities1.add(authority1);
			user1.setAuthorities(authorities1);
			customUserRepository.save(user1);
			System.out.println("Создан User " + user1.getUsername());
		}
		if (user2 == null) {
			user2 = new CustomUser();
			user2.setUsername("admin");
			user2.setPassword("admin");
			user1.setFirstName("Eugeni");
			user1.setLastName("Parashenko");
			user1.setEmailAddress("tost@tost.com");
			user1.setBirthdate(LocalDate.of(1980, 5, 14));
			Set<Authority> authorities2 = new HashSet<>();
			authorities2.add(authority2);
			authorities2.add(authority1);
			user2.setAuthorities(authorities2);
			customUserRepository.save(user2);
			System.out.println("Создан User " + user2.getUsername());
		}
	}
}
