package com.crud.tasks.coverage;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrelloValidatorTestSuite {
    @Autowired
    TrelloValidator trelloValidator;
    @Test
    void shouldFilterTrelloBoardsTest() {
        //Given
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(new TrelloBoard("1", "test", new ArrayList<>()));
        boardList.add(new TrelloBoard("2", "something", new ArrayList<>()));
        //When
        List<TrelloBoard> filteredList = trelloValidator.validateTrelloBoards(boardList);
        //Then
        Assertions.assertEquals(1, filteredList.size());

    }
}
