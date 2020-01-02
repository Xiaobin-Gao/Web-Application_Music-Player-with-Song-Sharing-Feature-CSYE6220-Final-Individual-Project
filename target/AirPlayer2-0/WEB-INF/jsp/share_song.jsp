<%-- 
    Document   : share
    Created on : Dec 7, 2019, 2:37:34 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Share a Song with Friends</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid mb-5">
            <h1 class="alert alert-info text-center text-secondary w-75 mx-auto">Share Song with Friends</h1>
        </div>
        <div class="container-fluid">
            <c:if test="${friendCount==null}">
                <p class="alert alert-warning">You have no friend on your friend list yet.</p>
                <a href="/${initParam['appName']}/myDashboard/addFriend.htm">[Add Your Friends]</a>
            </c:if>
            <c:if test="${friendCount>0}">
                <div class="w-25 mx-auto bg-light p-4" style="margin-top: 100px">
                <h4>Share with:</h4>
                <form action="/${initParam['appName']}/myDashboard/share/submit.htm?song_id=${songId}" method="post">
                    <div class="form-group text-center">
                        <c:forEach var="i" begin="0" end="${friendCount-1}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="friends" value="${friends[i].id}">
                                    ${friends[i].name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clearfix">
                    <button type="submit" class="btn btn-warning float-right">Confirm</button>
                    </div>
                </form>
                </div>
            </c:if>
        </div>
        <div class="ml-5 my-5">
            <a href="/${initParam['appName']}/myDashboard.htm">[Go to myDashboard]</a>
        </div>
    </body>
</html>
