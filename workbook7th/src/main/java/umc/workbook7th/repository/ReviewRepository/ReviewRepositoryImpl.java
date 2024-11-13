/* package umc.workbook7th.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.workbook7th.domain.QReview;
import umc.workbook7th.domain.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    @Override
    public List<Review> dynamicQueryWithBooleanBuilder(String id, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (id != null) {
            predicate.and(review.id.eq(id));
        }

        if (score != null) {
            predicate.and(review.score.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
} */