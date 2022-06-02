package site.metacoding.blogv4junit.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.service.BookService;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

// Controller, ControllerAdvice, Filter, WebMvcConfigurer(web.xml)
@WebMvcTest(BookApiController.class) // 실제 컨트롤러가 메모리에 뜸
public class BookApiControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc; // IoC 컨테이너가 들고있음 -> @Autowired 가능

    @MockBean
    private BookService bookService;

    @Test
    public void bookSave_테스트() throws Exception { // catch 제어를 JUnit에게 넘김

        BookSaveReqDto reqDto = new BookSaveReqDto();
        reqDto.setTitle("제목1");
        reqDto.setAuthor("메타코딩");

        // given (JSON 데이터)
        String body = new ObjectMapper().writeValueAsString(reqDto);
        System.out.println("===========================================");
        System.out.println(body);
        System.out.println("===========================================");

        // stub
        Mockito.when(bookService.책등록하기(reqDto)).thenReturn(new BookRespDto(1L, "제목1", "메타코딩"));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)); // accept 나는 json 데이터를 기대해! (필수 X)

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                // .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목1"))
                // .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("메타코딩"))
                .andDo(MockMvcResultHandlers.print()); // 테스트하면서 일어나는 일들 log로 뿌려줌

    }

    @Test
    public void bookFindOne_테스트() {
        // given

        // when

        // then

    }
}
