package br.com.projeto.watchthis.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodioTemporada(
        @JsonAlias ("Episode") Integer numeroEpisodio,
        @JsonAlias ("Title") String tituloEpisodio,
        @JsonAlias ("Plot") String sinopse,
        @JsonAlias ("imdbRating") String avaliacao,
        @JsonAlias ("Released") String anoLancamento
) {
}
