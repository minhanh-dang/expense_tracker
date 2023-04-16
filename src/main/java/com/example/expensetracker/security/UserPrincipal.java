package com.example.expensetracker.security;

import com.example.expensetracker.model.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private boolean lock;

	public Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String name, String password, GrantedAuthority authority) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal build(Users user) {
//		List<GrantedAuthority> authorities = user.getRole().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName().name()) ;
		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authority);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getUsername() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (this.lock == true) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;

	}

	@Override
	public boolean isEnabled() {
		if (this.lock == true) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrincipal user = (UserPrincipal) o;
		return Objects.equals(id, user.id);
	}


}