package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.RsaDO;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPageReqVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPairRespVO;
import cn.wnhyang.okay.system.vo.rsa.RsaUpdateReqVO;

/**
 * 密钥表 服务类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
public interface RsaService {

    /**
     * 生成密钥对
     * 默认生成不能设置补位方法和长度
     *
     * @return 密钥对
     */
    RsaPairRespVO generateKeyPair();

    /**
     * 新建密钥
     *
     * @param reqVO 密钥
     * @return id
     */
    Long createSecretKey(RsaCreateReqVO reqVO);

    /**
     * 更新密钥
     *
     * @param reqVO 更新密钥
     */
    void updateRsa(RsaUpdateReqVO reqVO);

    /**
     * 删除密钥
     *
     * @param id 密钥id
     */
    void deleteRsa(Long id);

    /**
     * 分页密钥
     *
     * @param reqVO 分页
     * @return 密钥集合
     */
    PageResult<RsaDO> getRsaPage(RsaPageReqVO reqVO);

    /**
     * 详细密钥
     *
     * @param id 密钥id
     * @return 密钥
     */
    RsaDO getRsa(Long id);
}
