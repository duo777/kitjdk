package br.com.ifpe.oxefood.servicos.acesso;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Roberto Alencar
 *
 */
@Entity
@Table
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Usuario SET enabled = false WHERE id = ?")
@Where(clause = "enabled = true")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = -5439645523024902501L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String username;

    @JsonIgnore
    @NotEmpty
    private String password;

    @NotNull
    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    private Boolean enabled = Boolean.TRUE;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public String getPassword() {
	return this.password;
    }

    public String getUsername() {
	return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
	return enabled;
    }

}