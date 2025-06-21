package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    private List<SerieDTO> converteDados(List<Serie> serieList) {
        return serieList
                .stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getAtoresDaSerie(),
                        s.getPoster(),
                        s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterTodasAsSeries() {
        return converteDados(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLacamentos() {
        return converteDados(serieRepository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s  = serie.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtoresDaSerie(),
                    s.getPoster(),
                    s.getSinopse()
            );
        }
        return null;
    }

    public List<EpisodioDto> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s  = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDto(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());

        }
        return null;

    }

    public List<EpisodioDto> obterTemporadasPorNumero(Long id, Long numero) {
        return serieRepository.obterEpisodioPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }
}
