package com.gitbaby.board.domain.projection;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Reply;

public record BoardWithReplyDTO(Board board, Reply reply) {
}
