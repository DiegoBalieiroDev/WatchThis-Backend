package br.com.projeto.watchthis.dto;

import java.time.LocalDate;

public record EpisodioDTO(Integer temporada,
                          Integer numeroEpisodio,
                          String tituloEpisodio,
                          double avaliacao,
                          LocalDate anoLancamento,
                          String sinopse) {

}
