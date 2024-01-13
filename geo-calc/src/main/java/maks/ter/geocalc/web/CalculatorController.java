package maks.ter.geocalc.web;

import maks.ter.geocalc.dto.*;
import maks.ter.geocalc.service.EducationDataService;
import maks.ter.geocalc.service.EmploymentDataService;
import maks.ter.geocalc.service.MigrationInfoRegionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class CalculatorController {

    @Autowired
    private EducationDataService educationDataService;
    @Autowired
    private EmploymentDataService employmentDataService;
    @Autowired
    private MigrationInfoRegionDataService migrationInfoRegionDataService;

    @GetMapping({"/calculator"})
    public String calculator(Model model, HttpServletResponse response) throws IOException {

        Cookie cookie = new Cookie("completed_questionnaire", "true");
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");

        model.addAttribute("educationRequest", new EducationDto());
        model.addAttribute("employeeRequest", new EmploymentDto());
        model.addAttribute("migrationInfoRequest", new MigrationInfoRegionDto());

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE, PurposeCalc.MIGRATION_INFO_REGION));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

        model.addAttribute("employmentDataTypes", List.of(EmploymentDataType.PROPOSED_SALARY, EmploymentDataType.VACANCIES));

        model.addAttribute("listMaxResults", List.of(MaxResult.RESULT_10, MaxResult.RESULT_20, MaxResult.RESULT_30, MaxResult.ALL));

        return "calculator";
    }

    @GetMapping({"/ed-calculator"})
    private String edCalculator(Model model, @RequestParam String educationLevel, @RequestParam String priority, @RequestParam int maxResult) {

        EducationDto educationDto = new EducationDto(
            EdPriority.valueOf(priority), EducationLevel.valueOf(educationLevel), maxResult
        );

        model.addAttribute("educationRequest", educationDto);
        model.addAttribute("employeeRequest", new EmploymentDto());
        model.addAttribute("migrationInfoRequest", new MigrationInfoRegionDto());

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE, PurposeCalc.MIGRATION_INFO_REGION));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

        model.addAttribute("employmentDataTypes", List.of(EmploymentDataType.PROPOSED_SALARY, EmploymentDataType.VACANCIES));

        model.addAttribute("listMaxResults", List.of(MaxResult.RESULT_10, MaxResult.RESULT_20, MaxResult.RESULT_30, MaxResult.ALL));

        model.addAttribute("listDataResult", educationDataService.getDataList(educationDto));
        model.addAttribute("typeTable", priority);

        return "calculator";
    }

    @GetMapping({"/em-calculator"})
    private String emCalculator(Model model, @RequestParam String dataType, @RequestParam int maxResult) {

        EmploymentDto educationDto = new EmploymentDto(
                maxResult, EmploymentDataType.valueOf(dataType)
        );

        model.addAttribute("educationRequest", new EducationDto());
        model.addAttribute("employeeRequest", educationDto);
        model.addAttribute("migrationInfoRequest", new MigrationInfoRegionDto());

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE, PurposeCalc.MIGRATION_INFO_REGION));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

        model.addAttribute("employmentDataTypes", List.of(EmploymentDataType.PROPOSED_SALARY, EmploymentDataType.VACANCIES));

        model.addAttribute("listMaxResults", List.of(MaxResult.RESULT_10, MaxResult.RESULT_20, MaxResult.RESULT_30, MaxResult.ALL));

        model.addAttribute("listDataResult2", employmentDataService.getDataList(educationDto));
        model.addAttribute("typeTable", dataType);

        return "calculator";
    }

    @GetMapping({"/di-calculator"})
    private String diCalculator(Model model, @RequestParam int maxResult) {

        MigrationInfoRegionDto migrationInfoRegionData = new MigrationInfoRegionDto(
                maxResult
        );

        model.addAttribute("educationRequest", new EducationDto());
        model.addAttribute("employeeRequest", new EmploymentDto());
        model.addAttribute("migrationInfoRequest", migrationInfoRegionData);

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE, PurposeCalc.MIGRATION_INFO_REGION));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

        model.addAttribute("employmentDataTypes", List.of(EmploymentDataType.PROPOSED_SALARY, EmploymentDataType.VACANCIES));

        model.addAttribute("listMaxResults", List.of(MaxResult.RESULT_10, MaxResult.RESULT_20, MaxResult.RESULT_30, MaxResult.ALL));

        model.addAttribute("listDataResult3", migrationInfoRegionDataService.getDataList(migrationInfoRegionData));
        model.addAttribute("typeTable", "MIGRATION");

        return "calculator";
    }

    @GetMapping({"/check-questionnaire"})
    public String check_questionnaire(@CookieValue(value = "completed_questionnaire") String data, Model model, HttpServletResponse response) throws IOException {
        if (data.equals("true")){
            calculator(model, response);
            return "calculator";
        }

        return "questionnaire";
    }
}
