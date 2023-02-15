package com.salesianostriana.dam.proyectoinmo.inmobosco.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="Usuario")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario implements UserDetails {

    //En el DTO de respuesta tienes que devolver el rol

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String nombre;

    private String apellidos;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    private String username;

    private String dni;

    private int edad;

    private String avatar;

    private Date fechaNacimiento;

    private String telefono;

    private String email;

    @OneToMany(mappedBy = "propietario")
    private List<Inmueble> propiedades = new ArrayList<>();

    @ElementCollection(targetClass=String.class)
    private List<Inmueble> inmueblesFav = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
