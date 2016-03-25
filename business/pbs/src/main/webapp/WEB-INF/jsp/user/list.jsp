<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/tag.jsp" %>

<div class="table-responsive">
    <input id="currpage" type="hidden" name="currpage" value="${currpage}"/>
    <input id="rowCount" type="hidden" name="rowCount" value="${rowCount}"/>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>账号</th>
            <th>姓名</th>
            <th>电话号码</th>
            <th>是否公司员工</th>
            <th>所属公司</th>
            <th>所属分公司</th>
            <th>所属门店</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach var="entity" items="${list}" varStatus="status">
                    <tr>
                        <td>${entity.userAccount}</td>
                        <td>${entity.userName}</td>
                        <td>${entity.userPhone}</td>
                        <td>${entity.isEmployee}</td>
                        <td>${entity.mchCompanyName}</td>
                        <td>${entity.mchSubcompanyName}</td>
                        <td>${entity.mchShopName}</td>
                        <td>
                        <div class="col-sm-3">
                            <a class="btn btn-primary list-add" style="width: 90px;" id="edit-button" href="${ctx}/user/edit?id=${entity.id}">
                                <i class="fa fa-edit"></i>
                                <span class="btn-text" >修改</span>
                            </a>
                        </div>
                        <div class="col-sm-3">
                            <a class="btn btn btn-danger list-add" style="width: 90px;" id="cance-button" onclick="cance(${entity.id})">
                                <i class="fa fa-times-circle"></i>
                                <span class="btn-text" >删除</span>
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