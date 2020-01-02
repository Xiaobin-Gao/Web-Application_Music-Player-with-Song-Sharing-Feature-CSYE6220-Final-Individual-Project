<%-- 
    Document   : message
    Created on : Dec 7, 2019, 4:49:43 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Message</title>        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/e86bf94c1a.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-success text-center text-secondary w-50 mx-auto">
                Songs Sent from <strong>${fromUser}</strong>
            </h1>
        </div>
        <c:if test="${messCount==0}">
            <p class="alert alert-warning w-25 font-italic mx-auto text-center">
                * You have dealt with all the songs sent from <strong>${fromUser}.</strong>
            </p>
        </c:if> 

        <c:if test="${messCount>0}">
            <div class="card-columns mx-auto w-75 ">
                <c:forEach var="i" begin="0" end="${messCount-1}">
                    <div class="card my-4 bg-light mx-5" style="width:310px;height:570px;">
                        <img class="card-img-top" src="${songMess[i][0].imgUrl}" alt="Album image" style="width:100%">
                        <div class="card-body">
                            <h4 class="card-title">
                                ${songMess[i][0].name}
                            </h4>
                            <div class="card-text">
                                <%--<c:forEach var="j" begin="0" end="${messages[i].artistsCount-1}">--%>
                                <%-- ${messages[i].artists[j]}<br/>
                             </c:forEach>--%>
                                <div class="text-muted mb-3"><small>${songMess[i][0].durationMin} Min ${songMess[i][0].durationSec} Sec</small></div>
                            </div>

                            <c:if test="${songMess[i][0].previewUrl!=null}">
                                <div class="d-flex justify-content-end">
                                    <a href="${songMess[i][0].previewUrl}" class="btn btn-primary mb-2" target="_blank">Preview</a><br/>
                                </div>
                            </c:if>
                            <div class="d-flex justify-content-between">
                                <a href="/${initParam['appName']}/myDashboard/message/interested.htm?message_id=${songMess[i][1]}&from=${fromUser}&song_id=${songMess[i][0].id}" class="btn btn-outline-success">Add to Playlist</a>
                                <a href="/${initParam['appName']}/myDashboard/message/not_interested.htm?message_id=${songMess[i][1]}&from=${fromUser}&song_id=${songMess[i][0].id}" class="btn btn-outline-danger">Not Interested</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${songExisted!=null}">
            <p class="alert alert-danger w-25 font-italic mx-auto text-center">
                * You already have that song on your playlist.
            </p>
        </c:if>
        <c:if test="${songAdded!=null}">
            <p class="alert alert-success w-25 font-italic mx-auto text-center">
                * You just added the song to your playlist successfully.
            </p>
        </c:if>
        <div class="container-fluid my-5">
            <a class="ml-5" href="/${initParam['appName']}/myDashboard.htm">[Go back to myDashboard]</a>
        </div>
    </body>
</html>
