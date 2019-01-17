package com.course.selection.dto;

import com.course.selection.bean.Admin;
import com.course.selection.bean.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class AccountDto {
    private List<Role> roles;
    private List<Admin> admins;
}
