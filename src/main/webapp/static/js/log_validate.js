$(document).ready(function () {
    $("#login_form").validate({
        rules:{
            userName:{
                space:true,
                isContainsSpecialChar:true,
                maxlength:64,
            },
            userPassword:{
                space:true,
                maxlength:10,
            },
        },
        messages:{
            userName:{
                space:"用户名不能包含空格或null",
                isContainsSpecialChar:"用户名不能包含特殊字符",
                maxlength:"用户名最大长度为64位",
            },
            userPassword:{
                space:"密码不能包含空格或null",
                maxlength:"密码最大长度为10位",
            },
        }
    })
})
