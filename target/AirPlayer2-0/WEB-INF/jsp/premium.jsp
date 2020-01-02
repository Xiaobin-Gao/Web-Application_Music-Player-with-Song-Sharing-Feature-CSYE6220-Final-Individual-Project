<%-- 
    Document   : premium
    Created on : Dec 7, 2019, 8:56:58 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AirPlayer - Go Premium</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-info text-center text-dark w-50 mx-auto">Try Premium Free</h1>
        </div>
        <div class="container w-50 mt-5 d-flex flex-column justify-content-center align-items-center" style="height:300px">

            <c:if test="${noSuchEmail==true}"><p class="alert alert-danger text-center w-50 mx-auto">* <strong><i>${email}</i></strong> doesn't exist.</p></c:if>
            <c:if test="${alreadyPrime==true}"><p class="alert alert-danger text-center w-50 mx-auto">* <strong><i>${email}</i></strong> is already a prime account.</p></c:if>

            <c:if test="${successful==true}"><p class="alert alert-success w-50 mx-auto"><strong><i>${email}</i></strong> has been updated as a prime account successfully.</p></c:if>
            <form class="needs-validation p-4 w-50 bg-light d-inline-block" action="/${initParam['appName']}/premium/submit.htm" method="post" novalidate>
                <div class="form-group">
                    <label for="email">Enter Your Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required/>
                    <div class="invalid-feedback">* Invalid Email</div>
                </div>
                <div class="form-group clearfix">
                    <button type="submit" class="btn btn-warning float-right">Go Premium</button>
                </div>
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
