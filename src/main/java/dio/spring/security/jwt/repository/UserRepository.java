package dio.spring.security.jwt.repository;


import dio.spring.security.jwt.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<AppUser, Integer> {
    @Query("SELECT e FROM AppUser e JOIN FETCH e.roles WHERE e.username= (:username)")
    public
    AppUser findByUsername(@Param("username") String username);
    boolean existsByUsername(String username);

}