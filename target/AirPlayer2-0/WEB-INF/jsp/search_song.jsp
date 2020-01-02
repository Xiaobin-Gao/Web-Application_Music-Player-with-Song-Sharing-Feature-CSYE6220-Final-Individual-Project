<%-- 
    Document   : result
    Created on : Nov 12, 2019, 9:06:01 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Search Songs</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-info text-center text-secondary w-75 mx-auto mb-5 py-4">Add Song to Your Playlist</h1>
            <br/>
            <div class="sticky-top mx-auto w-50">
                <form action="/${initParam['appName']}/myDashboard/search/result.htm" method="get">
                    <div class="input-group">
                        <input class="form-control" type="text" name="q" placeholder=""/>
                        <div class="input-group-append">
                            <input class="btn btn-warning" type="submit" value="Search"/>
                        </div>
                    </div>
                </form>
                <br/>
                <c:if test="${nullQ==true}">
                    <p class="text-danger font-italic">* Null</p>
                </c:if>
            </div>

            <c:if test="${result.q!=null}">
                <h5 class="text-dark font-weight-bold text-right pr-5 mr-4">Total Results <span class="badge badge-secondary">${result.total}</span></h5>
                <div class="card-columns mx-auto w-75 ">
                    <c:if test="${result.count>0}">
                        <c:forEach var="i" begin="0" end="${result.count-1}">
                            <div class="card my-4 bg-light mx-5" style="width:300px;height:530px;">
                                <img class="card-img-top" src="${result.tracks[i]["img"]}" alt="Album image" style="width:100%">
                                <div class="card-body">
                                    <h4 class="card-title">
                                        ${result.tracks[i]["name"]}
                                    </h4>
                                    <div class="card-text">
                                        <c:forEach var="j" begin="0" end="${result.tracks[i].artistsCount-1}">
                                            ${result.tracks[i].artists[j]}<br/>
                                        </c:forEach>
                                        <div class="text-muted mb-3"><small>${result.tracks[i]['durationMin']} Min ${result.tracks[i]['durationSec']} Sec</small></div>
                                    </div>
                                    <c:if test="${result.tracks[i]['isPlayable']==true}">
                                        <a href="../add.htm?q=${result.q}&song_id=${result.tracks[i]['track_id']}" class="btn btn-primary">Add to Playlist</a>
                                    </c:if>
                                    <c:if test="${result.tracks[i]['isPlayable']==false}">
                                        <button class="btn btn-secondary" disabled>Add to Playlist</button>
                                    </c:if>
                                    <c:if test="${result.tracks[i]['previewUrl']!=null}">
                                        <a href="${result.tracks[i]['previewUrl']}" class="btn btn-primary" target="_blank">Preview</a>
                                    </c:if>
                                    <c:if test="${result.tracks[i]['previewUrl']==null}">
                                        <button class="btn btn-secondary" disabled>Preview</button>
                                    </c:if>
                                </div>

                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <br/>

                <form class=" mr-5 mb-5" action="result.htm" method="get">
                    <input type="hidden" name="q" value="${result.q}"/>
                    <select class="form-control float-right ml-2" name="items_per_page" style="width:45px">
                        <c:if test="${result.items_per_page==10}">
                            <option value="10" selected>10</option>
                        </c:if>
                        <c:if test="${result.items_per_page!=10}">
                            <option value="10">10</option>
                        </c:if>
                        <c:if test="${result.items_per_page==20}">
                            <option value="20" selected>20</option>
                        </c:if>
                        <c:if test="${result.items_per_page!=20}">
                            <option value="20">20</option>
                        </c:if>
                        <c:if test="${result.items_per_page==30}">
                            <option value="30" selected>30</option>
                        </c:if>
                        <c:if test="${result.items_per_page!=30}">
                            <option value="30">30</option>
                        </c:if>
                    </select>
                    <ul class="pagination pg-blue justify-content-end">
                        <c:if test="${result.page_no==1}">
                            <li class="page-item disabled">
                                <a class="page-link" tabindex="-1">Previous</a>
                            </li>
                        </c:if>
                        <c:if test="${result.page_no!=1}">
                            <li class="page-item">
                                <button type="submit" class="page-link" name="page_no" value="${result.page_no-1}">Previous</button>
                            </li>
                        </c:if>
                        <c:if test="${result.pageTotal<=7}">
                            <c:forEach var="i" begin="1" end="${result.pageTotal}">
                                <c:if test="${i==result.page_no}">
                                    <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                    <c:if test="${i!=result.page_no}">
                                    <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${result.page_no<7&&result.pageTotal>7}">
                                <c:forEach var="i" begin="1" end="7">
                                    <c:if test="${i==result.page_no}">
                                    <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                    <c:if test="${i!=result.page_no}">
                                    <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${result.pageTotal>8}">
                                <li class="page-item"><button type="button" class="page-link">...</button></li>
                                </c:if>
                                <c:if test="${result.page_no==result.pageTotal}">
                                <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${result.pageTotal}">${result.pageTotal}</button></li>
                                </c:if>
                                <c:if test="${result.page_no!=result.pageTotal}">
                                <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${result.pageTotal}">${result.pageTotal}</button></li>
                                </c:if>
                            </c:if>

                        <c:if test="${result.page_no>=7&&result.page_no+1<result.pageTotal-1}">
                            <c:forEach var="i" begin="${result.page_no-5}" end="${result.page_no+1}">
                                <c:if test="${i==result.page_no}">
                                    <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                    <c:if test="${i!=result.page_no}">
                                    <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${result.pageTotal>result.page_no+2}">
                                <li class="page-item"><button type="button" class="page-link">...</button></li>
                                </c:if>
                                <c:if test="${result.page_no==result.pageTotal}">
                                <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${result.pageTotal}">${result.pageTotal}</button></li>
                                </c:if>
                                <c:if test="${result.page_no!=result.pageTotal}">
                                <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${result.pageTotal}">${result.pageTotal}</button></li>
                                </c:if>
                            </c:if>
                            <c:if test="${result.page_no>=7&&result.page_no+1>=result.pageTotal-1}">
                                <c:forEach var="i" begin="${result.pageTotal-6}" end="${result.pageTotal}">
                                    <c:if test="${i==result.page_no}">
                                    <li class="page-item active"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                    <c:if test="${i!=result.page_no}">
                                    <li class="page-item"><button type="submit" class="page-link" name="page_no" value="${i}">${i}</button></li>
                                    </c:if>
                                </c:forEach>

                        </c:if>

                        <c:if test="${result.page_no==result.pageTotal}">
                            <li class="page-item disabled">
                                <a class="page-link" tabindex="-1">Next</a>
                            </li>
                        </c:if>
                        <c:if test="${result.page_no!=result.pageTotal}">
                            <li class="page-item">
                                <button type="submit" class="page-link" name="page_no" value="${result.page_no + 1}">Next</button>
                            </li>
                        </c:if>

                    </ul> 
                </form>

            </c:if>
            <div class="ml-5 my-5">
                <a href="/${initParam['appName']}/myDashboard.htm">[Go to myDashboard]</a>
            </div>
        </div>
    </body>
</html>
