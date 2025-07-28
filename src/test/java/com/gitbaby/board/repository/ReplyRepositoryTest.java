package com.gitbaby.board.repository;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
  @Autowired
  private ReplyRepository repository;
  @Autowired
  private BoardRepository boardRepository;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist(){
    Assertions.assertNotNull(repository);
  }


  @Test
  @DisplayName("댓글 등록")
  public void testInsertReply(){
    List<Long> bnos = boardRepository.findAll().stream().map(Board::getBno).toList();

    IntStream.range(0, 100).forEach(i -> {
      long bno = bnos.get(new Random().nextInt(bnos.size()));
      Board board = Board.builder().bno(bno).build();
      Reply reply = Reply.builder().board(board).text("reply" + i).replyer("guest").build();
      repository.save(reply);
    });
  }


  @Test
  @DisplayName("댓글 조회")
  public void testRead(){
    Reply reply = repository.findById(1L).orElse(null);
    log.info(reply);
    log.info(reply.getBoard());
    log.info(reply.getBoard().getWriter());
  }

  @Test
  @DisplayName("게시글 번호로 리뷰 가져오기")
  public void testFindByBoard_bnoOrderByRnoDesc() {
    repository.findByBoard_bnoOrderByRnoDesc(115L).forEach(log::info);
  }
  @Test
  @DisplayName("게시글로 리뷰 가져오기")
  public void testFindByBoard() {
    repository.findByBoard(Board.builder().bno(115L).build()).forEach(log::info);
  }
}
