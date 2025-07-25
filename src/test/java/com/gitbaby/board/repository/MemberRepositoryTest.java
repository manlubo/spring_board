package com.gitbaby.board.repository;

import com.gitbaby.board.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository repository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    Assertions.assertNotNull(repository);
  }

  @Test
  public void insertMembers(){
    IntStream.range(0, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "@gmail.com").name("유저" + i).password("" + i).build();
      repository.save(member);
    });
  }
}
