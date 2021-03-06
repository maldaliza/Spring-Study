package com.maldaliza.freeboard.controller;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.dto.BoardDto;
import com.maldaliza.freeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록 보기
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        log.info("home");
        List<BoardDto> boardDtoList = boardService.findBoards();
        model.addAttribute("postList", boardDtoList);
        return "board/list";
    }

    /**
     * 글 쓰기 폼 (GET)
     * @return
     */
    @GetMapping("/post")
    public String createPostForm() {
        log.info("create post form");
        return "board/createPostForm";
    }

    /**
     * 글 쓰기 처리 (POST)
     * @param boardDto
     * @return
     */
    @PostMapping("/post")
    public String createPostProcess(@ModelAttribute BoardDto boardDto) {
        Long boardId = boardService.savePost(boardDto);
        return "redirect:/detail/" + boardId;
    }

    /**
     * 글 상세 보기
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        log.info("detail");
        BoardDto boardDto = boardService.findOne(id);
        model.addAttribute("board", boardDto);
        return "board/detail";
    }

    /**
     * 글 수정 폼 (GET)
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updatePostForm(@PathVariable("id") Long id, Model model) {

        log.info("update post form");

        // 1. PathVariable에 해당하는 값을 찾는다.
        BoardDto findBoardDto = boardService.findOne(id);

        // 2. 새로운 Board 객체 생성 및 값 세팅.
        Board board = new Board(findBoardDto.getId(), findBoardDto.getTitle(), findBoardDto.getAuthor(), findBoardDto.getContent());

        model.addAttribute("board", board);
        return "board/updatePostForm";
    }

    /**
     * 글 수정 처리 (POST)
     * @param boardDto
     * @return
     */
    @PostMapping("/update/{id}")
    public String updatePostProcess(@ModelAttribute BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/detail/" + boardDto.getId();
    }

    /**
     * 글 삭제
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public String deletePostProcess(@PathVariable("id") Long id) {
        log.info("delete post process");
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
