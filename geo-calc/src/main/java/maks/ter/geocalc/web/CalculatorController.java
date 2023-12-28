package maks.ter.geocalc.web;

import maks.ter.geocalc.dto.*;
import maks.ter.geocalc.service.EducationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class CalculatorController {

    @Autowired
    private EducationDataService educationDataService;

    @GetMapping({"/calculator"})
    public String calculator(Model model) {

        model.addAttribute("educationRequest", new EducationDto());
        model.addAttribute("employeeRequest", new EmploymentDto());

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

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

        model.addAttribute("purpose", List.of(PurposeCalc.EDUCATION, PurposeCalc.EMPLOYEE));
        model.addAttribute("levelEducation", List.of(EducationLevel.CVO, EducationLevel.SPEC, EducationLevel.MASTER));
        model.addAttribute("edPriority", List.of(EdPriority.AMOUNT_CONTRACT, EdPriority.COUNT_BUDGET, EdPriority.CONTRACT_COUNT));

        model.addAttribute("listMaxResults", List.of(MaxResult.RESULT_10, MaxResult.RESULT_20, MaxResult.RESULT_30, MaxResult.ALL));

        model.addAttribute("listDataResult", educationDataService.getDataList(educationDto));

        return "calculator";
    }
}
