package maks.ter.geocalc.repository;

import maks.ter.geocalc.model.DirectoryRegionsAndCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectoryRegionsAndCitiesRepo extends JpaRepository<DirectoryRegionsAndCities, Long> {
    DirectoryRegionsAndCities findByRegion(String region);
}
