package umc.study.repository.restaurantRepository;

import java.util.List;
import umc.study.domain.Restaurant;

public interface RestaurantRepositoryCustom {
    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Double starPoints);
}
