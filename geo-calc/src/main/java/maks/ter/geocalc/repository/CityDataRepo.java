package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.CityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDataRepo extends JpaRepository<CityData, Long> {
    List<CityData> findAllByCategoryAndCity(String category, String city);
}
