package br.com.projeto.watchthis.repository;

import br.com.projeto.watchthis.model.Categoria;
import br.com.projeto.watchthis.model.Serie;
import br.com.projeto.watchthis.model.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<Serie> findByAvaliacaoGreaterThanEqual(Double avaliacaoSerie);

    List<Serie> findTop10ByOrderByAvaliacaoDesc();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :id ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> buscaTop5Episodios(Long id);

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findTop3ByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqualOrderByAvaliacaoDesc(Integer totalTemporadas, Double avaliacaoIsGreaterThan);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.tituloEpisodio ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.anoLancamento) >= :dataLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int dataLancamento);

    List<Serie> findTop5ByOrderByEpisodiosAnoLancamentoDesc();

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.anoLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();

    @Query(value = "SELECT * FROM episodios e WHERE e.serie_id = :idSerie ORDER BY e.avaliacao DESC LIMIT 5",
            nativeQuery = true)
    List<Episodio> topEpisodiosNativoPorSerie(@Param("idSerie") Long idSerie);

}
