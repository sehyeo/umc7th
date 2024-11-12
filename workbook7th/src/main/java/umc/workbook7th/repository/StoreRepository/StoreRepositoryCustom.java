package umc.workbook7th.repository.StoreRepository;


import umc.workbook7th.domain.Store;

import java.util.List;


public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}