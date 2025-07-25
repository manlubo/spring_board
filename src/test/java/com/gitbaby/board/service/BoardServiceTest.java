package com.gitbaby.board.service;

import com.gitbaby.board.dto.PageRequestDTO;
import com.gitbaby.board.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
public class BoardServiceTest {
  @Autowired
  private BoardService service;

  @Test
  @DisplayName("객체 생성 테스트")
  public void testExist(){
    log.info(service);
  }

  @Test
  @DisplayName("게시글 작성 테스트")
  public void testRegister(){
    BoardDTO dto = BoardDTO.builder()
            .title("테스트 코드 제목")
            .content("테스트 코드 내용")
            .writerEmail("user1@gmail.com")
            .build();
    Long bno = service.register(dto);

    log.info(bno);
  }

  @Test
  @DisplayName("리스트 테스트")
  public void testGetList(){
    service.getList(PageRequestDTO.builder().page(1).size(10).build()).getList().forEach(log::info);
  }

  @Test
  @DisplayName("단일조회")
  public void testGet(){
    log.info(service.get(1L));
  }
  
  @Test
  @DisplayName("삭제처리")
  @Transactional
  public void testRemove(){
    service.remove(1L);
  }

  @Test
  @DisplayName("수정처리")
  public void testModify(){
    BoardDTO dto = service.get(1L);
    dto.setTitle("수정된 제목입니다");
    service.modify(dto);
  }

}
