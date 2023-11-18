package ecommerce.storeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_ikonkaProduct")
public class IkonkaProduct {

    @Id
    private String kod_kreskowy;
    private String nazwa;
    private String link_do_zdjec;
    private String zdp;
    private String wysokosc;
    private String objectosc;
    @Column(length = 2048)
    @ElementCollection
    private List<String> zdjecia;
    private String waga;
    private String czas_dostawy;
    private String najblizsza_dostawa;
    private String sztuk_w_kartonie;
    private String sugerowana_cena_detaliczna;
    @Column(length = 65536)
    private String opis_krotki;
    @Column(length = 65536)
    private String opis;
    private String kategoria;
    private String grupa_rabatowa;
    private String link_do_instrukcji;
    private String stan;
    private String vat;
    private String cena;
    private String kod;
    private String dlugosc;
}
