package io.github.andrehsvictor.deck.service.deck;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;

    @RequestMapping(method = RequestMethod.HEAD, value = "/decks/{id}")
    public ResponseEntity<Void> existsById(@PathVariable UUID id) {
        if (deckService.existsById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
