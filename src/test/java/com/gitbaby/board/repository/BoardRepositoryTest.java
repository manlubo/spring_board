package com.gitbaby.board.repository;

import com.gitbaby.board.projection.dto.*;
import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
  @Autowired
  private BoardRepository repository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    Assertions.assertNotNull(repository);
  }

  @Test
  @DisplayName("게시글 등록")
  public void testInsertBoard(){
    IntStream.range(0, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "@gmail.com").build();

      Board board = Board.builder().title("title" + i).content("content" + i).writer(member).build();
      repository.save(board);
    });
  }

  @Test
  @DisplayName("게시글 조회")
  @Transactional(readOnly = true)
  public void testRead(){
    Board board = repository.findById(1L).orElse(null);
    log.info(board);
    log.info(board.getWriter());
  }

  @Test
  public void testReadWithWriter1(){
    BoardWithWriterDTO dto = repository.getBoardWithWriter(1L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void testReadWithWriter2(){

    BoardWithWriterDTORecode dto = repository.getBoardWithWriter2(1L);
    log.info(dto.board());
    log.info(dto.member());
  }

  @Test
  public void testReadWithWriter3(){

    BoardWithWriterDTOClass dto = repository.getBoardWithWriter3(1L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void testReadWithReply() {
    List<BoardWithReplyDTO> dto = repository.getBoardWithReply(1L);
    dto.forEach(r -> {
      log.info(r.board());
      log.info(r.reply());
    });
  }

  @Test
  public void testGetBoardWithReplyCount(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"bno"));

//    Page<Object[]> result = repository.getBoardWithReplyCount2(pageable);
//    result.get().forEach(row -> {
//      log.info(Arrays.toString(row));
//    });

    Page<BoardWithReplyCountDTO> dtos = repository.getBoardWithReplyCount(pageable);

    dtos.stream().forEach(r -> {log.info(r.board()); log.info(r.member()); log.info(r.count());});

  }
}
