package br.com.projeto.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias ("totalSeasons") Integer totalTemporadas,
                         @JsonAlias ("Plot") String sinopse,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Writer") String diretores,
                         @JsonAlias ("Genre") String genero,
                         @JsonAlias ("Year") String ano,
                         @JsonAlias ("imdbRating") String avaliacao,
                         @JsonAlias ("Poster") String poster)
{
}
