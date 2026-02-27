<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Quản Lý Khách Hàng</title>
     <link rel="stylesheet" href="<c:url value='/admin/css/validate-customer.css'/>">
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
                Danh Sách Khách Hàng
                <small>
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    overview &amp; stats
                </small>
            </h1>
        </div><!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box ui-sortable-handle">
                    <div class="widget-header">
                        <h5 class="widget-title">Tìm kiếm</h5>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body" style="font-family:'Times New Roman', Times, serif;">
                        <div class="widget-main">
                            <form:form action="${customerListURL}" id="listForm" method="GET" modelAttribute="modelSearch">
    <!-- ROW 1 -->
    <div class="row">
        <div class="col-xs-4">
            <div class="form-group">
                <label class="name">Tên khách hàng</label>
                <form:input class="form-control" path="fullname" placeholder="Nhập tên khách hàng"/>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="form-group">
                <label class="name">Số điện thoại</label>
                <form:input class="form-control" path="phone" placeholder="Nhập số điện thoại"/>
            </div>
        </div>

        <div class="col-xs-4">
            <div class="form-group">
                <label class="name">Email</label>
                <form:input class="form-control" path="email" placeholder="Nhập email"/>
            </div>
        </div>
    </div>
    <!-- ROW 2: STAFF nằm THẲNG CỘT với TÊN KH -->
    <security:authorize access="hasRole('MANAGER')">
        <div class="row">
            <div class="col-xs-4">
                <div class="form-group">
                    <label class="name">Chọn nhân viên</label>
                    <form:select class="form-control" path="staffId">
                        <form:option value="">--- Chọn nhân viên ---</form:option>
                        <form:options items="${listStaffs}" />
                    </form:select>
                </div>
            </div>
        </div>
    </security:authorize>
    <!-- ROW 3 -->
    <div class="row">
        <div class="col-xs-12">
            <button type="submit" class="btn btn-sm btn-success" id="btnSearchCustomer">
                <i class="ace-icon fa fa-search"></i>
                Tìm kiếm
            </button>
        </div>
    </div>
</form:form>
                </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/customer-edit">
                            <button class ="btn btn-success" title="Thêm khách hàng">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
  <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
</svg>
                        </button>
                        </a>
                        <button class ="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomer">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
  <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
</svg>
                        </button>
                    </div>
                </div>
            </div>
            </div>
        <!-- Bảng danh sách -->
        <div class="row">
            <div class="col-xs-12">
                <table id="tableList" style="margin: 3em 0 0;" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" name="checkList" value="" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th>Tên khách hàng</th>
                        <th>Số điện thoại</th>
                        <th>Email</th>
                        <th>Nhu cầu</th>
                        <th>Người thêm</th>
                        <th>Ngày thêm</th>
                        <th>Tình trạng</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${customerList}">
                        <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                                    <span class="lbl"></span>
                                </label>
                            </td>
                        <td>${item.fullName}</td>
                        <td>${item.customerPhone}</td>
                        <td>${item.email}</td>
                        <td>${item.demand}</td>
                        <td>${item.modifiedBy}</td>
                        <td>${item.createdDate}</td>
                        <td>${item.status}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success"title="Giao nhân viên" onclick="assignmentCustomer(${item.id})">
                                            <i class="ace-icon fa fa-list bigger-120"></i>
                                    </button>
                                </security:authorize>
                                <a class="btn btn-xs btn-info"title="Sửa khách hàng" href="/admin/customer-edit-${item.id}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>
                                <button class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick=deleteCustomer(${item.id})>
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
    </div><!-- /.page-content -->
    </div>
</div>  <!-- /.main-content -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">
                Danh Sách Giao Khách Hàng Cho Nhân Viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th>Loại chọn nhân viên</th></tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="Building">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnassignmentCustomer">Hoàn tất</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
function assignmentCustomer(customerId){
        $('#assignmentCustomerModal').modal();
        loadStaff(customerId);
        $("#customerId").val(customerId);
    }
    $('#btnassignmentCustomer').click(function(e){
        e.preventDefault();
        var data ={};
        data['customerId'] = $('#customerId').val();
        var staffs=$('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
            return ($(this).val());
        }).get();
        data['staffs'] = staffs;
        if (data.staffs.length === 0) {
    alert("Vui lòng chọn ít nhất một nhân viên");
    return;
}
assignment(data);

    })
    function assignment(data){
        $.ajax({
            type:"POST",
            url:"${customerAPI}/" + "assignment",
            contentType:"application/json",
            data: JSON.stringify(data),
            success:function(reponse){
                alert("Giao khách hàng cho nhân viên thành công");
            $('#assignmentCustomerModal').modal('hide');
        },
            error:function(reponse){
                alert("Giao khách hàng cho nhân viên thất bại");
                console.log(reponse);
            }
        });
    }
    function loadStaff(customerId){
    $.ajax({
        type: "GET",
        url: "${customerAPI}/" + customerId + "/staffs",
        dataType: "JSON",
        success: function (response) {
            var rows = "";
            $.each(response.data, function (index, item) {
                rows += '<tr>';
                rows += '<td class="center">';
                rows += '<input type="checkbox" value="' + item.staffId + '" '
                     + (item.checked ? 'checked' : '') + ' />';
                rows += '</td>';
                rows += '<td>' + item.fullName + '</td>';
                rows += '</tr>';
            });
            $('#staffList tbody').html(rows);
        },
        error: function (e) {
            console.log(e);
        }
    });
}

    $('#btnSearchCustomer').click(function(e){
        e.preventDefault();
        $('#listForm').submit();
    });
    function deleteCustomer(id){
    showAlertBeforeDelete(function(){
        var customerId = [id];
        deleteCustomers(customerId);
    });
}
    $('#btnDeleteCustomer').click(function(e){
    e.preventDefault();

    var customerIds = $('#tableList').find('tbody input[type=checkbox]:checked').map(function(){
        return $(this).val();
    }).get();

    if(customerIds.length === 0){
        alert("Vui lòng chọn khách hàng cần xóa");
        return;
    }
    showAlertBeforeDelete(function(){
        deleteCustomers(customerIds);
    });
});
    function deleteCustomers(data){
    $.ajax({
        type:"DELETE",
        url:"${customerAPI}",
        contentType:"application/json",
        data: JSON.stringify(data),
        success:function(response){
           alert("Xóa khách hàng thành công");
           location.reload();
        },
        error:function(respond){
            console.log(respond);
            alert("Xóa khách hàng thất bại");
        }
    });
}
</script>
</body>
</html>