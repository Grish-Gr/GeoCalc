package maks.ter.geocalc.web;

import maks.ter.geocalc.repository.Block4Repo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MainApiController {

    private SessionFactory hibernateFactory;

    @Autowired
    public MainApiController(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }


    @GetMapping("/convert-table")
    public @ResponseBody void convertTable(@RequestBody TablesDto tablesDto) throws IllegalAccessException, FileNotFoundException, UnsupportedEncodingException {
        Reflections reflections = new Reflections("maks.ter");
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Entity.class);
        PrintWriter writer = new PrintWriter("add_new_tables.txt", "UTF-8");
        System.out.println(tablesDto.tables);

        writer.println("TRUNCATE TABLE region_data;");
        writer.println("TRUNCATE TABLE city_data;");

        Session session = hibernateFactory.openSession();

        for (Class<?> cls: allClasses) {
            Table annTable = cls.getAnnotation(Table.class);

            if (tablesDto.tables.contains(annTable.name())) {
                System.out.println(annTable.name());
                CriteriaBuilder cb = session.getCriteriaBuilder();
                List<?> dataList = session.createSQLQuery(String.format("SELECT * FROM %s", annTable.name())).addEntity(cls).getResultList();

                StringBuilder sqlScript = new StringBuilder();

                if (dataList.isEmpty()) {
                    continue;
                }

                if (isRegionInfo(cls)) {

                    sqlScript.append("INSERT INTO region_data (region, category, date_entry, value) VALUES\n");

                    for (Object entity: dataList) {
                        String category = annTable.name();
                        Field fieldRegion = Arrays.stream(entity.getClass().getDeclaredFields()).filter(field -> field.getName().equals("region")).findAny().get();
                        fieldRegion.setAccessible(true);
                        String region = (String) fieldRegion.get(entity);

                        for (Field field: entity.getClass().getDeclaredFields()) {
                            if (!field.getName().matches("[a-zA-Z]+_[0-9]+")) {
                                continue;
                            }
                            field.setAccessible(true);
                            LocalDate date = getDateByField(field);
                            long valueField = (long) field.get(entity);

                            sqlScript.append(String.format(", (\"%s\", \"%s\", \"%s\", %s)\n", region, category, date.format(DateTimeFormatter.ISO_DATE), valueField));
                        }
                    }

                } else {

                    sqlScript.append("INSERT INTO city_data (city, category, date_entry, value) VALUES\n");

                    for (Object entity: dataList) {
                        String category = annTable.name();
                        Field fieldRegion = Arrays.stream(entity.getClass().getDeclaredFields()).filter(field -> field.getName().equals("city")).findAny().get();
                        fieldRegion.setAccessible(true);
                        String region = (String) fieldRegion.get(entity);

                        for (Field field: entity.getClass().getDeclaredFields()) {
                            if (!field.getName().matches("[a-zA-Z]+_[0-9]+")) {
                                continue;
                            }
                            field.setAccessible(true);
                            LocalDate date = getDateByField(field);
                            long valueField = (long) field.get(entity);

                            sqlScript.append(String.format(", (\"%s\", \"%s\", \"%s\", %s)\n", region, category, date.format(DateTimeFormatter.ISO_DATE), valueField));
                        }
                    }

                }
                sqlScript.append(";");

                writer.println(sqlScript);
            }
        }

        writer.close();
    }

    private LocalDate getDateByField(Field field) {
        String dateInField = field.getName();
        int beginYear = dateInField.indexOf("_");

        String monthInField = dateInField.substring(0, beginYear);
        String yearInField = dateInField.substring(beginYear + 1);

        String stringDate = String.format("%s 1, %s", StringUtils.capitalize(monthInField), yearInField);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        return LocalDate.parse(stringDate, formatter);
    }

    private Boolean isRegionInfo(Class<?> cls) {
        return Arrays.stream(cls.getDeclaredFields()).anyMatch(field -> field.getName().equals("region"));
    }

    record TablesDto(List<String> tables) {
    }
}
