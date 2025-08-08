package br.com.projeto.screematch.model;

import br.com.projeto.screematch.principal.Episodio;
import br.com.projeto.screematch.service.ConsultaGemini;
import br.com.projeto.screematch.service.Formatar;
import jakarta.persistence.*;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "nomeDaSerie")
    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;

    @Column(length = 500)
    private String sinopse;

    @Enumerated(EnumType.STRING)
    private Categoria genero;


    private String ano;

    private String atores;

    private Double avaliacao;

    private String diretores;

    @Column(length = 500)
    private String poster;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();


    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.sinopse = ConsultaGemini.obterTraducao(dadosSerie.sinopse().trim());
        this.atores = dadosSerie.atores();
        this.diretores = dadosSerie.diretores();
        this.ano = Formatar.formatar(dadosSerie.ano());
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.poster = dadosSerie.poster();
    }

    public Serie() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e-> e.setSerie(this));
        this.episodios = episodios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Gênero(s): " + genero +
                "\nSérie: " + titulo +
                " (" + totalTemporadas + " temporadas)" +
                " -  atores: " + atores +
                " - Diretores: " + diretores +
                " - sinopse: " + sinopse +
                "no: " + ano +
                " - avaliação: " + avaliacao +
                "\nPoster: " + poster +
                "\nEpisodios: " + episodios;
    }
}
