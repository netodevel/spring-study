package br.com.netodevel.authserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netodevel.authserver.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    List<Users> findByName(String name);

    Users findByEmail(String username);
}
