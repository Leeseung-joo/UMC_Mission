package umc.study.repository.restaurantRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import umc.study.domain.QRestaurant;
import umc.study.domain.Restaurant;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom{
    private final JPAQueryFactory jpaqueryFactory;
    private final QRestaurant restaurant = QRestaurant.restaurant;

    @Override
    public List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Double starPoints) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(restaurant.name.eq(name));
        }
        if (starPoints != null) {
            predicate.and(restaurant.starPoints.goe(starPoints));
        }
        return jpaqueryFactory
                .selectFrom(restaurant)
                .where(predicate)
                .fetch();

    }
}
