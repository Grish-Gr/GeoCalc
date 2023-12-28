package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.RegionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionDataRepo extends JpaRepository<RegionData, Long> {
    List<RegionData> findAllByCategoryAndRegion(String category, String region);
    List<RegionData> findAllByCategoryAndOrderByValueDesc(String category);
    List<RegionData> findAllByCategoryAndOrderByValueAsc(String category);
}
