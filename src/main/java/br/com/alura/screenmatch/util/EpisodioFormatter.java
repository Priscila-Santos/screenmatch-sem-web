package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.DadosEpisodio;

public class EpisodioFormatter {

    public static void exibirTemporada(DadosTemporada temporada) {
        System.out.println("\n==============================================");
        System.out.println("📅  Temporada: " + temporada.numero());
        System.out.println("==============================================");

        for (DadosEpisodio episodio : temporada.episodio()) {
            exibirEpisodio(episodio);
        }
    }

    private static void exibirEpisodio(DadosEpisodio episodio) {
        System.out.printf("""
                🎬 Episódio %d: %s
                   ⭐ Avaliação: %s
                ----------------------------------------------
                """, episodio.numero(), episodio.titulo(), episodio.avaliacao());
    }
}

