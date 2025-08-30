package io.github.andrehsvictor.card.service.card;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.andrehsvictor.card.service.shared.dto.card.PostCardDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards")
    public Page<Card> findAll(Pageable pageable) {
        return cardService.findAll(pageable);
    }

    @GetMapping("/decks/{deckId}/cards")
    public Page<Card> findAllByDeckId(@PathVariable UUID deckId, Pageable pageable) {
        return cardService.findAllByDeckId(deckId, pageable);
    }

    @GetMapping("/cards/{id}")
    public Card findById(@PathVariable UUID id) {
        return cardService.findById(id);
    }

    @PostMapping("/decks/{deckId}/cards")
    public ResponseEntity<Card> create(@PathVariable UUID deckId, @RequestBody PostCardDto postCardDto) {
        Card card = cardService.create(deckId, postCardDto);
        return ResponseEntity.created(URI.create("/cards/" + card.getId())).body(card);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
