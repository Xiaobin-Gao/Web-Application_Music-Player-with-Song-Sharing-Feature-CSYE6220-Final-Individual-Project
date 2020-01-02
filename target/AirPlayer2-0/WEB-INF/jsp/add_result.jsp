<%-- 
    Document   : add_result
    Created on : Nov 21, 2019, 1:37:52 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Add Song Submit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:if test="${noMoreSong==true}">
            <p class="alert alert-danger w-25 my-5 mx-auto text-center"><br/>
                * You have reached 20 song limit as a non-prime user.<br/><br/>
                <a href="/${initParam['appName']}/myDashboard/search.htm" class="my-5">[Go to Song Search]</a>&nbsp;&nbsp;
                <a href="/${initParam['appName']}/myDashboard.htm">[Go to myDashboard]</a>
            </p>
        </c:if>
        <c:if test="${songInPlaylist==true}">
            <p class="alert alert-warning w-25 my-5 mx-auto text-center"><br/>
                * That song is already in your playlist.<br/><br/>
                <a href="/${initParam['appName']}/myDashboard/search.htm" class="my-5">[Go to Song Search]</a>&nbsp;&nbsp;
                <a href="/${initParam['appName']}/myDashboard.htm">[Go to myDashboard]</a>
            </p>
        </c:if>
        <c:if test="${songInPlaylist==false}">
            <p class="alert alert-success w-25 my-5 mx-auto text-center"><br/>
                * You just added a new song to your playlist successfully.<br/><br/>
                <a href="/${initParam['appName']}/myDashboard/search.htm" class="my-5">[Go to Song Search]</a>&nbsp;&nbsp;
                <a href="/${initParam['appName']}/myDashboard.htm">[Go to myDashboard]</a>
            </p>
        </c:if>
    </body>
</html>
