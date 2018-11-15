package cn.wenqi.oauth2.controller;

import cn.wenqi.oauth2.entity.Goods;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wenqi
 * @since v1.3
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("/list")
    public ResponseEntity<List<Goods>> getGoodsList(){

        List<Goods> goodsList=new LinkedList<>();
        Goods goods;
        for (int i = 0; i < 10; i++) {
            goods=new Goods();
            goods.setGoodsId(i);
            goods.setGoodsName("商品"+i);
            goods.setPrice(i*2+3);
            goodsList.add(goods);
        }
        return ResponseEntity.ok(goodsList);
    }
}
