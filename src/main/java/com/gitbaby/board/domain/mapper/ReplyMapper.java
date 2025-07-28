package com.gitbaby.board.domain.mapper;

import com.gitbaby.board.domain.dto.ReplyDTO;
import com.gitbaby.board.domain.entity.Reply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
  @Mapping(source = "board.bno", target = "bno")
  ReplyDTO toDTO(Reply reply);

//  @Mapping(source = "bno", target = "board.bno")
  @InheritInverseConfiguration // 위의 설정을 반전
  Reply toEntity(ReplyDTO replyDTO);
}
