package br.com.projeto.watchthis.service;

import br.com.projeto.watchthis.dto.EpisodioDTO;
import br.com.projeto.watchthis.dto.SerieDTO;
import br.com.projeto.watchthis.model.Categoria;
import br.com.projeto.watchthis.model.Serie;
import br.com.projeto.watchthis.model.Episodio;
import br.com.projeto.watchthis.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {



    @Autowired
    private SerieRepository repository;

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getSinopse(),
                        s.getGenero(),
                        s.getAno(),
                        s.getAtores(),
                        s.getAvaliacao(),
                        s.getDiretores(),
                        s.getPoster()))
                .collect(Collectors.toList());

    }

    private List<EpisodioDTO> converteDadosEp(List<Episodio> episodios){
        return episodios.stream()
                .map(e -> new EpisodioDTO(
                        e.getTemporada(),
                        e.getNumeroEpisodio(),
                        e.getTituloEpisodio(),
                        e.getAvaliacao(),
                        e.getAnoLancamento(),
                        e.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTop10Series(){
        return converteDados(repository.findTop10ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamento(){
        return converteDados(repository.encontrarEpisodiosMaisRecentes());
    }


    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getSinopse(),
                    s.getGenero(),
                    s.getAno(),
                    s.getAtores(),
                    s.getAvaliacao(),
                    s.getDiretores(),
                    s.getPoster());
        }
        return null;
    }

//    @Transactional(readOnly = true)
    public List<EpisodioDTO> obterEpisodios(Long id){
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(
                            e.getTemporada(),
                            e.getNumeroEpisodio(),
                            e.getTituloEpisodio(),
                            e.getAvaliacao(),
                            e.getAnoLancamento(),
                            e.getSinopse()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }


    public List<EpisodioDTO> obterEpisodiosPorTemporada(Long id, Integer numeroTemporada) {
        List<EpisodioDTO> todosEpisodios = obterEpisodios(id);

        // Filtra a lista para retornar apenas os episódios da temporada solicitada
        return todosEpisodios.stream()
                .filter(e -> e.temporada().equals(numeroTemporada))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriePorCategoria(String genero) {
                Categoria categoria = Categoria.fromPortugues((genero));
                return converteDados(repository.findByGenero(categoria));


    }

    public List<EpisodioDTO> obterTop5Serie(Long id) {
        List<Episodio> topEpisodios = repository.topEpisodiosNativoPorSerie(id);
        return converteDadosEp(topEpisodios);
    }

}
