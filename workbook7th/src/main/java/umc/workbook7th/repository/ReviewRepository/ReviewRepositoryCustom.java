package umc.workbook7th.repository.ReviewRepository;


import umc.workbook7th.domain.Review;

import java.util.List;


public interface ReviewRepositoryCustom {
    List<Review> dynamicQueryWithBooleanBuilder(String name, Float score);
}