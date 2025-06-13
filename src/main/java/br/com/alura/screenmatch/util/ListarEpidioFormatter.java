package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.Episodio;

public class ListarEpidioFormatter {
    public static void imprimirEpisodio (Episodio episodio) {
        System.out.println("🎬  Título: " + episodio.getTitulo());
        System.out.println("📚  Temporadas: " + episodio.getTemporada());
        System.out.println("\uD83C\uDFA5 Episodio: " + episodio.getNumeroEpisodio());
        System.out.println("⭐  Avaliação: " + episodio.getAvaliacao());
        System.out.println("***************************************************\n");
    }
}
