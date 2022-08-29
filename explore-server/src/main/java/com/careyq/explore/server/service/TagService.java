package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.vo.TagVO;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取标签列表
     *
     * @return 标签列表
     */
    List<TagVO> getTags();
}
