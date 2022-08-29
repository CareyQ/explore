package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;

/**
 * <p>
 * 可公共使用 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface CommonService<T> extends IService<T> {

    /**
     * 保存实体
     *
     * @param entity 实体
     * @return 结果
     */
    Result<Boolean> saveEntity(T entity);
}
