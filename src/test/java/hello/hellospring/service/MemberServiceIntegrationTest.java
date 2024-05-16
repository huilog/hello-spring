package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() { //test는 method명을 과감하게 한글로 변경해도 됨. 영어권 사람들과 일하는 것만 아니면 직관적으로 한글로 작성하기도 함.
        //1. given - 무언가 주어졌을 때
        Member member = new Member();
        member.setName("testtttttt");

        //2. when - 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //3. then - 결과가 이게 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test // 정상 flow 외에 비정상 flow도 test를 해야함. 정상 flow만 테스트를 하는 건 반쪽자리 테스트.
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/


        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}