<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'userManage.jsp' starting page</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        var url;
        function searchUser() {
            $("#dg").datagrid('load', {
                "userName" : $("#s_userName").val()
            });
        }
        function openUserAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
            url = "${pageContext.request.contextPath}/user/save.do";
        }

        function openUserModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑用户信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/user/save.do?id=" + row.id;
        }

        function saveUser() {
            $("#fm").form("submit", {
                url : url,
                onSubmit : function() {
                    return $(this).form("validate");
                },
                success : function(result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败！");
                        return;
                    }
                }
            });
        }

        function resetValue() {
            $("#userName").val("");
            $("#password").val("");
        }

        function closeUserDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function deleteUser() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for ( var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].id);
            }
            var ids = strIds.join(",");
            $.messager.confirm("系统提示", "您确定要删除这<font color=red>"
            + selectedRows.length + "</font>条数据吗？", function(r) {
                if (r) {
                    $.post("${pageContext.request.contextPath}/user/delete.do", {
                        ids : ids
                    }, function(result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "数据已成功删除！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
                        }
                    }, "json");
                }
            });
        }
    </script>
</head>

<body style="margin: 1px">

<table id="dg" title="用户管理" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/user/list.do" fit="true"
       toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="userName" width="50" align="center">用户名</th>
        <th field="password" width="50" align="center">密码</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:openUserAddDialog()" class="easyui-linkbutton"
       iconCls="icon-add" plain="true">添加</a> <a
        href="javascript:openUserModifyDialog()" class="easyui-linkbutton"
        iconCls="icon-edit" plain="true">修改</a> <a
        href="javascript:deleteUser()" class="easyui-linkbutton"
        iconCls="icon-remove" plain="true">删除</a>
    <div>
        &nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20"
                               onkeydown="if(event.keyCode == 13)searchUser()" /> <a
            href="javascript:searchUser()" class="easyui-linkbutton"
            iconCls="icon-search" plain="true">查询</a>
    </div>

    <div id="dlg-buttons">
        <a href="javascript:saveUser()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a> <a href="javascript:closeUserDialog()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>

    <div id="dlg" class="easyui-dialog"
         style="width: 730px;height:280px;padding:10px 10px;" closed="true"
         buttons="#dlg-buttons">
        <form method="post" id="fm">
            <table cellspacing="8px;">
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" id="userName" name="userName"
                               class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>密码：</td>
                    <td><input type="password" id="password" name="password"
                               class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
