package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.util.EpisodioFormatter;
import br.com.alura.screenmatch.util.ListarEpidioFormatter;
import br.com.alura.screenmatch.util.ListarSerieFormatter;
import br.com.alura.screenmatch.util.SerieFormatter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Scanner leitura  = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c3cc4537";
    List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> serieBuscada;


    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu =
                    """
                            ******** Welcome to ScreenMatch! ********
                            1 - Buscar séries
                            2 - Buscar episódios
                            3 - Listar séries buscadas
                            4 - Buscar serie por Titulo
                            5 - Buscar series por Ator
                            6 - Top 5 Melhores Series
                            7 - Buscar series por categoria
                            8 - Filtrar series por temporadas e avaliações
                            9 - Buscar por trecho do episodio
                            10 - Top 5 Melhores Episodios de uma Series
                            11 - Buscar episódios a partir de uma data
                            
                            0 - Sair
                            """;

            System.out.println(menu);
            System.out.println("Escolha a opção desejada: ");
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
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    filtrarSeriesPorTemporadaEAvaliacao();;
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                case 10:
                    topEpisodiosPorSerie();
                case 11:
                    buscarEpisodiosPorAno();
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
        Serie serie = new Serie(dados);
        serieRepository.save(serie);
        SerieFormatter.exibir(dados);
        //System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da serie: ");
        String nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie () {
        //DadosSerie dadosSerie = getDadosSerie();
        listarSeriesBuscadas();
        System.out.println("Digite o nome da serie: ");
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serie = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);
//                series.stream()
//                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
//                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoAPI.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodio().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            serieRepository.save(serieEncontrada);

            for (DadosTemporada temporada : temporadas) {
                EpisodioFormatter.exibirTemporada(temporada);
            }
        } else {
            System.out.println("Serie não encontrada");
        }
    }


    private void listarSeriesBuscadas() {
        series = serieRepository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero));

        if (series.isEmpty()) {
            System.out.println("Nenhuma série foi buscada");
        } else {
            for (Serie serie : series) {
                ListarSerieFormatter.imprimirListaDeSeries(serie);
            }
        }
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Eslha uma serie pelo nome: ");
        String nomeSerie = leitura.nextLine();
        serieBuscada = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Serie encontrado: " + serieBuscada.get());
        } else {
            System.out.println("Serie não encontrado :(");
        }
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Digite o nome de um ator ou atriz para encontrar as series que ele(a) atuou: ");
        var nomeAtor = leitura.nextLine();
        List<Serie> seriesEncontradas = serieRepository.findByAtoresDaSerieContainingIgnoreCase(nomeAtor);

        for (Serie serie : seriesEncontradas) {
            ListarSerieFormatter.imprimirListaDeSeries(serie);
        }
    }


    private void buscarTop5Series() {
        List<Serie> seriesTop = serieRepository.findTop5ByOrderByAvaliacaoDesc();

        for (Serie serie : seriesTop) {
            ListarSerieFormatter.imprimirListaDeSeries(serie);
        }
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("digite o gênero da serie: ");
        var genero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(genero);
        List<Serie> generoDaSerie = serieRepository.findByGenero(categoria);

        for (Serie serie : generoDaSerie) {
            ListarSerieFormatter.imprimirListaDeSeries(serie);
        }

    }

    private void filtrarSeriesPorTemporadaEAvaliacao() {
        System.out.println("Filtrar series até qauntas temporadas? ");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Quão bem avaliadas devem ser as series");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = serieRepository.seriePorTemporadaEAvaliacao(totalTemporadas, avaliacao);

        for (Serie serie : filtroSeries) {
            ListarSerieFormatter.imprimirListaDeSeries(serie);
        }
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o nome do trecho para buscar episodio: ");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = serieRepository.epsosiosPorTrecho(trechoEpisodio);

        //episodiosEncontrados.forEach(System.out::println);
        for (Episodio episodio : episodiosEncontrados) {
            ListarEpidioFormatter.imprimirEpisodio(episodio);
        }
    }

    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();
        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();
            List<Episodio>  listarTopEpisodios = serieRepository.topEpisodiosPorSerie(serie);

            for (Episodio episodio : listarTopEpisodios) {
                ListarEpidioFormatter.imprimirEpisodio(episodio);
            }
        }
    }


    private void buscarEpisodiosPorAno() {
        buscarSeriePorTitulo();
        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();
            System.out.println("Digite o ano limite de lançamento");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();
            List<Episodio> episodiosPorAno = serieRepository.buscarEpisodioPorAno(serie, anoLancamento);

            for (Episodio episodio : episodiosPorAno) {
                ListarEpidioFormatter.imprimirEpisodio(episodio);
            }
        }
    }


}


