package com.gitbaby.board.projection.dto;

import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Member;


public record BoardWithReplyCountDTO(Board board, Member member, Long count) {
}
