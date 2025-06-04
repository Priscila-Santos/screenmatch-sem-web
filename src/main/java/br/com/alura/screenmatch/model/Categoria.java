package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    Aventura("Adventure"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    FANTASIA("Fantasy"),
    ROMANCE("Romance");

    private final String categoriaOmdb;

    Categoria (String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
