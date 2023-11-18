package ecommerce.storeapi.service;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.IkonkaProduct;
import ecommerce.storeapi.model.Product;
import ecommerce.storeapi.repository.IkonkaProductRepository;
import ecommerce.storeapi.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IkonkaProductService {
    @Value("${ikonka.allProductsInfo.url}")
    private String allProductsIkonkaUrl;

    private final ProductRepository productRepository;
    private final IkonkaProductRepository ikonkaProductRepository;

    @Autowired
    private WebClient webClient;


    public GetIkonkaProductResponse getIkonkaProduct (String barcode) {
        Optional<IkonkaProduct> ikonkaProduct = ikonkaProductRepository.findById(barcode);
        return GetIkonkaProductResponse
                .builder()
                .ikonkaProduct(ikonkaProduct)
                .build();
    }

    public FetchAllIkonkaProductsResponse fetchAllIkonkaProducts () {
        String ikonkaProducts = webClient
                .get()
                .uri(allProductsIkonkaUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(ikonkaProducts);
        JSONArray jsonArr = new JSONArray(ikonkaProducts);
        System.out.println(jsonArr);

        for(int n = 0; n < jsonArr.length(); n++){
            JSONObject obj = jsonArr.getJSONObject(n);
            System.out.println(obj);
            Optional<IkonkaProduct> tempIkonkaProduct = ikonkaProductRepository.findById(!obj.isNull("kod_kreskowy") ? (String) obj.get("kod_kreskowy") : "");

            if(tempIkonkaProduct.isPresent()){
                tempIkonkaProduct.get().setCena((!obj.isNull("cena") ? (String) obj.get("cena") : ""));
                tempIkonkaProduct.get().setDlugosc(!obj.isNull("dlugosc") ? (String) obj.get("dlugosc") : "");
                tempIkonkaProduct.get().setCzas_dostawy(!obj.isNull("czas_dostawy") ? (String) obj.get("czas_dostawy") : "");
                tempIkonkaProduct.get().setGrupa_rabatowa(!obj.isNull("grupa_rabatowa") ? (String) obj.get("grupa_rabatowa") : "");
                tempIkonkaProduct.get().setKategoria(!obj.isNull("kategoria") ? (String) obj.get("kategoria") : "");
                tempIkonkaProduct.get().setNazwa(!obj.isNull("nazwa") ? (String) obj.get("nazwa") : "");
                tempIkonkaProduct.get().setLink_do_zdjec(!obj.isNull("link_do_zdjec") ? (String) obj.get("link_do_zdjec") : "");
                tempIkonkaProduct.get().setZdp(!obj.isNull("zdp") ? (String) obj.get("zdp") : "");
                tempIkonkaProduct.get().setWysokosc(!obj.isNull("wysokosc") ? (String) obj.get("wysokosc") : "");
                tempIkonkaProduct.get().setObjectosc(!obj.isNull("objectosc") ? (String) obj.get("objectosc") : "");
                JSONArray arr = new JSONArray(!obj.isNull("zdjecia") ? obj.get("zdjecia").toString() : "");
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < arr.length(); i++){
                    list.add(arr.getJSONObject(i).getString("value"));
                }
                tempIkonkaProduct.get().setZdjecia(list);
                tempIkonkaProduct.get().setWaga(!obj.isNull("waga") ? (String) obj.get("waga") : "");
                tempIkonkaProduct.get().setNajblizsza_dostawa(!obj.isNull("najblizsza_dostawa") ? (String) obj.get("najblizsza_dostawa") : "");
                tempIkonkaProduct.get().setSztuk_w_kartonie(!obj.isNull("sztuk_w_kartonie") ? (String) obj.get("sztuk_w_kartonie") : "");
                tempIkonkaProduct.get().setSugerowana_cena_detaliczna(!obj.isNull("sugerowana_cena_detaliczna") ? (String) obj.get("sugerowana_cena_detaliczna") : "");
                tempIkonkaProduct.get().setOpis_krotki(!obj.isNull("opis_krotki") ? (String) obj.get("opis_krotki") : "");
                tempIkonkaProduct.get().setOpis(!obj.isNull("opis") ? (String) obj.get("opis") : "");
                tempIkonkaProduct.get().setKategoria(!obj.isNull("kategoria") ? (String) obj.get("kategoria") : "");
                tempIkonkaProduct.get().setLink_do_instrukcji(!obj.isNull("link_do_instrukcji") ? (String) obj.get("link_do_instrukcji") : "");
                tempIkonkaProduct.get().setStan(!obj.isNull("stan") ? (String) obj.get("stan") : "");
                tempIkonkaProduct.get().setVat(!obj.isNull("vat") ? (String) obj.get("vat") : "");
                tempIkonkaProduct.get().setKod(!obj.isNull("kod") ? (String) obj.get("kod") : "");

            } else {
                JSONArray arr = new JSONArray(!obj.isNull("zdjecia") ? obj.get("zdjecia").toString() : "");
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < arr.length(); i++){
                    list.add(arr.getJSONObject(i).getString("value"));
                }
                IkonkaProduct ikonkaProduct = IkonkaProduct.builder()
                        .kod_kreskowy(!obj.isNull("kod_kreskowy") ? (String) obj.get("kod_kreskowy") : "")
                        .cena(!obj.isNull("cena") ? (String) obj.get("cena") : "")
                        .dlugosc(!obj.isNull("dlugosc") ? (String) obj.get("dlugosc") : "")
                        .czas_dostawy(!obj.isNull("czas_dostawy") ? (String) obj.get("czas_dostawy") : "")
                        .grupa_rabatowa(!obj.isNull("grupa_rabatowa") ? (String) obj.get("grupa_rabatowa") : "")
                        .kategoria(!obj.isNull("kategoria") ? (String) obj.get("kategoria") : "")
                        .nazwa(!obj.isNull("nazwa") ? (String) obj.get("nazwa") : "")
                        .link_do_zdjec(!obj.isNull("link_do_zdjec") ? (String) obj.get("link_do_zdjec") : "")
                        .zdp(!obj.isNull("zdp") ? (String) obj.get("zdp") : "")
                        .wysokosc(!obj.isNull("wysokosc") ? (String) obj.get("wysokosc") : "")
                        .objectosc(!obj.isNull("objectosc") ? (String) obj.get("objectosc") : "")
                        .zdjecia(list)
                        .waga(!obj.isNull("waga") ? (String) obj.get("waga") : "")
                        .najblizsza_dostawa(!obj.isNull("najblizsza_dostawa") ? (String) obj.get("najblizsza_dostawa") : "")
                        .sztuk_w_kartonie(!obj.isNull("sztuk_w_kartonie") ? (String) obj.get("sztuk_w_kartonie") : "")
                        .sugerowana_cena_detaliczna(!obj.isNull("sugerowana_cena_detaliczna") ? (String) obj.get("sugerowana_cena_detaliczna") : "")
                        .opis_krotki(!obj.isNull("opis_krotki") ? (String) obj.get("opis_krotki") : "")
                        .opis(!obj.isNull("opis") ? (String) obj.get("opis") : "")
                        .kategoria(!obj.isNull("kategoria") ? (String) obj.get("kategoria") : "")
                        .link_do_instrukcji(!obj.isNull("link_do_instrukcji") ? (String) obj.get("link_do_instrukcji") : "")
                        .stan(!obj.isNull("stan") ? (String) obj.get("stan") : "")
                        .vat(!obj.isNull("vat") ? (String) obj.get("vat") : "")
                        .kod(!obj.isNull("kod") ? (String) obj.get("kod") : "")
                        .build();

                ikonkaProductRepository.save(ikonkaProduct);
            }

        };

        return FetchAllIkonkaProductsResponse
                .builder()
                .allIkonkaProductsFetched(true)
                .build();
    }

    public TransferAllIkonkaProductsToProductsResponse transferAllIkonkaProductsToProducts() {

        Iterable<IkonkaProduct> ikonkaProducts = ikonkaProductRepository.findAll();

        System.out.println(ikonkaProducts);

        ikonkaProducts.forEach(ikonkaProduct -> {
            Optional<Product> product = productRepository.findById(ikonkaProduct.getKod_kreskowy());

            System.out.println(product);

            if(product.isPresent()) {
                product.get().setPrice(ikonkaProduct.getCena());
                product.get().setLength(ikonkaProduct.getDlugosc());
                product.get().setDeliveryTime(ikonkaProduct.getCzas_dostawy());
                product.get().setDiscountGroup(ikonkaProduct.getGrupa_rabatowa());
                product.get().setCategory(ikonkaProduct.getKategoria());
                product.get().setName(ikonkaProduct.getNazwa());
                product.get().setLinkToPictures(ikonkaProduct.getLink_do_zdjec());
                product.get().setTooBigForAParcelLocker(ikonkaProduct.getZdp());
                product.get().setHeight(ikonkaProduct.getWysokosc());
                product.get().setVolume(ikonkaProduct.getObjectosc());
                product.get().setPictures(ikonkaProduct.getZdjecia());
                product.get().setWeight(ikonkaProduct.getWaga());
                product.get().setNextDelivery(ikonkaProduct.getNajblizsza_dostawa());
                product.get().setAmountInBox(ikonkaProduct.getSztuk_w_kartonie());
                product.get().setSuggestedRetailPrice(ikonkaProduct.getSugerowana_cena_detaliczna());
                product.get().setShortDescription(ikonkaProduct.getOpis_krotki());
                product.get().setDescription(ikonkaProduct.getOpis());
                product.get().setCategory(ikonkaProduct.getKategoria());
                product.get().setLinkToInstruction(ikonkaProduct.getLink_do_instrukcji());
                product.get().setState(ikonkaProduct.getStan());
                product.get().setVat(ikonkaProduct.getVat());
                product.get().setCode(ikonkaProduct.getKod());
            } else {
                Product newProduct = Product.builder()
                        .id(ikonkaProduct.getKod_kreskowy())
                        .name(ikonkaProduct.getNazwa())
                .linkToPictures(ikonkaProduct.getLink_do_zdjec())
                .tooBigForAParcelLocker(ikonkaProduct.getZdp())
                .height(ikonkaProduct.getWysokosc())
                .volume(ikonkaProduct.getObjectosc())
                .pictures(ikonkaProduct.getZdjecia())
                .weight(ikonkaProduct.getWaga())
                .deliveryTime(ikonkaProduct.getCzas_dostawy())
                .nextDelivery(ikonkaProduct.getNajblizsza_dostawa())
                .amountInBox(ikonkaProduct.getSztuk_w_kartonie())
                .suggestedRetailPrice(ikonkaProduct.getSugerowana_cena_detaliczna())
                .shortDescription(ikonkaProduct.getOpis_krotki())
                .description(ikonkaProduct.getOpis())
                .category(ikonkaProduct.getKategoria())
                .discountGroup(ikonkaProduct.getGrupa_rabatowa())
                .linkToInstruction(ikonkaProduct.getLink_do_instrukcji())
                .state(ikonkaProduct.getStan())
                .vat(ikonkaProduct.getVat())
                .providerPrice(ikonkaProduct.getCena())
                .price(ikonkaProduct.getCena())
                .code(ikonkaProduct.getKod())
                .length(ikonkaProduct.getDlugosc())
                        .build();
                productRepository.save(newProduct);
            }
        });

        return TransferAllIkonkaProductsToProductsResponse
                .builder()
                .allIkonkaIkonsTransferedToProducts(true)
                .build();
    }
}
