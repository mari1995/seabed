package cn.sumeirui.base.Impl;

import cn.sumeirui.base.IBaseMapper;
import cn.sumeirui.base.IBaseService;
import cn.sumeirui.base.util.BeanUtil;
import cn.sumeirui.entity.SQLColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.lang.reflect.Field;
import java.util.*;

/**
 * @author sumei
 * @date 17/10/22
 */
@Repository
public abstract class BaseServiceImpl<T> implements IBaseService<T> {


    @Autowired(required = false)
    private IBaseMapper mapper;

    public abstract Class<T> getClazz();

    public abstract T getT();

    @Override
    public int insert(T t) throws IllegalAccessException {
        Map<String, Object> param = new HashMap<>(16);

        String tableName = BeanUtil.getTableName(t.getClass());

        // 开始获得属性
        List<SQLColumn> SQLColumns = BeanUtil.getSQLColumns(t);

        param.put("tableName", tableName);
        param.put("SQLColumns", SQLColumns);

        return mapper.save(param);
    }

    @Override
    public T queryById(Class<T> clazz, int id) {
        Map<String, Object> param = new HashMap<>(16);
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        String tableName = BeanUtil.getTableName(clazz);
        SQLColumn idColumn = BeanUtil.getId(t);
        idColumn.setColumnValue(id);
        System.err.println(idColumn);

        param.put("tableName", tableName);
        param.put("id", idColumn);
        Map<String, Object> map = mapper.queryById(param);

        return BeanUtil.getBean(t, map);
    }

    @Override
    public void del(int id) {
        Map<String, Object> param = new HashMap<>(16);


        String tableName = BeanUtil.getTableName(getClazz());
        param.put("tableName", tableName);
        param.put("id", 1);
        mapper.del(param);
    }

    @Override
    public List<T> queryByPage(int start, int rowSize) {
        Map<String, Object> param = new HashMap<>(16);

        List<T> list = new ArrayList<>();

        String tableName = "test";
        param.put("tableName", tableName);
        param.put("start", 0);
        param.put("rowsize", 2);
        List<Map<String, Object>> maps = mapper.queryByPage(param);
        for (Map<String, Object> map : maps) {
            T t = null;
            try {
                t = getClazz().newInstance();
                for (Map.Entry entry : map.entrySet()) {
                    Field[] fields = getClazz().getDeclaredFields();
                    for (Field f : fields) {
                        f.setAccessible(true);
                        if (entry.getKey().equals(f.getName())) {
                            f.set(t, entry.getValue());
                            break;
                        }
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            list.add(t);
        }
        return list;
    }

    @Override
    public int update(T t) {
        Map<String, Object> param = new HashMap<>(16);

        List<SQLColumn> list = BeanUtil.getSQLColumns(t);
        String tableName = BeanUtil.getTableName(t.getClass());

        param.put("tableName", tableName);
        param.put("SQLColumns", list);
        param.put("id", BeanUtil.getId(t));
        return mapper.update(param);
    }
}