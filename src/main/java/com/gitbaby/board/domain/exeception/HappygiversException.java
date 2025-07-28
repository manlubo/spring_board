package com.gitbaby.board.domain.exeception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HappygiversException extends RuntimeException {
  public HappygiversException(String message) {
    super(message);
  }
}
