package cn.sumeirui.base;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sumei
 * @date 17/10/22
 */
@Service
public interface IBaseService<T> {

    /**
     * 添加
     * @param t
     * @return
     */
    int insert(T t) throws IllegalAccessException;


    T queryById(Class<T> clazz, int id);

    void del(int id);

    List<T> queryByPage(int start,int rowSize);

    int update(T t);
}
