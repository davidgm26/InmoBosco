package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private LocalDate fechaNacimiento;

    private LocalDate fechaCreacion;

    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    private String telefono;

    private String email;

    @OneToMany(mappedBy = "propietario",fetch = FetchType.LAZY)
    private List<Inmueble> propiedades = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "propietario_id",
            foreignKey = @ForeignKey(name = "FK_FAVORITOS_USUARIO")),
            inverseJoinColumns = @JoinColumn(name = "inmbueble_id",
                    foreignKey = @ForeignKey(name = "FK_FAVORITOS_INMUEBLE")),
            name = "favoritos"
    )
    private List<Inmueble> inmueblesFav = new ArrayList<>();

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void addInmueble(Inmueble i){
        if(this.getInmueblesFav() == null)
            this.setInmueblesFav(new ArrayList<>());
        this.getInmueblesFav().add(i);

        if(i.getUsuariosFav() == null)
            i.setUsuariosFav(new ArrayList<>());
        i.getUsuariosFav().add(this);
    }

    public void removeFav(Inmueble i){
        i.getUsuariosFav().remove(this);
        this.getInmueblesFav().remove(i);
    }


}


