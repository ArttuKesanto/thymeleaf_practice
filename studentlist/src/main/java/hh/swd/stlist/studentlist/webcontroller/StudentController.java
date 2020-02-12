package hh.swd.stlist.studentlist.webcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd.stlist.studentlist.domain.Student;


@Controller
public class StudentController {

@RequestMapping(value="/hello", method = RequestMethod.GET)
public String getAllStudents(Model model) {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kate", "Cole"));
		students.add(new Student("Dan", "Brown"));
		students.add(new Student("Mike", "Mars"));
		// 2. sopiva Thymeleaf-templaten nimi palautetaan dispatcher-servletille - Arttu K.
		model.addAttribute("students", students); // ensimmäinen parametri teksti-keyword, jolla löydetään mapista. Toinen "vietävä" olio, viite, objekti.
		return "studentlist"; // palauttaa studentlist.html-templaten nimen

	};
}
