<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Cập nhập hoặc thêm tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-content">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Chỉnh sửa</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-header">
                <h1 style="font-family: 'Times New Roman', Times, serif;">
                    Thêm tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                </div>
            </div>
            <!-- Bảng danh sách -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="buildingEdit" id="listForm" method="POST">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form">
                            <div class ="form-group">
                                <label class="col-xs-3">Tên tòa nhà</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="name"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Quận</label>
                                <div class="col-xs-2">
                                    <label class="name">Quận hiện có</label>
                                    <form:select class="form-control" path="district">
                                        <form:option value="">---Chọn quận---</form:option>
                                        <form:options items="${districts}"></form:options>
                                    </form:select>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Phường</label>
                                <div class="col-xs-9">
                                   <form:input class="form-control" path="ward"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Đường</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="street"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Kết cấu</label>
                                <div class="col-xs-9">
                                     <form:input class="form-control" path="structure"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="numberOfBasement"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="floorArea"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class="col-xs-9">
                                <form:input class="form-control" path="direction"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class="col-xs-9">
                                <form:input class="form-control" path="level"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentArea"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentPrice"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Giá thuê mô tả</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentPriceDescription"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Tên quản lý</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerName"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Số điện thoại quản lý</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerPhone"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Phí môi giới</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="brokerageFee"/>
                                </div>
                            </div>
                            <div class ="form-group">
                                <label class="col-xs-3">Loại tòa nhà</label>
                                <div class="col-xs-9">
                                <form:checkboxes items ="${buildingTypes}" path="typeCode"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty buildingEdit.id}">
                                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Cập nhập tòa nhà
                                    </button>
                                    <button type="button" class="btn btn-danger"id="btnCancel">Hủy thao tác</button>
                                    </c:if>
                                    <c:if test="${empty buildingEdit.id}">
                                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm mới tòa nhà
                                    </button>
                                    <button type="button" class="btn btn-danger" id="btnCancel">Hủy thao tác</button>
                                     </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>
                        </form>
                    </div>
            </div><!-- /.span -->
</form:form>
        </div>
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<script>
$('#btnAddOrUpdateBuilding').click(function (e) {
    e.preventDefault();
    var data = {};
    var typeCode = [];
    var formData = $('#listForm').serializeArray();
    $.each(formData, function (i, v) {
        if (v.name !== 'typeCode') {
            data[v.name] = v.value;
        } else {
            typeCode.push(v.value);
        }
    });
    data['typeCode'] = typeCode;
    if (typeCode.length === 0) {
        alert("Vui lòng chọn loại tòa nhà");
        return;
    }
    addOrUpdateBuilding(data);
});
$('#btnCancel').click(function () {
    window.location.href = '/admin/building-list';
});
function addOrUpdateBuilding(data) {
    $.ajax({
        type: "POST",
        url: "${buildingAPI}",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            alert("Thêm tòa nhà thành công");
            window.location.href = "/admin/building-list";
        },
        error: function (res) {
            console.log(res);
            alert("Thêm tòa nhà thất bại");
        }
    });
}
</script>
</body>
</html>
