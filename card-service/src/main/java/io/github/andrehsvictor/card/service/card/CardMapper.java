package io.github.andrehsvictor.card.service.card;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import io.github.andrehsvictor.card.service.shared.dto.card.PostCardDto;
import io.github.andrehsvictor.card.service.shared.dto.card.PutCardDto;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card postCardDtoToCard(PostCardDto postCardDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Card updateCardFromPutCardDto(@MappingTarget Card card, PutCardDto putCardDto);

}
