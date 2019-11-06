package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TAccountTypeCert;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;
import zc.manager.dao.TAccountTypeCertMapper;
import zc.manager.service.AcctTypeService;

import java.util.List;

@Service
public class AcctTypeServiceImpl implements AcctTypeService {
    @Autowired
    private TAccountTypeCertMapper accountTypeCertMapper;
    @Override
    public List<TAcctType> getAllAcctTypes() {
        return accountTypeCertMapper.selectAcctType();
    }
    @Override
    public List<TCert> getAcctCertsByName(String name) {

        return accountTypeCertMapper.selectAcctCerts(name);
    }
    @Override
    public void deleteAcctCertById(int accttypeid) {
        accountTypeCertMapper.deleteByAcctTypeId(accttypeid);

    }
    @Override
    public void upadteAcctCert(TAccountTypeCert tact) {

        accountTypeCertMapper.insertAcct(tact);
    }
}
