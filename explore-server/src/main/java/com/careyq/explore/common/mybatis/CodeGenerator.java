package com.careyq.explore.common.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.careyq.explore.common.entity.BaseModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String author = input("作者名");
        String projectPath = System.getProperty("user.dir");
        String moduleName = input("模块名");
        String tableNames = input("表名，多个英文逗号分隔；所有输入 all");

        FastAutoGenerator.create("jdbc:mysql://xxxxxx:3306/explore", "xxxx", "xxxxx")
                .globalConfig(builder -> builder
                        .author(author)
                        .commentDate("yyyy-MM-dd")
                        .outputDir(projectPath + "/explore/src/main/java/")
                )
                .packageConfig(builder -> builder
                        .parent("com.careyq.explore")
                        .moduleName(moduleName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/explore/src/main/resources" +
                                "/mapper/" + moduleName)))
                .templateConfig(builder -> builder
                        .entity("/template/entity.java.vm")
                        .service("/template/service.java.vm")
                        .serviceImpl("/template/serviceImpl.java.vm")
                        .mapper("/template/mapper.java.vm")
                        .xml("/template/mapper.xml.vm")
                        .controller("/template/controller.java.vm"))
                .strategyConfig(builder -> builder
                        .addInclude(getTables(tableNames))
                        .enableSkipView()
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .superClass(BaseModel.class)
                        .addSuperEntityColumns("id", "isDel", "createTime", "updateTime")
                        .enableLombok()
                        .enableActiveRecord()
                        .enableChainModel()
                        .idType(IdType.AUTO)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .naming(NamingStrategy.underline_to_camel)
                        .logicDeleteColumnName("is_del")
                        .serviceBuilder()
                        .convertServiceFileName(entityName -> entityName + ConstVal.SERVICE)
                ).execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String input(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (StringUtils.isNotBlank(line)) {
                return line;
            }
        }
        return "";
    }

    /**
     * <p>
     * 处理 all 情况
     * </p>
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
