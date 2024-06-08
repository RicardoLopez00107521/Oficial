package com.example.prepractica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String username;
    private String email;
    private Date fechaNac;
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Historial> historiales;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="usuario_rol",
            joinColumns = @JoinColumn(name="userId"), // Nombre de columna que tendrá ID USER
            inverseJoinColumns = @JoinColumn(name="rolId") // Nombre de columna que tendrá ID ROL
    )
    @JsonIgnore
    private List<Rol> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CitaMedica> appointments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRol()))
                .collect(Collectors.toList());
    }
}
