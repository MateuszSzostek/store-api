package ecommerce.storeapi.service;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Post;
import ecommerce.storeapi.model.Product;
import ecommerce.storeapi.repository.IkonkaProductRepository;
import ecommerce.storeapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${ikonka.allProductsInfo.url}")
    private String allProductsIkonkaUrl;

    private final ProductRepository productRepository;
    private final IkonkaProductRepository ikonkaProductRepository;

    @Autowired
    private WebClient webClient;

    public GetProductResponse getProduct (String id) {
        Optional<Product> ikonkaProductFromRequest = productRepository.findById(id);
        return GetProductResponse.builder().product(ikonkaProductFromRequest).build();
    }

     public AddProductResponse addProduct (AddProductRequest request) {
         Product ikonkaProductFromRequest = request.getProduct();
         productRepository.save(ikonkaProductFromRequest);
        return AddProductResponse.builder().productAdded(true).build();
    }

    public DeleteProductResponse deleteProduct (DeleteProductRequest request) {
        Product productToDelete = productRepository.findById(request.getId()).orElseThrow();
        productRepository.delete(productToDelete);
        return DeleteProductResponse.builder().productDeleted(true).build();
    }

  public UpdateProductResponse updateProduct (UpdateProductRequest request) {
        Product productFromRequest = request.getUpdatedProduct();
        Optional<Product> updatedProduct = productRepository.findById(productFromRequest.getId());

            updatedProduct.get().setPrice(productFromRequest.getPrice());
            updatedProduct.get().setLength(productFromRequest.getLength());
            updatedProduct.get().setDeliveryTime(productFromRequest.getDeliveryTime());
            updatedProduct.get().setDiscountGroup(productFromRequest.getDiscountGroup());
            updatedProduct.get().setCategory(productFromRequest.getCategory());
            updatedProduct.get().setName(productFromRequest.getName());
            updatedProduct.get().setLinkToPictures(productFromRequest.getLinkToPictures());
            updatedProduct.get().setTooBigForAParcelLocker(productFromRequest.getTooBigForAParcelLocker());
            updatedProduct.get().setHeight(productFromRequest.getHeight());
            updatedProduct.get().setVolume(productFromRequest.getVolume());
            updatedProduct.get().setPictures(productFromRequest.getPictures());
            updatedProduct.get().setWeight(productFromRequest.getWeight());
            updatedProduct.get().setNextDelivery(productFromRequest.getNextDelivery());
            updatedProduct.get().setAmountInBox(productFromRequest.getAmountInBox());
            updatedProduct.get().setSuggestedRetailPrice(productFromRequest.getSuggestedRetailPrice());
            updatedProduct.get().setShortDescription(productFromRequest.getShortDescription());
            updatedProduct.get().setDescription(productFromRequest.getDescription());
            updatedProduct.get().setCategory(productFromRequest.getCategory());
            updatedProduct.get().setLinkToInstruction(productFromRequest.getLinkToInstruction());
            updatedProduct.get().setState(productFromRequest.getState());
            updatedProduct.get().setVat(productFromRequest.getVat());
            updatedProduct.get().setCode(productFromRequest.getCode());

            return UpdateProductResponse.builder().productUpdated(true).build();

    }

    public SubstituteProductResponse substituteProduct (SubstituteProductRequest request) {
        Product firstProduct = productRepository.findById(request.getFirstProductId()).orElseThrow();
        Product secondProduct = productRepository.findById(request.getSecondProductId()).orElseThrow();
        firstProduct.getSubstituteProducts().add(secondProduct);
        return SubstituteProductResponse.builder().substituteAppliedToProducts(true).build();
    }

    public CancelSubstituteProductResponse cancelSubstituteProduct (CancelSubstituteProductRequest request) {
        Product firstProduct = productRepository.findById(request.getFirstProductId()).orElseThrow();
        Product secondProduct = productRepository.findById(request.getSecondProductId()).orElseThrow();
        firstProduct.getSubstituteProducts().add(secondProduct);
        return CancelSubstituteProductResponse.builder().productSubstituteCanceled(true).build();
    }

    public LinkProductResponse linkProductProduct (LinkProductRequest request) {
        Product firstProductToLink = productRepository.findById(request.getFirstProductId()).orElseThrow();
        Product secondProductToLink = productRepository.findById(request.getSecondProductId()).orElseThrow();

        firstProductToLink.getLinkedProducts().add(secondProductToLink);
        return LinkProductResponse.builder().productsLinked(true).build();
    }

    public UnlinkProductResponse unlinkProductProduct (UnlinkProductRequest request) {
        Product firstProductToLink = productRepository.findById(request.getFirstProductId()).orElseThrow();
        Product secondProductToLink = productRepository.findById(request.getSecondProductId()).orElseThrow();

        firstProductToLink.getLinkedProducts().add(secondProductToLink);
        return UnlinkProductResponse.builder().productsUnlinked(true).build();
    }

    /*
    public SaveIkonkaProductToProductsResponse saveIkonkaProductToProducts (SaveProductRequest request) {
        IkonkaProduct> ikonkaProduct = ikonkaProductRepository.findById(SaveProductRequest.);

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

    public UpdateProductResponse updateProduct (UpdateProductRequest request) {

    }

    public DeleteProductResponse deleteProduct (DeleteProductRequest request) {
        
    }

    public GetIkonkaProductResponse getIkonkaProduct () {

    }
    */


}
