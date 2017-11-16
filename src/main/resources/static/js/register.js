/**
 * Created by NCP-620 on 2017/11/16.
 */
$(function () {
    function validPwd(pwd,rePwd) {
        return pwd===rePwd;
    }

    function doRegister(registerForm) {

    }

    $("#registerForm").submit(function (event) {
        event.preventDefault();
        var formData = {
            username: $('#username').val(),
            password: $('#password').val(),
            password1: $('#password1').val(),
            email: $('#email').val(),
            phoneNum: $('#phonenum').val()
        };
        if(validPwd(formData.password,formData.password1)){
            doRegister(formData);
        }else{

        }
    });
})