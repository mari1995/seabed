package cn.sumeirui.entity;

import lombok.*;

/**
 * sql 语句中的 数据库列 和 对象中的属性 对应
 * @author sumei
 * @date 17/10/22
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SQLColumn {

    private String columnKey;

    private Object  columnValue;
}
