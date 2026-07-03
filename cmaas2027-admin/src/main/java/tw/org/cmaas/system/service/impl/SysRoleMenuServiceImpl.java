package tw.org.cmaas.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import tw.org.cmaas.system.mapper.SysRoleMenuMapper;
import tw.org.cmaas.system.pojo.entity.SysRoleMenu;
import tw.org.cmaas.system.service.SysRoleMenuService;

/**
 * <p>
 * 角色與菜單 - 多對多關聯表 服务实现类
 * </p>
 *
 * @author Joey
 * @since 2024-05-10
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}
