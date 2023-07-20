package by.prus.springssoserver.repository;

import by.prus.springssoserver.entity.IpCustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpCustomUserRepository extends JpaRepository<IpCustomUser, String> {
}
