/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
@Controller
public class ChangeImageController extends AbstractController {

    private static final String UPLOAD_DIRECTORY = "/resources/images";

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains("changeImage.htm")) {
            return new ModelAndView("change_image");
        } else {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("image");
            String filename = file.getOriginalFilename();
            
            HttpSession session = request.getSession();
            ServletContext context = session.getServletContext();
            String path = context.getRealPath(UPLOAD_DIRECTORY);
            
            System.out.println(path + " " + filename);
            byte[] bytes = file.getBytes();
            int currentUserId = (int) session.getAttribute("currentUserId");
            filename = currentUserId + "-" + new Date() + "-" + filename;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(path + File.separator + filename)));
            stream.write(bytes);
            stream.flush();
            stream.close();
            UserDao.updateUserProfileImg(currentUserId, filename);

            RequestDispatcher rd =  request.getRequestDispatcher("/myDashboard.htm");
            rd.include(request, response);
            return null;
        }

    }

}
