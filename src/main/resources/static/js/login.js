/**
 * Created by NCP-620 on 2017/8/18.
 */


$(function () {

    var TOKEN_KEY = "jwtToken"

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }
    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    $("#loginForm").submit(function (event) {
        event.preventDefault();
        var $form = $(this);
        var formData = {
            username: $('#InputUsername').val(),
            password: $('#InputPassword').val()
        };
        doLogin(formData);
    });

    $("#register").click(function () {
        location.href="/register"
    });

    function doLogin(loginData) {
        $.ajax({
            url:"/login",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data,textStatus,jqXHR) {
                setJwtToken(data.token);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }
});