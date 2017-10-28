package cn.sumeirui.service;

import cn.sumeirui.base.Impl.BaseServiceImpl;
import cn.sumeirui.entity.Testt;
import org.springframework.stereotype.Service;

/**
 * Created by sumei on 17/10/22.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<Testt>{

    @Override
    public Class<Testt> getClazz() {
        return Testt.class;
    }

    @Override
    public Testt getT() {
        return null;
    }
}
