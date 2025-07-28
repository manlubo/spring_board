package com.gitbaby.board.repository.search;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.QBoard;
import com.gitbaby.board.domain.entity.QMember;
import com.gitbaby.board.domain.entity.QReply;
import com.gitbaby.board.domain.projection.BoardWithReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
  // QuerydslRepositorySupport : QueryDSL 사용을 쉽게 하기 위한 기본 지원 클래스
  public SearchBoardRepositoryImpl() {
    super(Board.class); // 엔티티를 알려줘야 QueryDsl 동작함
  }

  @Override
  public Board search1() {
    log.info("===================================================================");
    QBoard qBoard = QBoard.board;
    JPQLQuery<Board> jpqlQuery = from(qBoard);

    jpqlQuery.where(qBoard.bno.gt(0L));
    log.info(jpqlQuery);

    // member 조인
    jpqlQuery
            .leftJoin(QMember.member).on(qBoard.writer.eq(QMember.member))
            .leftJoin(QReply.reply).on(QReply.reply.board.eq(qBoard));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, QMember.member, QReply.reply.count()).groupBy(qBoard).limit(10).orderBy(qBoard.bno.desc());
    List<Tuple> list = tuple.fetch();


    log.info(list);
    log.info("===================================================================");
    return null;
  }


  @Override
  public Page<BoardWithReplyCountDTO> searchPage(String type, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QMember member = QMember.member;
    QReply reply = QReply.reply;

    JPQLQuery<Board> jpqlQuery = from(board);

    // 조인
    jpqlQuery
            .leftJoin(member).on(board.writer.eq(member))
            .leftJoin(reply).on(reply.board.eq(board));

    JPQLQuery<Tuple> tuple = jpqlQuery.groupBy(board).select(board, member, reply.count()) ;
//    tuple::orderBy
    // 검색

    BooleanBuilder builder = new BooleanBuilder();

    builder.and(board.bno.gt(0));

    if (!(type == null || type.trim().length() == 0)) {
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      if (type.contains("t")) {
        conditionBuilder.or(board.title.contains(keyword));
      }
      if (type.contains("c")) {
        conditionBuilder.or(board.content.contains(keyword));
      }
      if (type.contains("w")) {
        conditionBuilder.or(member.name.contains(keyword));
      }
      builder.and(conditionBuilder);
    }
    tuple.where(builder);

    getOrder(Board.class, pageable.getSort()).forEach(tuple::orderBy);

    // 페이지 적용
    tuple.limit(10).offset(pageable.getOffset());

    // DTO Projection 변환
    JPQLQuery<BoardWithReplyCountDTO> query = tuple.select(Projections.constructor(BoardWithReplyCountDTO.class, board, member, reply.count()));

    // page 타입 반환
    return new PageImpl<>(query.fetch(), pageable, tuple.fetchCount());
  }


}
