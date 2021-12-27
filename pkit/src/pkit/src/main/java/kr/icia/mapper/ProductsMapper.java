package kr.icia.mapper;

import kr.icia.vo.ProductsVO;

import java.util.List;

public interface ProductsMapper {
    public List<ProductsVO> getList();
    public void insert(ProductsVO products);
    public void insertSelectKey(ProductsVO products);
    public ProductsVO read(Long pno);
    public int delete(Long pno);
    public int update(ProductsVO products);
}
