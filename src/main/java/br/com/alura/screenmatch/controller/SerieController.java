package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.service.SerieService;
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
    private SerieService serieService;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return serieService.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return serieService.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLacamentos() {
        return serieService.obterLacamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return serieService.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> obterTodasTemporadas(@PathVariable Long id) {
        return serieService.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDto> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero) {
        return serieService.obterTemporadasPorNumero(id, numero);
    }
}
