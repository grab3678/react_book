package com.example.book.service;

//기능을 정의할 수 있고, 트랜잭션을 관리할 수 있음.

import com.example.book.domain.Book;
import com.example.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional // 서비스 함수가 종료될때 commit 할지 rollback할지 트랜잭션 관리하겠다.
    public Book 저장하기(Book book){
        return bookRepository.save(book);
    }
    @Transactional(readOnly = true) // JPA 변경 감지 내부기능 X,update시의 정합성을 유지해줌.insert 의 유령데이터 현상(팬텀현상)못막음
    public Book 한건가져오기(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id를 확인해 주세요"));
    }
    public List<Book> 모두가져오기(){
        return bookRepository.findAll();
    }
    @Transactional
    public Book 수정하기 (Long id, Book book){
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id를 확인해 주세요"));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    }//함수 종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신(flush) => commit ====> 더티체킹

    @Transactional
    public String 삭제하기(Long id){
        bookRepository.deleteById(id);
        return "ok";
    }
}
