package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block3GeneralTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Block3GeneralTermsRepo extends JpaRepository<Block3GeneralTerms, Long> {

    List<Block3GeneralTerms> findAllByYearResAndRegion(Long year, String region);
}
