package cn.sumeirui.base.util;

import cn.sumeirui.base.annotation.Column;
import cn.sumeirui.base.annotation.Id;
import cn.sumeirui.base.annotation.Table;
import cn.sumeirui.entity.SQLColumn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理实体类和数据库之间的对象关系
 *
 * @author sumei
 * @date 17/10/24
 */
public class BeanUtil {

    /**
     * 获取实体类中对应的表
     * @param clazz
     * @return
     */
    public static String getTableName(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (null != table) {
            return table.value();
        }
        return null;
    }

    /**
     * 获取实体类中字段对应和字段的值
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<SQLColumn> getSQLColumns(T t) {
        // 开始获得属性

        List<SQLColumn> SQLColumns = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                System.err.println(fields[i].getName() + " --- " + fields[i].get(t));
                Column annotation = fields[i].getAnnotation(Column.class);
                if (annotation != null) {
                    SQLColumn column = null;

                    column = new SQLColumn(annotation.value(), fields[i].get(t));

                    System.out.println(column);
                    SQLColumns.add(column);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return SQLColumns;
    }

    /**
     * 获取数据库中的id
     * @param t
     * @param <T>
     * @return
     */
    public static <T> SQLColumn getId(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        SQLColumn column = null;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                Id annotation = fields[i].getAnnotation(Id.class);
                if (annotation != null) {
                    column = new SQLColumn(annotation.value(), fields[i].get(t));
                    System.out.println(column);
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return column;
    }

    /**
     * 将数据库中的数据获取当个对象转化成单个对象
     * @param t
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T getBean(T t , Map<String,Object> map){
        System.err.println(map.toString());
        for ( Map.Entry entry: map.entrySet()) {
            Field[] fields = t.getClass().getDeclaredFields();
            for ( Field f : fields){
                f.setAccessible(true);
                if ( entry.getKey().equals(f.getName()) ){
                    System.err.println(f.getName() + "--------key : " + entry.getKey() + " : " + entry.getValue());
                    try {
                        f.set(t,entry.getValue());
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return t;
    }
}
