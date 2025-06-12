package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    AVENTURA("Adventure", "Aventura"),
    ANIMATION("Animation", "Animação"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    FANTASIA("Fantasy", "Fantasia"),
    ROMANCE("Romance", "Romance"),;

    private final String categoriaOmdb;
    private final String categoriaEmPortugues;

    Categoria (String categoriaOmdb, String categoriaEmPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEmPortugues = categoriaEmPortugues;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEmPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
