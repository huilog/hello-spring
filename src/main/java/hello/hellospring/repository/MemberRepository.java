package hello.hellospring.repository;

import java.util.Optional;
import java.util.List;
import hello.hellospring.domain.Member ;
import org.springframework.stereotype.Repository;

public interface MemberRepository {
    Member save(Member member);
    //Optional은 Java8에 들어간 기능으로 가져온 데이터가 null일 경우, optional로 감싸서 반환할 수 있음.
    // optional 타입으로 null이 반환될 경우 반환받은 곳에서 데이터 검증 후 null을 처리할 수 있음.(optional로 감싸서 반환)
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
