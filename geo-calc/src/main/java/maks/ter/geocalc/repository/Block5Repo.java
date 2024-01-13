package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Block5Repo extends JpaRepository<Block5, Long> {

    List<Block5> findAllByYearResAndRegion(Long year, String region);
    List<Block5> findAllByYearRes(Long year);
    List<Block5> findAllByRegionOrderByYearResDesc(String region);
}
