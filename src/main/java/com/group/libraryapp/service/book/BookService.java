package com.group.libraryapp.service.book;

import com.group.libraryapp.controller.book.request.BookCreateRequest;
import com.group.libraryapp.controller.book.request.BookLoanRequest;
import com.group.libraryapp.controller.book.request.BookReturnRequest;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository loanUserRepository;

    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository loanUserRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.loanUserRepository = loanUserRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    public void loanBook(BookLoanRequest request) {
        // 책 정보를 가져온다.

        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));

        // 대출 기록 정보를 확인해서 대출중인지 확인한다.
        // 만약 확인했는데 대출 중이라면 예외를 발생시킨다.
        if (loanUserRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출 중인 책입니다.");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        user.loanBook(book.getName());

        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory에 대출 정보를 저장한다.
        // loanUserRepository.save(UserLoanHistory.loan(user.getId(), book.getName()));

    }
    @Transactional
    public void returnBook(BookReturnRequest request) {
        // 1. 해당 유저가 있는지 확인한다.
        // 2. 책 대출 정보에서 해당 해당 유저 아이디와 책 이름으로 대출중인 기록이 있는지 확인한다.
        // 3. 있는 경우 반납으로 변경한다.

        // 강사님은 API로 결정된 정보를 통해서 책 대출 테이블에 필요한 정보를 얻기위한 작업을 먼저 생각합니다.
        //
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
//
//        UserLoanHistory userLoanHistory = loanUserRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                .orElseThrow(() -> new IllegalArgumentException("대출 기록이 없습니다."));
//
//        userLoanHistory.doReturn();
        user.returnBook(request.getBookName());
    }
}
