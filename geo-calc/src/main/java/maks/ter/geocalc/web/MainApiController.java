package maks.ter.geocalc.web;

import org.reflections.Reflections;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MainApiController {

    @GetMapping("/convert-table}")
    public @ResponseBody void convertTable(@RequestBody TablesDto tablesDto) {
        Reflections reflections = new Reflections("maks.ter.geocalc.model");
        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

        for (Class<?> cls: allClasses) {
            Table annTable = cls.getAnnotation(Table.class);
        }
    }

    record TablesDto(List<String> tables) {
    }
}
