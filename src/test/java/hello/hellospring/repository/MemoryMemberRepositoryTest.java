package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    /*
    * 주의사항
    * - 모든 테스트는 실행 순서가 보장되지 않으므로, 순서와 상관없이 메서드별로 각각 따로 작동될 수 있도록 설계해야한다.
    * (즉 순서에 의존적이게 설계하면 안된다는 말임)
    * */
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test method 실행이 끝날때마다 호출되는 callBack method
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        /* Assertions: jUnit의 jupiter가 제공하는 기능 - test 실행해서 성공, 실패여부를 확인할 수 있음.
        * 실무에서는 이 기능을 빌드 tool이랑 엮어서 만약 테스트 결과가 false면 다음 단계로 못넘어가게 막아버린다. */
        Assertions.assertEquals(member, result); //junit
        //assertThat(member).isEqualTo(null); // assertj static import
        assertThat(member).isEqualTo(result); // assertj static import

    };

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("string1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("string2");
        repository.save(member2);

        Member result1 = repository.findByName("string1").get();
        assertThat(member1).isEqualTo(result1);

        Member result2 = repository.findByName("string2").get();
        assertThat(member2).isEqualTo(result2);
    };

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("string1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("string2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    };

}
