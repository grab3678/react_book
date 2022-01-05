package com.example.book.Service;

//단위 테스트(Service 와 관련된 애들만 memory 에 띄움)
//BoardRepository => 가짜 객체로 만들 수 있음.


import com.example.book.domain.BookRepository;
import com.example.book.service.BookService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    @InjectMocks // BookService 객체가 만들어 질때 해당 파일에 @Mcok 로 등록된 모든 객체 주입.
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
}
