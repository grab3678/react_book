package com.example.book.domain;

//단위 테스트 (DB관련된 Bean 이 IoC에 등록되면 됨)

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)//가짜 DB로 테스트. != Replace.NONE
@DataJpaTest
public class BookRepositoryUnitTest {

    private BookRepository bookRepository;
}
