package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.AttachmentPageDTO;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Attachment;
import com.careyq.explore.server.vo.AttachmentPageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface AttachmentService extends IService<Attachment> {

    /**
     * 上传文件
     *
     * @param file     文件
     * @param pathEnum FilePathEnum
     * @return 结果
     */
    boolean uploadFile(MultipartFile file, FilePathEnum pathEnum);

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
    IPage<AttachmentPageVO> getAttachmentPage(AttachmentPageDTO dto);

    /**
     * 获取附件详情
     *
     * @param id 附件 ID
     * @return 详情
     */
    AttachmentVO getAttachment(Long id);

    /**
     * 批量操作附件
     *
     * @param dto 参数
     * @return 操作结果
     */
    Result<Boolean> batchOperate(AttachmentBatchOperateDTO dto);
}
