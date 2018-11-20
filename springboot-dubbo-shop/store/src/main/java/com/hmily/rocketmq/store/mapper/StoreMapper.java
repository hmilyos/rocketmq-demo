package com.hmily.rocketmq.store.mapper;

import com.hmily.rocketmq.store.domain.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(String storeId);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String storeId);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}