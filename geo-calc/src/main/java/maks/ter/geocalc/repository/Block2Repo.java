package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.Block2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Block2Repo extends JpaRepository<Block2, Long> {
    List<Block2> findAllByRegion(String region);
    List<Block2> findAllByRegionAndYearRes(String region, Long year);
}
