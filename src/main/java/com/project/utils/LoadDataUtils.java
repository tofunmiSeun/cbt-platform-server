package com.project.utils;

import com.project.model.*;
import com.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tofunmi on 25/12/2016.
 */
@Component
public class LoadDataUtils {

    @Autowired
    FacultyService facultyService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    CourseService courseService;
    @Autowired
    QuizService quizService;

    @EventListener(ContextRefreshedEvent.class)
    void initialiseFields() {
        String[] names = new String[]{"Education", "Engineering", "Arts", "Social Sciences"};

        Faculty f;
        for (int i = 0; i < names.length; i++) {
            f = new Faculty();
            f.setName(names[i]);
            facultyService.saveFaculty(f);
        }

        names = new String[]{"Adult Education", "Physics Education", "English", "French",
                "Electrical Engineering", "Mechanical Engineering", "Systems Engineering", "Economics"};
        Long[] facultyIds = new Long[]{1L, 1L, 3L, 3L, 2L, 2L, 2L, 4L};
        int[] courseDuration = new int[]{4, 4, 4, 4, 5, 5, 5, 4};

        Department d;
        for (int i = 0; i < names.length; i++) {
            d = new Department();
            d.setName(names[i]);
            d.setFacultyId(facultyIds[i]);
            d.setCourseDurationInYears(courseDuration[i]);
            departmentService.save(d);
        }

        String[] courseTitles = new String[]{"Introductory Chemistry", "General Nigerian Studies 1", "General Nigerian Studies 2"};
            String[] courseCodes = new String[]{"CHM101", "GNS111", "GNS211"};
        Long[] departmentIds = new Long[]{5L, 3L, 3L};
        int[] levelValues = new int[]{1, 1, 2};

        for (int i = 0; i < courseTitles.length; i++) {
            Course c = new Course();
            c.setCourseCode(courseCodes[i]);
            c.setCourseTitle(courseTitles[i]);
            c.setDepartmentId(departmentIds[i]);
            c.setLevelValue(levelValues[i]);

            courseService.save(c);
        }

        String[] quests = new String[]{
                "What is the chemical formula of Iron?",
                "On a PH scale, which number represents neutral PH?",
                "'Matter cannot be created nor destroyed' is which law?",
                "Which of these people wasn't a scientist?",
                "The by-product of neutralisation is?",
                "Manganese is a _______",
                "Which is the unit of electric charge",
                "Which of these is a term in organic chemistry",
                "Hydrogen has a valency of",
                "Ozone is made up of which element",
                "A way to prevent rust is by",
                "Which of these is Highest in the electromagnetic spectrum",
                "The unit of mass is",
                "Bohr invented atoms",
                "Metals ______"
        };

        String[][] options = new String[][]{
                {"Fe", "Ir", "Ag", "He"},
                {"14", "7", "0", "1"},
                {"Law of Conservation of mass", "Faraday's 2nd Law of Electrolysis", "Law of Combining Volume",
                        "Darwin's Law of natural selection"},
                {"Gay Lusacs", "Micheal Faraday", "Robert Schumann", "Ada Lovelace"},
                {"Acid and Base", "Soap and Water", "Salt and Water", "Power and Energy"},
                {"Transition  Element", "Metal", "Non-metal"},
                {"Newton", "Coulomb", "Meter", "Kilogram"},
                {"Electrolysis", "Upthrust", "Saponification"},
                {"7", "6", "1", "0"},
                {"Cinemas", "Oxygen", "Hydrogen", "Ozone element"},
                {"Drying", "Soaking in water", "Electrolysis", "Galvanising"},
                {"Na", "H", "K", "Al"},
                {"M", "N", "KG", "J"},
                {"True", "False"},
                {"Ionize by accepting electrons", "Ionize by giving out electrons"}
        };

        int[] correctIndices = new int[]{0, 1, 0, 2, 2, 0, 1, 2, 2, 1, 3, 1, 2, 1, 1};

        Long[] courseIds = new Long[]{1L, 1L, 2L, 1L, 3L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,};

        for (int i = 0; i < correctIndices.length; i++) {
            Question q = new Question();
            q.setId(i + 1L);
            q.setCorrectAnswerIndex(correctIndices[i]);
            q.setQuestion(quests[i]);
            q.setOptions(options[i]);
            q.setCourseId(courseIds[i]);

            quizService.addQuestion(q);
        }
    }
}
