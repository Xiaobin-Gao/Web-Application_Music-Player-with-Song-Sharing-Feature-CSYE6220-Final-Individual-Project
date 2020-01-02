<%-- 
    Document   : index
    Created on : Dec 12, 2019, 8:47:55 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/images/ny.jpg" var="bg_img" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AirPlayer - Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>

    <body data-spy="scroll" data-target=".navbar" data-offset="50">

        <nav class="navbar navbar-expand bg-dark navbar-dark fixed-top py-4">
            <!-- Brand/logo -->
            <a class="navbar-brand  ml-5" href="#">
                Logo
            </a>

            <!-- Links -->
            <ul class="navbar-nav ml-auto mr-4">
                <li class="nav-item">
                    <a class="nav-link" href="#premium">Premium</a>
                </li>
                <li class="nav-item mx-3">
                    <a class="nav-link disabled" href="#" >|</a>
                </li>
                <li class="nav-item mr-1">
                    <a class="nav-link" href="login.htm">Log in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.htm">Sign up</a>
                </li>
            </ul>
        </nav>
        <div class="container-fluid text-center bg-light" style="padding-top: 400px; padding-bottom: 350px">
            <h1>Welcome to <strong><i>AirPlayer</i></strong></h1>
        </div>

        <div class="container my-5" id="premium">
            <hr/>
            <h3 class="text-center mt-5">Try Premium Free</h3>
            <div style="height:100px"></div>
            <a href="/${initParam['appName']}/premium.htm" class="btn btn-success btn-block w-25 mx-auto my-5">Get Started</a>
        </div>

    </body>
</html>
