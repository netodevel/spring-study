package br.com.netodevel.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netodevel.userservice.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
