package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.server.entity.Comment;
import com.careyq.explore.server.mapper.CommentMapper;
import com.careyq.explore.server.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论留言表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
