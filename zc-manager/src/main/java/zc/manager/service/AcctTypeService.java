package zc.manager.service;

import zc.commons.pojo.TAccountTypeCert;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;

import java.util.List;

public interface AcctTypeService {
    List<TAcctType> getAllAcctTypes();

    List<TCert> getAcctCertsByName(String name);

    void upadteAcctCert(TAccountTypeCert tact);

    void deleteAcctCertById(int accttypeid);
}
