<%-- 
    Document   : change_image
    Created on : Dec 9, 2019, 9:52:23 PM
    Author     : gao.xiaob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myAirPlayer - Update Photo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="alert alert-success text-center text-secondary w-50 mx-auto">Update Photo</h1>
        </div>
        <div class="container"  style="height:300px">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="p-4 bg-light">
                    <form action="/${initParam['appName']}/myDashboard/changeImage/submit.htm" method="post" enctype="multipart/form-data">  
                        <div class="form-group">
                            <label>Select Image(.jpg, .png): <input type="file" name="image" id="image" class="form-control"/></label>
                            <p id="rqd" class="text-danger" style="display:none">* Required</p>
                            <p id="notSpt" class="text-danger" style="display:none"></p>
                        </div>
                        <div class="clearfix">
                            <input type="submit" class="btn btn-warning float-right" value="Upload Image" onclick="return uploadImg()"/>  
                        </div>
                    </form> 
                </div>
            </div>
        </div>

        <div class="container-fluid my-5">
            <a class="ml-5" href="/${initParam['appName']}/myDashboard.htm">[Go back to myDashboard]</a>
        </div>
        <script>
            function uploadImg() {
                var image = document.getElementById("image");
                var rqd = document.getElementById("rqd");
                var notSpt = document.getElementById("notSpt");
                rqd.style.display = "none";
                notSpt.style.display = "none";

                if (image.value == "") {
                    rqd.style.display = "block";
                    return false;
                }

                var fullPath = document.getElementById("image").value;
                if (fullPath) {
                    var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                    var filename = fullPath.substring(startIndex);
                    if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                        filename = filename.substring(1);
                    }
                }

                if (filename.indexOf(".jpg") == -1 && filename.indexOf(".png") == -1) {
                    notSpt.innerHTML = "* " + filename + " is not (*.jpg, *.png)"
                    notSpt.style.display = "block";
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
