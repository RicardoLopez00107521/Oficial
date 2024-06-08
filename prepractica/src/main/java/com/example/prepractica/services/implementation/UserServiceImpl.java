package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.dtos.User.RegisterDTO;
import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.entities.Token;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.RolRepository;
import com.example.prepractica.repositories.TokenRepository;
import com.example.prepractica.repositories.UserRepository;
import com.example.prepractica.services.UserService;
import com.example.prepractica.utils.JWTTools;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final JWTTools jwtTools;

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    private final RolRepository rolRepository;

    public UserServiceImpl(UserRepository userRepository, JWTTools jwtTools, TokenRepository tokenRepository, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.rolRepository = rolRepository;
    }


    @Override
    public User findUserByIdentifier(String identifier) {
        return this.findUserByUsernameOrEmail(identifier, identifier);
    }

    @Override
    public User findUserByUsernameOrEmail(String username, String email) {
        return userRepository
                .findByUsernameOrEmail(username, email)
                .orElse(null);
    }

    @Override
    public User findByUUID(UUID uuid) {
        return userRepository.findByUserId(uuid)
                .orElse(null);
    }

    @Override
    public boolean correctPassword(User user, String password) {
        return !user.getPassword().equals(password);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void registerUser(RegisterDTO info, Date fechaNacMapped) {

        User user = new User();

        List<Rol> roles = findRolesByIdentifier(List.of("AA13"));

        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setFechaNac(fechaNacMapped);
        user.setPassword(info.getPassword());
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public Date validDate(String fechaNac) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fechaNac);
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user,true); // Obtenemos todos los tokens activos del usuario en forma de lista

        tokens.forEach(token -> { // Creamos unestra lambda, para cada uno de los tokens
            if (!jwtTools.verifyToken(token.getContent())) { // Si la verificacion es false
                token.setActive(false); // Seteamos el Token como false a cada uno de los tokens en la lista
                tokenRepository.save(token); // Modificamos el Token a traves del Repositorio
            }
        });
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) {
        cleanTokens(user); // Llamamos a la funcion cleanTokens
        cleanPreviousTokens(user);

        String tokenString = jwtTools.generateToken(user); // Generamos el Token
        Token token = new Token(tokenString, user); // Le pasamos el String al Token y el usuario para que lo almacene en la base

        tokenRepository.save(token); // Guardamos el token a traves del Repositorio (CRUD)

        return token; // Devolvemos el Token
    }

    @Override
    @Transactional(rollbackOn = Exception.class) // Indica una modificacion transaccional a la base de datos (Insercion), el rollback indica si hay una excepcion se revertira
    public Boolean validateToken(User user, String token) {
        cleanTokens(user); // Limpiamos los tokens
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true); // Obtenemos los tokens activos por usuario

        tokens.stream() // Creamos nuestro stream
                .filter(tk -> tk.getContent().equals(token)) // Filtramos el token que recibimos y buscamos una coincidencia total con los tokens almacenados
                .findAny();// Obtenemos todos los que son iguales, sino solo devuelve null

        return tokens.stream().anyMatch(tk -> tk.getContent().equals(token)); // Si un token cumple con la condicion dada devolvera true, sino devolver false
    }

    @Override
    public void cleanPreviousTokens(User user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            token.setActive(false);
            tokenRepository.save(token);
        });
    }

    @Override
    public User findUserAuthenticated() {
        String email = SecurityContextHolder
                .getContext() // Proporciona acceso al contexto de seguridad en la aplicación. El método getContext() devuelve el contexto de seguridad actual.
                .getAuthentication() // Devuelve el objeto Authentication que representa los detalles de la autenticación del usuario actual.
                .getName(); // Devuelve el identificador del usuario autenticado

        return userRepository.findByUsernameOrEmail(email, email) // Buscamos el usuario
                .orElse(null); // Sino existe devolvemos false
    }

    @Override
    public void createDefaultUser(String name, String userEmail, String password) {
        User user = new User();

        List<Rol> roles = findRolesByIdentifier(List.of("AA11"));

        user.setUsername(name);
        user.setEmail(userEmail);
        user.setPassword(password);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public List<Rol> findRolesByIdentifier(List<String> rolId) {
        return rolRepository.findAllById(rolId
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }
}
