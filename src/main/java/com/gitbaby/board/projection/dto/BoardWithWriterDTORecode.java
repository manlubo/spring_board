package com.gitbaby.board.projection.dto;

import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Member;

public record BoardWithWriterDTORecode(Board board, Member member) {
}
