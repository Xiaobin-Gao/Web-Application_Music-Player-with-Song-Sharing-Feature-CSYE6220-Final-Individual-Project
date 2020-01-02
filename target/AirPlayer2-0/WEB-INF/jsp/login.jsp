<%-- 
    Document   : login
    Created on : Oct 10, 2019, 9:32:20 AM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AirPlayer - Log in</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-info text-center text-dark w-50 mx-auto">Log in</h1>
        </div>
        <div class="container d-flex flex-column justify-content-center align-items-center" style="height:400px">
            <c:if test="${error==1}">
                <p class="alert alert-danger font-italic w-50">* Invalid email or invalid password.</p>
            </c:if>
            <form action="myDashboard.htm" method="post"
                  class="needs-validation bg-light w-50 p-4" novalidate>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" name="email" id="email" required/>
                    <div class="invalid-feedback">* Invalid Email</div>
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" name="password" class="form-control" id="pwd" required/>
                    <div class="invalid-feedback">* Required</div>
                </div>
                <input type="submit" value="Log in" class="btn btn-primary btn-sm float-right">
                <p class="text-muted small">Don't have an account yet? <a href="signup.htm">Sign up here</a>.</p>
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
// Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
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
