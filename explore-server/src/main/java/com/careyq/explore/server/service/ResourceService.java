package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.ResourcePageDTO;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Resource;
import com.careyq.explore.server.vo.ResourcePageVO;
import com.careyq.explore.server.vo.ResourceVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 上传文件
     *
     * @param file       文件
     * @param categoryId 文件分类
     * @param pathEnum   FilePathEnum
     * @return 结果
     */
    boolean uploadFile(MultipartFile file, Long categoryId, FilePathEnum pathEnum);

    /**
     * 指定分类下是否存在附件
     *
     * @param categoryId 分类 id
     * @return 是否存在
     */
    boolean isExistByCategory(Long categoryId);

    /**
     * 附件分页
     *
     * @param dto 分页筛选参数
     * @return 分页结果
     */
    IPage<ResourcePageVO> getPage(ResourcePageDTO dto);

    /**
     * 获取附件详情
     *
     * @param id 附件 ID
     * @return 详情
     */
    ResourceVO getDetail(Long id);

    /**
     * 批量操作附件
     *
     * @param dto 参数
     * @return 操作结果
     */
    Result<Boolean> batchOperate(AttachmentBatchOperateDTO dto);
}
