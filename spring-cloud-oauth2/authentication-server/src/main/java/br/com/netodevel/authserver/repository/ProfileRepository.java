package br.com.netodevel.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netodevel.authserver.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByName(String role_admin);

}
