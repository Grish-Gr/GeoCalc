package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block4ProposedSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Block4Repo extends JpaRepository<Block4ProposedSalary, Long> {
}
