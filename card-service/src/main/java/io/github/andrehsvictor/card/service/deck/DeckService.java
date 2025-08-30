package io.github.andrehsvictor.card.service.deck;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "deck-service")
public interface DeckService {

    @RequestMapping(method = RequestMethod.HEAD, value = "/decks/{id}")
    boolean existsById(@PathVariable UUID id);

}
