package br.com.projeto.watchthis.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    TERROR("Horror", "Terror"),
    FANTASIA("Fantasy", "Fantasia"),
    FICCAO_CIENTIFICA("Science Fiction", "Ficção Científica"),
    DOCUMENTARIO("Documentary", "Documentário"),
    ANIMACAO("Animation", "Animação"),
    AVENTURA("Adventure", "Aventura"),
    MUSICAL("Musical", "Musical"),
    SUSPENSE("Thriller", "Suspense"),
    BIOGRAFIA("Biography", "Biografia"),
    HISTORICO("Historical", "Histórico"),
    GUERRA("War", "Guerra"),
    FAMILIA("Family", "Família"),
    MISTERIO("Mystery", "Mistério"),
    REALITY_SHOW("Reality Show", "Reality Show"),
    ESPORTIVO("Sport", "Esportivo");

    private String categoriaOmdb;

    private String categoriaPortugues;


    Categoria(String catergoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = catergoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
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
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
