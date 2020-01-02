<%-- 
    Document   : dashboard
    Created on : Nov 12, 2019, 8:55:21 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/images/${model.currentUser.profileImg}" var="profile_img" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AirPlayer - myDashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/e86bf94c1a.js" crossorigin="anonymous"></script>
    </head>
    <body style="height:1500px">      
        <div class="jumbotron">
            <h1 class="text-center">Hello ${model.currentUser.name}!</h1>
        </div>
        <div class="container-fluid d-flex justify-content-between">

            <div style="margin-left:100px"> 
                <div class="clearfix pt-4 pb-3 border">
                    <h2 class="float-left ml-3">Your Playlist</h2>
                    <a class="btn btn-success float-right mr-2 mt-2" href="/${initParam['appName']}/myDashboard/search.htm">Add More Songs to Playlist</a>
                </div>
                <c:if test="${model.songCount==0}">
                    <div class="alert alert-warning mt-2" style="width:550px">
                        No Song in Your Playlist Yet.
                    </div> 
                </c:if>
                <c:if test="${model.songCount!=0}">
                    <div id="accordion" style="width:550px">
                        <c:forEach var="i" begin="0" end="${(model.songCount-1)/2}">
                            <div class="card">
                                <div class="card-header" style="height:65px">
                                    <a class="card-link" data-toggle="collapse" href="#collapse${i}">
                                        ${model.songs[i].name} 
                                        <span class="float-right">
                                            <span class="text-muted small">
                                                <c:forEach var="j" begin="0" end="${model.songs[i].artistCount-1}">
                                                    ${model.songs[i].artists[j]}&nbsp;
                                                    <c:if test="${j==model.songs[i].artistCount-1}">|&nbsp;</c:if>
                                                </c:forEach>
                                            </span>
                                            <span class="text-muted small font-italic">${model.songs[i].durationMin} min ${model.songs[i].durationSec} sec</span>
                                        </span>
                                    </a>

                                </div>
                                <div id="collapse${i}" class="collapse <c:if test="${i==model.no}">show</c:if>" data-parent="#accordion">
                                        <div class="card-body">
                                            <img class="float-left w-50 mb-4" src="${model.songs[i].imgUrl}"/>


                                        <c:if test='${model.songs[i].uri==model.currentSongUri}'>
                                            <c:if test="${model.playMode=='pause'}">
                                                <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=${model.playMode}" class="btn btn-outline-primary my-2 mx-5">Pause</a><br/>
                                            </c:if>
                                            <c:if test="${model.playMode!='pause'}">
                                                <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=${model.playMode}" class="btn btn-outline-primary my-2 mx-5">Play</a><br/>
                                            </c:if>

                                        </c:if>
                                        <c:if test='${model.songs[i].uri!=model.currentSongUri}'>
                                            <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=start" class="btn btn-outline-primary my-2 mx-5">Play</a><br/>
                                        </c:if>
                                        <a href="/${initParam['appName']}/myDashboard/share.htm?song_id=${model.songs[i].songId}" class="border border-primary text-primary btn btn-outline-success my-2 mx-5">Share</a><br/>
                                        <a href="/${initParam['appName']}/myDashboard/delete.htm?song_id=${model.songs[i].songId}" class="border border-primary text-primary btn btn-outline-danger my-2 mx-5">Remove</a><br/>
                                        <c:if test="${model.songs[i].uri==model.currentSongUri && model.playMode=='pause'}">
                                            <div class="block" style="visibility:visible">
                                                <div class="mt-5 mr-3 text-right">       
                                                    <div class="spinner-grow text-primary"></div>
                                                    <div class="spinner-grow text-muted"></div>
                                                    <div class="spinner-grow text-success"></div>
                                                </div>
                                            </div>

                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                </c:if>  
            </div>

            <div class="ml-3 mr-auto" >
                <div style="height:88px;">
                </div>
                <c:if test="${model.songCount>1}">
                    <div id="accordion2" style="width:550px">
                        <c:forEach var="i" begin="${(model.songCount-1)/2+1}" end="${model.songCount-1}">
                            <div class="card">
                                <div class="card-header" style="height:65px">
                                    <a class="card-link" data-toggle="collapse" href="#collapse${i}">
                                        ${model.songs[i].name} 
                                        <span class="float-right">
                                            <span class="text-muted small">
                                                <c:forEach var="j" begin="0" end="${model.songs[i].artistCount-1}">
                                                    ${model.songs[i].artists[j]}&nbsp;
                                                    <c:if test="${j==model.songs[i].artistCount-1}">|&nbsp;</c:if>
                                                </c:forEach>
                                            </span>
                                            <span class="text-muted small font-italic">${model.songs[i].durationMin} min ${model.songs[i].durationSec} sec</span>
                                        </span>
                                    </a>

                                </div>
                                <div id="collapse${i}" class="collapse <c:if test="${i==model.no}">show</c:if>" data-parent="#accordion2">
                                        <div class="card-body">
                                            <img class="float-left w-50 mb-4" src="${model.songs[i].imgUrl}"/>


                                        <c:if test='${model.songs[i].uri==model.currentSongUri}'>
                                            <c:if test="${model.playMode=='pause'}">
                                                <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=${model.playMode}" class="btn btn-outline-primary my-2 mx-5">Pause</a><br/>
                                            </c:if>
                                            <c:if test="${model.playMode!='pause'}">
                                                <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=${model.playMode}" class="btn btn-outline-primary my-2 mx-5">Play</a><br/>
                                            </c:if>

                                        </c:if>
                                        <c:if test='${model.songs[i].uri!=model.currentSongUri}'>
                                            <a href="/${initParam['appName']}/myDashboard/play.htm?no=${i}&song_uri=${model.songs[i].uri}&mode=start" class="btn btn-outline-primary my-2 mx-5">Play</a><br/>
                                        </c:if>
                                        <c:if test="${model.currentUser.prime==true}">
                                            <a href="/${initParam['appName']}/myDashboard/share.htm?song_id=${model.songs[i].songId}" class="border border-primary text-primary btn btn-outline-success my-2 mx-5">Share</a><br/>
                                        </c:if>
                                        <a href="/${initParam['appName']}/myDashboard/delete.htm?song_id=${model.songs[i].songId}" class="border border-primary text-primary btn btn-outline-danger my-2 mx-5">Remove</a><br/>
                                        <c:if test="${model.songs[i].uri==model.currentSongUri && model.playMode=='pause'}">
                                            <div class="block" style="visibility:visible">
                                                <div class="mt-5 mr-3 text-right">       
                                                    <div class="spinner-grow text-primary"></div>
                                                    <div class="spinner-grow text-muted"></div>
                                                    <div class="spinner-grow text-success"></div>
                                                </div>
                                            </div>

                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                </c:if>  
            </div>

            <div class="card mr-3" style="width:280px; height:500px">
                <img class="card-img-top" src="${profile_img}" alt="Profile Image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">${model.currentUser.name}</h4>
                    <p class="card-text">${model.currentUser.email}</p>
                    <a href="#" class="btn btn-secondary disabled">Connect to Audio Device</a>
                    <a href="/${initParam['appName']}/myDashboard/changeImage.htm" class="btn btn-primary float-left my-2 mr-1">Update Photo</a>
                    <a href="/${initParam['appName']}/myDashboard/signout.htm" class="btn btn-primary float-right my-2 mr-1">Sign out</a>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <c:if test="${model.currentUser.prime==true}">
                <div id="accordion_friend" class="fixed-bottom mr-4 ml-auto" style="width:430px">
                    <div class="card">
                        <div class="card-header">
                            <a class="card-link ml-1" data-toggle="collapse" href="#collapseOne">
                                Your Friend List
                                <c:if test="${model.songMessCount>0}">
                                    <span class="badge badge-danger ml-1">${model.songMessCount}</span>
                                </c:if>
                            </a>
                            <c:if test="${model.friendRqsCount>0}">
                                <a href="#friendRequest" class="btn btn-warning btn-sm float-right" data-toggle="collapse">
                                    New <span class="badge badge-light">${model.friendRqsCount}</span>
                                </a>
                                <div id="friendRequest" class="collapse">
                                    <hr/>
                                    <div class="small font-italic">
                                        <c:forEach var="i" begin="0" end="${model.friendRqsCount-1}">
                                            <div  class="my-2 clearfix">
                                                <span class="float-left ml-4 my-1"><strong>${model.fromUsers[i].name}</strong>(${model.fromUsers[i].email})</span>
                                                <span class="float-right mr-1">
                                                    <a href="/${initParam['appName']}/myDashboard/friendRequest/accept.htm?user_name=${model.fromUsers[i].name}" class="btn btn-outline-success btn-sm">Accept</a>
                                                    <a href="/${initParam['appName']}/myDashboard/friendRequest/refuse.htm?user_name=${model.fromUsers[i].name}" class="btn btn-outline-danger btn-sm">Refuse</a>
                                                </span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div id="collapseOne" class="collapse" data-parent="#accordion_friend">
                            <div class="card-body text-center"> 
                                <a href="/${initParam['appName']}/myDashboard/addFriend.htm"><h1 class="fas fa-user-plus"></h1></a>
                                    <c:if test="${model.friendCount>0}">
                                    <ul class="list-group list-group-flush">
                                        <c:forEach var="i" begin="0" end="${model.friendCount-1}">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                ${model.friends[i].name}
                                                <c:if test="${model.songMess[model.friends[i].id]!=null}">
                                                    <a href="/${initParam['appName']}/myDashboard/message.htm?from=${model.friends[i].name}"><span class="badge badge-primary badge-pill">${model.songMess[model.friends[i].id]}</span></a>
                                                    </c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${model.friends==null}">
                                    <p class="alert alert-warning mt-2">No friend in your friend list yet.</p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
