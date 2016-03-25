<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/tag.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/css/index.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/js/jquery/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/util.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/confirm.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery/jquery.poshytip.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/list_common.js" type="text/javascript"></script>
    <script src="${ctx}/js/mchShop/mchShop.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/select.js" type="text/javascript"></script>

    <link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>


    <link href="${ctx}/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
</head>
<div class="memSuper">
    <div class="memSuper-title">商户管理 >门店管理 >编辑</div>
    <div class="memSuper-main">
        <div class="page-content ng-scope">
            <input id="currpage" type="hidden" name="currpage" value="${currpage}"/>
            <input id="rowCount" type="hidden" name="rowCount" value="${rowCount}"/>

            <div class="panel panel-default">
                <div class="panel-heading">
                    门店信息
                </div>
                <div class="panel-body">
                    <input id="id" type="hidden" name="id" value="${entity.id}"/>

                    <div class="form-group row mb15">
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon input-group-onlytext-muted">名称：</span>
                                <input id="name" type="text" value="${entity.name}"
                                       class="form-control dropdown-toggle ng-pristine ng-valid" required="required"
                                       placeholder="门店名称"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb15">
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon input-group-onlytext-muted">地址：</span>
                                <input id="address" type="text" value="${entity.address}"
                                       class="form-control dropdown-toggle ng-pristine ng-valid" required="required"
                                       placeholder="门店地址"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb15">
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon input-group-onlytext">公司：</span>
                                <select class="form-control" id="companyId" onchange="subCompanySelect('../mchSubCompany/getByCompanyId','companyId','subCompanyId')">
                                     <option value="0">选择所属公司</option>
                                    <c:forEach items="${entity.companyList}" var="item">
                                        <option value="${item.id}" <c:if test="${item.id==entity.companyId}">selected="true"</c:if>>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb15" style="display:none" id="subCompany-select">
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon input-group-onlytext">分公司：</span>
                                <select class="form-control" id="subCompanyId">
                                    <option value="0">选择所属公分司</option>
                                    <c:forEach items="${entity.subCompanyVOList}" var="item">
                                        <option value="${item.id}" <c:if test="${item.id==entity.subCompanyId}">selected="true"</c:if>>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb15">
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon input-group-onlytext" name="desc">备注：</span>
                                <textarea class="form-control" rows="3" id="desc"
                                          placeholder="门店备注">${entity.desc}</textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row mb15">
                        <div class="col-sm-2">
                            <a class="btn btn-danger" style="width: 90px;" id="cance-button"
                               onclick="window.history.go(-1);">
                                <i class="fa fa-reply"></i>
                                <span class="btn-text">取消</span>
                            </a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-primary" style="width: 90px;" id="save-button" onclick="save()">
                                <i class="fa fa-floppy-o"></i>
                                <span class="btn-text">保存</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
<%@ include file="../common/confirm.jsp"%>