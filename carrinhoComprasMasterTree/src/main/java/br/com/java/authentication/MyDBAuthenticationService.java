package br.com.java.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.java.dao.ContaDAO;
import br.com.java.entity.Conta;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
	
	@Autowired
	private ContaDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Conta conta = dao.descConta(username);
		System.out.println("Conta= " + conta);

		if (conta == null) {
			throw new UsernameNotFoundException("User " //
					+ username + " was not found in the database");
		}

		// EMPLOYEE,MANAGER,..
		String role = conta.getUserRole();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		// ROLE_EMPLOYEE, ROLE_MANAGER
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

		grantList.add(authority);

		boolean enabled = conta.isActive();
		boolean contaNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean contaNonLocked = true;

		UserDetails userDetails = (UserDetails) new User(conta.getUserName(), //
				conta.getPassword(), enabled, contaNonExpired, //
				credentialsNonExpired, contaNonLocked, grantList);

		return userDetails;
	}

}
