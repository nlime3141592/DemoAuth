package com.ex.demoauth.repository;

import com.ex.demoauth.dto.BoardDTO;
import com.ex.demoauth.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class BoardRepository
{
    @Autowired
    private final BoardMapper boardMapper;

    public int Save(BoardDTO _dto)
    {
        return boardMapper.Save(_dto);
    }

    public List<BoardDTO> FindAll()
    {
        return boardMapper.FindAll();
    }

    public void UpdateHits(Long _id)
    {
        boardMapper.UpdateHits(_id);
    }

    public BoardDTO FindById(Long _id)
    {
        return boardMapper.FindById(_id);
    }

    public void Delete(Long _id)
    {
        boardMapper.Delete(_id);
    }

    public void Update(BoardDTO _dto)
    {
        boardMapper.Update(_dto);
    }

    public List<BoardDTO> GetDialogList(Map<String, Integer> _map)
    {
        return boardMapper.GetDialogList(_map);
    }

    public int GetMaxPageCount()
    {
        return boardMapper.GetMaxPageCount();
    }
}