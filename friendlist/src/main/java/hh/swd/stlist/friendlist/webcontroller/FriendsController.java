package hh.swd.stlist.friendlist.webcontroller;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import hh.swd.stlist.friendlist.domain.Student;

@Controller
public class FriendsController {

List<Student> friends = new ArrayList<Student>();
// Luodaan tänne, koska halutaan käyttää täyttyvää listaa
// muuallakin, sekä GET että POST.
	
@RequestMapping(value="/index", method = RequestMethod.GET) // Tästä aloitetaan. Palauttaa lomakkeen newcarform. Alempana jatketaan tästä.
public String getAllFriends(Model model, Student friend) { // Luotu suoraan Studentista uusi objekti.
	//friends.add(new Student("John West"));
	//friends.add(new Student("Kate Brower"));
	//model.addAttribute("friend", new Student());
	model.addAttribute("friend", friend); // Täytyy luoda tässä, koska listanadd.html sisältää viittauksia Model-luokkaan.
	model.addAttribute("friends", friends);
	return "listandadd"; // listanadd.html, palauttaa tämän.
		
	}
	
@RequestMapping(value="/newfriend", method = RequestMethod.POST)
public String addNewFriend(@ModelAttribute Student friend, Model model) {
		//friends.add(new Student(friend.getFullName()));
		//friends.add(new Student("Lollero"));
		//model.getAttribute(friend.getFullName());
		model.addAttribute("friend", friend);
		friends.add(new Student(friend.getFullName()));
		model.addAttribute("friends", friends);
		return "listandadd";
		
	}
	
	
	
}
