package com.lx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.domain.entity.Rp;
import com.lx.mapper.RpMapper;
import com.lx.service.RpService;
import org.springframework.stereotype.Service;

/**
 * (Rp)表服务实现类
 *
 * @author makejava
 * @since 2022-11-28 20:13:27
 */
@Service("rpService")
public class RpServiceImpl extends ServiceImpl<RpMapper, Rp> implements RpService {

}
