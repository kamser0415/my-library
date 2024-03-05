package com.group.libraryapp.domain.user;


import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20,name= "name")
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistoryList = new ArrayList<>();

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 잘못된 값으로 들어옴");
        }
        this.name = name;
        this.age = age;
    }

    protected User() {

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public List<UserLoanHistory> getUserLoanHistoryList() {
        return userLoanHistoryList;
    }

    public void loanBook(String bookName) {
        UserLoanHistory userLoanHistory = new UserLoanHistory(this, bookName, false);
        userLoanHistory.registerUser(this);
        this.userLoanHistoryList.add(userLoanHistory);
    }

    public void returnBook(String name) {
        UserLoanHistory targetHistory = this.userLoanHistoryList.stream().filter(it -> it.getBookName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없어"));
        targetHistory.doReturn();
    }
}
