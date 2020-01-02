<%-- 
    Document   : login-error
    Created on : Oct 10, 2019, 9:38:15 AM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AirPlayer - Sign up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-info text-center text-dark w-50 mx-auto">Sign up</h1>
        </div>

        <div class="container d-flex flex-column align-items-center justify-content-center" style="height:610px">
            <c:if test="${errors['nameErr']==true}">
                <p class="alert alert-danger font-italic w-50">* User name already exists.</p>
            </c:if>
            <c:if test="${errors['emailErr']==true}">
                <p class="alert alert-danger font-italic w-50">* Email address has been signed up.</p>
            </c:if>
            <form action="/${initParam['appName']}/signup/submit.htm" method="post" 
                  class="needs-validation bg-light p-4 w-50" novalidate>
                <div class="form-group">
                    <label for="name">User Name:</label>
                    <input type="text" class="form-control" name="name" id="name" required/>
                    <div class="invalid-feedback">* Required</div>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" name="email" id="email" required/>
                    <div class="invalid-feedback">* Invalid Email</div>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" id="password" required/>
                    <div class="invalid-feedback">* Required</div>
                </div>
                <div class="form-group">
                    <label for="c_password">Confirm Password:</label>
                    <input type="password" class="form-control" name="c_password" id="c_password" required/>
                    <div class="invalid-feedback">* Inconsistent Password</div>
                </div>
                <div class="form-group">
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="gender" value="male" required>Male</label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="gender" value="female" required>Female</label>
                    </div>
                </div>
                <input type="submit" value="Sign up" class="btn btn-primary btn-sm float-right">

                <p class="text-muted small">Already have an account? <a href="/${initParam['appName']}/login.htm">Log in here</a>.</p>
            </form>
        </div>

        <div class="container-fluid my-5">
            <a class="ml-5" href="/${initParam['appName']}/home.htm">[Go back to AirPlayer]</a>
        </div>

        <script>
// Disable form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
// Get the forms we want to add validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    var pwd = document.getElementById("password");
                    var cPwd = document.getElementById("c_password");
// Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (pwd.value !== cPwd.value) {
                                cPwd.setCustomValidity("Password confirmation failed");
                            } else {
                                cPwd.setCustomValidity("");
                            }
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </body>
</html>
