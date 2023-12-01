package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/api")
    public String getApi(){return "request";}
    //1) get : ?key=value
//    검색, 해시태그
    // /get/response1?name=abc
@GetMapping("/get/response1")
    public String getResponse(@RequestParam(value="name")String name,  Model model){
//        @RequestParam : 요청의 파라미터를 매개변수로 받을 때 적는 annotation
//    value=? 뒤에 넘어온 키
//    required 값을 설정할 수 있다. true/ false -> 기본값은 true
    //required가 true로 되어 있으면 ?뒤에 해당하는 key가 없을 경우 메소드를 찾지 못한다.
        model.addAttribute("name",name);
//    model.addAttribute("age",age);
        return  "response";
}
@GetMapping("/get/response2")
    public String getResponse2(@RequestParam(value="name",required = false)String name, Model model){
        //String ~에서 ~는 사용자 지정임(마음대로해도 됨)
        model.addAttribute("name",name);
        return "response";
}
// /get/response3/이름/나이
@GetMapping("/get/response3/{name}/{age}")
    public String getResponse3(@PathVariable String name, @PathVariable String age, Model model){
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "response";
}

    @GetMapping({"/get/response4/{name}","/get/response4/{name}/{age}"})

    public String getResponse4(@PathVariable(value="name") String name, @PathVariable(required = false) String age, Model model){
        //@PathVariable 에 required 설정이 가능하나, 기본값은 True
        //@PathVariable 에 required 설정할 때는 @GetMapping 메서드에 url도 같이 설정해줘야 한다
        //required false일 때는
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "response";
    }
    @GetMapping({"/introduce"})
    public String getResponsePrac1(@PathVariable(value="name")String name,Model model){
        model.addAttribute("name",name);
        return "response";
    }
    @GetMapping({"/introduce2"})
    public String getResponsePrac2(@RequestParam(value="name")String name,@RequestParam(value="age")int age, Model model){
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "response";
    }

}
