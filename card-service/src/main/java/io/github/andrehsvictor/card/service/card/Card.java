package io.github.andrehsvictor.card.service.card;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cards")
@EqualsAndHashCode(of = { "id" })
public class Card implements Serializable {

    private static final long serialVersionUID = 7999453212838792502L;

    @Id
    private UUID id = UUID.randomUUID();

    @Indexed
    private UUID deckId;

    private String question;
    private String answer;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

}
