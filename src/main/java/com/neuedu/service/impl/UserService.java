package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.JsonResponse;
import com.neuedu.dao.NoteMapper;
import com.neuedu.dao.UserMapper;
import com.neuedu.entity.NoteVo;
import com.neuedu.entity.UserVo;
import com.neuedu.service.inter.IUserService;
import com.neuedu.util.MD5Utils;
import com.neuedu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoteMapper noteMapper;

    /**
     * 用户登录
     * */
    public JsonResponse do_login(UserVo userVo, HttpServletRequest request) throws UnsupportedEncodingException {
        JsonResponse jsonResponse = new JsonResponse();
        userVo.setUserName(URLDecoder.decode(userVo.getUserName(),"utf-8"));
        userVo.setUserPassword(MD5Utils.GetMD5Code(userVo.getUserPassword()));
        UserVo user = userMapper.userLogin(userVo);
        if (user != null){
            //将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute(Const.USERSESSION,user);
            jsonResponse.setStatus("1");
            jsonResponse.setMsg("登录成功!");
            jsonResponse.setData(user);
        }else {
            jsonResponse.setStatus("31");
            jsonResponse.setMsg("用户不存在!");
        }
        return jsonResponse;
    }

    /**
     * 用户注册
     * */
    public JsonResponse  do_register(UserVo userVo) {
        JsonResponse jsonResponse = new JsonResponse();
        UserVo user = new UserVo();
        //姓名解码
        try {
            //用户名解码
            userVo.setUserName(URLDecoder.decode(userVo.getUserName(),"utf-8"));
            user = userMapper.queryUserByName(userVo.getUserName());
            if (user != null){
                jsonResponse.setMsg("该用户已注册");
                jsonResponse.setStatus("31");
                return jsonResponse;
            }
            user = userMapper.queryByUserEmail(userVo.getUserEmail());
            if (user != null){
                jsonResponse.setMsg("该邮箱已注册");
                jsonResponse.setStatus("31");
                return jsonResponse;
            }
            user = userMapper.queryByUserPhone(userVo.getUserPhone());
            if (user != null){
                jsonResponse.setMsg("该手机号码已注册");
                jsonResponse.setStatus("31");
                return jsonResponse;
            }
            //密码MD5加密
            userVo.setUserPassword(MD5Utils.GetMD5Code(userVo.getUserPassword()));
            String userid = UUID.randomUUID().toString().replaceAll("-","");
            userVo.setUserId(userid);
            userMapper.insertUser(userVo);
            //创建用户笔记根目录
            String path = Const.FILEPATH+ File.separator+userid+File.separator+"我的文件夹";
            NoteVo root = new NoteVo();
            root.setUserId(userid);
            root.setNoteId("001000000000000000");
            root.setNoteStatus("1");
            root.setNoteType("2");
            root.setNotePath(path);
            root.setNoteCreateTime(StringUtil.getNow());
            root.setNoteModifyTime(StringUtil.getNow());
            root.setNoteName("我的文件夹");
            root.setNoteRoot("1");
            noteMapper.createNote(root);
            //创建文件
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            jsonResponse.setStatus("1");
            jsonResponse.setMsg("注册成功!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
