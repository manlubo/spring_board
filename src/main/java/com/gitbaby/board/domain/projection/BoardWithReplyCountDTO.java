package com.gitbaby.board.domain.projection;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Member;


public record BoardWithReplyCountDTO(Board board, Member member, Long count) {
}
