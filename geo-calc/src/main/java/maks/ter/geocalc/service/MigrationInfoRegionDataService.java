package maks.ter.geocalc.service;

import maks.ter.geocalc.dto.MigrationInfoRegionDto;
import maks.ter.geocalc.model.Block1;
import maks.ter.geocalc.repository.Block1Repo;
import maks.ter.geocalc.repository.Block2Repo;
import maks.ter.geocalc.repository.Block5Repo;
import maks.ter.geocalc.service.dto.EducationDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class MigrationInfoRegionDataService extends DataService{

    private Block1Repo block1Repo;
    private Block2Repo block2Repo;

    @Autowired
    public MigrationInfoRegionDataService(DataUtils dataUtils, Block5Repo block5Repo, Block1Repo block1Repo, Block2Repo block2Repo) {
        super(dataUtils, block5Repo);

        this.block1Repo = block1Repo;
        this.block2Repo = block2Repo;
    }

    public List<EducationDetailsDto> getDataList(MigrationInfoRegionDto migrationInfoRegionDto) {

        List<Block1> dataList = block1Repo.findAll();

return new ArrayList<>();
    }

    private Double getDiRegion(String region) {
return 0.0;
    }

    private Double getDiPeople(Block1 diData) {


        long currentYear = Year.now().getValue() - 1;

        List<Block1> allData = block1Repo.findAll();
        List<Block1> dataList = new ArrayList<>();
        List<Block1> dataListInYear = new ArrayList<>();

        while (dataList.isEmpty() && currentYear < 2018) {
            dataList = block1Repo.findAllByRegionAndYearRes(diData.getRegion(), currentYear);
            dataListInYear = block1Repo.findAllByRegion(diData.getRegion());
            currentYear--;
        }

        if (dataList.isEmpty()) {
            return null;
        }

        return 0.0;
    }

    private Double getDiBusiness(Block1 diData) {
return 0.0;
    }
}
