/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.Message;
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.SongDao;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class MessageController extends AbstractController {

    public MessageController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");
        String fromUserName = request.getParameter("from");
        User fromUser = UserDao.fetehUserByNV("name", fromUserName);
        List<Message> ms = UserDao.fetchUserMessages(currentUserId);
        List songContainer = new ArrayList();
        for (Message m : ms) {
            if (m.getFromUserId()==fromUser.getId() && m.getmType().equals("SONG")) {
                List songAndMessId = new ArrayList();
                songAndMessId.add(SongDao.fetchSongById(m.getSongContent()));               
                songAndMessId.add(m.getId());
                songContainer.add(songAndMessId);
            }
        }
        ModelAndView mv = new ModelAndView("message");
        String songExisted = request.getParameter("songExisted");
        String songAdded = request.getParameter("songAdded");
        if (songExisted != null) {
            mv.addObject("songExisted", songExisted);
        }
        if (songAdded != null) {
            mv.addObject("songAdded", songAdded);
        }
        mv.addObject("songMess", songContainer);
        mv.addObject("messCount", songContainer.size());
        mv.addObject("fromUser", fromUserName);
        return mv;
    }

}
