package com.careyq.explore.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.service.ArticleService;
import com.careyq.explore.server.vo.ArticlePageVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 保存文章
     *
     * @param dto 提交参数
     * @return 操作结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveArticle(@RequestBody @Validated ArticleDTO dto) {
        return articleService.saveArticle(dto);
    }

    /**
     * 文章分页
     *
     * @param dto 分页筛选参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<IPage<ArticlePageVO>> getArticlePage(@RequestBody ArticlePageDTO dto) {
        return Result.success(articleService.getArticlePage(dto));
    }
}

