package br.com.projeto.watchthis.principal;

import br.com.projeto.watchthis.model.*;
import br.com.projeto.watchthis.repository.SerieRepository;
import br.com.projeto.watchthis.service.ConsumoAPI;
import br.com.projeto.watchthis.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = System.getenv("OMDB_APIKEY");

    private SerieRepository repositorio;

    private List<Serie> series = new ArrayList<>();


    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenuPrincipal() {
        int opcao;
        do {
            System.out.println("""
                    \n\n*** Escolha uma das opções abaixo ***
                    1 - Informações básicas série (BD)
                    2 - Informações de temporada e episódio (BD)
                    3 - Listar séries (BD)
                    0 - Sair
                    """);
            while (!scan.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scan.next();
            }
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    BuscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBD();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Essa opção não existe. Tente novamente");
            }
        } while (opcao != 0);
        System.out.println("Encerrando o sistema...");
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        //dadosSeries.add(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.print("Digite o nome da série para busca:");
        var nomeSerie = scan.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void BuscarEpisodioPorSerie() {
        listarSeriesBD();
        System.out.print("Escolha uma série pelo nome:");
        var nomeSerie = scan.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
        // o mesmo que embaixo:
//        Optional<Serie> serie = series.stream()
//                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
//                .findFirst();

        if (serie.isPresent()) {

            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
//            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.temporada(), e)))
                    .collect(Collectors.toList());
            System.out.println("\n\n");
            episodios.forEach(System.out::println);

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada");
        }


    }

    private void listarSeriesBD(){
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

}

