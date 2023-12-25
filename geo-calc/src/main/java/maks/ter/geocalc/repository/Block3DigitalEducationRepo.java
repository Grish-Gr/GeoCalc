package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block3DigitalEducation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Block3DigitalEducationRepo extends JpaRepository<Block3DigitalEducation, Long> {
    List<Block3DigitalEducation> findByOrderByMinContractAsc(Pageable pageable);
    List<Block3DigitalEducation> findByOrderByContractCountDesc(Pageable pageable);
    List<Block3DigitalEducation> findByOrderByBudgetCountDesc(Pageable pageable);
}
