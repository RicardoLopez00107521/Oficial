package com.example.prepractica;

import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.RolRepository;
import com.example.prepractica.repositories.UserRepository;
import com.example.prepractica.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.relation.Role;

@SpringBootApplication
public class PrepracticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepracticaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerRol(RolRepository rolRepository, UserService userService, UserRepository userRepository) {
        return args -> {
            Rol existRole = rolRepository.findById("AA11").orElse(null);

            if (existRole == null) {
                Rol rol = new Rol();
                rol.setRolId("AA11");
                rol.setRol("SYSADMIN");
                rolRepository.save(rol);

            User user = userRepository.findByUsernameOrEmail("admin@admin.com","admin@admin.com")
                    .orElse(null);

            if(user == null) {
                String username = "admin";
                String userEmail = "admin@admin.com";
                String password = "AAb123457%";

                userService.createDefaultUser(username, userEmail, password);
            }
            }
        };
    }
}
