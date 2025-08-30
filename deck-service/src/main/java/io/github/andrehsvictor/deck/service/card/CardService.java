package io.github.andrehsvictor.deck.service.card;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "card-service", path = "/cards")
public interface CardService {

}
