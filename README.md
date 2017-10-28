# seabed
## 1）实现
基于 mybatis 封装的dao层和service层   
实现对单表的查询。  
并配合注解对实体类中的对象，实现字段和实体类属性的映射。

## 2）使用   
需要在 service 层中，实现BaseServiceImpl的getClazz(),并返回实体类
```
public class UserServiceImpl extends BaseServiceImpl<Testt>{

    @Override
    public Class<Testt> getClazz() {
        return Testt.class;
    }

}

```
