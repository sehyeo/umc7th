package umc.workbook7th.repository.FoodCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.workbook7th.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
