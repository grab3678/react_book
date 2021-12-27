package kr.icia.service;

import kr.icia.vo.ProductsVO;

import java.util.List;

public interface ProductsService {
    public void register(ProductsVO productsVO);

    public ProductsVO get(Long pno);

    public boolean modify(ProductsVO productsVO);

    public boolean remove(Long pno);

    public List<ProductsVO> getList();
}
