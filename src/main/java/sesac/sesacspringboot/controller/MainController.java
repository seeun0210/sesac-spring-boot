package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.dto.UserDTO;
import sesac.sesacspringboot.vo.UserVO;
import sesac.sesacspringboot.vo.UserVPractice;

import java.util.HashMap;
import java.util.Map;

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
//    @PostMapping("/signup")
//    @ResponseBody
//    public String postPractice(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "gender") String gender,
//            @RequestParam(value = "dob") String dob,
//            @RequestParam(value = "interests") String interest,
//            Model model) {
//
//
//        String response = "이름: " + name + "<br>" +
//                "성별: " + gender + "<br>" +
//                "생년월일: " + dob + "<br>" +
//                "관심사: " + interest;
//
//
//        model.addAttribute("userInfo", response);
//
//
//        return response;
//    }
    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute("UserDTO") UserDTO userDTO){
        // 변수로 값을 하나씩 가져오는 게 아니라 객체에 값을 담아서 가져오고 싶을 때 사용하는 방법
        // @ModelAttribute : html 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑해주는 친구
        // 매핑=setter 함수를 실행한다.
        // -> ?name=&age -> setName() setAge()
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    @PostMapping("/dto/response2")
    @ResponseBody
    public String dtoResponse2(UserDTO userDTO){
//아무것도 없는 상태=@ModelAttribute 상태
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    @PostMapping("/dto/response3")
    @ResponseBody
    public String dtoResponse3(@RequestBody UserDTO userDTO){
        //@RequestBody: 요청의 본문에 있는 데이터(body)를 받아와서 객체에 매핑(필드 값에 주입)
        // 전달받은 요청의 형식이 json 또는 xml형태 일 때만 실행이 가능
        // 일반 폼전송 = www-x-form-urlencoded -> requestbody가 처리할 수 없는 형태! -> error!
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    //Q. get 방식 -vo=null(modelAttribute=setter함수 실행)
    //post방식 - vo=null
    //post 방식 -vo -requestbody=오류
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }
    @PostMapping ("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }
    @PostMapping ("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }

    //DTO-axios
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosAPI1(@RequestParam(value="name")String name, @RequestParam(value="age")String age){
        String msg="이름 : "+name+", 나이: "+age;
        return msg;
    }
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosAPI2(UserDTO userDTO){
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    @PostMapping ("/axios/response3")
    @ResponseBody
    public String axiosAPI3(@RequestParam(value="name" ,required=false)String name, @RequestParam(value="age",required=false)String age){
        //@ResponseParam=@ModelAttribute=query string으로 넘어온 데이터를 읽을 수 있다.
        //@RequestParam에 require가 true=>
        String msg="이름 : "+name+", 나이: "+age;
        return msg;
    }
    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosAPI4(UserDTO userDTO){
        //ModelAttribute는 json으로 넘어온 데이터를 읽지 못한다.
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosAPI5(@RequestBody UserDTO userDTO){
        String msg="이름 : "+userDTO.getName()+", 나이: "+userDTO.getAge();
        return msg;
    }
    //axios-get -(RequestParam)-?>O
    //axios get, dto>O
    //axios post-(RequestParam)->required 가 false일때는 null, true일때는 x
    //axios post-requestbody x dto -> null
    //axios post -requestbody o dto -> o
    //vo-axios
    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosvoAPI1(@RequestParam(value="name")String name, @RequestParam(value="age")String age){
        String msg="이름 : "+name+", 나이: "+age;
        return msg;
    }
    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosvoAPI2(UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }
    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosvoAPI3(@RequestParam(value="name")String name, @RequestParam(value="age")String age){
        String msg="이름 : "+name+", 나이: "+age;
        return msg;
    }
    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosvoAPI4(UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }
    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosvoAPI5(@RequestBody UserVO userVO){
        String msg="이름 : "+userVO.getName()+", 나이: "+userVO.getAge();
        return msg;
    }
    //axios-get:가능
    //get VO;null=@ModelAttribute(setter함수를 실행할 수가 없어서)
    //Post 일반: error
    //Post VO-Request x:null=@ModelAttribute(setter함수를 실행할 수가 없어서)
    //Post VO-RequestBody :가능

    //실습
    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Object> postPractice(@RequestBody UserVPractice userVOpractice) {
        Map<String, Object> responseMap = new HashMap<>();

        String response = "이름: " + userVOpractice.getName() +
                "성별: " + userVOpractice.getGender() +
                "생년월일: " + userVOpractice.getDob() +
                "관심사: " + userVOpractice.getInterests();



        responseMap.put("response", response);
        responseMap.put("name", userVOpractice.getName());

        return responseMap;
    }


}
