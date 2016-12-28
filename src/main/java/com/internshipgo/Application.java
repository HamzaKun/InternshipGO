package com.internshipgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.internshipgo")
//@EntityScan("com.internshipgo")
//@EnableAutoConfiguration
//@EnableJpaRepositories("com.internshipgo.model.repository")
public class Application {
    /*@Bean
    public DataSource dataSource() {
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Test?useSSL=false");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        return dataSource;
        return DataSourceBuilder
                .create()
                .username("test")
                .password("test")
                .url("jdbc:mysql://localhost:3306/Test?useSSL=false")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.internshipgo.model");
        return em;
    }*/


    /*private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    private static EntityManager em = emf.createEntityManager();*/

    /*@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mysql.jdbc.JDBCDriver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Test?useSSL=false");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("package.where.your.entites.like.CustSys.are.stored");
        return em;
    }*/

    public static void main(String[] args) {
       /*User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        SpringApplication.run(Application.class, args);
    }
/*
    @Bean
    public CommandLineRunner demo(UserDao userDao) {
        return (args) -> {
            userDao.save(new User("Hamza", "hamza"));
        };
    }
*/
}