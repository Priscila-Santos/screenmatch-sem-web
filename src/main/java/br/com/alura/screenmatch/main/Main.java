package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner leitura  = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c3cc4537";
    List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu =
                    """
                            ******** Welcome to ScreenMatch! ********
                            1 - Buscar séries
                            2 - Buscar episódios
                            3 - Listar séries buscadas
                            
                            0 - Sair
                            """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        }
    }

    private void buscarSerieWeb () {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da serie: ");
        String nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie () {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i < dadosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {
        dadosSeries.forEach(System.out::println);
    }



//        System.out.println("Digite o nome da Serie: ");
//    var nomeSerie = leitura.nextLine();
//
//    var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
//
//    DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
//        System.out.println(dadosSerie);
//
//    List<DadosTemporada> temporadas = new ArrayList<>();
//
//
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//        json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
//        DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//        temporadas.add(dadosTemporada);
//
//    }
//        temporadas.forEach(System.out::println);
//
////        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
////            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodio();
////            for (int j = 0; j < episodiosTemporada.size(); j++) {
////                System.out.println(episodiosTemporada.get(j).titulo());
////            }
//
//        temporadas.forEach(t -> t.episodio().forEach(e -> System.out.println(e.titulo())));
//
//    List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//            .flatMap(t -> t.episodio().stream())
//            .collect(Collectors.toList());
//
////        System.out.println("Top 10 melhores episodios!\n");
////        dadosEpisodios.stream()
////                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
////                .peek(e -> System.out.println("Primeiro filtro N/A " + e))
////                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
////                .peek(e -> System.out.println("Ordenação " + e))
////                .limit(10)
////                .peek(e -> System.out.println("Limite " + e))
////                .map(e -> e.titulo().toUpperCase())
////                .peek(e -> System.out.println("Mapeamento " + e))
////                .forEach(System.out::println);
//
//    List<Episodio> episodios = temporadas.stream()
//            .flatMap(t -> t.episodio().stream()
//                    .map(d -> new Episodio(t.numero(), d))
//            ).collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);
//
////        System.out.println("Digite o título do episodio: ");
////        var tituloEpisodio = leitura.nextLine();
////        Optional<Episodio> episodioBuscado = episodios.stream()
////                .filter(e -> e.getTitulo().toUpperCase().contains(tituloEpisodio.toUpperCase()))
////                .findFirst();
////        if (episodioBuscado.isPresent()) {
////            System.out.println("Episódio Encontado com Sucesso!");
////            System.out.println("Temporada: "+ episodioBuscado.get().getTemporada());
////            System.out.println("Episódio: " + episodioBuscado.get().getNumeroEpisodio());
////        } else {
////            System.out.println("Episódio Encontado :(");
////        }
//
////        System.out.println("Digite o ano em que deseja ver os episodios: ");
////        var ano = leitura.nextInt();
////        leitura.nextLine();
////
////        LocalDate dataBusca = LocalDate.of(ano,1,1);
////
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        episodios.stream()
////                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
////                .forEach(e -> System.out.println(
////                        "Temporada: " + e.getTemporada() +
////                        "Episodio: " + e.getTitulo() +
////                                "Data de Lançamento: " + e.getDataLancamento().format(formatter)
////
////                ));
//
//    Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//            .filter(e -> e.getAvaliacao() > 0)
//            .collect(Collectors.groupingBy(Episodio::getTemporada,
//                    Collectors.averagingDouble(Episodio::getAvaliacao)));
//
//        System.out.println(avaliacoesPorTemporada);
//
//    DoubleSummaryStatistics statistic = episodios.stream()
//            .filter(e -> e.getAvaliacao() > 0)
//            .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
//        System.out.println("Média das avaliações: " + statistic.getAverage());
//        System.out.println("Melhor episódio: " + statistic.getMax());
//        System.out.println("Pior episódio: " + statistic.getMin());
//        System.out.println("Episódios Avaliados: " + statistic.getCount());

}


