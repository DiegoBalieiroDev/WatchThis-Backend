package br.com.projeto.watchthis.controller;

import br.com.projeto.watchthis.dto.EpisodioDTO;
import br.com.projeto.watchthis.dto.SerieDTO;
import br.com.projeto.watchthis.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servico;

    @GetMapping
    public List<SerieDTO> obterSerie(){
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top10")
    public List<SerieDTO> obterTop10Series(){
        return servico.obterTop10Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamento() {
        return servico.obterLancamento();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return servico.obterEpisodios(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obterTemporadaPorNumero(@PathVariable Long id, @PathVariable Integer numeroTemporada) {
        return servico.obterEpisodiosPorTemporada(id, numeroTemporada);
    }

    @GetMapping("categoria/{genero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String genero){
        return servico.obterSeriePorCategoria(genero);
    }


    @GetMapping("/{id}/temporadas/top5")
    public List<EpisodioDTO> obterTop5Serie(@PathVariable Long id){
        return servico.obterTop5Serie(id);
    }


}
