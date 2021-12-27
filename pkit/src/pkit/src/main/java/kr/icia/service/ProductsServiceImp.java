package kr.icia.service;

import kr.icia.mapper.ProductsMapper;
import kr.icia.vo.ProductsVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class ProductsServiceImp implements ProductsService{

    @Setter(onMethod_ = @Autowired)
    private ProductsMapper mapper;

    @Override
    public void register(ProductsVO productsVO) {
        mapper.insertSelectKey(productsVO);
    }

    @Override
    public ProductsVO get(Long pno) {
        return mapper.read(pno);
    }

    @Override
    public boolean modify(ProductsVO productsVO) {
        return (mapper.update(productsVO)) == 1;
    }

    @Override
    public boolean remove(Long pno) {
        return (mapper.delete(pno)) == 1;
    }

    @Override
    public List<ProductsVO> getList() {
        return mapper.getList();
    }
}
