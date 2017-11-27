package com.lester.core.dao;

import com.lester.core.model.CfgAuthUser;
import com.lester.core.viewModel.Option;
import com.lester.support.util.Pagination;

import java.util.List;

public interface IUserDao {

    List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception;

    Integer inserUser(CfgAuthUser user) throws Exception;

    Integer deleteUserById(String userId) throws Exception;

    Integer updateUser(CfgAuthUser user) throws Exception;

    String queryUsersCount() throws Exception;

    List<Option> queryDepOpts() throws Exception;

    List<Option> queryRoleOpts() throws Exception;

    CfgAuthUser loadUserByUsername(String userName, boolean ignoreSts) throws Exception;

    boolean isHasUser(String userName, String oldPassword) throws Exception;

    Integer updatePassword(CfgAuthUser user) throws Exception;
}
