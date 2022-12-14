package com.careyq.explore.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.annotation.ArticlePublishValid;
import com.careyq.explore.server.dto.ArticleDTO;
import com.careyq.explore.server.dto.ArticlePageDTO;
import com.careyq.explore.server.service.BlogPostService;
import com.careyq.explore.server.vo.ArticlePageVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogPostService blogPostService;

    /**
     * 保存文章
     *
     * @param dto 提交参数
     * @return 文章 ID
     */
    @PostMapping("/article/save")
    public Result<Long> saveArticle(@RequestBody @Validated ArticleDTO dto) {
        return Result.success(blogPostService.saveArticle(dto), "保存成功");
    }

    /**
     * 文章分页
     *
     * @param dto 分页筛选参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<IPage<ArticlePageVO>> getArticlePage(@RequestBody ArticlePageDTO dto) {
        return Result.success(blogPostService.getArticlePage(dto));
    }

    /**
     * 获取文章详情
     *
     * @param id 文章 ID
     * @return 文章
     */
    @GetMapping("/detail")
    public Result<ArticleDTO> getArticle(@RequestParam Long id) {
        return blogPostService.getArticle(id);
    }

    /**
     * 删除文章
     *
     * @param id 文章 ID
     * @return 操作结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> delArticle(@RequestParam Long id) {
        return blogPostService.delArticle(id);
    }

    /**
     * 发布文章
     *
     * @param dto 文章参数
     * @return 操作结果
     */
    @PostMapping("/publish")
    public Result<Boolean> publishArticle(@RequestBody @Validated(ArticlePublishValid.class) ArticleDTO dto) {
        return blogPostService.publishArticle(dto);
    }
}

