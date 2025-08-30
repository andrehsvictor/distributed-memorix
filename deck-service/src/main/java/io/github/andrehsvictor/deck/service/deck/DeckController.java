package io.github.andrehsvictor.deck.service.deck;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.andrehsvictor.deck.service.shared.dto.deck.PostDeckDto;
import io.github.andrehsvictor.deck.service.shared.dto.deck.PutDeckDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;

    @RequestMapping(method = RequestMethod.HEAD, value = "/decks/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        return ResponseEntity.ok(deckService.existsById(id));
    }

    @GetMapping("/decks")
    public ResponseEntity<Page<Deck>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            Pageable pageable) {
        Page<Deck> decks = deckService.findAllWithFilters(
                name,
                description,
                pageable);
        return ResponseEntity.ok(decks);
    }

    @GetMapping("/decks/{id}")
    public ResponseEntity<Deck> findById(@PathVariable UUID id) {
        Deck deck = deckService.findById(id);
        return ResponseEntity.ok(deck);
    }

    @PostMapping("/decks")
    public ResponseEntity<Deck> create(@RequestBody PostDeckDto postDeckDto) {
        Deck deck = deckService.create(postDeckDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(deck);
    }

    @PutMapping("/decks/{id}")
    public ResponseEntity<Deck> update(@PathVariable UUID id, @RequestBody PutDeckDto putDeckDto) {
        Deck deck = deckService.update(id, putDeckDto);
        return ResponseEntity.ok(deck);
    }

    @DeleteMapping("/decks/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deckService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
