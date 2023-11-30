package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PracticeController {
    @GetMapping("/practice")
    public String getPractice(Model model){
        Age age = new Age(11);
        model.addAttribute("classAge", age); // Use the correct attribute name

        return "practice";
    }

    class Age {
        private int age;

        public Age(int age){
            this.age = age;
        }

        public int getAge(){
            return age;
        }
    }
}
