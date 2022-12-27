package com.careyq.explore.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.explore.server.dto.AttachmentPageDTO;
import com.careyq.explore.server.entity.Resource;
import com.careyq.explore.server.vo.ResourceVO;
import com.careyq.explore.server.vo.ResourcePageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 资源表 Mapper
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询指定分类下的文件是否存在
     *
     * @param categoryId 分类 ID
     * @return 结果
     */
    @Select("select 1 from attachment where is_del = 0 and category_id = #{categoryId} limit 1;")
    Integer selectExistByCategory(@Param("categoryId") Long categoryId);

    /**
     * 查询附件分页
     *
     * @param page 分页
     * @param dto  参数
     * @return 分页结果
     */
    IPage<ResourcePageVO> selectAttachmentPage(Page<ResourcePageVO> page, @Param("dto") AttachmentPageDTO dto);

    /**
     * 根据 ID 查询
     *
     * @param id 附件 ID
     * @return 结果
     */
    @Select("select id, name, type, path, size, height, width, create_time from attachment where id = #{id}")
    ResourceVO selectAttachment(@Param("id") Long id);
}