<%-- 
    Document   : add_friend
    Created on : Nov 28, 2019, 2:55:13 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/images/${model.otherUser.profileImg}" var="profile_img" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Add Friend</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid mb-5">
            <h1 class="alert alert-success text-center text-secondary w-75 mx-auto">Add Friends to Your Contact</h1>
        </div>
        <div class="container">
            <div style="height:60px">
            </div>
            <form action="/${initParam['appName']}/myDashboard/addFriend/search.htm" method="get" class="needs-validation bg-light py-4 w-50 mx-auto" novalidate>
                <div class="text-center pb-4 text-muted font-weight-light"><h3>Search by</h3></div>
                <div class="form-group w-75 mx-auto">
                    <div class="form-check-inline">
                        <label class="form-check-label" for="radio1">
                            <input type="radio" class="form-check-input" id="radio1" name="search_by" value="name" onclick="option2Clicked(this)" checked>User Name
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label" for="radio2">
                            <input type="radio" class="form-check-input" id="radio2" name="search_by" value="email" onclick="option2Clicked(this)">User Email
                        </label>
                    </div>
                </div>
                <div class="form-group w-75 mx-auto">
                    <input type="text" class="form-control" name="search" id="search" placeholder="Enter an user name here" required/>
                    <div class="invalid-feedback">* Required</div>
                </div>
                <div class="text-right mr-4">
                    <button type="submit" class="btn btn-warning btn-sm">Search</button>
                </div>
            </form>
            <c:if test="${model.alreadyFriend==true}"> 
                <p class="alert alert-danger w-50 mx-auto mt-2">
                    * Friend is already on your contact.
                </p>
            </c:if>
            <c:if test="${model.notPrime==true}"> 
                <p class="alert alert-danger w-50 mx-auto mt-2">
                    * Friend request failed because the user is not a premium user.
                </p>
            </c:if>
            <c:if test="${model.friendRSent==true}"> 
                <p class="alert alert-success w-50 mx-auto mt-2">
                    * Friend request has been sent successfully.
                </p>
            </c:if>

        </div>
        <c:if test="${model.search==true}">
            <div class="container mt-2">
                <c:if test="${model.noResult==true}">
                    <p class="alert alert-danger alert-dismissible w-50 mx-auto">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <c:if test="${model.email!=null}">
                            * <strong><i>${model.email}</i></strong> doesn't exist.
                        </c:if>
                        <c:if test="${model.name!=null}">
                            * <strong><i>${model.name}</i></strong> doesn't exist.
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${model.noResult!=true}">
                    <div class="card mx-auto" style="width:500px; height:260px">
                        <div class="card-body d-flex justify-content-between">

                            <img class="w-50" src="${profile_img}" alt="Profile Image">
                            <div class="mt-5">
                                <h4 class="card-title">${model.otherUser.name}</h4>
                                <p class="card-text">${model.otherUser.email}</p>
                                <a href="/${initParam['appName']}/myDashboard/addFriend/search/sendFriendRequest.htm?user_id=${model.otherUser.id}" class="btn btn-primary">Request as Friend</a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:if>

        <div class="container-fluid my-5">
            <a class="ml-5" href="/${initParam['appName']}/myDashboard.htm">[Go back to myDashboard]</a>
        </div>

        <script>
            function option2Clicked(obj) {
                var search = document.getElementById("search");
                var invalidFeedback = document.getElementsByClassName("invalid-feedback")[0];
                if (obj.id == "radio2") {
                    search.type = "email";
                    search.placeholder = "Enter an email address here";
                    invalidFeedback.innerHTML = "* Invalid Email";
                } else {
                    search.placeholder = "Enter an user name here";
                    invalidFeedback.innerHTML = "* Required";
                }
            }

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
