package site.metacoding.blogv4junit.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    @PostMapping("/api/book")
    public ResponseEntity<?> test(@RequestBody BookSaveReqDto bookSaveReqDto) {

        return new ResponseEntity<>(bookSaveReqDto, HttpStatus.OK);
    }

}
