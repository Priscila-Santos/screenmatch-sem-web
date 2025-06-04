package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.DadosSerie;

public class SerieFormatter {
    public static void exibir(DadosSerie serie) {
        System.out.println("\n***** 🎬 Série *****");
        System.out.println("Título: " + serie.titulo());
        System.out.println("Ano: " + serie.ano());
        System.out.println("Avaliação: " + serie.avaliacao());
        System.out.println("Gênero: " + serie.genero());
        System.out.println("Atores: " + serie.atoresDaSerie());
        System.out.println("Sinopse: " + serie.sinopse());
        System.out.println("Poster: " + serie.poster());
        System.out.println("Temporadas: " + serie.totalTemporadas());
        System.out.println("====================\n");
    }
}

