package com.gitbaby.board.service;

import com.gitbaby.board.domain.dto.ReplyDTO;
import com.gitbaby.board.domain.entity.Reply;
import com.gitbaby.board.domain.exeception.HappygiversException;
import com.gitbaby.board.domain.mapper.ReplyMapper;
import com.gitbaby.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class ReplyServiceImpl implements ReplyService {
  private final ReplyMapper replyMapper;
  private final ReplyRepository replyRepository;

  @Override
  public ReplyDTO toDTO(Reply reply) {
    return replyMapper.toDTO(reply);
  }

  @Override
  public Reply toEntity(ReplyDTO replyDTO) {
    return replyMapper.toEntity(replyDTO);
  }


  @Override
  public ReplyDTO get(Long rno) {
    return replyMapper.toDTO(replyRepository.findById(rno).orElseThrow(() -> new HappygiversException("존재하지 않는 리뷰번호")));
  }

  @Override
  public List<ReplyDTO> getList(Long bno) {
    List<Reply> replies = replyRepository.findByBoard_bnoOrderByRnoDesc(bno);

    return replies.stream().map(replyMapper::toDTO).toList();
  }

  @Override
  public Long register(ReplyDTO replyDTO) {
    return replyRepository.save(toEntity(replyDTO)).getRno();
  }

  @Override
  @Transactional
  public void modify(ReplyDTO replyDTO) {
    replyRepository.save(toEntity(replyDTO));
  }


  @Override
  public void delete(Long rno) {
    replyRepository.deleteById(rno);
  }


}
