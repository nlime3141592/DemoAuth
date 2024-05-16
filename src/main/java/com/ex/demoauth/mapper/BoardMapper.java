package com.ex.demoauth.mapper;

import com.ex.demoauth.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper
{
    int Save(BoardDTO _dto);
    List<BoardDTO> FindAll();
    BoardDTO FindById(Long _id);
    void UpdateHits(Long _id);

    void Delete(Long _id);
    void Update(BoardDTO _dto);
    List<BoardDTO> GetDialogList(Map<String, Integer> _map);
    int GetMaxPageCount();
}
