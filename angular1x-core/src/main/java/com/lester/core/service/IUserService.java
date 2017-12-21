package com.lester.core.service;

import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.ModifyPasswordParam;
import com.lester.core.model.ResultMsg;
import com.lester.core.viewModel.Option;
import com.lester.core.viewModel.UserBoardEditReq;
import com.lester.support.util.Pagination;

import java.util.List;

public interface IUserService {

    List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception;

    String queryUsersCount() throws Exception;

    List<Option> queryDepOpts() throws Exception;

    List<Option> queryRoleOpts() throws Exception;

    ResultMsg inserUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;

    ResultMsg updateUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;

    CfgAuthUser loadUserByUsername(String userName) throws Exception;

    boolean isHasUser(String userName, String oldPassword) throws Exception;

    ResultMsg modifyPassword(CfgAuthUser user, ModifyPasswordParam param) throws Exception;

    ResultMsg revertPassword(CfgAuthUser user, CfgAuthUser loginUser) throws Exception;
}
