package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.AttachmentCategory;
import com.careyq.explore.server.vo.AttachmentCategoryVO;

import java.util.List;

/**
 * <p>
 * 文件分类表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
public interface AttachmentCategoryService extends IService<AttachmentCategory> {

    /**
     * 保存文件分类
     *
     * @param category 分类
     * @return 结果
     */
    Result<Boolean> saveAttachmentCategory(AttachmentCategory category);

    /**
     * 删除文件分类
     *
     * @param id 文件分类 ID
     * @return 结果
     */
    Result<Boolean> delAttachmentCategory(Long id);

    /**
     * 获取文件分类列表
     *
     * @return 分类列表
     */
    List<AttachmentCategoryVO> getAttachmentCategoryList();
}
