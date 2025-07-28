package com.gitbaby.board.controller;

import com.gitbaby.board.domain.dto.BoardDTO;
import com.gitbaby.board.domain.dto.PageRequestDTO;
import com.gitbaby.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
@Log4j2
@Data
public class BoardController {
  private final BoardService service;

  // 등록 폼
  @GetMapping("register")
  public void registerForm(){

  }

  // 등록 프로세스
  @PostMapping("register")
  public String register(BoardDTO dto, RedirectAttributes rttr){
    Long bno = service.register(dto);

    rttr.addFlashAttribute("msg", bno);
    return "redirect:list";
  }

  // 목록 조회
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model){
    model.addAttribute("dto", service.getList(dto));
  }

  @GetMapping("read")
  public void read(Long bno, Model model,@ModelAttribute("requestDto") PageRequestDTO pageDto){
    model.addAttribute("dto", service.get(bno));
  }

  @GetMapping("modify")
  public void modify(Long bno, Model model, @ModelAttribute("requestDto") PageRequestDTO pageDto){
    model.addAttribute("dto", service.get(bno));
  }

  @PostMapping("modify")
  public String modify(BoardDTO boardDto, PageRequestDTO dto, RedirectAttributes rttr){
    service.modify(boardDto);
    rttr.addAttribute("bno",  boardDto.getBno());
    rttr.addFlashAttribute("page", dto.getPage());
    rttr.addFlashAttribute("size", dto.getSize());
    rttr.addAttribute("type", dto.getType());
    rttr.addAttribute("keyword", dto.getKeyword());
    return "redirect:read";
  }

  @PostMapping("remove")
  public String remove(PageRequestDTO dto, Long bno, RedirectAttributes rttr){
    service.remove(bno);
    rttr.addFlashAttribute("msg", bno);
    rttr.addFlashAttribute("size", dto.getSize());
    rttr.addAttribute("page", 1);
    return "redirect:list";
  }
}
