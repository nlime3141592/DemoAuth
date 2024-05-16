package com.ex.demoauth.service;

import com.ex.demoauth.dto.BoardDTO;
import com.ex.demoauth.dto.PageDTO;
import com.ex.demoauth.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService
{
    private final BoardRepository boardRepository;

    public int Save(BoardDTO _dto)
    {
        return boardRepository.Save(_dto);
    }

    public List<BoardDTO> FindAll()
    {
        return boardRepository.FindAll();
    }

    public void UpdateHits(Long _id)
    {
        boardRepository.UpdateHits(_id);
    }

    public BoardDTO FindById(Long _id)
    {
        return boardRepository.FindById(_id);
    }

    public void Delete(Long _id)
    {
        boardRepository.Delete(_id);
    }

    public void Update(BoardDTO _dto)
    {
        boardRepository.Update(_dto);
    }

    public List<BoardDTO> GetDialogList(int _page)
    {
        int dialogPerPage = 3;
        int offset = (_page - 1) * dialogPerPage;
        Map<String, Integer> pagingParams = new HashMap<String, Integer>();
        pagingParams.put("offset", offset);
        pagingParams.put("dialogPerPage", dialogPerPage);

        return boardRepository.GetDialogList(pagingParams);
    }

    public PageDTO GetPager(int _page)
    {
        int pageLimit = 3;
        int blockLimit = 3;

        int boardCount = boardRepository.GetMaxPageCount();
        int maxPage = (int)(Math.ceil((double)boardCount / pageLimit));
        int startPage = (((int)(Math.ceil((double)_page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;

        if(endPage > maxPage)
            endPage = maxPage;

        PageDTO dto = new PageDTO();
        dto.setPage(_page);
        dto.setMaxPage(maxPage);
        dto.setStartPage(startPage);
        dto.setEndPage(endPage);

        return dto;
    }
}
