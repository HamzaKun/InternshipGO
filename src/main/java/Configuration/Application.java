package Configuration;

import Model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Application {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        SpringApplication.run(Application.class, args);
    }

}