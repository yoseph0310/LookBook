package com.pjt2.lb.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pjt2.lb.entity.User;

// 현재 액세스 토큰으로부터 인증된 유저의 부가 상세정보(유저정보, 활성화여부, 만료, 롤 등) 정의
public class LBUserDetails implements UserDetails {

	@Autowired
	User user;
	boolean accountNonExpired;
	boolean accountNonLocked;
	boolean credentialNonExpired;
	boolean enabled = false;
	List<GrantedAuthority> roles = new ArrayList();
	
	public LBUserDetails(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.user.getUserPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
	
}
