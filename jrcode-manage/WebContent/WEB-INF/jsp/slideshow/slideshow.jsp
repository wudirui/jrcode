<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——轮播图管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="${ctx }/css/css.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx }/css/pager.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <link href="${ctx }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="${ctx }/js/jquery-ui.js"></script>
    <style>
        .enlargeImg_wrapper {
            display: none;
            position: fixed;
            z-index: 999;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-color: rgba(52, 52, 52, 0.8);
            background-size: 60%;
        }

        img:hover {
            cursor: zoom-in;
        }

        .enlargeImg_wrapper:hover {
            cursor: zoom-out;
        }
    </style>
    <script type="text/javascript">
        $(function () {

            var boxs = $("input[type='checkbox'][id^='box_']");
            /** 给全选按钮绑定点击事件  */
            $("#checkAll").click(function () {
                // this是checkAll  this.checked是true
                // 所有数据行的选中状态与全选的状态一致
                boxs.attr("checked", this.checked);
            })

            /** 给每个数据行绑定点击事件：判断如果数据行都选中全选也应该选中，反之  */
            boxs.click(function (event) {
                /** 去掉复选按钮的事件传播：点击复选会触发行点击：因为复选在行中 */
                event.stopPropagation();

                /** 判断当前选中的数据行有多少个  */
                var checkedBoxs = boxs.filter(":checked");
                /** 判断选中的总行数是否等于总行数：以便控制全选按钮的状态   */
                $("#checkAll").attr("checked", checkedBoxs.length == boxs.length);
            })

            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function () {
                $(this).css("backgroundColor", "#eeccff");
            }, function () {
                $(this).css("backgroundColor", "#ffffff");
            }).click(function () {
                /** 控制该行是否需要被选中 */
                /** 获取此行的复选框id */
                var checkboxId = this.id.replace("data_", "box_");

                /** 触发本行的复选点击 */
                $("#" + checkboxId).trigger("click");
            })

            /** 删除员工绑定点击事件 */
            $("#delete").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1) {
                    $.ligerDialog.error("请选择一个需要删除的文档！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?", "删除文档", function (r) {
                        if (r) {
                            // alert("删除："+ids.get());
                            // 发送请求
                            window.location = "${ctx }/slideshow/removeSlideshow?ids=" + ids.get();
                        }
                    });
                }
            });


            /** 轮播图排序 */
            $("#btn-sort").click(function () {

                var menuId = $('#menuId').val();
                var msg = "";
                if (menuId == "0") {
                    msg = "请选择所属菜单";
                }
                if (msg != "") {
                    alert(msg);
                    return false;
                }


                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs;


                /** 获取轮播图的ids和sorts */
                var sorts = [];
                var i = 0;
                $("td[name='sort-name']").each(function () {
                    sorts[i] = $(this).html();
                    i++;
                });
                var slideshowids = [];
                var j = 0;
                $("input[name='slideshowId']").each(function () {
                    slideshowids[j] = $(this).val();
                    j++;
                });
                $.ligerDialog.confirm("你确定要重新排序吗?", "轮播图排序", function (r) {
                    if (r) {
                        var idsAndSorts = slideshowids.join(',') + "@" + sorts.join(",");

                        $.ajax({
                            url: "${ctx }/slideshow/sortSlideshow",
                            data: {
                                "idsAndSorts": idsAndSorts,
                                "menuId": menuId
                            },
                            type: "GET",
                            dataType: "json",
                            success: function (req) {
                                if (req) {
                                    alert("排序成功");
                                }
                            }
                        });
                    }
                });
            });


            /** 下载文档功能 */
            $("a[id^='down_']").click(function () {
                /** 得到需要下载的文档的id */
                var id = this.id.replace("down_", "");
                /** 下载该文档 */
                window.location = "${ctx}/document/downLoad?id=" + id;
            });

            //查看大图
            $(".enlargeImg").click(function () {
                $(this).after("<div οnclick='closeImg()' id='removeImg' class='enlargeImg_wrapper'></div>");
                var imgSrc = $(this).attr('src');
                $(".enlargeImg_wrapper").css("background-image", "url(" + imgSrc + ")");
                $('.enlargeImg_wrapper').fadeIn(200);
            });
            //关闭并移除图层
            $(document).on('click', '#removeImg', function () {
                $("#removeImg").fadeOut(200).remove();
            });

        });

        function down(id) {
            $("a[id='down_" + id + "']").trigger("click");
        }
    </script>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：轮播图管理 &gt; 轮播图查询</td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <form:form modelAttribute="user" method="post" action="${ctx }/slideshow/selectSlideshow">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="fftd">
                            <form name="slideshowForm" method="post" id="slideshowForm"
                                  action="${ctx }/slideshow/selectSlideshow">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="font3">
                                            所属菜单：
                                            <form:select path="id" id="menuId" name="menuId">
                                                <form:option value="0" label="--请选择所属菜单--"></form:option>
                                                <form:options items="${menuList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                                <%--<form:select path="id" items="${menuMap}"/>--%>
                                            <input type="submit" value="搜索"/>
                                            <input type="button" id="delete" value="删除">
                                            <input type="button" id="btn-sort" value="排序">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>

    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table id="sort" width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <thead>
                <tr class="main_trbg_tit" align="center">
                    <td><input type="checkbox" id="checkAll"></td>
                    <td>名称</td>
                    <td>排序</td>
                    <td>图片</td>
                    <td>所属菜单</td>
                    <td>创建人</td>
                    <td>创建时间</td>

                </tr>
                </thead>

                <c:forEach items="${requestScope.slideshowes}" var="slideshow" varStatus="stat">
                    <tr ondblclick="down(${slideshow.id});" class="main_trbg" align="center" id="data_${stat.index}">
                        <td><input name="slideshowId" type="checkbox" id="box_${stat.index}" value="${slideshow.id}">
                        </td>
                        <td>${slideshow.fileName }</td>
                        <td name="sort-name" class="index">${slideshow.sort}</td>
                        <td><img class="enlargeImg" src="${slideshow.imgStr }" style="width: 100px;height: 30px" alt="">
                        </td>
                        <td>${slideshow.menuName }</td>
                        <td>${slideshow.user.username }</td>
                        <td>
                            <f:formatDate value="${slideshow.createTime}"
                                          type="date" dateStyle="long"/>
                        </td>

                            <%--<td>${slideshow.remark }</td>--%>


                    </tr>
                </c:forEach>


            </table>
        </td>
    </tr>
    <!-- 分页标签 -->
    <tr valign="top">
        <td align="center" class="font3">
            <fkjava:pager
                    pageIndex="${pageModel.pageIndex}"
                    pageSize="${pageModel.pageSize}"
                    recordCount="${pageModel.recordCount}"
                    submitUrl="${ctx}/document/selectDocument.action?pageModel.pageIndex={0}&document.title=${document.title}"
                    style="flickr"
            />
        </td>
    </tr>
</table>
<div style="height:10px;"></div>

<script type="application/javascript">
    $(document).ready(function () {
        var fixHelperModified = function (e, tr) {
                var $originals = tr.children();
                var $helper = tr.clone();
                $helper.children().each(function (index) {
                    $(this).width($originals.eq(index).width())
                });
                return $helper;
            },
            updateIndex = function (e, ui) {
                $('td.index', ui.item.parent()).each(function (i) {
                    $(this).html(i + 1);
                });
            };
        $("#sort tbody").sortable({
            helper: fixHelperModified,
            stop: updateIndex
        }).disableSelection();
    });
</script>
</body>
</html>