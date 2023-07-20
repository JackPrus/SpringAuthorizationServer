package by.prus.springssoserver.repository;

import by.prus.springssoserver.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByUsername(String username);

    @Query(value = "SELECT u FROM CustomUser u JOIN IpCustomUser ipc ON u.id = ipc.userId WHERE ipc.ip = :ip")
    Optional<CustomUser> findByIp(@Param("ip") String ip);
}
