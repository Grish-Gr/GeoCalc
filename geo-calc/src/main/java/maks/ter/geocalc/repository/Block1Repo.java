package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Block1Repo extends JpaRepository<Block1,Long> {
    List<Block1> findAllByRegion(String region);
    List<Block1> findAllByRegionAndYearRes(String region, Long year);
}
