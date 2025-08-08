package br.com.projeto.screematch.Obsoleto;

import br.com.projeto.screematch.model.DadosEpisodio;
import br.com.projeto.screematch.model.DadosSerie;
import br.com.projeto.screematch.model.DadosTemporada;
import br.com.projeto.screematch.model.Serie;
import br.com.projeto.screematch.principal.Episodio;

import java.util.*;
import java.util.stream.Collectors;

public class Obsoleto {
//         Puxar estatísticas apuradas da serie
//    private void estatisticasSerie() {
//
//        DadosSerie dadosSerie = getDadosSerie();
//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
//            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//            temporadas.add(dadosTemporada);
//        }
//
//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(e -> new Episodio(t.temporada(), e)))
//                .collect(Collectors.toList());
//
//        System.out.println("Avaliação temporadas: ");
//        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//                .filter(e -> e.getAvaliacao() > 0.0)
//                .collect(Collectors.groupingBy(Episodio::getTemporada,
//                        Collectors.averagingDouble(Episodio::getAvaliacao)));
//        System.out.println(avaliacoesPorTemporada);
//
//        // fim "Imprime os dados da série com temporada + episódios detalhados"
//
//        DoubleSummaryStatistics est = episodios.stream()
//                .filter(e -> e.getAvaliacao() > 0.0)
//                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
//        System.out.println("Média: " + est.getAverage() +
//                "\nNota máxima: " + est.getMax() +
//                "\nNota mínima: " + est.getMin());
//    }


//          Apresenta mais dados dos episodios
//    public void exibeEpisodiosDetalhadosDaTemporada() {
//
//
//        List<DadosEpisodio> episodiosDetalhados = new ArrayList<>();
//        System.out.print("Serie:");
//        var nomeSerie = scan.nextLine();
//        System.out.print("Temporada:");
//        int temporada = scan.nextInt();
//
//        String jsonTemporada = consumo.obterDados("https://www.omdbapi.com/?t=" + nomeSerie +"&Season=" + temporada + "&apikey=44e7b972");
//        DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
//        int totalEpisodios = dadosTemporada.episodios().size();
//
//        for (int i = 1; i <= totalEpisodios; i++) {
//            //"https://www.omdbapi.com/?t=supernatural&Season="+ temporada +"&Episode=" + i + "&apikey=44e7b972"
//            String jsonEpisodio = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + temporada + "&episode="+ i + API_KEY);
//            DadosEpisodio episodioDetalhado = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);
//            episodiosDetalhados.add(episodioDetalhado);
//            episodiosDetalhados.forEach(System.out::println);
//        }
//    }


//          Lista series buscadas na sessão
//    private void listarSeriesBuscadas() {
//        List<Serie> series = new ArrayList<>();
//        series =  dadosSeries.stream()
//                .map(d-> new Serie(d))
//                .collect(Collectors.toList());
//
//        series.stream()
//                .sorted(Comparator.comparing(Serie::getGenero))
//                .forEach(System.out::println);
//    }


    //          Busca episodios da serie
    //    private void buscarEpisodioTemporada(){
//        DadosSerie dadosSerie = getDadosSerie();
//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
//            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//            temporadas.add(dadosTemporada);
//        }
//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(e -> new Episodio(t.temporada(), e)))
//                .collect(Collectors.toList());
//        System.out.println("\n\n");
//        episodios.forEach(System.out::println);
//    }

}
