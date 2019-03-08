<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\3\8
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.4.5/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.4.5/layui/layui.js"></script>
    <style>
    </style>
</head>
<body>

<div style="display: inline-block; width: 180px; height: 210px; padding: 10px; border: 1px solid #ddd; overflow: auto;">
    <ul id="demo1"></ul>
</div>

<p>点击每一级菜单，都会执行一个回调，返回该节点的一些信息</p>

<ul id="demo2"></ul>
<script>
    //Demo
    layui.use(['tree', 'layer'], function(){
        var layer = layui.layer
            ,$ = layui.jquery;

        layui.tree({
            elem: '#demo1' //指定元素
            ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            ,click: function(item){ //点击节点回调
                layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
                console.log(item);
            }
            ,nodes: [ //节点
                {
                    name: '常用文件夹'
                    ,id: 1
                    ,alias: 'changyong'
                    ,children: [
                        {
                            name: '所有未读（设置跳转）'
                            ,id: 11
                            ,href: 'http://www.layui.com/'
                            ,alias: 'weidu'
                        }, {
                            name: '置顶邮件'
                            ,id: 12
                        }, {
                            name: '标签邮件'
                            ,id: 13
                        }
                    ]
                }, {
                    name: '我的邮箱'
                    ,id: 2
                    ,spread: true
                    ,children: [
                        {
                            name: 'QQ邮箱'
                            ,id: 21
                            ,spread: true
                            ,children: [
                                {
                                    name: '收件箱'
                                    ,id: 211
                                    ,children: [
                                        {
                                            name: '所有未读'
                                            ,id: 2111
                                        }, {
                                            name: '置顶邮件'
                                            ,id: 2112
                                        }, {
                                            name: '标签邮件'
                                            ,id: 2113
                                        }
                                    ]
                                }, {
                                    name: '已发出的邮件'
                                    ,id: 212
                                }, {
                                    name: '垃圾邮件'
                                    ,id: 213
                                }
                            ]
                        }, {
                            name: '阿里云邮'
                            ,id: 22
                            ,children: [
                                {
                                    name: '收件箱'
                                    ,id: 221
                                }, {
                                    name: '已发出的邮件'
                                    ,id: 222
                                }, {
                                    name: '垃圾邮件'
                                    ,id: 223
                                }
                            ]
                        }
                    ]
                }
                ,{
                    name: '收藏夹'
                    ,id: 3
                    ,alias: 'changyong'
                    ,children: [
                        {
                            name: '爱情动作片'
                            ,id: 31
                            ,alias: 'love'
                        }, {
                            name: '技术栈'
                            ,id: 12
                            ,children: [
                                {
                                    name: '前端'
                                    ,id: 121
                                }
                                ,{
                                    name: '全端'
                                    ,id: 122
                                }
                            ]
                        }
                    ]
                }
            ]
        });


        //生成一个模拟树
        var createTree = function(node, start){
            node = node || function(){
                var arr = [];
                for(var i = 1; i < 10; i++){
                    arr.push({
                        name: i.toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
                    });
                }
                return arr;
            }();
            start = start || 1;
            layui.each(node, function(index, item){
                if(start < 10 && index < 9){
                    var child = [
                        {
                            name: (1 + index + start).toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
                        }
                    ];
                    node[index].children = child;
                    createTree(child, index + start + 1);
                }
            });
            return node;
        };

        layui.tree({
            elem: '#demo2' //指定元素
            ,nodes: createTree()
        });

    });
</script>

</body>
</html>