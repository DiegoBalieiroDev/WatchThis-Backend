package br.com.projeto.watchthis.dto;

import br.com.projeto.watchthis.model.Categoria;

public record SerieDTO(
        Long id,
         String titulo,
         Integer totalTemporadas,
         String sinopse,
         Categoria genero,
         String ano,
         String atores,
         Double avaliacao,
         String diretores,
         String poster
) {
}
