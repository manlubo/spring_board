package com.gitbaby.board.service;

import com.gitbaby.board.domain.dto.ReplyDTO;
import com.gitbaby.board.domain.entity.Reply;

import java.util.List;

public interface ReplyService {
  ReplyDTO toDTO(Reply reply);
  Reply toEntity(ReplyDTO replyDTO);
  Long register(ReplyDTO replyDTO);
  void modify(ReplyDTO replyDTO);
  void delete(Long rno);

  ReplyDTO get(Long rno);
  List<ReplyDTO> getList(Long bno);

}
