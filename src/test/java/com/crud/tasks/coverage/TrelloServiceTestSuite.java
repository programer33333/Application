package com.crud.tasks.coverage;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    AdminConfig adminConfig;
    @Mock
    SimpleEmailService emailService;

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("test", "1", new ArrayList<>()));
        boardDtos.add(new TrelloBoardDto("test2", "2", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtos);
        //When
        List<TrelloBoardDto> fetchedList = trelloService.fetchTrelloBoards();
        //Then
        Assertions.assertEquals(2, fetchedList.size());

    }

    @Test
    void shouldCreateTrelloCardTest() {
        //Given
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("1", "test", "test.com");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "testing", "center","11");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@mail");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        Assertions.assertEquals("1", createdCard.getId());
        Assertions.assertEquals("test", createdCard.getName());
        Assertions.assertEquals("test.com", createdCard.getShortUrl());
    }
}
