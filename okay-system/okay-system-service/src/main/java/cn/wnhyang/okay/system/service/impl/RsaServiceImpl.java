package cn.wnhyang.okay.system.service.impl;

import cn.hutool.crypto.asymmetric.RSA;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.RsaConvert;
import cn.wnhyang.okay.system.entity.RsaPO;
import cn.wnhyang.okay.system.mapper.RsaMapper;
import cn.wnhyang.okay.system.service.RsaService;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPageVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPairVO;
import cn.wnhyang.okay.system.vo.rsa.RsaUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 密钥表 服务实现类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@Service
@RequiredArgsConstructor
public class RsaServiceImpl implements RsaService {

    private final RsaMapper rsaMapper;

    @Override
    public RsaPairVO generateKeyPair() {
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        RsaPairVO respVO = new RsaPairVO();
        respVO.setPublicKey(publicKey).setPrivateKey(privateKey);
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSecretKey(RsaCreateVO reqVO) {
        RsaPO rsaPO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.insert(rsaPO);
        return rsaPO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRsa(RsaUpdateVO reqVO) {
        RsaPO rsaPO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.updateById(rsaPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRsa(Long id) {
        rsaMapper.deleteById(id);
    }

    @Override
    public PageResult<RsaPO> getRsaPage(RsaPageVO reqVO) {
        return rsaMapper.selectPage(reqVO, null);
    }

    @Override
    public RsaPO getRsa(Long id) {
        return rsaMapper.selectById(id);
    }
}
