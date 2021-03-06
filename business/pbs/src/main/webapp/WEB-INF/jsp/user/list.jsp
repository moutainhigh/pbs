<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/tag.jsp" %>

<div class="table-responsive">
    <input id="currpage" type="hidden" name="currpage" value="${currpage}"/>
    <input id="rowCount" type="hidden" name="rowCount" value="${rowCount}"/>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <th>账号</th>
        <th>姓名</th>
        <%--<th>电话号码</th>--%>
        <th>角色</th>
        <th>所属区域</th>
        <th>所属分销代理</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach var="entity" items="${list}" varStatus="status">
                    <tr>
                        <td>${entity.userAccount}</td>
                        <td>${entity.userName}</td>
                            <%--<td>${entity.userPhone}</td>--%>
                        <td>${entity.roleName}</td>
                        <td>${entity.areaName}</td>
                        <td>${entity.agentName}</td>
                        <td>
                            <div class="col-sm-3">
                                <a class="btn btn-primary list-add" style="width: 90px;" id="edit-button"
                                   href="${ctx}/user/edit?id=${entity.id}">
                                    <i class="fa fa-edit"></i>
                                    <span class="btn-text">修改</span>
                                </a>
                            </div>
                            <sec:authorize ifAnyGranted="ADMINISTRATOR,COMPANY_SHAREHOLDER,AREA_AGENT">
                                <div class="col-sm-3">
                                    <a class="btn btn btn-danger list-add" style="width: 90px;" id="cance-button"
                                       onclick="cance(${entity.id})">
                                        <i class="fa fa-times-circle"></i>
                                        <span class="btn-text">删除</span>
                                    </a>
                                </div>
                            </sec:authorize>
                            <div class="col-sm-3">
                                <a class="btn btn btn-danger list-add" style="width: 100px;" id="reset-button"
                                   onclick="reset(${entity.id})">
                                    <i class="fa fa-wrench"></i>
                                    <span class="btn-text">重置密码</span>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td style="text-align: center;" colspan="18">暂无记录</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<%@ include file="../footer.jsp" %>