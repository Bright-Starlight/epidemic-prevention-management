package com.parachute.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.constant.RoleConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.dao.CarrierDao;
import com.parachute.main.entity.Carrier;

import com.parachute.main.entity.Hospital;
import com.parachute.main.service.CarrierService;

import com.parachute.main.utils.DateUtils;
import com.parachute.main.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * (Carrier)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@Service("carrierService")
public class CarrierServiceImpl extends ServiceImpl<CarrierDao, Carrier> implements CarrierService {

    @Autowired
    private CarrierDao carrierDao;

    @Override
    public void insert(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        this.save(carrier);
    }

    @Override
    public List<Carrier> selectNewIntimate() {

        String s = DateUtils.date2String(new Date());
        String[] date = s.split(" ");
        List<Carrier> list = carrierDao.selectNewIntimate(date[0] + '%');
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
}

    @Override
    public ValidateConstants validate(Carrier carrier) {
        String identityCard = carrier.getIdentityCard();
        Integer age = carrier.getAge();
        LocalDateTime isolationTime = carrier.getIsolationTime();
        String gender = carrier.getGender();
        String fromHospital = carrier.getFromHospital();

        Integer hospital = this.getHospital(fromHospital);
        if (hospital != null){
            carrier.setFromHospital(hospital + "");
        }
        //电话号码校验
        String regex = "^\\d+$";
        String substring = identityCard.substring(0, 17);
        //身份证校验
        if (!substring.matches(regex)) {
            return ValidateConstants.of(ValidateConstants.IDENTITY_CARD_ERROR,false);
        }
        if (!"X".equals(identityCard.substring(17)) && !identityCard.substring(17).matches(regex) ){
            return ValidateConstants.of(ValidateConstants.IDENTITY_CARD_ERROR,false);
        }
        if (Boolean.TRUE.equals(selectIdentityCard(carrier))){
            return ValidateConstants.of(ValidateConstants.ALREADY_EXISTED,false);
        }
        //年龄校验
        if (age > ValidateConstants.AGE_MAX || age < ValidateConstants.AGE_MIN){
            return ValidateConstants.of(ValidateConstants.AGE_ERROR,false);
        }
        if (isolationTime.isAfter(LocalDateTime.now())){
            return ValidateConstants.of(ValidateConstants.TIME_ERROR,false);
        }
        if (!(ValidateConstants.MALE.equals(gender) || ValidateConstants.FEMALE.equals(gender))){
            return ValidateConstants.of(ValidateConstants.GENDER_ERROR,false);
        }
        return ValidateConstants.of("",true);
    }

    @Override
    public Integer getHospital(String fromHospital) {
         return carrierDao.getHospital(fromHospital);
    }

    @Override
    public List<Carrier> getInIsolation() {
        LambdaQueryWrapper<Carrier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Carrier::getIsIsolation,1)
                .eq(Carrier::getIsCure,0)
                .eq(Carrier::getIsConfirm,0)
                .eq(Carrier::getIsDie,0);
        List<Carrier> list = this.list(queryWrapper);
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
    }

    @Override
    public void insertIsolation(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        carrier.setIsIsolation("1");
        this.save(carrier);
    }

    @Override
    public List<Carrier> getInIsolationComplete() {
        LambdaQueryWrapper<Carrier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Carrier::getIsIsolation,2)
                .eq(Carrier::getIsCure,0)
                .eq(Carrier::getIsConfirm,0)
                .eq(Carrier::getIsDie,0);
        List<Carrier> list = this.list(queryWrapper);
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
    }

    @Override
    public void insertIsolationComplete(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        carrier.setIsIsolation("2");
        this.save(carrier);
    }

    @Override
    public List<Carrier> getConfirm() {
        LambdaQueryWrapper<Carrier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Carrier::getIsCure,0)
                .eq(Carrier::getIsConfirm,1)
                .eq(Carrier::getIsDie,0);
        List<Carrier> list = this.list(queryWrapper);
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
    }

    @Override
    public void insertConfirm(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        carrier.setIsConfirm("1");
        this.save(carrier);
    }
    @Override
    public void insertCure(Carrier carrier) {
        carrier.setCreateTime(LocalDateTime.now());
        carrier.setUpdateTime(LocalDateTime.now());
        carrier.setUpdateName(RoleConstants.ADMIN);
        carrier.setIsCure("1");
        this.save(carrier);
    }

    @Override
    public List<Carrier> getNewConfirm() {
        String s = DateUtils.date2String(new Date());
        String[] date = s.split(" ");
        List<Carrier> list = carrierDao.getNewConfirm(date[0] + '%');
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
    }

    @Override
    public List<Carrier> getCure() {
        LambdaQueryWrapper<Carrier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Carrier::getIsCure,"1")
                .eq(Carrier::getIsConfirm,0)
                .eq(Carrier::getIsDie,0);
        List<Carrier> list = this.list(queryWrapper);
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;
    }

    @Override
    public List<Carrier> getNewCure() {
        String s = DateUtils.date2String(new Date());
        String[] date = s.split(" ");
        List<Carrier> list = carrierDao.getNewCure(date[0] + '%');
        list.forEach(obj->{
            String hospitalName = carrierDao.getHospitalName(obj.getFromHospital());
            obj.setFromHospital(hospitalName);
        });
        return list;

    }

    public Boolean selectIdentityCard(Carrier carrier){
        LambdaUpdateWrapper<Carrier> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Carrier::getIdentityCard,carrier.getIdentityCard());
        List<Carrier> list = this.list(wrapper);
        if (list.isEmpty()){
            return false;
        }
        return list.size() >= 2 || !carrier.getId().equals(list.get(0).getId());
    }




}

