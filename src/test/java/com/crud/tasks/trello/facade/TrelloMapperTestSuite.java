package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crud.tasks.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card name", "Card desc", "Center", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Card name", trelloCard.getName());
        assertEquals("Card desc", trelloCard.getDescription());
        assertEquals("Center", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card name", "Card desc", "Center", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Card name", trelloCardDto.getName());
        assertEquals("Card desc", trelloCardDto.getDescription());
        assertEquals("Center", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void mapToTrelloListTest() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1", "one", false));
        trelloListDtoList.add(new TrelloListDto("2", "two", false));
        trelloListDtoList.add(new TrelloListDto("3", "three", true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        int listSize = trelloLists.size();
        //Then
        assertEquals(3, listSize);
    }

    @Test
    void mapToTrelloListEmptyTest() {
        //Given
        List<TrelloListDto> emptyList = new ArrayList<>();
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(emptyList);
        //Then
        assertTrue(trelloLists.isEmpty());
    }

    @Test
    void mapToTrelloListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "one", false));
        trelloLists.add(new TrelloList("2", "two", false));
        trelloLists.add(new TrelloList("3", "three", true));
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        int listSize = trelloLists.size();
        //Then
        assertEquals(3, listSize);
    }

    @Test
    void mapToTrelloListDtoEmptyTest() {
        //Given
        List<TrelloList> emptyList = new ArrayList<>();
        //When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(emptyList);
        //Then
        assertTrue(trelloLists.isEmpty());
    }

    @Test
    void mapToTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("Board name", "1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("Another board name", "2", Arrays.asList(new TrelloListDto("1", "one", true), new TrelloListDto("2", "two", false))));
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        int listSize = trelloBoardList.size();
        //Then
        assertEquals(2, listSize);
    }

    @Test
    void mapToTrelloBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "Board name", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("2", "Another board name", Arrays.asList(new TrelloList("1", "one", true), new TrelloList("2", "two", false))));
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        int listSize = trelloBoardDtoList.size();
        //Then
        assertEquals(2, listSize);
    }

    @Test
    void mapToBoardEmptyTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        //When
        List<TrelloBoard> emptyList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void mapToBoardDtoEmptyTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        //When
        List<TrelloBoardDto> emptyList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertTrue(emptyList.isEmpty());
    }
}
