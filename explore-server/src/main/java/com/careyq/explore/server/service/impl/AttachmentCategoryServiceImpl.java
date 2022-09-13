package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.AttachmentCategory;
import com.careyq.explore.server.mapper.AttachmentCategoryMapper;
import com.careyq.explore.server.service.AttachmentCategoryService;
import com.careyq.explore.server.service.AttachmentService;
import com.careyq.explore.server.vo.AttachmentCategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文件分类表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Service
@AllArgsConstructor
public class AttachmentCategoryServiceImpl extends ServiceImpl<AttachmentCategoryMapper, AttachmentCategory> implements AttachmentCategoryService {

    private final AttachmentService attachmentService;

    @Override
    public Result<Boolean> saveAttachmentCategory(AttachmentCategory category) {
        this.saveOrUpdate(category);
        return Result.success("保存成功");
    }

    @Override
    public Result<Boolean> delAttachmentCategory(Long id) {
        if (attachmentService.isExistByCategory(id)) {
            return Result.fail("该分类下还存在文件");
        }
        this.removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public List<AttachmentCategoryVO> getAttachmentCategoryList() {
        return baseMapper.selectCategoryList();
    }
}
