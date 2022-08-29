package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.mapper.TagMapper;
import com.careyq.explore.server.service.TagService;
import com.careyq.explore.server.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<TagVO> getTags() {
        return this.lambdaQuery().list()
                .stream()
                .map(t -> new TagVO(t.getId(), t.getName(), t.getAlias()))
                .toList();
    }
}
