package com.bukrieiev.bookstore.payload;

import com.bukrieiev.bookstore.dto.MainUserInformation;
import com.bukrieiev.bookstore.entity.Gender;
import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class AllUsersResponse {

    @Getter
    @Setter
    @NonNull
    private List<MainUserInformation> mainUserInformationList;

    @Getter
    @Setter
    @NonNull
    private Long totalCount;

    @Getter
    @Setter
    @NonNull
    private int totalPages;
}
