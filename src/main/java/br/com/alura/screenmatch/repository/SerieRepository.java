package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresDaSerieContainingIgnoreCase(String nomeAtor);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    //List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    @Query("select s from Serie s WHERE s.totalTemporadas >= :temporadas and s.avaliacao > :avaliacao")
    List<Serie> seriePorTemporadaEAvaliacao(int temporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> epsosiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s join s.episodios e WHERE s = :serie and YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> buscarEpisodioPorAno(Serie serie, int anoLancamento);

    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc();

//    @Query("SELECT s FROM Serie s JOIN s.episodios e GROUP BY s ORDER BY MAX(e.dataLancamento) DESC LIMIT 5" )
//    List<Serie> lancamentosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodioPorTemporada(Long id, Long numero);
}
