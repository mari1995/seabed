package cn.sumeirui.base;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by sumei on 17/10/22.
 */
public interface IBaseMapper {
    /**
     * 添加
      */
    int save(Map<String,Object> param);

    Map<String , Object> queryById(Map<String,Object> param);

    int del(Map<String,Object> param);

    int update(Map<String,Object> param);

    List<Map<String,Object>> queryByPage(Map<String,Object> map);

    List<Map<String,Object>> queryByCondition(Map<String,Object> map);

}
