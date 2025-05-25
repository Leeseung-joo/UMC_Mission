package umc.study.repository.missionHistoryRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMission;
import umc.study.domain.QRestaurant;
import umc.study.mapping.QMissionHistory;
import umc.study.web.response.MemberMissionResponse;

@Repository
@RequiredArgsConstructor
public class MissionHistoryRepositoryImpl implements MissionHistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    QMissionHistory mh = QMissionHistory.missionHistory;
    QMission m = QMission.mission;
    QRestaurant r = QRestaurant.restaurant;

    @Override
    public List<MemberMissionResponse> findMissionsByMemberId(Long memberId) {
        return queryFactory
                .select(Projections.constructor(
                        MemberMissionResponse.class,
                        m.id,
                        m.title,
                        r.name,
                        mh.status,
                        mh.price,
                        mh.deadLine,
                        mh.point
                ))
                .from(mh)
                .join(mh.mission, m)
                .join(m.restaurant, r)
                .where(mh.member.id.eq(memberId))
                .fetch();
    }
}


