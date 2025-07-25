package com.gitbaby.board.projection.dto;

import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Reply;

public record BoardWithReplyDTO(Board board, Reply reply) {
}
