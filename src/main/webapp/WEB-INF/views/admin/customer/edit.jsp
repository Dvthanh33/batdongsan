<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerAPI" value="/api/customer"/>

<html>
<head>
    <title>Chỉnh sửa thông tin</title>
</head>
<body>
<div class="main-content" id="main-content">
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
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-header">
            <h1>
                Thông tin khách hàng
                <small>
                    <i class="ace-icon fa fa-angle-double-right"></i>
                </small>
            </h1>
        </div><!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box ui-sortable-handle">
                    <div class="widget-header">
                        <h5 class="widget-title">Thêm thông tin</h5>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body" style="font-family:'Times New Roman', Times, serif;">
                        <div class="widget-main">
                        <form:form modelAttribute="customerEdit" id="listForm" class="form-horizontal" >
           <form:hidden path="id"/>
    <div class="row">
        <!-- CỘT TRÁI -->
        <div class="col-xs-6">
            <div class="form-group">
                <label class="col-xs-4 control-label">Tên khách hàng</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="fullName"
                                placeholder="Nhập tên khách hàng" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label">Số điện thoại</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="customerPhone"
                                placeholder="Nhập số điện thoại" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label">Email</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="email"
                                placeholder="example@email.com"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label">Tên công ty</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="companyName"
                                placeholder="Tên công ty"/>
                </div>
            </div>
        </div>
        <!-- CỘT PHẢI -->
        <div class="col-xs-6">
            <div class="form-group">
                <label class="col-xs-4 control-label">Nhân viên quản lý</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="managementStaff"
                                placeholder="Tên nhân viên"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label">Nhu cầu</label>
                <div class="col-xs-8">
                    <form:input class="form-control" path="demand"
                                placeholder="Nhu cầu khách hàng"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label">Tình trạng</label>
                <div class="col-xs-8">
                      <form:select name="statuses" path="status">
                          <form:option value="">---Chọn Tình Trạng---</form:option>
                          <form:options items="${statuses}"></form:options>
                      </form:select><br>
                </div>
            </div>
        </div>
    </div>
    <hr/>
    <c:if test="${not empty customerEdit.id}">
<div class="widget-box">
    <div class="widget-header">
        <h5 class="widget-title">
            <i class="ace-icon fa fa-phone"></i>
            Chăm sóc khách hàng (CSKH)
        </h5>
        <div class="widget-toolbar">
            <button type="button"
                    class="btn btn-xs btn-primary btnAddTransaction"
                    data-code="CSKH">
                <i class="ace-icon fa fa-plus"></i>
                Thêm CSKH
            </button>
        </div>
    </div>
    <div class="widget-body">
        <div class="widget-main">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th style="width:50%">Nội dung</th>
                    <th>Ngày tạo</th>
                    <th style="width:120px">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty CSKH}">
                    <tr>
                        <td colspan="3" class="text-center">
                            Chưa có giao dịch CSKH
                        </td>
                    </tr>
                </c:if>
                <c:forEach var="item" items="${CSKH}">
                    <tr>
                        <td>${item.note}</td>
                        <td>${item.createdDate}</td>
                        <td>
                            <button type="button" title="Sửa giao dịch" class="btn btn-xs btn-info btnEditTransaction"
                                    data-id="${item.id}">
                                <i class="ace-icon fa fa-pencil"></i>
                            </button>
                            <button type="button" class="btn btn-xs btn-danger btnDeleteTransaction"
                                    data-id="${item.id}"
                                    title="Xóa giao dịch">
                                <i class="ace-icon fa fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
</c:if>
<c:if test="${not empty customerEdit.id}">
<div class="widget-box">
    <div class="widget-header">
        <h5 class="widget-title">
            <i class="ace-icon fa fa-phone"></i>
            Dẫn Đi Xem (DDX)
        </h5>
        <div class="widget-toolbar">
            <button type="button"
                    class="btn btn-xs btn-primary btnAddTransaction"
                    data-code="DDX">
                <i class="ace-icon fa fa-plus"></i>
                Thêm DDX
            </button>
        </div>
    </div>

    <div class="widget-body">
        <div class="widget-main">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th style="width:50%">Nội dung</th>
                    <th>Ngày tạo</th>
                    <th style="width:120px">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty DDX}">
                    <tr>
                        <td colspan="3" class="text-center">
                            Chưa có giao dịch DDX
                        </td>
                    </tr>
                </c:if>
                <c:forEach var="item" items="${DDX}">
                    <tr>
                        <td>${item.note}</td>
                        <td>${item.createdDate}</td>
                        <td>
                            <button type="button"
                            title="Sửa giao dịch" class="btn btn-xs btn-info btnEditTransaction"
                                    data-id="${item.id}">
                                <i class="ace-icon fa fa-pencil"></i>
                            </button>
                            <button type="button" class="btn btn-xs btn-danger btnDeleteTransaction"
                                    data-id="${item.id}" title="Xóa giao dịch">
                                <i class="ace-icon fa fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
</c:if>
    <!-- NÚT HÀNH ĐỘNG -->
    <div class="form-group">
        <div class="col-xs-12 text-right">
        <c:if test="${not empty customerEdit.id}">
            <button type="submit" class="btn btn-success" id="btnAddOrUpdateCustomer">
                <i class="ace-icon fa fa-save"></i>
                Cập nhập thông tin khách hàng
            </button>
            <button type="button" class="btn btn-default" id="btnCancel">
                <i class="ace-icon fa fa-undo"></i>
                Hủy
            </button>
            </c:if>
            <c:if test="${empty customerEdit.id}">
            <button type="submit" class="btn btn-success" id="btnAddOrUpdateCustomer">
                <i class="ace-icon fa fa-save"></i>
                Thêm mới khách hàng
            </button>
            <button type="button" class="btn btn-default" id="btnCancel">
                <i class="ace-icon fa fa-undo"></i>
                Hủy
            </button>
            </c:if>
        </div>
    </div>
</form:form>
<div class="modal fade" id="transactionModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Thêm giao dịch</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="transactionId"/>
                <input type="hidden" id="transactionCode"/>
                <textarea id="transactionNote" class="form-control"
                          placeholder="Nhập nội dung giao dịch" rows="3"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnSaveTransaction">
                    Lưu
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Hủy
                </button>
            </div>
        </div>
    </div>
</div>
<script>
       $('#btnAddOrUpdateCustomer').click(function (e) {
    e.preventDefault();
    //kiểm tra form hợp lệ
    if (!document.getElementById("listForm").checkValidity()) {
        document.getElementById("listForm").reportValidity();
        return;
    }
    var data = {};
    var formData = $('#listForm').serializeArray();
    $.each(formData, function (i, v) {
        data[v.name] = v.value;
    });
    $.ajax({
        type: "POST",
        url: "${customerAPI}",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            alert("Lưu khách hàng thành công");
            window.location.href = "/admin/customer-list";
        },
        error: function () {
            alert("Lưu khách hàng thất bại");
        }
    });
});
$('#btnCancel').click(function () {
    window.location.href = '/admin/customer-list';
});
$('.btnAddTransaction').click(function () {
    var code = $(this).data('code');
    $('#transactionId').val('');
    $('#transactionCode').val(code);
    $('#transactionNote').val('');
    $('#transactionModal').modal('show');
});
    $('.btnEditTransaction').click(function () {
    var transactionId = $(this).data('id');
    $.ajax({
        type: "GET",
        url: "/api/transaction/" + transactionId,
        success: function (res) {
            $('#transactionId').val(res.id);
            $('#transactionNote').val(res.note);
            $('#transactionCode').val(res.code);
            $('#transactionModal').modal('show');
        }
    });
});
    $('.btnDeleteTransaction').click(function () {
    var transactionId = $(this).data('id');
    if (confirm("Bạn có chắc muốn xóa giao dịch này?")) {
        $.ajax({
            type: "DELETE",
            url: "/api/transaction/" + transactionId,
            success: function () {
                alert("Xóa giao dịch thành công");
                location.reload();
            },
            error: function () {
                alert("Xóa giao dịch thất bại");
            }
        });
    }
});
$('#btnSaveTransaction').click(function () {
    var data = {
        id: $('#transactionId').val(),
        customerId: $('input[name="id"]').val(),
        code: $('#transactionCode').val(),
        note: $('#transactionNote').val()
    };
    console.log(data); // để debug xem có dữ liệu không
    $.ajax({
        type: "POST",
        url: "/api/transaction",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            alert("Lưu giao dịch thành công");
            $('#transactionModal').modal('hide');
            location.reload();
        },
        error: function (res) {
            console.log(res);
            alert("Lưu giao dịch thất bại");
        }
    });

});
</script>
</body>
</html>
