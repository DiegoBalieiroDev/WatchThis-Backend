package br.com.projeto.screematch.principal;

import br.com.projeto.screematch.model.DadosEpisodioTemporada;
import br.com.projeto.screematch.model.Serie;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;

    private Integer numeroEpisodio;

    private String tituloEpisodio;

    private double avaliacao;

    private LocalDate anoLancamento;


    @ManyToOne
    private Serie serie;

    public Episodio(Integer numeroTemporada, DadosEpisodioTemporada dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.numeroEpisodio = dadosEpisodio.numeroEpisodio();
        this.tituloEpisodio = dadosEpisodio.tituloEpisodio();
            try {
                this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
            } catch (NumberFormatException ex) {
                this.avaliacao = 0.0;
            }
            try {
                this.anoLancamento = LocalDate.parse(dadosEpisodio.anoLancamento());
            } catch (DateTimeParseException ex) {
                this.anoLancamento = null;
            }
    }

    public Episodio() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getTituloEpisodio() {
        return tituloEpisodio;
    }

    public void setTituloEpisodio(String tituloEpisodio) {
        this.tituloEpisodio = tituloEpisodio;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "(Temporada " + temporada + ") - Episódio: " + numeroEpisodio + " - Titulo episódio: " + tituloEpisodio + " - Avaliação: " + avaliacao + " - Lançamento: " + anoLancamento;
    }
}
