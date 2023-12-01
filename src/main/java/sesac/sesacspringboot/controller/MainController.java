package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// @RestController: Controller 면서 모든 메소드가 @ResponseBody를 갖도록 하는 친구
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

    //////////////////////////////
    //post로 값을 전달할 때 그 값을 controller에서 받는 방법은 @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(@RequestParam(value="name")String name, Model model){
        model.addAttribute("name",name);
        return "response";
    }
    @PostMapping("/post/response2")
    public String postResponse2(@RequestParam(value="name",required = false)String name, Model model){
        model.addAttribute("name",name);
        return "response";
    }
    @PostMapping("/post/response3")
    @ResponseBody //res.send
    // return 하는 문자열의 template 파일을 불러오는 게 아니라
    // return 하는 문자열 그대로 값을 전달하는 것
    public String postResponse3(@RequestParam(value="name",required = false)String name, Model model){
        model.addAttribute("name",name);
        return name+"님 안녕하세요!";
    }
    @GetMapping("/signup")
    public String getPractice(){
        return "signup";
    };
    @PostMapping("/signup")
    @ResponseBody
    public String postPractice(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "dob") String dob,
            @RequestParam(value = "interests") String interest,
            Model model) {


        String response = "이름: " + name + "<br>" +
                "성별: " + gender + "<br>" +
                "생년월일: " + dob + "<br>" +
                "관심사: " + interest;


        model.addAttribute("userInfo", response);


        return response;
    }




}
