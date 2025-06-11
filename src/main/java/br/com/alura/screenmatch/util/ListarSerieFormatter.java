package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Serie;

import java.util.List;

public class ListarSerieFormatter {
    public static void imprimirListaDeSeries(Serie serie) {
        System.out.println("\n🎭  GÊNERO: " + serie.getGenero());
        System.out.println("🎬  Título: " + serie.getTitulo());
        //System.out.println("📅  Ano: " + serie.get(0).getAno());
        System.out.println("⭐  Avaliação: " + serie.getAvaliacao());
        //System.out.println("👥  Atores: " + serie.get(0).getAtoresDaSerie());
        System.out.println("📝  Sinopse: " + serie.getSinopse());
        //System.out.println("🖼️  Poster: " + serie.poster());
        System.out.println("📚  Temporadas: " + serie.getTotalTemporadas());
        System.out.println("\uD83C\uDFA5  Episódios: " + serie.getEpisodios());
        System.out.println("***************************************************\n");
    }
}
