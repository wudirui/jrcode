<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="${ctx }/css/css.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            /** 表单提交的校验 */
            $("#btn").click(function () {
                var file = $("#file").val();
                var menuId = $('#menuId').val();
                if (menuId == "0") {
                    msg = "请选择所属菜单";
                    alert("请选择菜单！");
                    return;
                }
                else if (!file) {
                    alert("请上传文档！");
                    return;
                }

                $("#slideshowForm").submit();

            })
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
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：轮播图管理 &gt; 上传轮播图
        </td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>

            <form id="slideshowForm" name="slideshowForm" action="${ctx }/slideshow/addSlideshow"
                  enctype="multipart/form-data" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <form:form modelAttribute="user" method="post">
                    <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
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
                            <td class="main_tdbor"></td>
                        </tr>

                        <tr>
                            <td class="font3 fftd">图片：<br/>
                                <input type="file" name="file" id="file" size="30"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="main_tdbor"></td>
                        </tr>


                        <tr>
                            <td class="font3 fftd">
                                <input type="button" id="btn" value="上传">
                                <input type="reset" value="重置">
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
</body>
</html>