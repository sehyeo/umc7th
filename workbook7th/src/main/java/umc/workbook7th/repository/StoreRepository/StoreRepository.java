package umc.workbook7th.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.workbook7th.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}