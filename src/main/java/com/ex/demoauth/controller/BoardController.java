package com.ex.demoauth.controller;

import com.ex.demoauth.dto.BoardDTO;
import com.ex.demoauth.dto.PageDTO;
import com.ex.demoauth.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // NOTE: 의존성 주입을 위해 제공하는 어노테이션, // TODO: 알아볼 것.
@RequestMapping("/board") // NOTE: 해당 인자로 시작하는 모든 페이지를 받음.
public class BoardController
{
    // NOTE: 여기서 에러가 난다면 아래 링크 참조
    // https://www.inflearn.com/chats/841366/%EC%97%90%EB%9F%AC-%EA%B3%B5%EC%9C%A0-requiredargsconstructor-not-initialized-in-the-default-constructor
    private final BoardService boardService;

    @GetMapping("/")
    public String OnLoadMainForm(Model _model) // NOTE: DB에서 뭔가를 가져올 필요가 있으면 Model 객체를 포함.
    {
        List<BoardDTO> boardDTOList = boardService.FindAll();
        _model.addAttribute("boardList", boardDTOList);

        return "/board/list";
    }

    @GetMapping("/save")
    public String OnLoadSaveForm()
    {
        return "/board/save";
    }

    @PostMapping("/save")
    public String Save(@ModelAttribute BoardDTO _dto)
    {
        int saveResult = boardService.Save(_dto);

        if(saveResult > 0)
            return "redirect:/board/paging";
        else
            return "/board/save"; // NOTE: 요청 실패하면 페이지로 이동. (페이지 이름이 같으므로 같은 페이지에 머무르는 효과)
    }

    @GetMapping()
    public String FindById(@RequestParam("id") Long _id,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int _page,
                           Model _model)
    {
        boardService.UpdateHits(_id);
        BoardDTO boardDTO = boardService.FindById(_id);
        _model.addAttribute("board", boardDTO);
        _model.addAttribute("page", _page);

        return "/board/detail";
    }

    @GetMapping("/delete")
    public String Delete(@RequestParam("id") Long _id)
    {
        boardService.Delete(_id);
        return "redirect:/board/";
    }

    @GetMapping("/update")
    public String Update(@RequestParam("id") Long _id, Model _model)
    {
        BoardDTO dto = boardService.FindById(_id);
        _model.addAttribute("board", dto);
        return "/board/update";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute BoardDTO _dto, Model _model)
    {
        boardService.Update(_dto);
        BoardDTO dto = boardService.FindById(_dto.getId());
        _model.addAttribute("board", dto);

        return "/board/detail";
        // return "redirect:/board?=id" + _dto.getId(); // NOTE: 조회수를 올려버린다.
    }

    @GetMapping("/paging")
    // NOTE:
    // Default Parameter를 @RequestParam 어노테이션으로 설정하는 방법.
    // /board/paging?page=1 형태로 웹 브라우저에서 요청이 들어옴.
    // /board/paging 형태로 요청이 들어오더라도 알아서 ?page=1로 설정해줌.
    public String GetPage(Model _model, @RequestParam(value = "page", required = false, defaultValue = "1") int _page)
    {
        List<BoardDTO> dialogList = boardService.GetDialogList(_page);
        PageDTO pager = boardService.GetPager(_page);
        _model.addAttribute("dialogList", dialogList);
        _model.addAttribute("pager", pager);

        return "/board/paging"; // TEST: 임시로 index 페이지로 이동합니다.
    }
}