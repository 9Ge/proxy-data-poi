ExcelTableEnum 枚举类用于反射。 导出的相关配置都需要在此类中配置
1：因为本项目基于EasyPoi做的导入导出，所以需要导入导出的属性，需要通过**@Excel**标识
2：实体类中需要转换的属性，通过**@ConversionPropertiesValue** 标识，相关逻辑在 **BusinessDataService** 中处理
    列入数据库存1002(code值) 需要导出转换后的属性。
3：项目中的数据从数据库中读取，因此使用到Mybatis+MybatisTk ,统一使用通用Mapper进行新增和查询
4：需要转换的属性，都需要用String接收，int类型的也需要设置成String
技术点：
    Springboot、Mybatis、MybatisTk、EasyPoi、Log输出

