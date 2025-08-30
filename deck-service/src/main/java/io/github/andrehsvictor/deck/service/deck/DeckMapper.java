package io.github.andrehsvictor.deck.service.deck;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import io.github.andrehsvictor.deck.service.shared.dto.deck.PostDeckDto;
import io.github.andrehsvictor.deck.service.shared.dto.deck.PutDeckDto;

@Mapper(componentModel = "spring")
public interface DeckMapper {

    Deck postDeckDtoToDeck(PostDeckDto postDeckDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Deck updateDeckFromPutDeckDto(PutDeckDto putDeckDto, @MappingTarget Deck deck);

    @AfterMapping
    default void afterMapping(PutDeckDto putDeckDto, @MappingTarget Deck deck) {
        if (putDeckDto.getDescription() != null && putDeckDto.getDescription().isBlank()) {
            deck.setDescription(null);
        }
    }

}
