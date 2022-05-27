package site.metacoding.blogv4junit.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩 // h2 동작 // 자동 롤백
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void db_init() {
        // 데이터베이스 초기화
        bookRepository.deleteAll();
        em
                .createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1")
                .executeUpdate();
    }

    // C : 유효성 검사 필요 (X)
    @Test
    @Order(1)
    public void save_test() { // 매개변수 주입 X
        // given 가짜 데이터
        // 데이터를 바로 넣지말고 변수화 시키기
        String title = "스트링부트1강";
        String author = "권진용";

        Book book = new Book(title, author);

        // when 테스트 진행
        Book bookEntity = bookRepository.save(book);

        // then 검증(verify)
        assertEquals(1, bookEntity.getId());
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
    }

    @Test
    @Order(2)
    public void findById_test() {
        // given 가짜 데이터
        // find할 데이터가 하나 있어야 하기 때문에 save
        String title = "스트링부트1강";
        String author = "권진용";

        Book book = new Book(title, author);
        bookRepository.save(book);

        Long id = 1L;

        // when 테스트 진행
        Optional<Book> bookOp = bookRepository.findById(id);

        if (bookOp.isPresent()) {
            Book bookEntity = bookOp.get();

            // then 검증
            assertEquals(1, bookEntity.getId());
            assertEquals(title, bookEntity.getTitle());
            assertEquals(author, bookEntity.getAuthor());
        } else {
            assertNotNull(bookOp.get()); // null 아니지? -> null 들어옴 -> false
        }

    }
}