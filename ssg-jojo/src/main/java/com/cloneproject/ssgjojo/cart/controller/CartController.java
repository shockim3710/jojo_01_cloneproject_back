package com.cloneproject.ssgjojo.cart.controller;

import com.cloneproject.ssgjojo.cart.dto.CartAddDto;
import com.cloneproject.ssgjojo.cart.service.ICartService;
import com.cloneproject.ssgjojo.cartproductlist.domain.CartProductList;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListAddDto;
import com.cloneproject.ssgjojo.cartproductlist.dto.CartProductListGetIdEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add") // 장바구니 추가
    public ResponseEntity<?> addCart(@RequestBody CartAddDto cartAddDto, HttpServletRequest request) {
        List<CartProductListAddDto> cart = iCartService.addCart(cartAddDto, request);

        if(cart!=null){
            return ResponseEntity.status(200).body("장바구니에 추가되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @GetMapping("/cart/get") // 해당 사용자의 장바구니 조회
    public ResponseEntity<?> getCart(HttpServletRequest request) {
        List<CartProductListGetIdEditDto> cart = iCartService.getCartByUserId(request);

        if(cart!=null){
            return ResponseEntity.status(200).body(cart);
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @PutMapping("/cart/edit") // 상품 수량 변경
    public ResponseEntity<?> editCart(@RequestBody CartProductListGetIdEditDto cartProductListGetIdEditDto) {
        Optional<CartProductList> cart = iCartService.editCart(cartProductListGetIdEditDto);

        if(cart.isPresent()){
            return ResponseEntity.status(200).body("수량이 변경되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @DeleteMapping("/cart/delete/{id}") // 장바구니 상품 삭제
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        Optional<CartProductList> cart = iCartService.deleteCart(id);

        if(cart.isPresent()){
            return ResponseEntity.status(200).body("장바구니 상품이 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }

    @DeleteMapping("/cart/deleteall/{id}") // 장바구니 상품 전체삭제
    public ResponseEntity<?> deleteCartAll(@PathVariable Long id) {
        boolean cart = iCartService.deleteCartAll(id);

        if(cart == true){
            return ResponseEntity.status(200).body("장바구니 상품이 전체 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("error page");
        }
    }


//    @PutMapping("/cart/edit") // 상품 옵션변경
//    public CartEditGetIdDto editCart(@RequestBody CartEditGetIdDto cartEditGetIdDto) {
//        return iCartService.editCart(cartEditGetIdDto);
//    }
}
