<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="org.fkit.hrm.domain.Menu" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——后台管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet"/>

    <%--富文本编辑器--%>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.js"></script>
    <script type="text/javascript">

        $(function () {

            /** 表单提交的校验 */
            $("#noticeForm").submit(function () {
                var title = $("#title");
                var content = $('#content').summernote('code');
                var menuId = $('#menuId').val();
                var msg = "";

                var msg = "";
                if ($.trim(title.val()) == "") {
                    msg = "公告标题不能为空！";
                    title.focus();
                }
                else if ($.trim(title.val()).length <= 2) {
                    msg = "请输入两个字符以上的标题！";
                    title.focus();
                }
                else if (menuId == "0") {
                    msg = "请选择所属菜单";
                }
                else if (content == "<p><br></p>") {
                    msg = "公告内容不能为空！";
                    //content.focus(); 因为这个对象没有focus函数，所以加上导致页面报错，提交出现问题
                }
                if (msg != "") {
                    alert(msg);
                    return false;
                } else {
                    return true;
                }
                $("#noticeForm").submit();
            });
        });


    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：公告管理 &gt; 添加公告</td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>


<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form id="noticeForm" name="noticeForm" action="${ctx }/notice/addNotice" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <form:form modelAttribute="user" method="post">
                    <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                        <tr>
                            <td class="font3 fftd">
                                公告标题：<input type="text" name="title" size="30" id="title"/>
                                <span style="color: #ff0000;"></span>
                            </td>
                        </tr>

                        <tr>
                            <td class="font3 fftd">
                                所属菜单：
                                <form:select path="" id="menuId" name="menuId">
                                    <form:option value="0" label="--请选择所属菜单--"></form:option>
                                    <form:options items="${menuList}" itemLabel="name" itemValue="id"/>
                                </form:select>


                                <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="main_tdbor"></td>
                        </tr>

                        <tr>
                            <td class="font3 fftd">公告内容：<br/> <br/>

                                <textarea name="content" cols="88" rows="11"
                                          id="content"></textarea>
                                    <%--<div id="summernote"><p>Hello Summernote</p></div>--%>
                            </td>
                        </tr>
                        <tr>
                            <td class="main_tdbor"></td>
                        </tr>

                        <tr>
                            <td class="font3 fftd">
                                <input type="submit" value="添加">
                                <input id="reset_context" type="reset" value="重置">
                            </td>
                        </tr>
                        <tr>
                            <td class="main_tdbor"></td>
                        </tr>

                    </table>
                </form:form>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>

<script>
    $(document).ready(function () {
        $('#content').summernote({
            height: 300,
            toolbar: [
                ['edit',['undo','redo']],//编辑
                ['headline', ['style']],//大字标题
                ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],//样式
                ['fontface', ['fontname','color','fontsize']],//字体
                ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],//对齐方式
                ['height', ['height']],//行高(自定义行高时一定不要忘记写这个)
                ['table', ['table']],//表单
                ['insert', ['link','picture','hr']],//插入链接，图片，下划线
                ['view', ['fullscreen', 'codeview']]//全屏，代码视图
            ]
        });
    });
    $("#reset_context").click(function () {
        $('#content').summernote('code', '');
    });
</script>
</body>
</html>