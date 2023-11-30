package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @GetMapping("/people")
    public String getPeople(Model model){
        Person kim = new Person("kim", 10);
        Person lee = new Person("lee", 10);
        Person hong = new Person("hong", 10);
        Person park = new Person("park", 40);
        Person shin = new Person("shin", 50);
        List<Person> people = new ArrayList<>();
        people.add(kim);
        people.add(lee);
        people.add(hong);
        people.add(park);
        people.add(shin);


        model.addAttribute("people", people);


        return "people";
    }

    class Person {
        private int age;
        private String name;

        private Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName(){
            return name;
        }

        public int getAge(){
            return age;
        }
    }
}
