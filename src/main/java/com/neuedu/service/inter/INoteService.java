package com.neuedu.service.inter;

import com.neuedu.common.JsonResponse;
import com.neuedu.entity.NoteFo;
import com.neuedu.entity.NoteVo;
import com.neuedu.entity.UserVo;

import java.io.IOException;

public interface INoteService {
    JsonResponse getRootKids(String userId);

    /**
     * 新建
     * @param fo
     * @return
     * @throws IOException
     */
    JsonResponse createNote(NoteFo fo) throws IOException;

    JsonResponse getNotes(String userid);

    JsonResponse getContent(String userid);

    JsonResponse reName(NoteFo fo);

    JsonResponse getTree(NoteFo fo);

    /**
     * 点击文档展示内容
     * @param fo
     * @return
     */
    JsonResponse getNote(NoteFo fo);

    /**
     * 删除文件
     * @param fo
     */
    JsonResponse invalid(NoteFo fo);

    /**
     * 回收站
     * @param userid
     * @return
     */
    JsonResponse recycleBin(String userid);

    /**
     * 笔记存储
     * @param fo
     * @return
     */
    JsonResponse save(NoteFo fo);
}
