package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    /*
    * new 키워드를 이용해 MemberService 객체를 생성해서 사용할 수도 있으나,
    * 스프링이 관리를 하게되면 다 스프링 컨테이너에 등록을 하고 다 스프링 컨테이너로부터 받아서 쓸 수 있도록 바꿔야 함.
    * 왜냐하면, new 키워드를 이용해 직접 객체를 생성해서 사용할 시
    * MemberController 말고 다른 여러 Controller에서 MemberService를 가져다 쓸 수 있기 때문이다.
    * Service의 경우 비즈니스 로직을 작성한 객체이므로 여러개 띄워놓고 쓸 필요 없이
    * 하나만 생성한 후 공유해서 쓰면 됨(singleton).
    * -> 따라서 스프링 컨테이너에 service를 등록해놓고 쓰면 됨.(스프링 컨테이너에 등록하면 딱 하나만 등록됨)
    * */
    private final MemberService memberService;

    @Autowired
    /*컨트롤러 생성자에 Autowired 어노테이션을 사용하면 서비스 매개변수에
     스프링 컨테이너에 있는(등록되어있는) 서비스 객체를 주입시켜줌(DI 의존관계 주입)
     (wired = 연결하다)
     Q. 따로 해당 서비스(MemberService)를 인스턴스화 하지 않았는데 스프링이 어떻게 알고 해당 메서드에 매개변수로 주입시켜줌?
     A. 매개변수로 주입시키고자하는 서비스에 @Service 애노테이션을 사용해야 함.
      service class 자체는 순수 class(정의서)이기 때문에
     스프링에게 해당 클래스가 주입 대상임을 어노테이션으로 알려서 객체를 생성해야 함(스프링 컨테이너에 관리 대상으로 등록).
        -> repository도 동일한 과정을 거쳐야 함(관리 대상 Repository에 @Repository 애노테이션 작성)
        왜냐하면 controller, service, repository와 같은 개념이 매우 정형화 된 개념이기 때문에 생긴 규칙임.
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
