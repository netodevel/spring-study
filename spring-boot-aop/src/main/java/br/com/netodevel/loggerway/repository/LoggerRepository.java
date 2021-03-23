package br.com.netodevel.loggerway.repository;

import br.com.netodevel.loggerway.domain.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
}
