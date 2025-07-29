package com.gitbaby.board.controller;

import com.gitbaby.board.domain.dto.ReplyDTO;
import com.gitbaby.board.domain.entity.Reply;
import com.gitbaby.board.service.ReplyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController // Json 전용 컨트롤러로 변경
@RequestMapping("replies")
@RequiredArgsConstructor
public class ReplyController {
  final ReplyService replyService;

//  @RequestMapping(value = "board/{bno}", method = {RequestMethod.GET, RequestMethod.POST})
  @GetMapping("board/{bno}")
  public ResponseEntity<?> getList(@PathVariable("bno") Long bno) {
//    log.info(bno);
    return ResponseEntity.ok(replyService.getList(bno));
  }

  @PostMapping()
  public ResponseEntity<?> post(@RequestBody ReplyDTO dto) {

    return ResponseEntity.ok(replyService.register(dto));
  }

  @DeleteMapping("{rno}")
  public ResponseEntity<?> delete(@PathVariable("rno") Long rno) {
    replyService.delete(rno);
    return ResponseEntity.ok(rno);
  }

  @GetMapping("{rno}")
  public ResponseEntity<?> getReply(@PathVariable("rno") Long rno) {
    return ResponseEntity.ok(replyService.get(rno));
  }

  @PutMapping("{rno}")
  public ResponseEntity<?> Modify(@RequestBody ReplyDTO dto) {
    replyService.modify(dto);
    return ResponseEntity.ok(dto.getRno());
  }
}
