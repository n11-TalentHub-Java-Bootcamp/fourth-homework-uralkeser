package com.uralkeser.service;

import com.uralkeser.dao.DebtDao;
import com.uralkeser.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class DebtService {
    @Autowired
    DebtDao debtDao;

    @Transactional
    public Debt saveDebt(Debt debt){ return debtDao.save(debt); }

    @Transactional
    public List<Debt> getAllDebtsByClient(Long clientId){ return debtDao.findAllByClientId(clientId); }

    @Transactional
    public List<Debt> getAllDebtsByTimePeriod(Date startDate, Date endDate){ return debtDao.findAllDebtsByTimePeriod(startDate,endDate); }

    @Transactional
    public List<Debt> getOverdueDebtsOfAClient(Long clientId, Date today){ return debtDao.findOverdueDebtsOfAClient(clientId, today);}

    @Transactional
    public List<Debt> getAllById(Long id){return debtDao.findAllById(id);}

    public List<Debt> getPaidLateDebtByClient(Long clientId){return debtDao.findAllByClientIdAndType(clientId,"GECIKME_ZAMMI");}

}
